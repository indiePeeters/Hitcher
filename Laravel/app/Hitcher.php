<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Hitcher extends Model
{
    protected $table = 'hitchers';
    public $incrementing = true;
    protected $fillable = [
        'name',
        'password',
        'age',
        'profession',
        'photo',
        'countriesVisited',
        'totalHikes',
        'totalDistance',
        'preventedCarbonImpact',
        'totalTime',
        'totalSavedMoney'
    ];

    public function hikes()
    {
        return $this->belongsToMany('App\Hike');
    }

    public function newHike(){
        
    }
}
