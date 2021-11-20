<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['Fname']) && isset($_POST['Lname']) && isset($_POST['email']) && isset($_POST['age']) && isset($_POST['tel']) && isset($_POST['al_flag']) && isset($_POST['hd_flag']) && isset($_POST['dia_flag']) && isset($_POST['al_risk']) && isset($_POST['hd_risk']) && isset($_POST['dia_risk']) && isset($_POST['pat_pwd'])&& isset($_POST['gpno'])&& isset($_POST['insno'])&& isset($_POST['ref'])) {
    if ($db->dbConnect()) {
        if ($db->patientsignUp("patient", $_POST['Fname'], $_POST['Lname'], $_POST['email'], $_POST['age'], $_POST['tel'], $_POST['al_flag'], $_POST['hd_flag'], $_POST['dia_flag'], $_POST['al_risk'], $_POST['hd_risk'], $_POST['dia_risk'], $_POST['pat_pwd'], $_POST['gpno'], $_POST['insno'], $_POST['ref'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>