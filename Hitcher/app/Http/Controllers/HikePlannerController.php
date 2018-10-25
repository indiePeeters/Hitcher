<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\repositories\HitchHotspotRepository;
use App\repositories\HereRepository;
use App\HitchhotSpot;

class HikePlannerController extends Controller
{
    private $hitchHotspotRepository;
    private $hereRepository;

    public function __construct(){
        $this->hitchHotspotRepository = new HitchHotspotRepository();
        $this->hereRepository = new HereRepository();
    }

    public function calculateRouteMain($departureLocation, $destinationLocation){
        $departLocation = (json_decode($this->hereRepository->getLocationDetails($departureLocation)))->response->view[0]->result[0]->location->displayPosition;
        //find closeby hotspots
        $destinationLocation = (json_decode($this->hereRepository->getLocationDetails($destinationLocation)))->response->view[0]->result[0]->location->displayPosition;
        return $this->hereRepository->calculateRoute($departLocation->latitude, $departLocation->longitude, $destinationLocation->latitude, $destinationLocation->longitude,'car');
    }

    public function calculateRouteToHotspot(Request $request){
        $hitchhotspot = $hitchHotspotRepository->find($request->hitchhotspotid);
        $currentLat = $request->currentLat;
        $currentLong = $request->currentLong;

        if($hitchhotspot && $currentLat && $currentLong){
            return $this->hereRepository->calculateRoute($currentLat, $currentLong,$hitchhotspot->lat, $hitchhotspot->long, 'bicycle');
        }       
    }   
}