<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Carbon\Carbon;
use App\repositories\HitchHotspotRepository;
use App\repositories\HikeRepository;
use App\repositories\HereRepository;
use App\HitchhotSpot;
use App\Hike;

class HikeController extends Controller
{
    private $hitchHotspotRepository;
    private $hikeRepository;
    private $hereRepository;

    public function __construct(){
        $this->hitchHotspotRepository = new HitchHotspotRepository();
        $this->hikeRepository = new HikeRepository();
        $this->hereRepository = new HereRepository();
    }

    public function startHike(Request $request){
        $longitude = $request->longitude;
        $latitude = $request->latitude;
        $destination = $request->destination;
        $numberOfHikers = $request->numberOfHikers;
        $startTime = Carbon::now()->toDateTimeString();
        $hitchhotspot = $this->hitchHotspotRepository->findbyLatLong($latitude, $longitude);
        if(!$hitchhotspot){
            $hitchhotspot = $this->hitchHotspotRepository->create(['lat'=> $latitude, 'long' => $longitude]);
        }
        return $this->hikeRepository->create(['hotspot'=> $hitchhotspot,'destination' => $destination, 'numberOfHikers' => $numberOfHikers, 'moneySaved' => 0,'distance'=> 0, 'starttime'=> $startTime, 'endtime'=> null, 'preventedCarbonImpact' => 0]);
    }
    
    public function endHike(Request $request){
        $hike = $request->hike;
        $endTime = Carbon::now()->toDateTimeString();
        if($hike = $this->hikeRepository->update($hike['id'], ['endtime' => $endTime])){
            return strtotime($hike->endtime) - strtotime($hike->starttime);
        } 
    }
}