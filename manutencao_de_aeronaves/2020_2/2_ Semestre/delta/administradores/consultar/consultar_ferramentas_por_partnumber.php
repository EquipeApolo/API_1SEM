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
                    <title>Consultar Ferramentas</title>
          <link rel="stylesheet" href="../css/bootstrap.css">
          <link rel="stylesheet" href="../fonts/ionicons.min.css">
          <link rel="stylesheet" href="../css/styles.min.css">
          
          </head>
          <body>

          <center><h1>Consultar por Partnumber</h2></center>
          <table border="0" align="center">
          <form name="f" method="post" action="consultar_ferramentas_por_partnumber2.php">
          <tr>
               <td><font color="black"><center>(*)PART NUMBER: <input type="text" name="c_partnumber"><br><br><input type="submit" name="botao" value="ENVIAR"> <input type="reset" name="botao" value="LIMPAR"> <a href="consultar_ferramentas.php"><input type="button" name="botao" value="VOLTAR"></a><br><br>

               <font color="black"><center>(*)CAMPOS OBRIGATÓRIOS
               </td>
          </tr>
          </form>
          </table>
          
          </body>
          </html>
<?php

     }
?>
