
<?php
     //mantendo uma sessão
     
     session_start();
     
     //recuperando as variaveis da sessão
     
     $system_control = $_SESSION["system_control"];
     $status = $_SESSION["status"];
     
     //verificando se o usuário realizou o login
     
     if(!(($system_control == 1)&&($status == 1)))
     {
                   //acesso inválido
?>
          <script>
               alert("Acesso Inválido");
               document.location.href="../index.php";
          </script>
<?php
     }
     else
     {
?>
          <html>
          <head>

          <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
          <title>ADMINISTRADOR</title>
          <link rel="stylesheet" href="css/bootstrap.css">
          <link rel="stylesheet" href="fonts/ionicons.min.css">
          <link rel="stylesheet" href="css/styles.min.css">
      
          </head>
          <body>
          <center><h1>TELA ADMINISTRADOR</h1></center>
          <div class="menus">
                <div class="menus btn-group-vertical d-flex justify-content-center">
               <a href="cadastrar/cadastrar.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">CADASTRAR</a>
               <a href="consultar/consultar.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">CONSULTAR</a>
               <a href="atualizar/atualizar.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">ATUALIZAR</a>
               <a href="excluir/excluir.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">EXCLUIR</a>
               <a href="../finalizar.php" class="btn btn-Secondary btn-lg active" role="button" aria-pressed="true">LOGOUT</a>
          </div>

        
          </div>
<BR>
<BR>
 
          </div>
               


            
<?php
     }
?>
