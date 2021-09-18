<?php
     //mantendo uma sessão

     session_start();

     //recuperando as variaveis da sessão

     $system_control = $_SESSION["system_control"];
     $status = $_SESSION["status"];
     $codigo_login = $_GET["codigo_login"];
     $codigo_administrador = $_GET["codigo_administrador"];

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
     require("../../connect.php");

     $consulta_administrador = mysqli_query($link, "SELECT * FROM $table_administradores WHERE codigo = $codigo_administrador");
     $quantos_administrador = mysqli_num_rows($consulta_administrador);
     $vetor_administrador = mysqli_fetch_array($consulta_administrador);

     $consulta_login = mysqli_query($link, "SELECT * FROM $table_logins WHERE codigo = $codigo_login");
     $quantos_login = mysqli_num_rows($consulta_login);
     $vetor_login = mysqli_fetch_array($consulta_login);
?>
<html>
      <head>
	  <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
          <title>Excluir Administradores</title>
          <link rel="stylesheet" href="../css/bootstrap.css">
          <link rel="stylesheet" href="../fonts/ionicons.min.css">
          <link rel="stylesheet" href="../css/style.min.css">
	  </head>
<body>
      <form name="f" action="excluir_administradores_confirmacao2.php" method="get">

       <center><h1>EXCLUIR ADMINISTRADOR</h1></center>
      
	  <input type="hidden" name="c_codigo_login" value="<?php print($codigo_login); ?>">
      <input type="hidden" name="c_codigo_administrador" value="<?php print($codigo_administrador);?>">
	  <table align="center">
	  <tr>
				<td>Tem certeza que deseja excluir o(a) administrador(a) <?php print($vetor_administrador['nome']);?> <?php print($vetor_administrador['sobrenome']);?> do sistema?</td>     
	  </tr>
	 </table><br>
      <center><input type="submit" name="botao" value="ENVIAR"> <input type="button" name="botao" value="VOLTAR" onclick="history.go(-1)"></a></center> <br></form>
      
</body>


</html>

<?php
     }
?>
