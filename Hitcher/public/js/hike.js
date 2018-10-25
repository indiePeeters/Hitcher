$("#btnStartHike").click(function () {
    startHike($("#inputDeparture").val(), $("#inputPeople").val());
});


function startHike(departureCity, numberOfHikers) {
    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(function(position) {       
            $.ajaxSetup({
                headers: {
                'X-CSRF-TOKEN': $('meta[name="_token"]').attr('content')
                }
            });
            
            $.ajax({
                url: "api/hike",
                type: "POST",
                data: { latitude: position.coords.latitude, longitude: position.coords.longitude, destination: departureCity, numberOfHikers: numberOfHikers },
                success: function (result) {
                    console.log(result);
                    setInterval(setTime, 1000);
                    document.getElementById("btnStartHike").disabled = true; 
                },
                error: function (request, error) {
                    console.log(arguments);
                    alert(" Can't do because: " + error);
                }
            });
        });
    } else {
        console.log("User Location is not available");
    }
}

var minutesLabel = document.getElementById("minutes");
var secondsLabel = document.getElementById("seconds");
var hoursLabel = document.getElementById("hours");
var totalSeconds = 0;

function setTime() {
  ++totalSeconds;
  secondsLabel.innerHTML = pad(totalSeconds % 60);
  minutesLabel.innerHTML = pad(parseInt(totalSeconds / 60));
  hoursLabel.innerHTML = pad(Math.round(parseInt(totalSeconds / 60 / 60)));
}

function pad(val) {
  var valString = val + "";
  if (valString.length < 2) {
    return "0" + valString;
  } else {
    return valString;
  }
}
