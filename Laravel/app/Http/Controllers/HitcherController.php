<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Hitcher;
use App\Hike;
use App\HitchHotspot;

use App\repositories\HitcherRepository;
use App\repositories\HikeRepository;
use App\repositories\HitchHotspotRepository;


class HitcherController extends Controller
{
    private $hitcherRepository;
    private $hitchHotspotRepository;
    private $hikeRepository;

    public function __construct(HitchHotspot $hitchhotspot, Hike $hike, Hitcher $hitcher){
        $this->hitcherRepository = new HitcherRepository($hitcher);
        $this->hitchHotspotRepository = new HitchHotspotRepository($hitchhotspot);
        $this->hikeRepository = new HikeRepository($hike);
    }

    public function retrieveProfile($id){
        return $this->hitcherRepository->find($id);
    }

    public function retrieveStatics(){
        return $this->hitcherRepository->find($id);
    }



    public function calculateRoute(){
        //start by selecting number of best hotspots
    }

    public function calculateRouteToHotspot(){

    }
}