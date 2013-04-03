<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();
$id = $_POST['ID'];
$city = $_POST['city'];
$state = $_POST['state'];

$result=mysql_query("UPDATE Item SET City = '$city', State = '$state' WHERE ID = $id;");
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