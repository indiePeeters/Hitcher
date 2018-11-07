<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateHitcherHikeTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('hike_hitcher', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('hitcher_id')->unsigned();;
            $table->integer('hike_id')->unsigned();;

            $table->foreign('hitcher_id')->references('id')->on('hitchers');
            $table->foreign('hike_id')->references('id')->on('hikes');
        });
    }

    /** 
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('hitcher_hike');
    }
}
