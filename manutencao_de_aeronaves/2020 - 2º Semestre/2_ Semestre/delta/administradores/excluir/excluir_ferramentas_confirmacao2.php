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
     
	 $codigo_ferramenta = $_GET["codigo_ferramenta"];
           
                require("../../connect.php");
					
						 $consulta_ferramenta = mysqli_query($link, "SELECT * FROM $table_ferramentas WHERE codigo = $codigo_ferramenta");
						 $vetor_ferramenta = mysqli_fetch_array($consulta_ferramenta);
						 $extensao = ".jpg";
						 $partnumber = $vetor_ferramenta['part_number'];
						 $data_imagem = $vetor_ferramenta['data_imagem'];
						 $nome_imagem = $partnumber."-".$data_imagem.$extensao;
						 
                         $excluir_ferramentas = mysqli_query($link, "DELETE FROM $table_ferramentas WHERE codigo = '$codigo_ferramenta'");
						 echo unlink("../../imagens/$nome_imagem"); //excluindo possível imagem já existente no diretório
                         
                         if($excluir_ferramentas == 0)
                         {
?>
                              <script language="JavaScript">
                                   alert("Erro ao excluir a ferramenta do departamento. Entre em contato com o desenvolvedor do sistema.");
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
                    
?>