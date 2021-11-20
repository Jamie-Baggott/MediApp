<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['Name'])) {
    if ($db->dbConnect()) {
        if ($db->insurancesignUp("insurance_company", $_POST['Name'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
