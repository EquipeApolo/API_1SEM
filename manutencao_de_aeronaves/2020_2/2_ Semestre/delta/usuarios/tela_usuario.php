<?php
//header("Content-type: text/html; charset=ISO-8895-1");
     //mantendo uma sessão
     
     session_start();
     
     //recuperando as variaveis da sessão
     
     $system_control = $_SESSION["system_control"];
     $status = $_SESSION["status"];
     
     //verificando se o usuário realizou o login
     
     if(!(($system_control == 1)&&($status == 2)))
     {
          //acesso inválido
?>
          <script>
               alert("Acesso Inválido");
               history.go(-1);
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
          <title>USUÁRIO</title>
          <link rel="stylesheet" href="css/bootstrap.css">
          <link rel="stylesheet" href="fonts/ionicons.min.css">
          <link rel="stylesheet" href="css/styles.min.css">
               </head>
          <body>
               <center><h1>TELA USUÁRIO</h1></center>
               <div class="menus">
			   <div class="menus btn-group-vertical d-flex justify-content-center">
			   
                    <a href="cadastrar/cadastrar.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">CADASTRAR<br>
                    <a href="consultar/consultar.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">CONSULTAR</a>
                    <a href="atualizar/atualizar.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">ATUALIZAR</a>
					<a href="../finalizar.php" class="btn btn-Secondary btn-lg active" role="button" aria-pressed="true">LOGOUT</a></center>
				</div>

        
          </div>
<?php
     }
?>
