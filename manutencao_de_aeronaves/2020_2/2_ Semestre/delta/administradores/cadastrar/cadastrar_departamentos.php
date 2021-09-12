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
					<title>Cadastro Departamento</title>
					<link rel="stylesheet" href="../css/bootstrap.css">
					<link rel="stylesheet" href="../fonts/ionicons.min.css">
					<link rel="stylesheet" href="../css/style.min.css">
               </head>
          <body>

          <center><h1>Cadastrar Departamento</h2></center>
          <table border="0" align="center">
          <form name="f" method="post" action="cadastrar_departamentos2.php">
          <tr>
               <td><font color="black"><center>(*)NOME: <input type="text" name="c_nome"><br><br><input type="submit" name="botao" value="CADASTRAR"> <input type="reset" name="botao" value="LIMPAR"> <a href="cadastrar.php"><input type="button" name="botao" value="VOLTAR"></a><br><br>

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
