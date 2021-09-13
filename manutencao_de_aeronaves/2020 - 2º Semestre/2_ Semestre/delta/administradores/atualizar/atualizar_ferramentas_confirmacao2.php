<?php
     //mantendo uma sessão
//header("Content-type: text/html; charset=ISO-8895-1");
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
          
          $descricao = $_POST["c_descricao"];
          $nome = $_POST["c_nome"];
          $partnumber1 = $_POST["c_partnumber1"];
		  $partnumber2 = $_POST["c_partnumber2"];
		  $partnumber = $partnumber1.$partnumber2;
          $departamento = $_POST["c_departamento"];
          $quantidade = $_POST["c_quantidade"];
		  $codigo_ferramenta = $_POST["c_codigo_ferramenta"];
		  
		  $quant_partnumber1 = strlen($partnumber1);
 
               if(empty($descricao))
               {
?>
                    <script language="JavaScript">
                         alert("Descricao Vazio!!!");
                         history.go(-1);
                    </script>
<?php
               }
               else if(empty($nome))
               {
?>
                    <script language="JavaScript">
                         alert("Nome Vazio!!!");
                         history.go(-1);
                    </script>
<?php
               }
               else if(empty($partnumber1))
               {
?>
                    <script language="JavaScript">
                            alert("Part Number Vazio!!!");
                            history.go(-1);
                    </script>
<?php
               }
			   else if($quant_partnumber1 != 3)
               {
?>
                    <script language="JavaScript">
                            alert("Part Number fora do padrão, primeira entrada deve ter 3 letras!!!");
                            history.go(-1);
                    </script>
<?php
               }
			   else if(empty($partnumber2))
               {
?>
                    <script language="JavaScript">
                            alert("Part Number Vazio!!!");
                            history.go(-1);
                    </script>
<?php
               }
               else if(empty($departamento))
               {
?>
                    <script language="JavaScript">
                         alert("Departamento Vazio!!!");
                         history.go(-1);
                    </script>
<?php
               }
               else if($quantidade == "")
               {
?>
                    <script>
                            alert("Quantidade Vazio!!!");
                            history.go(-1);
                    </script>
<?php
               }
               else
               {
                    //Conectando com o banco de dados
                    
                    require("../../connect.php");
                    
                         //Realizando consulta do partnumber
                         
                         $consulta_part_ferramentas = mysqli_query($link, "SELECT * FROM $table_ferramentas WHERE part_number = '$partnumber' AND codigo != '$codigo_ferramenta'");
                         $quant_part_ferramentas = mysqli_num_rows($consulta_part_ferramentas);
                         
                         //Elaborando resultado da consulta
                         
                         if($quant_part_ferramentas == 1)
                         {
?>
                              <script language="JavaScript">
                                   alert("Partnumber já registrado no Banco de Dados!!!");
                                   history.go(-1);
                              </script>
<?php
                         }
                         else
                         {
                                   //Inserindo dados necessarios na tabela
                                   
                                   $atualizar_ferramentas = mysqli_query($link, "UPDATE $table_ferramentas SET nome='$nome',descricao='$descricao',part_number='$partnumber',numeral='$partnumber2',codigo_departamento='$departamento',quantidade='$quantidade' WHERE codigo = '$codigo_ferramenta'");
                                   
                                   //Verificando se foi cadastrado com sucesso
                                   
                                   if($atualizar_ferramentas == 0)
                                   {
?>
                                        <script language="JavaScript">
                                             alert("Houve Erro No Cadastramento dos Dados!!!");
                                             history.go(-1);
                                        </script>
<?php
                                   }
                                   else
                                   {
                                   
?>
                                        <script language="JavaScript">
                                             alert("Cadastramento de FERRAMENTA Realizado Com Sucesso!!!")
                                             history.go(-2);
                                        </script>
<?php
                                   }

                                   
                              }
                         }
                    } 
?>
               

