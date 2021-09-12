import pandas as pd
from datetime import datetime
import plotly.graph_objects as go
import plotly.express as px


def limpa_coluna(df):
    df.columns = [x.strip() for x in df.columns]
    return df.columns


def retira_espacos(df):
    for column in df.columns:
        df[column] = df[column].str.strip()
    return df


def indicador_pontualidade(media):
    fig = go.Figure(go.Indicator(
        mode="gauge+number",
        value=media,
        title={'text': "Pontualidade no Pagamento"},
        domain={'x': [0, 1], 'y': [0, 1]}
    ))

    fig.show()


def agrupa_idade(df, index):
    cont1, cont2, cont3, cont4, cont5 = 0, 0, 0, 0, 0
    for rows in df.itertuples():
        if rows[index] == "NULL":
            continue
        elif int(rows[index]) <= 25:
            cont1 += 1
        elif int(rows[index]) <= 35:
            cont2 += 1
        elif int(rows[index]) <= 45:
            cont3 += 1
        elif int(rows[index]) <= 59:
            cont4 += 1
        else:
            cont5 += 1
    faixa_idade = {'18-25': cont1, '26-35': cont2, '36-45': cont3, '46-59': cont4, '60+': cont5}

    return faixa_idade


reg_centro_oeste = ['GOIAS', 'MATO GROSSO', 'MATO GROSSO DO SUL', 'DISTRITO FEDERAL']
reg_nordeste = ['ALAGOAS', 'BAHIA', 'CEARA', 'MARANHAO', 'PARAIBA', 'PERNAMBUCO', 'PIAUI', 'RIO GRANDE DO NORTE',
                'SERGIPE']
reg_sul = ['PARANA', 'SANTA CATARINA', 'RIO GRANDE DO SUL']
reg_sudeste = ['ESPIRITO SANTO', 'MINAS GERAIS', 'RIO DE JANEIRO', 'SAO PAULO']
reg_norte = ['ACRE', 'AMAPA', 'AMAZONAS', 'PARA', 'RONDONIA', 'RORAIMA', 'TOCANTINS']

endereco = pd.read_csv('fatec_endereco_pessoa_fisica.csv', sep='|', skiprows=1)
pessoa = pd.read_csv('fatec_pessoa_fisica.csv', sep='|', skiprows=1)
opr = pd.read_csv('fatec_opr.csv', sep='|', skiprows=1)
pgt = pd.read_csv('fatec_pgt.csv', sep='|', skiprows=1)
mvt = pd.read_csv('fatec_mvt.csv', sep='|', skiprows=1)

limpa_coluna(endereco)
limpa_coluna(pessoa)
limpa_coluna(opr)
limpa_coluna(pgt)
limpa_coluna(mvt)
endereco = endereco.rename(columns={"id_pessoa_fisica": "id"})
endereco = endereco.iloc[1:, 1:-1].dropna(how='all')
opr = opr.iloc[1:, 1:-1].dropna(how='all')
pgt = pgt.iloc[1:, 1:-1].dropna(how='all')
mvt = mvt.iloc[1:, 1:-1].dropna(how='all')
pessoa = pessoa.iloc[1:, 1:-1].dropna(how='all')
retira_espacos(endereco)
retira_espacos(pessoa)
retira_espacos(pgt)
retira_espacos(opr)
retira_espacos(mvt)

resultado = pd.merge(pessoa, endereco, on='id')
opr = opr.loc[opr['cod_mdl'] == 'D01']
resultado = resultado.rename(columns={"cpf": "doc_cli"})
resultado2 = pd.merge(resultado, opr, on='doc_cli')

creditos = resultado2[
    ['id', 'doc_cli', 'idc_sexo', 'ano_dat_nascimento', 'des_estado', 'id_opr_cad_pos', 'sdo_ddr_tfm', 'cod_mdl']]

for index, rows in creditos.iterrows():
    ano = rows['ano_dat_nascimento']
    if ano == 'NULL':
        creditos.loc[index, 'Idade'] = 'NULL'
    else:
        creditos.loc[index, 'Idade'] = 2020 - int(ano)

pgt['dat_pgt'] = pgt['dat_pgt'].astype(str)
width = 8
pgt["dat_pgt"] = pgt["dat_pgt"].str.zfill(width)

cartao_credito = pgt.loc[pgt['cod_mdl'] == 'D01']

for i in cartao_credito.index:
    teste = cartao_credito.at[i, 'dat_pgt']
    teste2 = list(teste)
    ano = ''.join(teste2[4:])
    ano = int(ano)
    mes = ''.join(teste2[2:4])
    mes = int(mes)
    dia = ''.join(teste2[:2])
    dia = int(dia)
    teste2 = str(ano) + '/' + str(mes) + '/' + str(dia)
    cartao_credito.at[i, 'dat_pgt'] = teste2

for index, row in cartao_credito.iterrows():
    row['dat_pgt'] = datetime.strptime(row['dat_pgt'], "%Y/%m/%d").date()
    row['dat_vct_tfm'] = row['dat_vct_tfm'].strip()
    row['dat_vct_tfm'] = datetime.strptime(row['dat_vct_tfm'], "%Y-%m-%d %H:%M:%S").date()
    cartao_credito.loc[index, 'nova_coluna'] = (row['dat_vct_tfm'] - row['dat_pgt']).days

resultado_final = pd.merge(cartao_credito, creditos, on='id_opr_cad_pos')

tudo = pd.merge(resultado_final, mvt, on=['id_mvt_cad_pos', 'id_opr_cad_pos'])

tudo2 = tudo[
    ['doc_cli', 'idc_sexo', 'nova_coluna', 'Idade', 'des_estado', 'id_opr_cad_pos', 'sdo_ddr_tfm', 'vlr_pgt_tfm',
     'vlr_tot_fat_tfm', 'vlr_min_fat_tfm']]

for index, rows in tudo2.iterrows():
    estado = rows['des_estado']
    if estado in reg_centro_oeste:
        tudo2.loc[index, 'Região'] = 'CENTRO_OESTE'
    elif estado in reg_nordeste:
        tudo2.loc[index, 'Região'] = 'NORDESTE'
    elif estado in reg_sul:
        tudo2.loc[index, 'Região'] = 'SUL'
    elif estado in reg_sudeste:
        tudo2.loc[index, 'Região'] = 'SUDESTE'
    elif estado in reg_norte:
        tudo2.loc[index, 'Região'] = 'NORTE'
    else:
        tudo2.loc[index, 'Região'] = 'NULL'

for index, rows in tudo2.iterrows():
    verifica_pontualidade = int(rows['nova_coluna'])
    if verifica_pontualidade > 0:
        tudo2.loc[index, 'Indicador de Pontualidade'] = 5
    elif verifica_pontualidade == 0:
        tudo2.loc[index, 'Indicador de Pontualidade'] = 4
    elif verifica_pontualidade >= -7:
        tudo2.loc[index, 'Indicador de Pontualidade'] = 3
    elif verifica_pontualidade >= -15:
        tudo2.loc[index, 'Indicador de Pontualidade'] = 2
    else:
        tudo2.loc[index, 'Indicador de Pontualidade'] = 1

centro_oeste = tudo2[tudo2['Região'] == 'CENTRO_OESTE']

agrupa_id_unico = centro_oeste.groupby(by='id_opr_cad_pos').size()
soma_centro_oeste = centro_oeste.groupby(['id_opr_cad_pos']).sum()
soma_centro_oeste['Média'] = soma_centro_oeste['Indicador de Pontualidade'] / agrupa_id_unico
media_centro_oeste = soma_centro_oeste['Média'].sum() / len(soma_centro_oeste)
dropar = centro_oeste.drop_duplicates('doc_cli')

indicador_pontualidade(media_centro_oeste)

agrupa_id = centro_oeste.groupby(by='id_opr_cad_pos').mean()
concatena = pd.merge(dropar, agrupa_id, on=['id_opr_cad_pos'])
concatena.drop(['nova_coluna_y', 'nova_coluna_x', 'doc_cli', 'Indicador de Pontualidade_x'], axis=1, inplace=True)
limpa_coluna(concatena)
concatena = concatena.rename(columns={"Indicador de Pontualidade_y": "Media"})
a = concatena['des_estado'].value_counts()

trace = go.Pie(labels=a.index, values=a, title_text='Distribuição de Clientes por Estados')
data = [trace]
fig_centro_oeste_cli = go.Figure(data=data)
fig_centro_oeste_cli.show()

# Grupo de bons pagadores
media_4 = concatena.query('Media >=4')
sexo = list(media_4['idc_sexo'].value_counts())
contador = 0
contador_idade = 0
for index, rows in media_4.iterrows():
    ano = rows['Idade']
    if ano == 'NULL':
        continue
    else:
        contador += int(ano)
        contador_idade += 1
idade_media = contador / contador_idade

# agrupa = agrupa_idade(media_4,2)
val_total_vlr_fatura = media_4['vlr_tot_fat_tfm'].astype(float).sum()
val_total_vlr_fatura_media = media_4['vlr_tot_fat_tfm'].astype(float).mean()

fem = concatena.query('idc_sexo=="F" & Media>=4')
mas = concatena.query('idc_sexo=="M" & Media>=4')
agrupa_por_idade_mas = agrupa_idade(mas, 2)
agrupa_por_idade_fem = agrupa_idade(fem, 2)

fig_tipo = go.Figure(data=[
    go.Bar(name='Masculino', x=list(agrupa_por_idade_mas.keys()), y=list(agrupa_por_idade_mas.values())),
    go.Bar(name='Feminino', x=list(agrupa_por_idade_fem.keys()), y=list(agrupa_por_idade_fem.values()))
])
# Change the bar mode
fig_tipo.update_layout(barmode='group', title='Quantidade de bons pagadores por faixa de idade')
fig_tipo.show()

Bons_pagadores = pd.DataFrame(
    [val_total_vlr_fatura, val_total_vlr_fatura_media, int(sexo[0]), int(sexo[1]), idade_media],
    index=['Valor Total das Faturas dos Bons Pagadores',
           'Média do Valor Total das Faturas',
           'Clientes Homens',
           'Clientes Mulheres',
           'Média de Idade dos clientes'],
    columns=['Características dos bons pagadores'])
