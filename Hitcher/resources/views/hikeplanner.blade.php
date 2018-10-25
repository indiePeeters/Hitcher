<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="_token" content="{{ csrf_token() }}"/>

    <title>Hitcher</title>
    <link rel="shortcut icon" type="image/x-icon" href="{{ asset('images/icon.png') }}" />

    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Our Custom CSS -->
    <link href="{{ asset('css/style.css') }}" rel="stylesheet">


    <!-- Scrollbar Custom CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>

</head>

<body>

    <div class="wrapper">
        <!-- Sidebar  -->
        <nav id="sidebar">
            <div id="dismiss">
                <i class="fas fa-arrow-left"></i>
            </div>

            <div class="sidebar-header">
                <h3>Hitcher</h3>
            </div>

            <ul class="list-unstyled components">
                <li class="active">
                    <a href="/hikeplanner">Hikeplanner</a>
                </li>
                <li>
                    <a href="/newhike">Start new Hike</a>
                </li>
                <li>
                    <a href="/recenthikes">Recent Hikes</a>
                </li>
            </ul>
        </nav>

        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container-fluid">
                <button type="button" id="sidebarCollapse" class="btn">
                    <i class="fas fa-bars"></i>
                </button>
                <img id="navImage"src="{{ asset('images/icon.png') }}"/>
            </div>
        </nav>

        <!-- Page Content  -->
        <div id="content">
            <p>Where will your next adventure be?</p>
            <div id="routeMainInput">
                <div>
                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="40" height="80" viewBox="0 0 40 80">
                        <image id="Capture" x="-20" width="80" height="80" xlink:href="data:img/png;base64,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"/>
                    </svg>
                </div>
                <div>
                    <input id="InputDeparture" type="text">
                    <input id="InputDestination" type="text">
                </div>
            </div>
            <button id="btnCalculateRoute" type="button" class="btn btn-own">Calculate Route</button> 
            <div id="map" style="max-width:100%; height:256px; background:grey" ></div>
        </div>
    </div>

    <div class="overlay"></div>

    <!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    <!-- jQuery Custom Scroller CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#sidebar").mCustomScrollbar({
                theme: "minimal"
            });

            $('#dismiss, .overlay').on('click', function () {
                $('#sidebar').removeClass('active');
                $('.overlay').removeClass('active');
            });

            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').addClass('active');
                $('.overlay').addClass('active');
                $('.collapse.in').toggleClass('in');
                $('a[aria-expanded=true]').attr('aria-expanded', 'false');
            });
        });
    </script>
    <script type="text/javascript" src="https://js.api.here.com/v3/3.0/mapsjs-core.js"></script>
    <script type="text/javascript" src="https://js.api.here.com/v3/3.0/mapsjs-service.js"></script>
    <script type="text/javascript" src="https://js.api.here.com/v3/3.0/mapsjs-ui.js"></script>
    <script type="text/javascript" src="https://js.api.here.com/v3/3.0/mapsjs-mapevents.js"></script>
    <script src="{{ asset('js/map.js') }}"></script>
    <script src="{{ asset('js/route.js') }}"></script>
</body>

</html>