<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateHikeTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('hikes', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('hitch_hotspot_id')->unsigned();
            $table->string('destination');
            $table->integer('numberOfHikers');
            $table->integer('moneySaved');
            $table->integer('distance');
            $table->timestamp('starttime')->nullable();
            $table->timestamp('endtime')->nullable();
            $table->integer('preventedCarbonImpact');
            $table->timestamps();

            $table->foreign('hitch_hotspot_id')->references('id')->on('hitch_hotspots');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('hike');
    }
}
