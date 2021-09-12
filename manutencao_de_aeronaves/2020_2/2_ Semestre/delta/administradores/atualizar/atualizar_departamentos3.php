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
          //Recebendo Variaveis
          
          $nome = $_POST["c_nome"];
		  $codigo_departamento = $_POST['c_codigo_departamento'];
          
          //Verificando se os campos foram preenchidos
          
          if(empty($nome))
          {
?>
               <script language="JavaScript">
                    alert("Campo NOME Vazio!!!");
                    history.go(-1);
               </script>
<?php
          }
          else
          {
               //Consultar tabela funcoes para encontrar o nome do cargo
               
               require("../../connect.php");
               
               $consulta_nome_departamentos = mysqli_query($link, "SELECT * FROM $table_departamentos WHERE nome = '$nome' AND codigo != '$codigo_departamento'");
               $quant_nome_departamentos = mysqli_num_rows($consulta_nome_departamentos);
               //Elaborando resultado da pesquisa
               
               if($quant_nome_departamentos == 1)
               {
?>
                    <script language="JavaScript">
                         alert("Departamento Já Existente!!!");
                         history.go(-1);
                    </script>
<?php
               }
               else
               {
                    //Inserindo os dados na tabela
                    
                    $resultado_inserir_departamentos = mysqli_query($link, "UPDATE $table_departamentos SET nome='$nome' WHERE codigo = '$codigo_departamento'");
                    
                    //Verificando se os dados foram inseridos com sucesso
                    
                    if($resultado_inserir_departamentos == 0)
                    {
?>
                         <script language="JavaScript">
                              alert("Houve Problema No Cadastramento do Departamento!!!");
                              history.go(-1);
                         </script>
<?php
                    }
                    else
                    {
                         //Cargo cadastrado com sucesso
                         
?>
                         <script language="JavaScript">
                              alert("Atualização Realizada Com Sucesso!!!");
                              history.go(-1);
                         </script>
<?php
                    }
               }
               
          }
        }
?>
