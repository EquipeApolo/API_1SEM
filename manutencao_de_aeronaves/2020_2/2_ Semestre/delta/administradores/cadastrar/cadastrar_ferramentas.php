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
          <title>Cadastro Ferramenta</title>
          <link rel="stylesheet" href="../css/bootstrap.css">
          <link rel="stylesheet" href="../fonts/ionicons.min.css">
          <link rel="stylesheet" href="../css/style.min.css">
          <script>
          function quantidade()
                    {
                         var retorno;

                         retorno = isNaN(f.c_quantidade.value);

                         if(retorno==1)
                         {
                              alert("Dígito Inválido");
                              f.c_quantidade.value = "";
                              f.c_quantidade.focus();
                         }
                    }
		 function partnumber2()
                    {
                         var retorno;

                         retorno = isNaN(f.c_partnumber2.value);

                         if(retorno==1)
                         {
                              alert("Dígito Inválido");
                              f.c_partnumber2.value = "";
                              f.c_partnumber2.focus();
                         }
                    }
		  function ApenasLetras(e, t) 
					{
						try 
						{
							if (window.event) 
							{
								var charCode = window.event.keyCode;
							} 
							else if (e) 
							{
								var charCode = e.which;
							} 
							else 
							{
								return true;
							}
							if (
							    (charCode > 64 && charCode < 91) || 
								(charCode > 96 && charCode < 123) ||
								(charCode > 191 && charCode <= 255) // letras com acentos
							   )
							{
								return true;
							} 
								else 
								{
									alert("Apenas letras!!!");
									return false;
								}
						} 
						catch (err) 
						{
							alert(err.Description);
						}
					}
          </script>
     </head>
<body>
      <center><h1>Cadastrar Ferramenta</h1></center>
      <center>
     <form name="f" method="post" action="cadastrar_ferramentas2.php" enctype="multipart/form-data">
               <br><font color="black">
               (*)NOME: <input type="text" name="c_nome"><br>
               (*)PART NUMBER: <input type="text" name="c_partnumber1" size="3" maxlength="3" onkeypress="return ApenasLetras(event,this);"> - <input type="text" name="c_partnumber2" size="5" onKeyUp="partnumber2();"><br>
               (*)QUANTIDADE: <input type="number" min="0" step="1" id="entrada" name="c_quantidade"><br>
               (*)DEPARTAMENTO: <select name="c_departamento">
      <?php
           //Realizando consulta para recuperar dados de departamentos cadastrados

               require("../../connect.php");

               $consulta_departamentos = mysqli_query($link, "SELECT * FROM $table_departamentos");
               $quant_nome_departamentos = mysqli_num_rows($consulta_departamentos);

               for($i=1;$i<=$quant_nome_departamentos;$i++)
               {
                    $vetor_departamentos = mysqli_fetch_array($consulta_departamentos);
?>
                    <option value="<?php print($vetor_departamentos['codigo']);?>"><?php print($vetor_departamentos['nome']);?></option>
<?php

               }
?>
       </select><br><br>
       (*)DESCRIÇÃO(100 caractéres MAX): <br><textarea rows="5" cols="50" style="background-color: #ffffff" wrap="soft" name="c_descricao" maxlength="100"></textarea><br><br>
	   IMAGEM: <input type="file" name="imagem"><br><br>

               <input type="submit" name="botao" value="ENVIAR"> <input type="reset" name="botao" value="LIMPAR"> <a href="cadastrar.php"><input type="button" name="botao" value="VOLTAR"></a><br><br>

               (*)CAMPOS OBRIGATÓRIOS
               </td>
          </tr>
          </table>

     </form>
</body>
</html>
<?php
     }
?>
