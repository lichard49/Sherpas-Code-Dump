<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();

$id = $_POST['ID'];

$result=mysql_query("DELETE FROM Person WHERE ID='$id';");
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