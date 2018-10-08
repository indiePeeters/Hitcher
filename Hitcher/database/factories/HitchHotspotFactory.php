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

$factory->define(App\HitchHotspot::class, function (Faker $faker) 
{
    return [
        'lat' => $faker->latitude($min = -90, $max = 90),
        'long' => $faker->longitude($min = -180, $max = 180),
        'averageTime' => $faker->numberBetween(0,1000),
    ];
});
