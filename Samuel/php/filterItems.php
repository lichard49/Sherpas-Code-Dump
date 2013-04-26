<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();
$conditions = "";
$and = " AND ";
if (isset($_GET['date']))
{
	$date = $_GET['date'];
	$conditions .= "DatePosted >= '".$date."'" . $and;
}
if (isset($_GET['typeID']))
{
	$typeID = $_GET['typeID'];
	$conditions .= "TypeID = ".$typeID . $and;
}
if (isset($_GET['categoryID']))
{
	$categoryID = $_GET['categoryID'];
	$conditions .= "CategoryID = " . $categoryID . $and;
}
if (isset($_GET['name']))
{
	$name = $_GET['name'];
	$conditions .= "Name LIKE '%".$name."%'" . $and;
}
if (isset($_GET['description']))
{
	$description = $_GET['description'];
	$conditions .= "Description LIKE '%".$description."%'" . $and;
}
if (isset($_GET['city']))
{
	$city = $_GET['city'];
	$conditions .= "City LIKE '%".$city."%'" . $and;
}
if (isset($_GET['state']))
{
	$state = $_GET['state'];
	$conditions .= "State LIKE '%".$state."%'" . $and;
}
/*$conditions = substr($conditions, 0, -1*(strlen($and))); */
$conditions = $conditions . "1";
$sql=mysql_query("select ID, Name, Description, TypeID, CategoryID, IsResolved, Lat, Lon, City, State, PosterID, DatePosted from Item WHERE ".$conditions.";");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>