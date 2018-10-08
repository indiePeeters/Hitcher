<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class HitchHotspot extends Model
{
    protected $fillable = [
        'lat',
        'long',
        'averageTime'
    ];

    public function hikes()
    {
        return $this->belongsToMany('App\Hike');
    }
}
