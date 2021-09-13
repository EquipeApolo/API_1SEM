# Projeto-SPC-Brasil-Fatec-2020

<p align="center">
  <img src="https://github.com/HenriqueNawa/Projeto-SPC-Brasil-Fatec-2020/blob/master/Gif/fatec.jpg" />
</p>

**<H3>Disciplinas integradas: </H3>** 
<ul>
  <li>Engenharia de Software - Profº Giuliano Araujo Bertoti</li>
  <li>Linguagem de Programação - Profª Juliana Forin Pasquini Martinez</li>
  <li>Sistemas de Informação - Profº José Walmir Gonçalves Duque</li>
</ul>

**<H2>Descrição</H2>**

Com a implementação do Cadastro Positivo, surgiu-se a necessidade de analisar os dados recebidos para realizar uma avaliação mais confiável dos consumidores. 

Tendo como norte a utilização e potencial das informações fornecidas, temos como premissa oferecer às áreas de negócios indicadores que possam fortalecer a tomada de decisão dos gestores, clientes e empresas que irão se utilizar dessas ferramentas aqui produzidas. Portanto, o foco será na utilização pela ponta comercial que precisa de meios mais diretos de análise dos clientes em seus mais diversos segmentos. 

**<H2>Software</H2>**


<ul>
 <li>Back-end</li>

Desenvolvido em Python, versão 3.7.4.


Bibliotecas utilizadas:

**Pandas:** Utilizada para a análise e estrutura de dados.

**Plotly:** Utilizada para a criação de gráficos interativos

<li>Front-end</li>
Tkinter para o desenvolvimento da interface gráfica do sistema.

  
 </ul>
 
 <h2>Instalação das ferramentas</h2>
 
 <ul>
  
  <li>Editor de código-fonte VSCode</li>
  
  
  ```bash
  https://code.visualstudio.com/download
  ```
  
 <li>Python</li>

```bash
https://www.python.org/downloads/
```

<li>NodeJS</li>

```bash
https://nodejs.org/en/download/
```

<li>Tkinter</li>

```bash
pip install tkinter
```

<li>Pandas</li>

```bash
pip install pandas
```

<li>Plotly</li>

```bash
$ pip install plotly==4.8.2
```

</ul>


**<H2>Sobre o Produto</H2>**

  

**_Área de Negócio:_** Marketing para ampliação de carteira de clientes

**_Público Alvo:_** Bancos Digitais

**_Problema:_** 

Com o surgimento de novas instituições financeiras e a expansão de pagamentos online, surgiu-se o problema da alta concorrência entre eles.

Notícias relacionadas aos problemas enfrentados pelos bancos digitais:

* https://diariodocomercio.com.br/financas/novos-concorrentes-colocam-bancos-digitais-a-prova/

* https://g1.globo.com/economia/noticia/2019/12/17/multiplicacao-de-competidores-criam-desafio-extra-para-bancos-digitais.ghtml

**_Solução:_**

Vista a alta demanda por pagamentos digitais e a ampla concorrência gerada, nossa solução está em gerar informações sobre os pagamentos realizados com cartões de crédito por meio de indicadores que qualificam os consumidores e distribuir por regiões (Sul, Sudeste, Norte, Nordeste e Centro-Oeste) como os consumidores se comportam e assim ter uma visão das regiões e seu perfil, com o intuito de facilitar a área de marketing para a tomada de decisão na captação de clientes em potencial.

**<H2>Sprints</H2>**

<ul>
  <li>Sprint 1
   
   Foi feito o estudo das planilhas e em conjunto ao SPC, foi dada a prioridade de fazer o levantamento de requisitos para se ter uma noção geral do produto final e caso houvessem discordâncias, os requisitos poderiam ser modificados a tempo.
   
   Card de Requisitos: [Sprint 1](https://github.com/HenriqueNawa/Projeto-SPC-Brasil-Fatec-2020/tree/master/Cards%20de%20requisitos/Sprint%201)
  
  <li>Sprint 2
  
  Foi realizada uma primeira limpeza dos dados, sendo retirados os dados duplicados, contabilizados os erros de tipo de cliente incorreto e valores nulos. E foi feito uma primeira análise mostrando a distribuição das modalidades de operações e o seu tipo de cliente. 
  
  Como as remessas possuem milhares de dados, muitas vezes podem ocorrer erros que ocasionam em informações falsas que podem ocasionar em tomada de decisão errada, por isso foi dada a importância de primeiro se tratar os dados para depois se aprofundar nas análises.
  
  Card de Requisitos: [Sprint 2](https://github.com/HenriqueNawa/Projeto-SPC-Brasil-Fatec-2020/tree/master/Cards%20de%20requisitos/Sprint%202)
  
  <li>Sprint 3
  
  Nesta sprint, priorizamos a limpeza total dos dados (validação dos CNPJs, contabilização de dados nulos e verificação da data de vencimento), a visualização dos valores totais da remessa e recência das remessas de dados.
  
   Card de Requisitos: [Sprint 3](https://github.com/HenriqueNawa/Projeto-SPC-Brasil-Fatec-2020/tree/master/Cards%20de%20requisitos/Sprint%203)
  
  <li>Sprint 4
  
  Reorganização do projeto e revisão das entregas, visando melhorar a estrutura e o entendimento do objetivo do produto (foco em marketing para ampliação da carteira de clientes na área de crédito), esboço do diagrama de caso de uso, análise dos dados dos clientes, visando entender a ligação entre as planilhas e visualização do relacionamento entre os dados por meio de matriz de correlação.
    
  <li>Sprint 5
  
  Desenvolvimento da interface gráfica com as funcionalidades de cada gráfico.  
    
  <li>Sprint 6
  
  Desenvolvimento da interface gráfica utilizando Tkinter e correções no sistema.
  
    
</ul>


**<H3>Equipe</H3>**

**Scrum Master:** Lucas Hiroaki Okazaki

**Product Owner:** Henrique Kenji Nawa

**Dev's:** Igor Soares da Silva e Matheus Andrade Prado de Azevedo


