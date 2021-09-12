import pandas as pd
from datetime import datetime
import plotly.graph_objects as go
import plotly.express as px
import time

endereco = pd.read_csv('fatec_endereco_pessoa_fisica.csv', sep = '|',skiprows = 1)
pessoa = pd.read_csv ('fatec_pessoa_fisica.csv', sep = '|', skiprows = 1)
opr = pd.read_csv ('fatec_opr.csv', sep = '|', skiprows =1)
pgt = pd.read_csv ('fatec_pgt.csv', sep  = '|', skiprows = 1)
mvt = pd.read_csv ('fatec_mvt.csv', sep ='|', skiprows = 1)


def limpa_coluna(df):
    df.columns = [x.strip() for x in df.columns]
    return df.columns


def retira_espacos(df):
    for column in df.columns:
        df[column] = df[column].str.strip()
    return df


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


def indicador_pontualidade(media):
    fig = go.Figure(go.Indicator(
        mode="gauge+number",
        value=media,
        title={'text': "Pontualidade no Pagamento"},
        domain={'x': [0, 1], 'y': [0, 1]}
    ))

    fig.show()


def media_pontualidade(df):
    agrupa_id = df.groupby(by='id_opr_cad_pos').size()
    soma_regiao = df.groupby(['id_opr_cad_pos']).sum()
    soma_regiao['Media'] = soma_regiao['Indicador de Pontualidade'] / agrupa_id
    return soma_regiao

limpa_coluna(endereco)
limpa_coluna(pessoa)
limpa_coluna (opr)
limpa_coluna(pgt)
limpa_coluna(mvt)
endereco = endereco.rename(columns={"id_pessoa_fisica": "id"})
pessoa = pessoa.rename(columns={"cpf": "doc_cli"})
endereco = endereco.iloc[1:,1:-1].dropna(how='all')
opr = opr.iloc[1:,1:-1].dropna(how='all')
pessoa = pessoa.iloc[1:,1:-1].dropna(how='all')
pgt = pgt.iloc[1:,1:-1].dropna(how='all')
mvt = mvt.iloc[1:,1:-1].dropna(how='all')
retira_espacos (endereco)
retira_espacos (pessoa)
retira_espacos (opr)
retira_espacos (pgt)
retira_espacos (mvt)
pgt['dat_pgt'] = pgt['dat_pgt'].astype(str)
width = 8
pgt["dat_pgt"]= pgt["dat_pgt"].str.zfill(width)
opr = opr.loc[opr['cod_mdl'] == 'D01']
pgt = pgt.loc[pgt['cod_mdl'] == 'D01']

resultado = pd.merge(pessoa,endereco, on = 'id' )
resultado2 = pd.merge(resultado,opr, on ='doc_cli')
resultado2 = resultado2.drop(['nom_cidade', 'qtd_pcl','dat_vct_ult_pcl','vlr_ctrd_fta_tfm','vlr_ctrd','id_ult_rss_opr','id_mdl','id_fnt'], axis =1)

for index, rows in resultado2.iterrows():
    ano = rows['ano_dat_nascimento']
    if ano == 'NULL':
        resultado2.loc[index,'Idade'] = 'NULL'
    else:
        resultado2.loc[index,'Idade'] = 2020 - int(ano)

for i in pgt.index:
    teste = pgt.at[i,'dat_pgt']
    teste2 = list(teste)
    ano = ''.join(teste2[4:])
    ano = int(ano)
    mes = ''.join(teste2[2:4])
    mes = int(mes)
    dia = ''.join(teste2[:2])
    dia = int(dia)
    teste2  = str(ano) + '/' + str(mes) + '/' + str(dia)
    pgt.at[i,'dat_pgt'] = teste2

for index, row in pgt.iterrows():
    row['dat_pgt'] = datetime.strptime(row['dat_pgt'], "%Y/%m/%d").date()
    row['dat_vct_tfm'] = row['dat_vct_tfm'].strip()
    row['dat_vct_tfm'] = datetime.strptime(row['dat_vct_tfm'], "%Y-%m-%d %H:%M:%S").date()
    diferenca = (row ['dat_vct_tfm']-row['dat_pgt']).days
    pgt.loc[index,'nova_coluna'] = diferenca

resultado_final = pd.merge(pgt,resultado2, on ='id_opr_cad_pos')
tudo = pd.merge(resultado_final,mvt, on=['id_mvt_cad_pos','id_opr_cad_pos'])
tudo = tudo[['doc_cli','idc_sexo','nova_coluna','Idade','des_estado','id_opr_cad_pos','sdo_ddr_tfm','vlr_pgt_tfm','vlr_tot_fat_tfm','vlr_min_fat_tfm']]

reg_centro_oeste = ['GOIAS','MATO GROSSO','MATO GROSSO DO SUL','DISTRITO FEDERAL']
reg_nordeste =['ALAGOAS', 'BAHIA', 'CEARA', 'MARANHAO', 'PARAIBA', 'PERNAMBUCO', 'PIAUI', 'RIO GRANDE DO NORTE', 'SERGIPE']
reg_sul= ['PARANA', 'SANTA CATARINA', 'RIO GRANDE DO SUL' ]
reg_sudeste =['ESPIRITO SANTO', 'MINAS GERAIS','RIO DE JANEIRO','SAO PAULO']
reg_norte = ['ACRE', 'AMAPA','AMAZONAS','PARA','RONDONIA','RORAIMA','TOCANTINS' ]

for index, rows in tudo.iterrows():
    estado = rows['des_estado']
    if estado in reg_centro_oeste:
        tudo.loc[index,'Região'] = 'CENTRO_OESTE'
    elif estado in reg_nordeste:
        tudo.loc[index,'Região'] = 'NORDESTE'
    elif estado in reg_sul:
        tudo.loc[index,'Região'] = 'SUL'
    elif estado in reg_sudeste:
        tudo.loc[index,'Região'] = 'SUDESTE'
    elif estado in reg_norte:
        tudo.loc[index,'Região'] = 'NORTE'
    else:
        tudo.loc[index,'Região'] = 'NULL'

for index, rows in tudo.iterrows():
    verifica_pontualidade = int(rows['nova_coluna'])
    if verifica_pontualidade > 0:
        tudo.loc[index,'Indicador de Pontualidade'] = 5
    elif verifica_pontualidade == 0:
        tudo.loc[index,'Indicador de Pontualidade'] = 4
    elif verifica_pontualidade >= -7:
        tudo.loc[index,'Indicador de Pontualidade'] = 3
    elif verifica_pontualidade >=-15:
        tudo.loc[index,'Indicador de Pontualidade'] = 2
    else:
        tudo.loc[index,'Indicador de Pontualidade'] = 1

categoria = tudo.groupby(by='id_opr_cad_pos').size()
soma = tudo.groupby(['id_opr_cad_pos']).sum()
soma['Média'] = soma['Indicador de Pontualidade'] / categoria
media = soma['Média'].sum()/len(soma)

regioes = tudo.groupby(by='Região').size()

del_duplicadas = tudo.drop_duplicates('doc_cli')

concatena = pd.merge(del_duplicadas,soma, on=['id_opr_cad_pos'])
limpa_coluna(concatena)

fem = concatena.query('idc_sexo=="F" & Média>=4')
mas = concatena.query('idc_sexo=="M" & Média>=4')
agrupa_por_idade_mas = agrupa_idade(mas,4)
agrupa_por_idade_fem = agrupa_idade(fem,4)

fig = go.Figure(data=[
    go.Bar(name='Masculino', x=list(agrupa_por_idade_mas.keys()), y=list(agrupa_por_idade_mas.values())),
    go.Bar(name='Feminino', x=list(agrupa_por_idade_fem.keys()), y=list(agrupa_por_idade_fem.values()))
])
# Change the bar mode
fig.update_layout(barmode='group', title = 'Quantidade de bons pagadores por faixa de idade')
#fig.show()

media_4 = concatena.query('Média >=4')
sexo =list(media_4['idc_sexo'].value_counts())
contador = 0
contador_idade =0
for index, rows in media_4.iterrows():
    ano = rows['Idade']
    if ano == 'NULL':
        continue
    else:
        contador +=int(ano)
        contador_idade +=1
media_idade = contador/contador_idade

tipo = media_4.groupby('idc_sexo').size()

#indicador_pontualidade(media)

regiao = concatena['Região'].value_counts()
trace = go.Pie(labels = regiao.index, values = regiao, title_text= 'Distribuição de Clientes por Regiões')
data = [trace]
fig_clientes = go.Figure(data = data)
#fig_clientes.show()

#Agrupa as regiões
sul =tudo[tudo['Região']  == 'SUL' ]
sudeste = tudo[tudo['Região']  == 'SUDESTE' ]
norte = tudo[tudo['Região']  == 'NORTE']
nordeste = tudo[tudo['Região']  == 'NORDESTE']
centro_oeste = tudo[tudo['Região']  == 'CENTRO_OESTE' ]

#Média da pontuacao do centro oeste
media_pontualidade(centro_oeste)
media_centro_oeste = media_pontualidade(centro_oeste).loc[:,"Media"].sum()/len(media_pontualidade(centro_oeste))

#Média da pontuação do sudeste
media_pontualidade(sudeste)
media_sudeste = media_pontualidade(sudeste).loc[:,"Media"].sum()/len(media_pontualidade(sudeste))

#Média da pontuação do norte
media_pontualidade(norte)
media_norte = media_pontualidade(norte).loc[:,"Media"].sum()/len(media_pontualidade(norte))

#Média da pontuacao do sul
media_pontualidade(sul)
media_sul = media_pontualidade(sul).loc[:,"Media"].sum()/len(media_pontualidade(sul))

#Média da pontuacao do nordeste
media_pontualidade(nordeste)
media_nordeste = media_pontualidade(nordeste).loc[:,"Media"].sum()/len(media_pontualidade(nordeste))

#Quantidade Total de Pagamentos com Cartão de Crédito Realizados dividido por Região
qnt_pgt_regiao = tudo.groupby('Região').size()
tabela_qnt_pgt = qnt_pgt_regiao.to_frame(name = 'Quantidade de Pagamentos Realizados')

#Ranking das regioes de acordo com indicador de pontualidade
media_regiao = [['Norte', media_norte], ['Nordeste', media_nordeste],['Sul', media_sul], ['Sudeste', media_sudeste], ['Centro-Oeste', media_centro_oeste]]
l_sorted = sorted(media_regiao, key=lambda x: x[1], reverse=True)
Ranking = pd.DataFrame([l_sorted[0][1],l_sorted[1][1],l_sorted[2][1],l_sorted[3][1],l_sorted[4][1]],
                          index=[l_sorted[0][0],
                                l_sorted[1][0],
                                l_sorted[2][0],
                                l_sorted[3][0],
                                l_sorted[4][0]],
                          columns=['Ranking das Regiões'])



def rodage(run):
    if run =="1":
        indicador_pontualidade(media)
        time.sleep(10)
        fig_clientes.show()
        time.sleep(10)
        fig.show()