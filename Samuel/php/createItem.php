<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();
String name, String description, int typeID, int categoryID, boolean isResolved, long posterID, int reward, double lat, double lon
$name = $_POST['name'];
$description = $_POST['description'];
$typeID = $_POST['typeID'];
$categoryID = $_POST['categoryID'];
$isResolved = $_POST['isResolved'];
$posterID = $_POST['posterID'];
$reward = $_POST['reward'];
$lat = $_POST['lat'];
$lon = $_POST['lon'];

$result=mysql_query("INSERT INTO Item(FirstName, LastName, Email, Phone, Password, IsAdmin) VALUES ('$firstName', '$lastName','$email', $phone, '$password', '$admin');");
if($result)
{
	$row=mysql_query("SELECT ID FROM Person WHERE Email = '$email';");
	while($row=mysql_fetch_assoc($sql))
	$output[]=$row;
	$output["success"]=1;
}
else
{
	$output["success"]=0;
}
print(json_encode($output));
mysql_close();
?>