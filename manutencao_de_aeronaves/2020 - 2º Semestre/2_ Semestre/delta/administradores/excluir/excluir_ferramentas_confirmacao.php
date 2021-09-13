<?php
//header("Content-type: text/html; charset=ISO-8895-1");
     //mantendo uma sessão

     session_start();

     //recuperando as variaveis da sessão

     $system_control = $_SESSION["system_control"];
     $status = $_SESSION["status"];
	 $codigo_departamento = $_GET["codigo_departamento"];
	 $codigo_ferramenta = $_GET["codigo_ferramenta"];

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

     $consulta_departamento = mysqli_query($link, "SELECT * FROM $table_departamentos WHERE codigo = $codigo_departamento");
     $vetor_departamento = mysqli_fetch_array($consulta_departamento);
	 
	 $consulta_ferramenta = mysqli_query($link, "SELECT * FROM $table_ferramentas WHERE codigo = $codigo_ferramenta");
	 $vetor_ferramenta = mysqli_fetch_array($consulta_ferramenta);
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
      <form name="f" action="excluir_ferramentas_confirmacao2.php" method="get">

       <center><h1>EXCLUIR FERRAMENTA</h1></center>
      
	  <input type="hidden" name="codigo_ferramenta" value="<?php print($codigo_ferramenta); ?>">
	  <table align="center">
	  <tr>
				<td align="center">Tem certeza que deseja excluir a Ferramenta <b><?php print($vetor_ferramenta['nome']);?></b> do Departamento <b><?php print($vetor_departamento['nome']);?></b> do sistema?<br></td>     
	  </tr>
	 </table><br>
      <center><input type="submit" name="botao" value="ENVIAR"> <input type="button" name="botao" value="VOLTAR" onclick="history.go(-1)"></a></center> <br></form>
      
</body>


</html>

<?php
     }
?>