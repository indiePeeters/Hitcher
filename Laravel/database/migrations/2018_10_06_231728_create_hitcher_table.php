<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateHitcherTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('hitchers', function (Blueprint $table) {
            $table->increments('id');
            $table->string('name');
            $table->string('password');
            $table->integer('age');
            $table->string('profession');
            $table->binary('photo');
            $table->integer('countriesVisited');
            $table->integer('totalHikes');
            $table->integer('totalDistance');
            $table->integer('preventedCarbonImpact');
            $table->integer('totalTime');
            $table->integer('totalSavedMoney');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('hitcher');
    }
}
