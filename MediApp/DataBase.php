<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function gplogIn($table, $gp_email, $gp_pwd)
    {
        $username = $this->prepareData($gp_email);
        $password = $this->prepareData($gp_pwd);
        $this->sql = "select * from " . $table . " where gp_email = '" . $gp_email . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['gp_email'];
            $dbpassword = $row['gp_pwd'];
            if ($dbusername == $gp_email && password_verify($gp_pwd, $dbpassword)) {
                $gplogin = true;
            } else $gplogin = false;
        } else $gplogin = false;

        return $gplogin;
    }

    function gpsignUp($table, $gpfname, $gplname, $gptel, $gp_email, $gpuid, $gp_pwd)
    {
        $gpfname = $this->prepareData($gpfname);
        $gplname = $this->prepareData($gplname);
        $gptel = $this->prepareData($gptel);
        $gp_email = $this->prepareData($gp_email);
        $gpuid = $this->prepareData($gpuid);
        $gp_pwd = $this->prepareData($gp_pwd);
        $gp_pwd = password_hash($gp_pwd, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (gpfname, gplname, gptel, gp_email, gpuid, gp_pwd) VALUES ('" . $gpfname . "','" . $gplname . "','" . $gptel . "','" . $gp_email . "','" . $gpuid . "','" . $gp_pwd . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }


    function insurancesignUp($table, $Name)
    {
        $Name = $this->prepareData($Name);
        $this->sql =
            "INSERT INTO " . $table . " (Name) VALUES ('" . $Name .  "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }




    function patientlogIn($table, $email, $pat_pwd)
    {
        $email = $this->prepareData($email);
        $pat_pwd = $this->prepareData($pat_pwd);
        $this->sql = "select * from " . $table . " where email = '" . $email . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['email'];
            $dbpassword = $row['pat_pwd'];
            if ($dbusername == $email && password_verify($pat_pwd, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function patientsignUp($table, $Fname, $Lname, $tel, $email, $age, $al_flag, $hd_flag, $dia_flag, $al_risk, $hd_risk, $dia_risk, $pat_pwd, $gpno, $insno, $ref)
    {
        $Fname = $this->prepareData($Fname);
        $Lname = $this->prepareData($Lname);
        $email = $this->prepareData($email);
        $age = $this->prepareData($age);
        $tel = $this->prepareData($tel);
        $al_flag = $this->prepareData($al_flag);
        $hd_flag = $this->prepareData($hd_flag);
        $dia_flag = $this->prepareData($dia_flag);
        $al_risk = $this->prepareData($al_risk);
        $hd_risk = $this->prepareData($hd_risk);
        $dia_risk = $this->prepareData($dia_risk);
        $pat_pwd = $this->prepareData($pat_pwd);
        $gpno = $this->prepareData($gpno);
        $insno = $this->prepareData($insno);
        $ref = $this->prepareData($ref);
        $pat_pwd = password_hash($pat_pwd, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (Fname, Lname, age, tel, email, al_flag, hd_flag, dia_flag, al_risk, hd_risk, dia_risk, pat_pwd, gpno, insno, ref) VALUES ('" . $Fname . "','" . $Lname . "','" . $email . "','". $age . "','" . $tel . "','" . $al_flag . "','"   . $hd_flag . "','" .$dia_flag . "','" . $al_risk . "','"   . $hd_risk . "','" .$dia_risk . "','" . $pat_pwd ."','" . $gpno ."','" . $insno . "','" . $ref . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}



?>
