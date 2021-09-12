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
		 $partnumber = $_POST['c_partnumber'];
		 
		 if(empty($partnumber))
		 {
?>
			<script>
				alert("Campo Part Number vazio!!!");
				history.go(-1);
			</script>
<?php
		 }
		 else
		 {
		 
		 require("../../connect.php");
		 
		 $consulta_partnumber = "SELECT * FROM $table_ferramentas WHERE part_number = '$partnumber'";
		 $resultado_consulta_partnumber = mysqli_query($link, $consulta_partnumber);
		 $quant_partnumber = mysqli_num_rows($resultado_consulta_partnumber);
		 
		 if($quant_partnumber == 0)
		 {
?>
			<script>
				alert("Part Number não encontrado em nosso banco de dados!!!");
				history.go(-1);
			</script>
<?php
		 }
		 else
		 {
?>
          <html>
               <head>
                    <meta charset="utf-8">
					<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
                    <title>Visualizar ferramenta</title>
					<link rel="stylesheet" href="../css/bootstrap.css">
					<link rel="stylesheet" href="../fonts/ionicons.min.css">
					<link rel="stylesheet" href="../css/styles.min.css">
<style>
#myImg {
  border-radius: 5px;
  cursor: pointer;
  transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
}

/* Caption of Modal Image */
#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}

/* Add Animation */
.modal-content, #caption {  
  -webkit-animation-name: zoom;
  -webkit-animation-duration: 0.6s;
  animation-name: zoom;
  animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
  from {-webkit-transform:scale(0)} 
  to {-webkit-transform:scale(1)}
}

@keyframes zoom {
  from {transform:scale(0)} 
  to {transform:scale(1)}
}

/* The Close Button */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}
</style>
               </head>
          <body>
			   <center><h1> Visualizar Ferramenta</h1></center>
               <table border="1" align="center" class="table table-bordered table-info">
               <tr>
                    <td align="center"><font color="black">NOME</td>
					<td align="center"><font color="black">PART NUMBER</td>
					<td align="center"><font color="black">QUANTIDADE</td>
					<td align="center"><font color="black">DESCRIÇÃO</td>
					<td align="center"><font color="black">DEPARTAMENTO</td>
					<td align="center"><font color="black">IMAGEM</td>
               </tr>
<?php
                    //Realizando consultas necessarioas
						 for($i = 1;$i<=$quant_partnumber;$i++)
						 {
                         $vetor_ferramentas = mysqli_fetch_array($resultado_consulta_partnumber);
                         $codigo_departamento = $vetor_ferramentas['codigo_departamento'];

                         $consultar_departamento = mysqli_query($link, "SELECT * FROM $table_departamentos WHERE codigo = '$codigo_departamento'");
                         $vetor_departamento = mysqli_fetch_array($consultar_departamento);
                         $nome_departamento = $vetor_departamento['nome'];
						 $imagem = $vetor_ferramentas['imagem'];
?>
               <tr>
                    <td align="center"><font color="black"><?php print($vetor_ferramentas['nome']);?></td>
					<td align="center"><font color="black"><?php print($vetor_ferramentas['part_number']);?></td>
					<td align="center"><font color="black"><?php print($vetor_ferramentas['quantidade']);?></td>
					<td align="center"><font color="black"><?php print($vetor_ferramentas['descricao']);?></td>
					<td align="center"><font color="black"><?php print($nome_departamento);?></td>
					<td align="center"><font color="black"><img id="myImg<?php print($i);?>" src="../../imagens/<?php print($imagem);?>" width="150" height="150"></td>
               </tr>
<?php
						}
?>
<!-- The Modal -->
<div id="myModal<?php print($i);?>" class="modal">
  <span class="close">&times;</span>
  <img class="modal-content" id="img<?php print($i);?>">
  <div id="caption"></div>
</div>
<?php
for($e=1;$e<=$i;$e++)
{
?>
<script>
// Get the modal
var texto1 = "myModal";
var texto2 = "<?php echo $e ?>";
var textocompleto = texto1.concat(texto2);
var modal = document.getElementById(textocompleto);

// Get the image and insert it inside the modal - use its "alt" text as a caption
var textomyimg = "myImg";
var textomyimg2 = "<?php echo $e ?>";
var textomyimgcomp = textomyimg.concat(textomyimg2);
var img = document.getElementById(textomyimgcomp);

var textoimg = "img";
var textoimg2 = "<?php echo $e ?>";
var textoimgcomp = textoimg.concat(textoimg2);
var modalImg = document.getElementById(textoimgcomp);
var captionText = document.getElementById("caption");
img.onclick = function(){
  modal.style.display = "block";
  modalImg.src = this.src;
  captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
  modal.style.display = "none";
}
</script>
<?php
}
?>
               </table><br>
               <center><a href="consultar_ferramentas.php"><input type="button" name="botao" value="VOLTAR"></a></center>
<?php
		 }
     }
	} 
?>

