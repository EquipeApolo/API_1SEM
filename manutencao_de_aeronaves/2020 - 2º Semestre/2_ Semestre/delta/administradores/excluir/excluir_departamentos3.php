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

     //Recebendo os campos do formulario
     
	 $codigo_departamento = $_GET["codigo_departamento"];
           
                require("../../connect.php");
					
						 $consulta_ferramenta = "SELECT * FROM $table_ferramentas WHERE codigo_departamento = '$codigo_departamento'";
						 $resultado_consulta_ferramenta = mysqli_query($link, $consulta_ferramenta);
						 $quant_ferramenta = mysqli_num_rows($resultado_consulta_ferramenta);
						 
						 for($i=1;$i<=$quant_ferramenta;$i++)
						 {
							 $vetor_ferramenta = mysqli_fetch_array($resultado_consulta_ferramenta);
							 $partnumber = $vetor_ferramenta['part_number'];
							 $extensao = ".jpg";
							 $nome_imagem = $partnumber.$extensao;
							 echo unlink("../../imagens/$nome_imagem"); //excluindo possível imagem já existente no diretório
							 
						 }
                     
                         $excluir_ferramentas = mysqli_query($link, "DELETE FROM $table_ferramentas WHERE codigo_departamento = '$codigo_departamento'");
                         
                         if($excluir_ferramentas == 0)
                         {
?>
                              <script language="JavaScript">
                                   alert("Erro ao excluir as ferramentas do departamento. Entre em contato com o desenvolvedor do sistema.");
                                   history.go(-1);
                              </script>
<?php
                         }
                         else
                         {
                              $excluir_departamento = mysqli_query($link, "DELETE FROM $table_departamentos WHERE codigo = '$codigo_departamento'");
							  echo unlink("../../imagens/$nome_imagem"); //excluindo possível imagem já existente no diretório
                              
                              //Verificando se o cliente foi cadastrado com sucesso
                              
                              if($excluir_departamento == 0)
                              {
?>
                                   //Houve erro no cadastro
                                   <script language="JavaScript">
                                        alert("Erro ao excluir o DEPARTAMENTO. Entre em contato com o desenvolvedor do sistema");
                                        history.go(-1);
                                   </script>
<?php
                              }
                              else
                              {
                                   //Cadastro Realizado com Sucesso
?>
                                   <script language="JavaScript">
                                        alert("Exclusão Realizada com Sucesso!");
                                        document.location.href="../tela_administrador.php";
                                   </script>
<?php
                              }
                            }
						}
                    
?>