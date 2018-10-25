<?php namespace App\Repositories;

use GuzzleHttp;


class HereRepository
{

    public function getLocationDetails($location){
        $client = new GuzzleHttp\Client();
        $response = $client->request('GET', 'http://geocoder.api.here.com/6.2/geocode.json',['query' => [
            'app_id' => env("HERE_APP_ID"),
            'app_code' => env("HERE_APP_CODE"),
            'searchtext' => $location,
            'jsonattributes' => '1',
            'gen' => '9'
        ]]);
        return (string) $response->getBody();
    }

    public function getLocationSugestions(Request $request){
        $client = new GuzzleHttp\Client();
        $response = $client->request('GET', 'http://autocomplete.geocoder.api.here.com/6.2/suggest.json',['query' => [
            'app_id' => env("HERE_APP_ID"),
            'app_code' => env("HERE_APP_CODE"),
            'query' => $request->input,
            'beginHighlight' => '<b>',
            'endHighlight' => '</b>'
        ]]);
        return (string) $response->getBody();
    }

    public function calculateRoute($currentLat, $currentLong, $nextLat, $nextLong, $mode){
        $client = new GuzzleHttp\Client();
        $response = $client->request('GET', 'https://route.api.here.com/routing/7.2/calculateroute.json',['query' => [
            'app_id' => env("HERE_APP_ID"),
            'app_code' => env("HERE_APP_CODE"),
            'mode' => 'fastest;'. $mode,
            'representation'=> 'display',
            'routeattributes' => 'waypoints,summary,shape,legs',
            'maneuverattributes'=> 'direction,action',
            'waypoint0' => 'geo!' . $currentLat . ',' . $currentLong,
            'waypoint1' => 'geo!' . $nextLat . ',' . $nextLong,

        ]]);
        return (string) $response->getBody();
    }
}