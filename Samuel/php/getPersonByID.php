<?php
require_once('D:\Hosting\6939230\html\sherpas\db_connect.php');
$db = new DB_CONNECT();

$id = $_GET['ID'];

$sql=mysql_query("SELECT ID, FirstName, LastName, Email, Phone FROM Person WHERE ID='$id';");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>