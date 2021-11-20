<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['gp_email']) && isset($_POST['gp_pwd'])) {
    if ($db->dbConnect()) {
        if ($db->gplogIn("gp", $_POST['gp_email'], $_POST['gp_pwd'])) {
            echo "Login Success";
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>

