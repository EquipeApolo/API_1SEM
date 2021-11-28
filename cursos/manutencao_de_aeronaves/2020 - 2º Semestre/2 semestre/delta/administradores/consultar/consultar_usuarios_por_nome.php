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
					<title>Consultar Nome</title>
					<link rel="stylesheet" href="../css/bootstrap.css">
					<link rel="stylesheet" href="../fonts/ionicons.min.css">
					<link rel="stylesheet" href="../css/style.min.css">
               </head>
          <body>

				<center><h1>Consultar Usuários</h1></center>
               <form name="f" method="post" action="consultar_usuarios_por_nome2.php">

               <table border="0" align="center">
               <tr>
                    <td><font color="black">Insira o Nome que deseja consultar: <input type="text" name="c_nome" size="10"><br><br>
					<center><input type="submit" name="botao" value="ENVIAR"> <input type="reset" name="limpar" value="LIMPAR"> <a href="consultar_usuarios.php"><input type="button" name="voltar" value="VOLTAR"></a></center></td>
               </tr>
               </table>
               </form>
<?php
     }
?>
