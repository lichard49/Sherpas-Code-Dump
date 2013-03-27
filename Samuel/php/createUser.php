<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();

$firstName = $_POST['firstName'];
$lastName = $_POST['lastName'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$password = $_POST['password'];
$admin = $_POST['isAdmin'];

$result=mysql_query("INSERT INTO Person(FirstName, LastName, Email, Phone, Password, IsAdmin) VALUES ('$firstName', '$lastName','$email', $phone, '$password', '$admin');");
if($result)
{
/*	$row=mysql_query("SELECT ID FROM Person WHERE Email = '$email';");
	while($row=mysql_fetch_assoc($sql)) */
	$output["ID"]=$mysql_insert_id();
	$output["success"]=1;
}
else
{
	$output["success"]=0;
}
print(json_encode($output));
mysql_close();
?>