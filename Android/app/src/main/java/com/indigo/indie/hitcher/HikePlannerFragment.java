package com.indigo.indie.hitcher;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapRoute;
import com.here.android.mpa.mapping.MapView;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Indie on 11/14/2018.
 */

public class HikePlannerFragment extends Fragment {

    MainActivity activity;
    View currentView;
    private MapView m_mapView;
    private Map m_map;
    private ArrayList<MapObject> mapObjects;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.hike_planner_layout, container, false);
        centerImageViewActionbar();
        initMap();

        Button btnCalculateROute = (Button) currentView.findViewById(R.id.btn_calculateRoute);
        btnCalculateROute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView txtFrom = currentView.findViewById(R.id.txtFrom);
                TextView txtTo = currentView.findViewById(R.id.txtTo);

                if(txtFrom.getText() != "" && txtTo.getText() != ""){
                    calculateRoute(v, (String)txtFrom.getText(),(String)txtTo.getText());
                }
            }
        });
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
        if(m_mapView != null) {
            m_mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(m_mapView != null) {
            m_mapView.onPause();
        }
    }

    public void initMap() {
        m_mapView = currentView.findViewById(R.id.here_map);
        MapEngine.getInstance().init(activity.getApplicationContext(),
                new OnEngineInitListener() {
                    @Override
                    public void onEngineInitializationCompleted(Error error) {
                        if (error == Error.NONE) {
                            /* get the map object */
                            if(m_map == null) {
                                m_map = new Map();
                            }
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

    public void calculateRoute(View v, String from, String to){
        //Create RouteManager
        RouteManager rm = new RouteManager();

        //Create new Routeplan and add waypoints to it.
        RoutePlan routePlan = new RoutePlan();

        routePlan.addWaypoint(new GeoCoordinate(51.452530, 5.470814));
        routePlan.addWaypoint(new GeoCoordinate(52.352718, 4.898461));

        //set Routeoptions for the routeplan.
        RouteOptions routeOptions = new RouteOptions();
        routeOptions.setTransportMode(RouteOptions.TransportMode.CAR);
        routeOptions.setRouteType(RouteOptions.Type.FASTEST);
        routePlan.setRouteOptions(routeOptions);

        class RouteListener implements RouteManager.Listener {

            // Method defined in Listener
            public void onProgress(int percentage) {
                // Display a message indicating calculation progress
            }

            // Method defined in Listener
            public void onCalculateRouteFinished(RouteManager.Error error, List<RouteResult> routeResult) {
                // If the route was calculated successfully
                if (error == RouteManager.Error.NONE) {
                    // Render the route on the map
                    MapRoute mapRoute = new MapRoute(routeResult.get(0).getRoute());
                    m_map.addMapObject(mapRoute);
                }
                else {
                    // Display a message indicating route calculation failure
                }
            }
        }

        //Display hitchhotspots on map
        final Image marker = new Image();
        final Image markerSelected = new Image();

        final ArrayList<HitchHotspot> hitchHotspotList = new ArrayList<>();
        try {
            marker.setImageResource(R.drawable.marker);
            markerSelected.setImageResource(R.drawable.marker_selected);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapObjects = new ArrayList<>();
        //TODO: get hitchhotspot for given route
        hitchHotspotList.add(new HitchHotspot("Amsterdam",51.453284, 5.463111, 600));
        hitchHotspotList.add(new HitchHotspot("Amsterdam",51.459858, 5.485761, 981));
        hitchHotspotList.add(new HitchHotspot("Amsterdam",51.444549, 5.441764, 1234));

        for (HitchHotspot hotspot : hitchHotspotList) {
            MapMarker mapMarker = new MapMarker(new GeoCoordinate(hotspot.getLatitude(), hotspot.getLongitude()), marker);
            mapMarker = mapMarker.setAnchorPoint(new PointF(marker.getWidth()/2, marker.getHeight()));
            mapObjects.add(mapMarker);
            m_map.addMapObject(mapMarker);
        }
        rm.calculateRoute(routePlan, new RouteListener());

        //add map gesture for selecting mapMarkers
        MapGesture.OnGestureListener mapGestureListener =
                new MapGesture.OnGestureListener.OnGestureListenerAdapter() {
                    @Override
                    public boolean onMapObjectsSelected(List<ViewObject> objects) {
                        ArrayList<MapObject> temp = new ArrayList<>();
                        for (MapObject mapobj : mapObjects) {
                            if(mapobj.getType() == MapObject.Type.MARKER) {
                                m_map.removeMapObject(mapobj);
                                MapMarker mapmarker = new MapMarker(((MapMarker) mapobj).getCoordinate(), marker);
                                mapmarker.setAnchorPoint(new PointF(marker.getWidth() / 2, marker.getHeight()));
                                m_map.addMapObject(mapmarker);
                                temp.add(mapmarker);
                            }
                        }
                        mapObjects.clear();
                        mapObjects.addAll(temp);
                        for (ViewObject viewObj : objects) {
                            if (viewObj.getBaseType() == ViewObject.Type.USER_OBJECT) {
                                if (((MapObject) viewObj).getType() == MapObject.Type.MARKER) {
                                    HitchHotspot currentHotspot = null;
                                    // At this point we have the originally added
                                    // map marker, so we can do something with it
                                    // (like change the visibility, or more
                                    for (HitchHotspot hotspot : hitchHotspotList){
                                        if(hotspot.getLatitude() == ((MapMarker) viewObj).getCoordinate().getLatitude() && hotspot.getLongitude() == ((MapMarker) viewObj).getCoordinate().getLongitude()){
                                            currentHotspot = hotspot;
                                        }
                                    }
                                    ((MapMarker) viewObj).setVisible(false);
                                    MapMarker mapMarker = new MapMarker(((MapMarker) viewObj).getCoordinate(), markerSelected);
                                    mapMarker.setAnchorPoint(new PointF(markerSelected.getWidth()/2, markerSelected.getHeight()));
                                    m_map.addMapObject(mapMarker);
                                    mapObjects.add(mapMarker);
                                    if(currentHotspot != null) {
                                        openPanel(currentHotspot);
                                    }
                                }
                            }
                        }
                        // return false to allow the map to handle this callback also
                        return false;
                    }
                };
        m_mapView.getMapGesture().addOnGestureListener(mapGestureListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void openPanel(final HitchHotspot hotspot){
        TextView txtDestination = currentView.findViewById(R.id.txtDestination);
        TextView txtAverageTime = currentView.findViewById(R.id.txtAverageTime);
        txtDestination.setText(hotspot.getDestination());
        txtAverageTime.setText("hitchhiking time:" + hotspot.getAverageTime()/60 + "minutes");

        Button btnStartNavigation = currentView.findViewById(R.id.btnstartNavigation);
        btnStartNavigation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startNavigation(new GeoCoordinate(hotspot.getLongitude(), hotspot.getLatitude()));
            }
        });
    }

    private void startNavigation(GeoCoordinate geoCoordinate){
        Location currentLocation = activity.currentLocation;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=Your location&daddr=" + geoCoordinate.getLongitude() +"," + geoCoordinate.getLatitude()));
        startActivity(intent);
    }

    public Object getHitchhotspots(String startingLocation, String Destination){
        return null;
    }
}
