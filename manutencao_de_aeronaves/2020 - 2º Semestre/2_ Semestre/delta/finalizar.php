<?php
     //Mantendo a sessão
     
     session_start();
     
     //Recuperando as variaveis da sessão

     $system_control = $_SESSION["system_control"];
     $status = $_SESSION["status"];
     
     //Verificando se o usuário realizou o login

     if(($system_control == 1))
     {

               //Finalizando o sistema
               
               //Destruindo a sessão
               
               session_destroy();
               
               //Mensagem para o Usuário
?>
               <script>
                    alert("Obrigado por utilizar o sistema!");
                    document.location.href="index.php";
               </script>
<?php

     }
     else
     {
          //Acesso Inválido
          
          //Finalizando a sessão
          
          session_destroy();
          
          //Mensagem para o Usuário
?>
          <script>
                  alert("Acesso Inválido!");
                  history.go(-1);
          </script>
<?php
     }
?>
