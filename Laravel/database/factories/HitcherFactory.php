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

$factory->define(App\Hitcher::class, function (Faker $faker) 
{
    return [
        'name' => $faker->name,
        'password' => $faker->password,
        'age' => $faker->numberBetween(18,100),
        'profession' => $faker->jobTitle,
        'photo' => '',
        'countriesVisited' => $faker->numberBetween(0,88),
        'totalHikes' => $faker->numberBetween(0,1000),
        'totalDistance' => $faker->numberBetween(0,100000),
        'preventedCarbonImpact' => $faker->numberBetween(0,100000),
        'totalTime' => $faker->numberBetween(0,10000),
        'totalSavedMoney' => $faker->numberBetween(0,10000),
    ];
});
