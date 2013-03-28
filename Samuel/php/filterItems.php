<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();
$conditions = "";
$and = " AND ";
if (isset($_GET['date']))
{
	$date = $_GET['date'];
	$conditions .= "DatePosted >= '$date'" . $and;
}
if (isset($_GET['typeID']))
{
	$typeID = $_GET['typeID'];
	$conditions .= "TypeID = $typeID" . $and;
}
if (isset($_GET['categoryID']))
{
	$categoryID = $_GET['categoryID'];
	$conditions .= "CategoryID = $categoryID" . $and;
}
$conditions = substr($conditions, 0, -1*(strlen($and))); 
$sql=mysql_query("select ID, Name, Description, TypeID, CategoryID, IsResolved, PosterID, DatePosted from Item WHERE "+$conditions+";");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>