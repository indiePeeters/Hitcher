package com.indigo.indie.hitcher;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.locationtech.jts.util.Stopwatch;

/**
 * Created by Indie on 11/23/2018.
 */

public class TimerService extends Service{

    Stopwatch stopwatch = new Stopwatch();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stopwatch.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopwatch.stop();
    }
}
