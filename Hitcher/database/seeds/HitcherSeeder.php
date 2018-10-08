<?php

use Illuminate\Database\Seeder;

class HitcherSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        factory(App\Hitcher::class, 3)->create()->each(function($hitcher) {
            $hitcher->hikes()->saveMany(factory(App\Hike::class, 8)->create());
          });
    }
}
