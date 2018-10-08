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
            $table->integer('hitchhotspot_id')->unsigned();;
            $table->float('latTo',9,6);
            $table->float('longTo',9,6);
            $table->string('destination');
            $table->integer('numberOfHikers');
            $table->integer('moneySaved');
            $table->integer('distance');
            $table->integer('preventedCarbonImpact');
            $table->timestamps();

            $table->foreign('hitchhotspot_id')->references('id')->on('hitch_hotspots');
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
