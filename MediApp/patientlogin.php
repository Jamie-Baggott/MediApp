<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email']) && isset($_POST['pat_pwd'])) {
    if ($db->dbConnect()) {
        if ($db->patientlogIn("patient", $_POST['email'], $_POST['pat_pwd'])) {
            echo "Login Success";
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
