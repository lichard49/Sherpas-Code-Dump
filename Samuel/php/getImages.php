<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();

$itemID = $_GET['itemID'];

$sql=mysql_query("select ID, ItemID, Ordinal, Data FROM ItemImages WHERE ItemID = $itemID ORDER BY Ordinal ASC;");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>