<?php
$bounce_description = $_POST[‘bounce_description’]; // Descrição do bounce
$bounce_code = $_POST[‘bounce_code’]; // Codigo do erro do bounce
$message_sender = $_POST[‘sender’]; // Remetente
$message_to = $_POST[‘to’]; // Destinatário
$message_subject = $_POST[‘subject’];// Assunto da mensagem
$x_smtplw = $_POST[‘x-smtplw’];// cabeçalho

$myfile = fopen(“myfile.txt”, “a”) or die(“Unable to open file”);// Abertura do arquivo TXT
$date = date(‘m/d/Y H:i:s’, time());// Geração da data para o log

$txt = “[$date] bounce_description: $bounce_description\tbounce_code: $bounce_code\tsender: $message_sender\tto: $message_to\tsubject: $message_subject\tx_smtplw: $x_smtplw\n”; // Definindo uma variável com o texto formatado

fwrite($myfile, $txt); // Gravação do texto no arquivo txt criado

fclose($myfile); // Fechamento do arquivo
?>