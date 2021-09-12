<?php
//header("Content-type: text/html; charset=ISO-8895-1");
     //mantendo uma sessão

     session_start();

     //recuperando as variaveis da sessão

     $system_control = $_SESSION["system_control"];
     $status = $_SESSION["status"];
	 $codigo_departamento = $_GET["codigo_departamento"];

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
?>
<html>
      <head><title>Excluir Departamento</title></head>
<body>
      <form name="f" action="excluir_departamentos3.php" method="get">

       <center><h1>EXCLUIR DEPARTAMENTO</h1></center>
      
	  <input type="hidden" name="codigo_departamento" value="<?php print($codigo_departamento); ?>">
	  <table align="center">
	  <tr>
				<td align="center">Tem certeza que deseja excluir o Departamento <b><?php print($vetor_departamento['nome']);?></b> do sistema?<br><br>
				<h3>Todas as ferramentas associadas ao Departamento serão deletadas!!!</h3></td>     
	  </tr>
	 </table><br>
      <center><input type="submit" name="botao" value="ENVIAR"> <input type="button" name="botao" value="VOLTAR" onclick="history.go(-1)"></a></center> <br></form>
      
</body>


</html>

<?php
     }
?>