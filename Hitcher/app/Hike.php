<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Hike extends Model
{
    protected $table = 'hikes';
    public $incrementing = true;
    protected $fillable = [
        'destination',
        'numberOfHikers',
        'moneySaved',
        'distance',
        'starttime',
        'endtime',
        'preventedCarbonImpact',
    ];

    public function hitcher()
    {
        return $this->belongsToMany('App\Hitcher');
    }

    public function hitch_hotspot(){
        return $this->belongsTo('App\HitchHotspot');
    }

    public function calculatePreventedCarbonImpact(){
        $this->preventedCarbonImpact = $this->distance * 0.18;
    }

    public function calculateMoneySaved(){
        return null;
    }

    public function calculateDistance(){
        return null;       
    }
}
