<!DOCTYPE html>
<html lang="cz">

<?php

$host_name = 'localhost';
$user_name = 'root';
$user_password = '';
$database_name = 'demo';

$create_connection = mysqli_connect($host_name, $user_name, $user_password, $database_name);
if ($create_connection) {
    //echo 'connection created';
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

    <style>

    </style>

</head>
<script src="assets/js/jquery-3.1.1.js"></script>

<body>



<header class="jumbotron text-center">
    <div class="container">
        <h1 style="color: white">AUTOSERVIS</h1>

        <h1 style="color: white">ALL <img src="assets/images/icon1.png"> STARS</h1>
    </div>
</header>

<div class="container">

    <h2>Nová objednávka</h2>
      <?php
    if (isset($_POST['save'])) {

        $firstname  = $_POST['firstname'];
        $lastname  = $_POST['lastname'];
        $phone   = $_POST['phone'];
        $date      = $_POST['date'];
        $time     = $_POST['time'];
        $type     = $_POST['type'];
        $notes     = $_POST['notes'];

        $dup = mysqli_query($create_connection, "select * from customers where date ='$date' and time ='$time'");
        if(mysqli_num_rows($dup)>1) {

            echo '<div class="alert alert-danger">  
                             Tento čas je už zabraný.   
                          </div> ';
        }
        else{


            //insert values to db
            $sql = "INSERT INTO customers (firstname, lastname, phone,	date, time, type, notes)
        VALUES ('$firstname','$lastname','$phone','$date','$time','$type','$notes')";
            $result = mysqli_query($create_connection, $sql);

            if ($result) {
                echo '<div class="alert alert-success">  
                         Vše proběhlo v pořádku 
                      </div> ';


            } else {
                echo '<div class="alert alert-danger">  
                         Objednávka se neuložila   
                      </div> ';
            }


            mysqli_close($create_connection);
        }
    }

    if (isset($_POST['cancle'])) {

        header("location: index.php");
    }

    ?>

    <div class="body_section">
        <form method="POST" enctype="multipart/form-data" autocomplete="off">
            <div class="row">




                <div class="col-md-6">
                    <div class="form-group">
                        <label for="firstname">Jméno:</label>
                        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Jméno">
                        <p id="validation_status_name" style="color: red;">Zadejte pouze písmena!</p>
                    </div>



                </div>


                <div class="col-md-6">
                    <div class="form-group">
                        <label for="lastname">Příjmení:</label>
                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Příjmení">
                        <p id="validation_status_lastname" style="color: red;">Zadejte pouze písmena!</p>
                    </div>



                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="date">Datum:</label>
                        <input type="date" class="form-control" id="date" name="date"  placeholder="Datum">

                    </div>



                </div>


                <div class="col-md-6">
                    <div class="form-group">
                        <label for="time">Čas:</label>
                        <select type="time" class="form-control" id="time" name="time" placeholder="Čas">
                            <option>08:00</option>
                            <option>09:00</option>
                            <option>10:00</option>
                            <option>11:00</option>
                            <option>12:00</option>
                            <option>13:00</option>
                            <option>14:00</option>
                            <option>15:00</option>
                            <option>16:00</option></select>
                    </div>



                </div>



                <div class="col-md-6">
                    <div class="form-group">
                        <label for="type">Typ auta:</label>
                        <input type="text" class="form-control" id="type" name="type" placeholder="Typ auta">
                    </div>



                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="phone">Telefon:</label>
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="777777777">
                        <p id="validation_status" style="color: red;">Zadejte pouze čísla. Max. délka tel. čísla je 9 čísel.</p>
                    </div>
                </div>

                <div class="col-md-1"></div>


            </div>



            <div class="row">

                <div class="col-md-12"></div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="notes">Poznámky:</label>
                        <textarea name="notes" id="notes" class="form-control" rows="4" placeholder="Poznámky"></textarea>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>





            <div class="row">
                <div class="col-md-12"></div>
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-6"><button type="submit" class="btn btn-primary btn-block" id="save" name="save">Uložit</button> </div>
                        <div class="col-md-6"><button type="submit" class="btn btn-danger btn-block" name="cancle">Zpět</button></div>

                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
        </form>
        <br>
    </div>


</div>




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
                <div class="col-md-6 footer-copyright">
                    &copy; Jan Sonnek
                </div>

            </div>
        </div>
    </div>
</footer>





<script>


    $(document).ready(function() {
        console.log('document is ready');
        var validate_name = /^[a-zA-ZěščřžýáíéúůĚŠČŘŽÝÁÍÉÚŮ]+$/;
        var validate_number = /^[0-9]{0,9}$/;

        var valid_name = false;
        var valid_quantity = false;

        $('#validation_status_name').hide();
        $('#validation_status_lastname').hide();
        $('#validation_status').hide();



        $('#firstname').keyup(function(event) {
            var firstname = $('#firstname').val();
            if (validate_name.test(firstname)) {
                $('#validation_status_name').hide();
                valid_name = true;
            } else {
                valid_name = false;
                event.preventDefault();
                $('#validation_status_name').show();
            }
        });


        $('#lastname').keyup(function(event) {
            var lastname = $('#lastname').val();
            if (validate_name.test(lastname)) {
                $('#validation_status_name').hide();
                valid_name = true;
            } else {
                valid_name = false;
                event.preventDefault();
                $('#validation_status_lastname').show();
            }
        });




        $('#phone').keyup(function(event) {
            var phone = $('#phone').val();
            if (validate_number.test(phone)) {
                $('#validation_status').hide();
                valid_quantity = true;
            } else {
                valid_quantity = false;
                event.preventDefault();
                $('#notes').addClass('empty_box');
                $('#validation_status').show();

            }
        });



        $('#save').click(function(event) {

            var firstname = $('#firstname').val();
            var lastname = $('#lastname').val();
            var date = $('#date').val();
            var time = $('#time').val();
            var phone = $('#phone').val();
            var type = $('#type').val();
            var notes = $('#notes').val();


            if (!valid_name) {
                event.preventDefault();
            }

            if (!valid_quantity) {
                event.preventDefault();
            }


            if (firstname == '') {
                event.preventDefault();
                $('#firstname').addClass('empty_box');
            } else {
                $('#firstname').removeClass('empty_box');
            }

            if (lastname == '') {
                event.preventDefault();
                $('#lastname').addClass('empty_box');
            } else {
                $('#lastname').removeClass('empty_box');
            }

            if (date == '') {
                event.preventDefault();
                $('#date').addClass('empty_box');
            } else {
                $('#date').removeClass('empty_box');
            }

            if (time == '') {
                event.preventDefault();
                $('#time').addClass('empty_box');
            } else {
                $('#time').removeClass('empty_box');
            }

            if (phone == '') {
                event.preventDefault();
                $('#phone').addClass('empty_box');
            } else {
                $('#phone').removeClass('empty_box');
            }

            if (type == '') {
                event.preventDefault();
                $('#type').addClass('empty_box');
            } else {
                $('#type').removeClass('empty_box');
            }

            if (notes == '') {
                event.preventDefault();
                $('#notes').addClass('empty_box');
            } else {
                $('#notes').removeClass('empty_box');
            }

        });


    });
</script>

<!-- Javascript -->
<script src="assets/js/jquery-3.1.1.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTables.bootstrap.min.js"></script>




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