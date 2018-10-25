<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('/hike', 'HikeController@startHike');
Route::put('/hike', 'HikeController@endHike');

Route::get('/hikeplanner/calculateRoute/main/{departureLocation}/{destinationLocation}', 'HikePlannerController@calculateRouteMain' );
Route::get('/hikeplanner/calculateRoute/hotspot', 'HikePlannerController@calculateRouteToHotspot' );
Route::get('/hikeplanner/suggestions', 'HikePlannerController@getLocationSugestions');

