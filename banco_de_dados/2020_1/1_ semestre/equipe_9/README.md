<p><div align="center"><b>INTEGRANTES</b></div></p>

<div align="center">Lucas Aparecido Alves da Silva</div>
<div align="center">Maximiles Silva Barcelos Ribeiro</div>
<div align="center">Paulo Messias Soares da Silva</div>
<div align="center">Rodrigo Amâncio do Prado Tenório</div>
<div align="center">Mateus Costa Luz</div>



<p><div align="center"><b>1º ENTREGA - SARAH - ASSISTENTE VIRTUAL DE RH </b></div></p>

<p><div align="justify">Sarah é uma assistente virtual voltada para funcionalidades de RH, o tema foi definido de acordo com as funções que acreditamos ser relevantes para o curso, como uma busca em um banco de dados ou simplesmente cálculos rescisórios dos quais utilizamos lógica de programação.</div><p>
<p><div align="justify">A linguagem escolhida foi Python, é a linguagem que será ensinada no primeiro semestre então conseguiremos avançar tanto no projeto quanto na linguagem em si. Utilizaremos tambem um banco de dados em  SQLite, pois, o mesmo já é nativo do python. Para exibição dos formularios e interface, podemos utilizar de duas  linguagens, sendo elas a FLASK e o DJANGO, ainda a ser definido. A aplicação será um controle de funcionarios tendo ações que poderão ser executadas através de comando de voz.</div></p>
<p><div align="left"> As seguintes ações serão chamadas através de comando de voz (ainda não definidos pelo grupo):</div></p>
<p><div align="left">• Desabilitar SARAH por voz – Desabilitará o modo escuta do programa;</div></p>
<p><div align="left">• Inclusão de funcionários – Efetuará o cadastro de funcionários;</div></p>
<p><div align="left">• Pesquisa de funcionários – Mostrará o resultado da pesquisa de funcionarios;</div></p>
<p><div align="left">• Alteração de Dados – Fará a alteração dos dados do funcionario;</p>
<p><div align="left">• Demissão de funcionarios - O registro do funcionário demitido se tornará inativo.</div></p>
<p><div align="Center"><b>POR QUE A SARAH?</b></div></p>


<p><div align="justify">SARAH foi desenvolvida para auxiliar algumas funções que são primordiais na tomada de decisão, fazendo uma junção com a facilidade do programa de abrir e fazer pesquisas por voz, com a análise de dados. Cálculos de verbas rescisórias, cálculo da atualização do dissídio, são tarefas simples, porém que te obrigam a ter um prévio conhecimento das leis trabalhistas. A nossa função é colocar todo esse entendimento a partir de um “Olá Sarah” que te abrirá um leque de opções.</div></p>


<p><div align="Center"><b>2ª ENTREGA - EXPLICANDO AS FUNCIONALIDADES ESCOLHIDAS</b></div></p>

<p><div align="justify">O programa conta com funcionalidade desabilitar este comando será por voz,  o usuário  precisa deixar o aplicativo no modo escuta o tempo todo, para ativação do desabilitar, habilitada essa função, o usuário poderá usar a voz para executar comando de fechamento total da SARAH.</div></p>
<p><div align="justify">• Inclusão ou cadastro onde será inserido um novo funcionário com seu registro único dentro da empresa;</div></p>
<p><div align="justify">• Pesquisa: Conforme o número de pessoas cadastradas no sistema, cresce de importância uma boa pesquisa com filtragens para acharmos um registro desejado, a função de pesquisa cumprirá com essa demanda;</div></p>
<p><div align="justify">• Alterações: imagine a situação em que o funcionário receba uma promoção e precise alterar suas informações cadastrais, após utilizar da função de pesquisa o empregado é selecionado e tem seu formulário de cadastros habilitado novamente para atualização de seus dados;</div></p>
<p><div align="justify">• Demissão: uma função que caso o contratado seja demitido, seu registro se torne inativo.</div></p>
<p><div align="center"><b>PROVA DE CONCEITO (POC)</b></div></p>

<p><div align="justify">No link abaixo temos um arquivo gravado no repositório do GitHub do projeto,  que foi criado com o intuito de testar a funcionalidade e conhecer melhor a biblioteca speech_recognition.</div></p>
<p><div align="justify">https://github.com/rodrigo-prado1705/Sarah/tree/master/backend-app</div></p>
<p><div align="justify">O teste foi realizado obtendo sucesso no seu uso, assim sendo possível a implementação no programa SARAH.No teste armazenamos o que era dito pelo usuário em uma variável para podermos comparar posteriormente com comando pré-estabelecidos, executando assim funções já definidas.</div></p>

<p><div align="center"><b>3ª ENTREGA - IMPLEMENTAÇÃO DAS FUNCIONALIDADES</b></div></p>
<p><div align="justify">Neste ponto da trajetória do projeto obtivemos sucesso ao implementar o comando por voz, utilizando como base a Prova de Conceito realizada no começo deste trabalho. Houve a remoção da função “Habilitar SARAH”, pois conforme testado pelos integrantes do grupo, não foi encontrada uma utilização funcional ou mesmo aplicável ao aplicativo, visto que SARAH estará sempre habilitada escutando os comandos ditos pelo usuário.</div></p>
<p><div align="justify">No arquivo que pode ser encontrado no link: https://github.com/rodrigo-prado1705/Sarah/blob/master/backend-app/app.py pode-se encontrar a função “Desabilitar SARAH” já implementada, onde o usuário ao dizer a frase, Desabilitar SARAH, fará com que o programa encerre, ou seja, SARAH será finalizado.</div></p>
<p><div align="justify">Estas ideias e alterações foram realizadas em reunião gravada com os integrantes e supervisionada pelo Master do grupo. A gravação desta reunião pode ser acessada através do link:</div></p>
<p><div align="justify">https://github.com/rodrigo-prado1705/Sarah/blob/master/apresentacoes/2020-05-22.mp4..</div></p>

<p><div align="center"><b>4ª ENTREGA - IMPLEMENTAÇÃO DA FUNCIONALIDADE "CADASTRAR FUNCIONÁRIO"</b></div></p>
<p><div align="justify">Nesta entrega decidimos utilizar o banco de dados, através do SQLite, para garantir a permanência dos dados quando a Sarah for desabilitada, tornando possível o acesso ao que foi previamente cadastrado.</div></p>
<p><div align="justify">O usuário pode solicitar à Sarah o cadastro de um funcionário, passando os campos por voz.</div></p>
<p><div align="justify">O código foi implementado no arquivo base do projeto, que pode ser encontrado em:</div></p>
<p><div align="justify">https://github.com/rodrigo-prado1705/Sarah/blob/master/backend-app/functions_database.py</div></p>
<p><div align="justify">O arquivo do banco de dados pode ser encontrado em:</div></p>
<p><div align="justify">https://github.com/rodrigo-prado1705/Sarah/blob/master/backend-app/database.db </div></p>
<p><div align="justify">Também criamos um menu para otimizar a interação do usuário com a Sarah, que pode ser visto em:</div></p>
<p><div align="justify">https://github.com/rodrigo-prado1705/Sarah/blob/master/backend-app/menu.py</div></p>
<p><div align="justify">Todas as funcionalidades foram implementadas no arquivo base do projeto, visto no link:</div></p>
<p><div align="justify">https://github.com/rodrigo-prado1705/Sarah/blob/master/backend-app/app.py</div></p>
<p><div align="justify"> A demonstração da funcionalidade implementada que pode ser encontrada no link:</div></p>
<p><div align="justify">https://github.com/rodrigo-prado1705/Sarah/blob/master/apresentacoes/2020-06-05.mp4</div></p>



<p><div align="center"><b>APRESENTAÇÕES</b></div></p>

<p><div align="justify">No link abaixo temos um arquivo em vídeo no repositório do GitHub do projeto, com as apresentações das entregas.</div></p>
<p><div align="justify">https://github.com/rodrigo-prado1705/Sarah/tree/master/apresentacoes</div></p>

