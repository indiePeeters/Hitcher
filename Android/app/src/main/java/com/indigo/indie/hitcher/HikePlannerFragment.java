package com.indigo.indie.hitcher;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapView;

/**
 * Created by Indie on 11/14/2018.
 */

public class HikePlannerFragment extends Fragment {

    MainActivity activity;
    View currentView;
    private MapView m_mapView;
    private Map m_map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.hike_planner_layout, container, false);
        centerImageViewActionbar();
        initMap();
        return currentView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.activity = (MainActivity) activity;
        } catch (ClassCastException e) {}
    }


    public void centerImageViewActionbar(){
        ActionBar mActionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mActionbar.setDisplayShowCustomEnabled( true );
        mActionbar.setDisplayShowTitleEnabled(false);
        mActionbar.setDisplayUseLogoEnabled(true);
        mActionbar.setDisplayHomeAsUpEnabled(false);
        mActionbar.setDisplayShowHomeEnabled(false);
        // Inflate the custom view
        LayoutInflater inflater = LayoutInflater.from( ((AppCompatActivity)getActivity()) );
        View header = inflater.inflate( R.layout.actionbar_layout, null );

        //Here do whatever you need to do with the view (set text if it's a textview or whatever)
        ImageView tv = (ImageView) header.findViewById( R.id.ActionbarIcon );

        // Magic happens to center it.
        Display display = ((AppCompatActivity)getActivity()).getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int actionBarWidth = size.x;

        tv.measure( 0, 0 );
        int tvSize = tv.getMeasuredWidth();

        int leftSpace = 0;
        try
        {
            View homeButton = ((AppCompatActivity)getActivity()).findViewById( android.R.id.home );
            final ViewGroup holder = (ViewGroup) homeButton.getParent();

            View firstChild =  holder.getChildAt( 0 );
            View secondChild =  holder.getChildAt( 1 );

            leftSpace = firstChild.getWidth()+secondChild.getWidth();
        }
        catch ( Exception ignored )
        {}

        mActionbar.setCustomView( header );

        if ( null != header )
        {
            ActionBar.LayoutParams params = (ActionBar.LayoutParams) header.getLayoutParams();

            if ( null != params )
            {
                int leftMargin =  ( actionBarWidth / 2 - ( leftSpace ) ) - ( tvSize / 2 ) ;

                params.leftMargin = 0 >= leftMargin ? 0 : leftMargin;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        m_mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        m_mapView.onPause();
    }

    private void initMap() {
        m_mapView = currentView.findViewById(R.id.here_map);
        MapEngine.getInstance().init(activity.getApplicationContext(),
                new OnEngineInitListener() {
                    @Override
                    public void onEngineInitializationCompleted(Error error) {
                        if (error == Error.NONE) {
                            /* get the map object */
                            m_map = new Map();
                            m_mapView.setMap(m_map);
                            Location currentLocation = activity.getCurrentLocation();
                            if(currentLocation != null) {
                                m_map.setCenter(new GeoCoordinate(currentLocation.getLatitude(), currentLocation.getLongitude()), Map.Animation.NONE);
                            } else{
                                m_map.setCenter(new GeoCoordinate(52.520008,13.404954), Map.Animation.NONE);
                            }
                        } else {
                            Toast.makeText(activity.getApplicationContext(), error.name(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
