<?php
//header("Content-type: text/html; charset=ISO-8895-1");
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
               document.location.href="../../index.php";
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
          <title>CADASTRAR</title>
          <link rel="stylesheet" href="../css/bootstrap.css">
          <link rel="stylesheet" href="../fonts/ionicons.min.css">
          <link rel="stylesheet" href="../css/styles.min.css">
      
          </head>
<body>
      <center><h1>CADASTRAR</h1><center>
      <div class="menus">
                <div class="menus btn-group-vertical d-flex justify-content-center">
               <a href="cadastrar_administradores.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">ADMINISTRADORES</a>
               <a href=cadastrar_usuarios.php class="btn btn-primary btn-lg active" role="button" aria-pressed="true">USUÁRIOS</a>
               <a href="cadastrar_departamentos.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">DEPARTAMENTOS</a>
               <a href="cadastrar_ferramentas.php" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">FERRAMENTAS</a>
               <a href="../tela_administrador.php" class="btn btn-Secondary btn-lg active" role="button" aria-pressed="true">VOLTAR</a>
          </div>
		  </div>


</body>
</html>

<?php
     }
?>
