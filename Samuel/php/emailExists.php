<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();

$email = $_GET['email'];

$sql=mysql_query("select exists(select Email from Person where Email='$email') as exist;");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>