<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();

$date = $_GET['date'];

$sql=mysql_query("select ID, Name, Description, TypeID, CategoryID, IsResolved, PosterID, DatePosted from Item WHERE DatePosted >= '$date';");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>