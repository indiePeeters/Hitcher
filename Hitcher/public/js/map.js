var mapObjects = [];

var mapContainer = document.getElementById('map');
var platform = new H.service.Platform({
    app_id: 'JxR4xyaSi8PDkpJlyxpG',
    app_code: 'zey0ms64CSnKn8gKYxEcNg',
    useHTTPS: true
  });

var pixelRatio = window.devicePixelRatio || 1;
var defaultLayers = platform.createDefaultLayers({
  tileSize: pixelRatio === 1 ? 256 : 512,
  ppi: pixelRatio === 1 ? undefined : 320
});

var map = new H.Map(mapContainer,
    defaultLayers.normal.map,{
    center: {lat:52.5160, lng:13.3779},
    zoom: 13,
    pixelRatio: pixelRatio
  });

var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

var ui = H.ui.UI.createDefault(map, defaultLayers);

function addToMap(object){
  mapObjects.push(object);
  map.addObject(object);
}
function clearMap(){
  for(i=0;i<mapObjects.length;i++){
    map.removeObject(mapObjects[i]);
  }
}

