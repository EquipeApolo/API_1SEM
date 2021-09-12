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
     //Recebendo os campos do formulario
     
     $nome = $_POST["c_nome"]; //1
     $sobrenome = $_POST["c_sobrenome"];//1
     $nasc = $_POST["c_nasc"];            //3
     $sexo = $_POST["c_sexo"];          //6
     $telefone1 = $_POST["c_telefone1"];//7
     $telefone2 = $_POST["c_telefone2"];//8
     $telefone3 = $_POST["c_telefone3"];//9
     $celular1 = $_POST["c_celular1"];  //10
     $celular2 = $_POST["c_celular2"];  //11
     $celular3 = $_POST["c_celular3"];  //12
     $email = $_POST["c_email"];        //13
     $email2 = $_POST["c_email2"];      //14
     $cpf1 = $_POST["c_cpf1"];
     $cpf2 = $_POST["c_cpf2"];
     $cpf3 = $_POST["c_cpf3"];
     $cpf4 = $_POST["c_cpf4"];
     $nickname = $_POST["c_nickname"];  //19
     $senha = $_POST["c_senha"];        //20
     $senha2 = $_POST["c_senha2"];      //21
     $rua = $_POST["c_rua"];
     $numero = $_POST["c_numero"];
     $bairro = $_POST["c_bairro"];
     $cep = $_POST["c_cep"];
     $cidade = $_POST["c_cidade"];
     $estado = $_POST["c_uf"];
     $departamento = $_POST["c_departamento"];
     
     if(empty($nome))
     {
                 //vazioo
?>
        <script>
                alert("Campo Nome vazio");
                history.go(-1);

        </script>

<?php
     }
     else if(empty($sobrenome))
     {
?>
        <script>
                alert("Campo Sobrenome vazio");
                history.go(-1);
        </script>

<?php
     }
     else if(empty($nasc))
     {
?>
        <script>
                alert("Campo NASCIMENTO vazio");
                history.go(-1);
        </script>

<?php
     }
     else if(empty($sexo))
     {
?>
        <script>
                alert("Campo SEXO vazio");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($celular1))
     {
?>
        <script>
                alert("CAMPO CELULAR VAZIO");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($celular2))
     {
?>
        <script>
                alert("CAMPO CELULAR VAZIO");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($celular3))
     {
?>
        <script>
                alert("CAMPO CELULAR VAZIO");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($email))
     {
?>
        <script>
                alert("CAMPO EMAIL VAZIO");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($email2))
     {
?>
        <script>
                alert("CAMPO DE CONFIRMAÇÃO DE EMAIL VAZIO");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($cpf1))
     {
?>
        <script>
                alert("CAMPO CPF VAZIO");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($cpf2))
     {
?>
        <script>
                alert("CAMPO CPF VAZIO");
                history.go(-1);
        </script>
<?php

     }
     else if(empty($cpf3))
     {
?>
        <script>
                alert("CAMPO CPF VAZIO");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($cpf4))
     {
?>
        <script>
                alert("CAMPO CPF VAZIO");
                history.go(-1);

        </script>
<?php
     }
     else if(empty($nickname))
     {
?>
        <script>
                alert("campo NICKNAME vazio");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($senha))
     {
?>
        <script>
                alert("campo SENHA vazio");
                history.go(-1);
        </script>
<?php
     }
     else if(empty($senha2))
     {
?>
        <script>
                alert("campo CONFIRME SENHA vazio");
                history.go(-1);
        </script>

<?php
     }
     else if(empty($rua))
     {
?>
          <script>
               alert("campo RUA vazio");
               history.go(-1);
          </script>
<?php
     }
     else if(empty($numero))
     {
?>
          <script>
               alert("campo NUMERO vazio");
               history.go(-1);
          </script>
<?php
     }
     else if(empty($bairro))
     {
?>
          <script>
               alert("campo BAIRRO vazio");
               history.go(-1);
          </script>
<?php
     }
     else if(empty($cep))
     {
?>
          <script>
               alert("campo CEP incompleto ou vazio");
               history.go(-1);
          </script>
<?php
     }
     else if(empty($cidade))
     {
?>
          <script>
               alert("Campo CIDADE incompleto");
               history.go(-1);
          </script>
<?php
     }
     else if(empty($estado))
     {
?>
          <script>
               alert("Campo ESTADO incompleto");
               history.go(-1);
          </script>
<?php
     }
     else
     {
          //CAMPOS PREENCHIDOS
?>



<?php

          //verificando se o email bate com a confirmacao de email passo 5
     
          $e = strcmp($email,$email2);
          $r = strcmp($senha,$senha2);

           if($e != 0)
           {
?>
               <script language="JavaScript">
                    alert("Confirmação de email não confere !!!");
                    history.go(-1);
               </script>

<?php
           }
           else if($r != 0)
           {
?>
                <script language="JavaScript">
                     alert("Confirmação de senha não confere !!!");
                     history.go(-1);
                </script>
<?php

           }
           else
           {

           //concatenando o cpf - PASSO 7
       
           $cpf = $cpf1.$cpf2.$cpf3.$cpf4;
       
           //concatenando o telefone
       
           $telefone = $telefone1.$telefone2.$telefone3;
           
           //concatenando o celular
           
           $celular = $celular1.$celular2.$celular3;
           
           //verificar se o telefone e o celular estão com a quantidade de caracteres corretos - PASSO 7B
           
           $quant_telefone = strlen($telefone);
           $quant_celular = strlen($celular);
           $quant_cep = strlen($cep);
           $quant_senha = strlen($senha);
           
           if($quant_senha < 6)
           {
?>
                <script language="JavaScript">
                     alert("A senha deve conter 6 caracteres no minimo !!!");
                     history.go(-1);
                </script>
<?php
           }
           else if($quant_celular < 11)
           {
?>

                <script language="JavaScript">
                     alert("formato do CELULAR inválido.");
                     history.go(-1);
                </script>
<?php
           }
           else if(($quant_telefone != 0)&&($quant_telefone <10))
           {
?>

                <script language="JavaScript">
                     alert("Formato de celular inválido.");
                     history.go(-1);
                </script>
<?php
           }
           else if($quant_cep < 8)
           {
?>
                <script>
                     alert("Formato de CEP inválido.");
                     history.go(-1);
                </script>
<?php
           }
           else
           {
           
                require("../../connect.php");

                //verificando se o CPF está cadastrado - PASSO 8

                $consulta_clientes = mysqli_query($link, "SELECT * FROM $table_usuarios WHERE cpf = '$cpf'");
                $quant_registros_clientes = mysqli_num_rows($consulta_clientes);
                
                //Verificando se o CPF ja foi encontrado
                
                if($quant_registros_clientes == 1)
                {
                     //cpf ja existente
?>
                     <script language="JavaScript">
                             alert("CPF já cadastrado em nosso Banco de Dados");
                             history.go(-1);
                     </script>
<?php
                }
                else
                {
                    //cpf ainda não cadastrado

                    //verificando se o nickname ja existe  - PASSO 9

                    $consulta_logins = mysqli_query($link, "SELECT * FROM $table_logins WHERE nickname = '$nickname'");
                    $quant_registros_logins = mysqli_num_rows($consulta_logins);
                
                    if($quant_registros_logins == 1)
                    {
                     //nickname ja existente
?>

                     <script language="JavaScript">
                             alert("NICKNAME já existente em nosso Banco de Dados");
                             history.go(-1);
                     </script>
<?php
                    }
                    else
                    {
                    
                         //verificando se o email ja existe

                         $consulta_email = mysqli_query($link, "SELECT * FROM $table_logins WHERE email = '$email'");
                         $quant_consulta_email = mysqli_num_rows($consulta_email);
                         
                         if($quant_consulta_email == 1)
                         {
?>
                              <script language="JavaScript">
                                   alert("EMAIL já existeste !!!");
                                   history.go(-1);
                              </script>
<?php
                         }
                         else
                         {
                         
                         //Nickname está disponível
                         
                     
                         //Cadastrando o Nickname - PASSO 10
                     
                         $inserir_logins = mysqli_query($link, "INSERT INTO $table_logins(nickname,email,senha,status) VALUE ('$nickname','$email','$senha',2)");
                         
                         //Verificando se o NICKNAME foi cadastrado com sucesso
                         
                         if($inserir_logins == 0)
                         {
                              //Erro ao cadastrar o NICKNAME
?>
                              <script language="JavaScript">
                                   alert("Erro ao cadastrar o NICKNAME. Entre em contato com o desenvolvedor do sistema.");
                                   history.go(-1);
                              </script>
<?php
                         }
                         else
                         {
                              //nickname cadastrado com sucesso
                              
                              //Verificando qual foi a chave primaria cadastrada para O NICKNAME - PASSO 11
                              
                              $consultar_logins = mysqli_query($link, "SELECT codigo FROM $table_logins WHERE nickname = '$nickname'");
                              
                              $vetor = mysqli_fetch_array($consultar_logins);
                              
                              //cadastrando o administrador
                              
                              $inserir_usuarios = mysqli_query($link, "INSERT INTO $table_usuarios (nome,sobrenome,nascimento,sexo,telefone,
                              celular,cpf,codigo_login,rua,numero,bairro,cep,cidade,estado,codigo_departamento) VALUES ('$nome','$sobrenome','$nasc','$sexo','$telefone','$celular','$cpf',$vetor[0],'$rua',$numero,'$bairro',$cep,'$cidade','$estado','$departamento')");
                              
                              //Verificando se o cliente foi cadastrado com sucesso
                              
                              if($inserir_usuarios == 0)
                              {
?>
                                   //Houve erro no cadastro
                                   <script language="JavaScript">
                                        alert("Erro ao cadastrar o USUÁRIO. Entre em contato com o desenvolvedor do sistema");
                                        history.go(-1);
                                   </script>
<?php
                              }
                              else
                              {
                                   //Cadastro Realizado com Sucesso
?>
                                   <script language="JavaScript">
                                        alert("Cadastro Realizado com Sucesso!");
                                        document.location.href="../tela_administrador.php";
                                   </script>
<?php
                                   }
                              }

                          }

                    }
                  }
              }
           }
           }
       }
?>


     









