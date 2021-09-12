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
                    <title>Visualizar todos departamentos</title>
          <link rel="stylesheet" href="../css/bootstrap.css">
          <link rel="stylesheet" href="../fonts/ionicons.min.css">
          <link rel="stylesheet" href="../css/styles.min.css">
          </head>
          <body>
			   <center><h1> Visualizar todos departamentos</h1></center>
               <table border="1" align="center" class="table table-bordered table-info">
               <tr>
                    <td align="center"><font color="black"><h2>NOME<h2></td>
               </tr>
<?php
                    //Realizando consultas necessarioas

                    require("../../connect.php");

                    $consultar_departamentos = mysqli_query($link, "SELECT * FROM $table_departamentos");
					$quant_departamentos = mysqli_num_rows($consultar_departamentos);

                    for($i = 1;$i<=$quant_departamentos;$i++)
                    {
						$vetor_departamentos = mysqli_fetch_array($consultar_departamentos);
						$nome_departamento = $vetor_departamentos['nome'];
?>
               <tr>
					<td align="center"><font color="black"><?php print($nome_departamento);?></td>
               </tr>
<?php
                    }
?>
               </table><br>
               <center><a href="consultar.php"><input type="button" name="botao" value="VOLTAR"></a></center>
<?php
     }
?>

