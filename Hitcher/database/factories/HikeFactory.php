<?php

use Faker\Generator as Faker;

/*
|--------------------------------------------------------------------------
| Model Factories
|--------------------------------------------------------------------------
|
| This directory should contain each of the model factory definitions for
| your application. Factories provide a convenient way to generate new
| model instances for testing / seeding your application's database.
|
*/

$factory->define(App\Hike::class, function (Faker $faker) 
{
    return [
        'hitch_hotspot_id' => factory(App\HitchHotspot::class)->create()->id,
        'destination' => $faker->city,
        'numberOfHikers' => $faker->numberBetween(0,5),
        'moneySaved' => $faker->numberBetween(0,1000),
        'distance' => $faker->numberBetween(0,10000),
        'preventedCarbonImpact' => $faker->numberBetween(0,100000),
    ];
});
