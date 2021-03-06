<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        $this->call(HikeSeeder::class);
        $this->call(HitcherSeeder::class);
        $this->call(HitchHotspotSeeder::class);
    }
}
