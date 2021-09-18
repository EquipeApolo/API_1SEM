<h1>Sprint 5</h1>
<h2>Tarefas Desenvolvidas:</h2>

<li>Indicador de pontualidade no pagamento por cartão de crédito</li>
<li>Desenvolvimento da Interface Gráfica</li>
<li>Programação das funcionalidades do software</li>

<h3>Indicador de Pontualidade</h3>
Pontualidade de pagamento por pessoa: indica se a pessoa física está em dia ou não com o vencimento da fatura. 

Dados utilizados: na tabela de pagamento, contém dados de crédito, onde será verificado a data de vencimento da fatura com a data de pagamento do cliente. 

Cálculo: quanto maior o déficit de atraso menor a pontuação. 

<li>Antes da data de vencimento = nota 5</li>

<li>Pagamento na data de vencimento = nota 4 </li>

<li>Entre 1 a 7 dias de atraso = nota 3 </li>

<li>Entre 8 a 14 dias de atraso = nota 2 </li>

<li>Acima de 14 dias de atraso = nota 1</li> 

Por meio do indicador de pontualidade, classificamos os clientes conforme todos os pagamentos efetuados de forma individual. Assim, pontuando confome a data de pagamento, gerando uma média sobre o total de pagamentos realizados, para assim qualificar os clientes e gerar uma pontuação geral para os grupos de regiões do Brasil, com o intuito de indicar quais as regiões com melhor índice de pontualidade no pagamento por cartão de crédito e qual o perfil deste consumidor.

![IndicadorPontualidade](https://github.com/HenriqueNawa/Projeto-SPC-Brasil-Fatec-2020/blob/master/Gif/Indicador_pontualidade.png)

<h3>Gráfico de Distribuição de Clientes por Estados</h3>

Por meio do gráfico, mostrar os estados com mais clientes que utilizam o cartão de crédito e assim determinar qual região seria interessante investir.

![Distribuição_de_clientes](https://github.com/HenriqueNawa/Projeto-SPC-Brasil-Fatec-2020/blob/master/Gif/distribuicao_clientes_reg_sudeste.png)

<h3>Gráfico com perfil do cliente com melhor indicador de pontualidade no pagamento</h3>

Mostra o perfil do cliente que tem o pagamento das faturas do cartão em dia, mostrando sua faixa etária e sexo do cliente. Com o objetivo de aperfeiçoar as campanhas de marketing para determinado consumidor.

![PerfilConsumidor](https://github.com/HenriqueNawa/Projeto-SPC-Brasil-Fatec-2020/blob/master/Gif/perfil_bons_pagadores_reg_sudeste.png)
