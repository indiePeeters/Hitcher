<?php namespace App\Repositories;

use Illuminate\Database\Eloquent\Model;
use App\HitchHotspot;


class HitchHotspotRepository implements RepositoryInterface
{
    public function create(array $data)
    {
        $hitchHotspot = new HitchHotspot();
        $hitchHotspot->lat = $data['lat'];
        $hitchHotspot->long = $data['long'];
        $hitchHotspot->averageTime = $hitchHotspot->calculateAverageTime();
        $hitchHotspot->save();
        return $hitchHotspot;
    }

    public function update($id, array $data)
    {
        $hitchHotspot = $this->find($id);
        $hitchHotspot->update($data);
        $hitchHotspot->save();
        return $hitchHotspot;  
    }

    public function delete($id)
    {
        HitchHotspot::destroy($id);
    }

    public function find($id, $columns = array('*'))
    {
        return $this->findBy('id', $id, $columns)->first();
    }

    public function findBy($field, $value, $columns = array('*'))
    {
        return HitchHotspot::where($field, '=',$value)->orderBy('id', 'desc')->get($columns);
    }

    public function all($columns = array('*'))
    {
        return HitchHotspot::query()->orderBy('id', 'desc')->get();
    }


    public function findbyLatLong($lat, $long){
        return HitchHotspot::where('lat', $lat)->where('long', $long)->get()->first();
    }

    public function findNearby($lat, $long){

    }
}