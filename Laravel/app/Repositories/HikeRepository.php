<?php namespace App\Repositories;

use Illuminate\Database\Eloquent\Model;
use App\Hike;


class HikeRepository implements RepositoryInterface
{
    public function create(array $data)
    {
        $hike = new Hike();
        $hike->hitch_hotspot()->associate($data['hotspot']);
        $hike->destination = $data['destination'];
        $hike->numberOfHikers = $data['numberOfHikers'];
        $hike->moneySaved = $data['moneySaved'];
        $hike->distance = $data['distance'];
        $hike->starttime = $data['starttime'];
        $hike->preventedCarbonImpact = $data['preventedCarbonImpact'];
        $hike->endtime = $data['endtime'];
        $hike->save();
        return $hike;
    }

    public function update($id, array $data)
    {
        $hike = $this->find($id);
        $hike->update($data);
        $hike->save();
        return $hike;  
    }

    public function delete($id)
    {
        Hike::destroy($id);
    }

    public function find($id, $columns = array('*'))
    {
        return $this->findBy('id', $id, $columns)->first();
    }

    public function findBy($field, $value, $columns = array('*'))
    {
        return Hike::where($field, '=',$value)->orderBy('id', 'desc')->get($columns);
    }

    public function all($columns = array('*'))
    {
        return Hike::query()->orderBy('id', 'desc')->get();
    }

}