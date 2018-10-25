<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class HitchHotspot extends Model
{
    protected $table = 'hitch_hotspots';
    public $incrementing = true;
    protected $fillable = [
        'lat',
        'long',
        'averageTime'
    ];

    public function hikes()
    {
        return $this->HasMany('App\Hike');
    }
    
    public function calculateAverageTime(){
        $total = 0;
        foreach($this->hikes as $hike){
            $total += $hike->endtime - $hike->starttime;
        }
        if(count($this->hikes)!= 0){
            return $total / count($this->hikes);
        }
        return 0;
    } 
}
