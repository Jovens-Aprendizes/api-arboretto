<?php
$message_sender = $_POST[‘sender’]; // Remetente
$message_to = $_POST[‘to’]; // Destinatário
$message_subject = $_POST[‘subject’]; // Assunto da mensagem
$x_smtplw = $_POST[‘x-smtplw’]; // cabeçalho

$opened_at = $_POST[‘opened_at’]; // Data de abertura

$myfile = fopen(“myfile.txt”, “a”) or die(“Unable to open file”); // Abertura do arquivo TXT
$date = date(‘m/d/Y H:i:s’, time()); // Geração da data para o log

$txt = “sender: $message_sender\tto: $message_to\tsubject: $message_subject\tx_smtplw: $x_smtplw\topened at: $opened_at: $openedat\n”; // Definindo uma variável com o texto formatado

fwrite($myfile, $txt); // Gravação do texto no arquivo txt criado

fclose($myfile); // Fechamento do arquivo
?>