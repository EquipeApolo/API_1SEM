<?php

     //Recebendo as Variaveis
     
     $nickname = $_POST["c_email_ou_nickname"];
     $senha = $_POST["c_senha"];
     
          //verificando se os campos foram preenchidos
          
          if(empty($nickname))
          {
?>
               <script language="JavaScript">
                    alert("Campo NICKNAME vazio");
                    history.go(-1);
               </script>
<?php
          }
          else if(empty($senha))
          {
?>
               <script language="JavaScript">
                    alert("Campo SENHA vazio");
                    history.go(-1);
               </script>
<?php
          }
          else
          {
               //campos preenchidos

               //iniciando consultas
               
               require("connect.php");
               
               //consultando o nickname na tabela logins
               
                    $consulta_nickname_logins = mysqli_query($link, "SELECT * FROM $table_logins WHERE nickname = '$nickname' OR email = '$nickname'");
                    $quant_registros_logins = mysqli_num_rows($consulta_nickname_logins);
               
               if($quant_registros_logins == 0)
               {
                    //nickname inexistente
?>
                         <script language="JavaScript">
                              alert("Email ou Nickname Incorreto !!!");
                              history.go(-1);
                         </script>
<?php
               }
               else
               {
                    //nickname existe
                    
                    //consultando nickname e a senha na tabela logins
                    
                    $consulta_nickname_senha_logins = mysqli_query($link, "SELECT * FROM $table_logins WHERE email = '$nickname' OR nickname = '$nickname' AND senha = '$senha'");
                    $quant_nickname_logins = mysqli_num_rows($consulta_nickname_senha_logins);

                    if($quant_nickname_logins == 0)
                    {
                         //senha invalida
?>
                         <script language = "JavaScript">
                              alert("SENHA INVÁLIDA");
                              history.go(-1);
                         </script>

<?php

                    }
                    else
                    {
                         //O USUARIO ESTA OK E LOGADO
                         
                         //VERIFICANDO A SITUAÇÃO DO JOGADOR
                         
                         $vetor = mysqli_fetch_array($consulta_nickname_senha_logins);

                                //inicializando uma nova sessão
                              
                                session_start();
                              
                                //criando uma variavei de controle do sistema
                              
                                //registrando esta variavel no sistema
                              
                                $_SESSION["system_control"] = 1;
                              
                                //verificando o tipo de login do usuário

                                //VERIFICANDO SE É ADMIN OU CLIENTE
                              
                                if($vetor['status'] == 1)
                                {
                                     //O usuário é um administrador
                                   
                                     //criando a variavel status na sessão com valor 1

                                        $_SESSION["status"] = 1;
                                        $_SESSION["codigo"] = $vetor['codigo'];
                                   
                                        //redirecinonando para a tela administrador
?>
                                        <script language = "JavaScript">
                                             document.location.href="administradores/tela_administrador.php";
                                        </script>
<?php
                                        }
                                        else
                                        {
                                        $_SESSION["status"] = 2;
                                        $_SESSION["codigo"] = $vetor['codigo'] ;
?>

                                             <script language = "JavaScript">
                                                  document.location.href="usuarios/tela_usuario.php";
                                             </script>

<?php
                                       }
                    }
               }
          }
?>
                              




               
               
               
               
               
               
               
          
          
