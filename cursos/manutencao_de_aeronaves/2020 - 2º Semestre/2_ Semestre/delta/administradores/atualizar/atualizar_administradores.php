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
                    <title>Atualizar Administradores</title>
          <link rel="stylesheet" href="../css/bootstrap.css">
          <link rel="stylesheet" href="../fonts/ionicons.min.css">
          <link rel="stylesheet" href="../css/styles.min.css">
               </head>
          <body>

<div class="form-check">
		  <center><h1>Atualizar Administradores</h1></center>	
          
          <table border="0" align="center">
          <form name="f" method="post" action="atualizar_administradores2.php">
          <tr>
               <td><font color="black"><input type="radio" name="c_opcao" value="visualizar_todos" checked>Visualizar Todos<br><input type="radio" name="c_opcao" value="por_cpf">Por CPF
               <br>


               <input type="radio" name="c_opcao" value="por_nome">Por Nome<br><br>


               <input type="submit" name="botao" value="ENVIAR"> <a href="atualizar.php">


                    <input type="button" name="botao" value="VOLTAR"></a></td>
          </tr>
          </form>
          </table>
     </font>
          </body>
          </html>
<?php
     }
?>
