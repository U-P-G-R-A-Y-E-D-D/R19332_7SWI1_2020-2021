<!DOCTYPE html>
<html lang="cz">


<?php

$host_name = 'localhost';
$user_name = 'root';
$user_password = '';
$database_name = 'demo';

$create_connection = mysqli_connect($host_name, $user_name, $user_password, $database_name);
if ($create_connection) {
    //echo 'Navázání proběhlo.';

}
?>

<head>



    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style1.css">



    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/media-queries.css">




</head>
<script src="assets/js/jquery-3.1.1.js"></script>

<body>


<!-- Top content -->
<div class="top-content">

    <header class="jumbotron text-center">
        <div class="container">
            <h1 style="color: white">AUTOSERVIS</h1>

            <h1 style="color: white">ALL <img src="assets/images/icon1.png"> STARS</h1>
        </div>
    </header>

</div>
<!-- Section 1 -->
<div class="section-1-container section-container">
    <div class="container">
        <div class="row">
            <div class="col section-4 section-description wow fadeInLeftBig">
                <h2>Způsob objednání</h2>
                <p>Objednat se můžete telefonicky nebo online formou.</p>

                <table  class="table table-striped table-bordered" style="text-align: center;">
                    <thead>
                    <tr>

                        <th colspan="2" style="text-align: center">PROVOZNÍ DOBA</th>


                    </tr>
                    </thead>
                    <tbody  class="table table-striped table-bordered">
                    <tr>

                        <td>PO - PA</td>
                        <td>7:00 - 14:00</td>


                    </tr>
                    <tr>

                        <td>SO - NE</td>
                        <td>ZAVŘENO</td>


                    </tr>

                    <tbody>
                </table>

                <p><a class="btn btn-primary btn-lg" href="add.php" role="button">Nová objednávka</a></p>
            </div>
        </div>
    </div>
</div>








<div class="section-2-container section-container section-container-gray-bg">
    <div class="container">
        <div class="row">
            <h2>Seznam rezervací</h2>
            <h4 style="color: red">PRO STORNOVÁNÍ NEBO ÚPRAVU VAŠÍ OBJEDNÁVKY NÁS KONTAKTUJTE TELEFONICKY NEBO EMAILEM!!! </h4>
            <table id="list_of_product" class="table table-striped table-bordered" style="text-align: center;">
                <thead>
                <tr>
                    <td>#</td>

                    <td>Datum</td>
                    <td>Čas</td>

                </tr>
                </thead>
                <tbody id="list_of_product" class="table table-striped table-bordered">
                <?php
                $sql = "SELECT * FROM customers";
                $result_arr = mysqli_query($create_connection, $sql);
                $num = 1;
                if (mysqli_num_rows($result_arr) > 0) {
                    while ($row = mysqli_fetch_assoc($result_arr)) {

                        ?>

                        <tr>
                            <td><?php echo $num ?></td>

                            <td><?php echo $row['date'] ?></td>
                            <td><?php echo $row['time'] ?></td>
                        </tr>

                        <?php
                        $num = $num + 1;
                    }
                }
                ?>

                <script>
                    $(document).ready(function() {
                        $('#list_of_product').dataTable();
                    })
                </script>

                <tbody>
            </table>
        </div>
    </div>
</div>



<!-- Footer -->
<footer>
    <div class="footer-top">
        <div class="container">
            <div class="row">
                <div class="col-md-3 footer-about wow fadeInUp">
                    <img class="logo-footer" src="assets/images/icon1.png" alt="logo-footer" data-at2x="assets/img/logo.png">
                    <p>
                        Opravujeme všechny typy a všechny značky.  Zaměřujeme se hlavně na auta, ale pokud si přejete opravit motorku nebo čtyřkolku, není to problém.
                    </p>
                </div>
                <div class="col-md-4 offset-md-1 footer-contact wow fadeInDown">
                    <h3>Kontakt</h3>
                    <p><i class="fas fa-map-marker-alt"></i> 30. dubna 22, 701 03 Ostrava</p>
                    <p><i class="fas fa-phone"></i> Telefon: +420 597 09 2100, +420 553 46 2100 </p>
                    <p><i class="fas fa-envelope"></i> Email: <a href="info.prf@osu.cz">info.prf@osu.cz</a></p>

                </div>
                <div class="col-md-4 footer-links wow fadeInUp">
                    <div class="row">
                        <div class="col">
                            <h3>Mapa</h3>
                        </div>
                    </div>
                    <div class="row">
                        <div class="flicker-img">
                            <iframe style="border:none" src="https://frame.mapy.cz/s/gopamujudu" width="500" height="333" frameborder="0"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="container">
            <div class="row">
                <div class="col-md-6 footer-copyright" style="text-align: center">
                    &copy; Jan Sonnek
                </div>

            </div>
        </div>
    </div>
</footer>




<script src="assets/js/jquery-3.1.1.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTables.bootstrap.min.js"></script>



<!-- Javascript -->
<script src="assets/js/jquery-3.2.1.min.js"></script>
<script src="assets/js/jquery-migrate-3.0.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="assets/js/jquery.backstretch.min.js"></script>
<script src="assets/js/wow.min.js"></script>
<script src="assets/js/retina-1.1.0.min.js"></script>
<script src="assets/js/scripts.js"></script>


</body>

</html>