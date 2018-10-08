<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Hike extends Model
{
    protected $fillable = [
        'latFrom',
        'longFrom',
        'latTo',
        'longTo',
        'destination',
        'numberOfHikers',
        'moneySaved',
        'distance',
        'preventedCarbonImpact',
    ];

    public function hitcher()
    {
        return $this->belongsToMany('App\Hitcher');
    }

    public function hotspot(){
        return $this->belongsTo('App\HitchHotspot');
    }
}
