<? 
    //change this to your email. 
    $to = "ojas25@gmail.com"; 
    $from = "ojas25@gmail.com"; 
    $subject = "Hello! This is HTML email"; 

    //begin of HTML message 
    $message = <<<EOF 
<html> 
  <body bgcolor="#DCEEFC"> 
    <center> 
        <b>Looool!!! I am reciving HTML email......</b> <br> 
        <font color="red"></font> <br> 
       
    </center> 
      <br><br>*** Now you Can send HTML Email <br> 
  </body> 
</html> 
EOF; 
   //end of message 
    $headers  = "From: $from\r\n"; 
    $headers .= "Content-type: text/html\r\n"; 

    //options to send to cc+bcc 
    //$headers .= "Cc: [email]maa@p-i-s.cXom[/email]"; 
    //$headers .= "Bcc: [email]email@maaking.cXom[/email]"; 
     
    // now lets send the email. 
    mail($to, $subject, $message, $headers); 

    echo "Message has been sent....!"; 
?>