<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();
$name = $_POST['name'];
$description = $_POST['description'];
$typeID = $_POST['typeID'];
$categoryID = $_POST['categoryID'];
$isResolved = $_POST['isResolved'];
$posterID = $_POST['posterID'];

$result=mysql_query("INSERT INTO Item(Name, Description, TypeID, CategoryID, IsResolved, PosterID, DatePosted) VALUES ('$name', '$description',$typeID, $categoryID, $isResolved, $posterID, CURDATE());");
if($result)
{
/*	$row=mysql_query("SELECT ID FROM Person WHERE Email = '$email';");
	while($row=mysql_fetch_assoc($sql)) */
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