<?php
     // Arquivo de conexão do PHP com MySQL
     
     //Declaração das variaveis locais
     
     $hostname = "localhost"; //endereço de servidor da WEB
     $username = "root";
     $password = "";
     $databasename = "ferramentaria";
     $table_logins = "logins";
     $table_administradores = "administradores";
     $table_usuarios = "usuarios";
     $table_ferramentas = "ferramentas";
     $table_departamentos = "departamentos";
     $table_funcionarios = "funcionarios";
     $table_controle = "controle";

     //realizando a conexao com MySQL
     
     $link = mysqli_connect($hostname,$username,$password,$databasename);

     if (!$link)
     {
        echo "Error: Unable to connect to MySQL." . PHP_EOL;
        echo "Debugging error: " . mysqli_connect_errno() . PHP_EOL;
        echo "Debugging error: " . mysqli_connect_error() . PHP_EOL;
     exit;
     }
?>
