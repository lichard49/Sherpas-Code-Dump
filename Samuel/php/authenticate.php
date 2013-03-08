<?php
require_once __DIR__ . '/db_connect.php';
$db = new DB_CONNECT();

$email = $_GET['email'];
$password = $_GET['password'];

$sql=mysql_query("SELECT ID, FirstName, LastName, Email FROM Person WHERE Email='$email' AND Password='$password';");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>