<?php

$imagem = $_FILES["imagem"];
if(empty($imagem))
{
?>
  <script language="JavaScript">
          alert("Está vazio!!!");
          history.go(1);
  </script>
<?php
     //Dou prosseguimento ao cadastro dando nome da imagem = vazio
}
else
{
?>
  <script language="JavaScript">
          alert("Tem arquivo");
  </script>
<?php
           //Dou prosseguimento ao cadastro dando nome da imagem = partnumber da ferramenta
           // Array com as extensões permitidas
          $extensoes_permitidas = array('.jpg', '.gif', '.png');

          // Faz a verificação da extensão do arquivo enviado
          $extensao = strrchr($_FILES['imagem']['name'], '.');

          // Faz a validação do arquivo enviado
          if(in_array($extensao, $extensoes_permitidas) === false)
          {
               echo 'Por favor, envie arquivos com as seguintes extensões: jpg, gif ou png.';
          }
          else
          {
               //Extensão permitida. Arquivo recebido com sucesso.
               $dir = './imagens/'; //diretório para onde vai a imagem
               $new_name = time().$extensao;
               move_uploaded_file($_FILES['imagem']['tmp_name'], $dir.$new_name); //Fazer upload do arquivo

          }

}
?>


