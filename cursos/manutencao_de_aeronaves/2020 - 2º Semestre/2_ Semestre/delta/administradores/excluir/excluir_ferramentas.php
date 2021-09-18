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
                    <title>Excluir Ferramentas</title>
          <link rel="stylesheet" href="../css/bootstrap.css">
          <link rel="stylesheet" href="../fonts/ionicons.min.css">
          <link rel="stylesheet" href="../css/styles.min.css">
          </head>
          <body>

		  <center><h1>Excluir Ferramentas</h1></center>	
          <table border="0" align="center">
          <form name="f" method="post" action="excluir_ferramentas2.php">
          <tr>
               <td><font color="black"><input type="radio" name="c_opcao" value="visualizar_todas" checked>Visualizar Todas<br><input type="radio" name="c_opcao" value="por_partnumber">Por Part Number
               <br><input type="radio" name="c_opcao" value="por_nome">Por Nome<br><input type="radio" name="c_opcao" value="por_departamento">Por Departamento<br><br><input type="submit" name="botao" value="ENVIAR"> <a href="excluir.php"><input type="button" name="botao" value="VOLTAR"></a></td>
          </tr>
          </form>
          </table>
          </body>
          </html>
<?php
     }
?>
