<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['gpfname']) && isset($_POST['gplname']) && isset($_POST['gptel']) && isset($_POST['gp_email']) && isset($_POST['gpuid']) && isset($_POST['gp_pwd'])) {
    if ($db->dbConnect()) {
        if ($db->gpsignUp("gp", $_POST['gpfname'], $_POST['gplname'], $_POST['gptel'], $_POST['gp_email'], $_POST['gpuid'], $_POST['gp_pwd'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
