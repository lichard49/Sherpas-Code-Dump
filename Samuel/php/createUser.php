<?php
require_once __DIR__ . '/db_connect.php';
$db = new DB_CONNECT();

$firstName = $_POST['firstName'];
$lastName = $_POST['lastName'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$password = $_POST['password'];

$sql=mysql_query("INSERT INTO Person(FirstName, LastName, Email, Phone, Password) VALUES ('$firstName', '$lastName','$email', $phone, '$password');");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>