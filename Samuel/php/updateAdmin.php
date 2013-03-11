<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();

$email = $_POST['email'];
$admin = $_POST['isAdmin'];

$result=mysql_query("UPDATE Person SET IsAdmin='$admin' WHERE Email='$email';");
if($result)
{
	$output["success"]=1;
}
else
{
	$output["success"]=0;
}
print(json_encode($output));
mysql_close();
?>