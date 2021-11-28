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
					<title>Consultar CPF</title>
					<link rel="stylesheet" href="../css/bootstrap.css">
					<link rel="stylesheet" href="../fonts/ionicons.min.css">
					<link rel="stylesheet" href="../css/style.min.css">
                    <script>
                    function cpf1()
                    {
                         var tamanho;
                         var retorno;

                         retorno = isNaN(f.c_cpf1.value);

                         if(retorno==1)
                         {
                              alert("Dígito Inválido");
                              f.c_cpf1.value = "";
                              f.c_cpf1.focus();
                         }

                         tamanho = f.c_cpf1.value.length;

                         if(tamanho == 3)
                         {
                              f.c_cpf2.focus();
                         }
                     }
                     //segunda caixa cpf
                    function cpf2()
                    {
                         var tamanho;
                         var retorno;

                         retorno = isNaN(f.c_cpf2.value);

                         if(retorno==1)
                         {
                              alert("Dígito Inválido");
                              f.c_cpf2.value = "";
                              f.c_cpf2.focus();
                         }

                         tamanho = f.c_cpf2.value.length;

                         if(tamanho == 3)
                         {
                              f.c_cpf3.focus();
                         }
                     }
                     //terceira caixa do cpf
                     function cpf3()
                    {
                         var tamanho;
                         var retorno;

                         retorno = isNaN(f.c_cpf3.value);

                         if(retorno==1)
                         {
                              alert("Dígito Inválido");
                              f.c_cpf3.value = "";
                              f.c_cpf3.focus();
                         }

                         tamanho = f.c_cpf3.value.length;

                         if(tamanho == 3)
                         {
                              f.c_cpf4.focus();
                         }
                     }
                     //quarta caixa do cpf
                     function cpf4()
                    {
                         var retorno;

                         retorno = isNaN(f.c_cpf4.value);

                         if(retorno==1)
                         {
                              alert("Dígito Inválido");
                              f.c_cpf4.value = "";
                              f.c_cpf4.focus();
                         }
                    }
                    </script>
               </head>
          <body>

				<center><h1>Consultar Usuário</h1></center>
               <form name="f" method="get" action="consultar_usuarios_por_cpf2.php">

               <table border="0" align="center">
               <tr>
                    <td><font color="black">Insira o CPF que deseja consultar: <input type="text" name="c_cpf1" size="3" maxlength="3" onKeyUp="cpf1();">--<input type="text" name="c_cpf2" size="3" maxlength="3" onKeyUp="cpf2();">--<input type="text" name="c_cpf3" size="3" maxlength="3" onKeyUp="cpf3();">--<input type="text" name="c_cpf4" size="2" maxlength="2" onKeyUp="cpf4();"><br><br>
					<center><input type="submit" name="botao" value="ENVIAR"> <input type="reset" name="limpar" value="LIMPAR"> <a href="consultar_usuarios.php"><input type="button" name="voltar" value="VOLTAR"></a></center></td>
               </tr>
               </table>
               </form>
<?php
     }
?>
