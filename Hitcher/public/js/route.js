

$("input").change(function () {
  autocomplete(this.value);
});

$("#btnCalculateRoute").click(function () {
  calculateRoute($("#InputDeparture").val(), $("#InputDestination").val());
});

function autocomplete(input) {
  var url = "/hikeplanner/suggestions";
  var type = "GET";
  //finish autocomplete function
}

function calculateRoute(departureCity, destinationCity) {
  $.ajaxSetup({
    headers: {
      'X-CSRF-TOKEN': $('meta[name="_token"]').attr('content')
    }
  });

  $.ajax({
    url: "api/hikeplanner/calculateRoute/main/" + departureCity + "/" + destinationCity,
    type: "GET",
    contentType: false,
    processData: false,
    success: function (result) {
      var route = (JSON.parse(result)).response.route[0];
      var lineString = new H.geo.LineString(),
        routeShape = route.shape,
        polyline;

      routeShape.forEach(function (point) {
        var parts = point.split(',');
        lineString.pushLatLngAlt(parts[0], parts[1]);
      });

      polyline = new H.map.Polyline(lineString, {
        style: {
          lineWidth: 4,
          strokeColor: 'rgba(0, 128, 255, 0.7)'
        }
      });
      // Add the polyline to the map
      clearMap();
      addToMap(polyline);
      // And zoom to its bounding rectangle
      map.setViewBounds(polyline.getBounds(), true);
    },
    error: function (request, error) {
      console.log(arguments);
      alert(" Can't do because: " + error);
    }
  });
}