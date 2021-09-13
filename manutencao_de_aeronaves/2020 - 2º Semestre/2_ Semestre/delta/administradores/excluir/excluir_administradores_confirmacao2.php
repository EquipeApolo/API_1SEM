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
     
	 $codigo_login = $_GET["c_codigo_login"];
	 $codigo_administrador = $_GET["c_codigo_administrador"];
           
                require("../../connect.php");
                     
                         $excluir_logins = mysqli_query($link, "DELETE FROM $table_logins WHERE codigo = '$codigo_login'");
                         
                         if($excluir_logins == 0)
                         {
?>
                              <script language="JavaScript">
                                   alert("Erro ao excluir os dados de login. Entre em contato com o desenvolvedor do sistema.");
                                   history.go(-1);
                              </script>
<?php
                         }
                         else
                         {
                              $excluir_administradores = mysqli_query($link, "DELETE FROM $table_administradores WHERE codigo = '$codigo_administrador'");
                              
                              //Verificando se o cliente foi cadastrado com sucesso
                              
                              if($excluir_administradores == 0)
                              {
?>
                                   //Houve erro no cadastro
                                   <script language="JavaScript">
                                        alert("Erro ao excluir o ADMINISTRADOR. Entre em contato com o desenvolvedor do sistema");
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
