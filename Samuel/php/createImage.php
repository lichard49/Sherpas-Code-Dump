<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();

$itemID = $_POST['itemID'];
$ordinal = $_POST['ordinal'];
$data = $_POST['data'];

$result=mysql_query("INSERT INTO ItemImages(ItemID, Ordinal, Data) VALUES ($itemID, $ordinal,'$data');");
if($result)
{
	$output["ID"]=mysql_insert_id();
	$output["success"]=1;
}
else
{
	$output["success"]=0;
}
print(json_encode($output));
mysql_close();
?>