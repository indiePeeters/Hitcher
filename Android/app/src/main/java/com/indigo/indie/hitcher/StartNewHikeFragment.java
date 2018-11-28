package com.indigo.indie.hitcher;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * Created by Indie on 11/14/2018.
 */

public class StartNewHikeFragment  extends Fragment {

    MainActivity activity;
    View currentView;
    Spinner spinNumberOfHikers;
    Button btnStartHike;
    Integer numberOfHikers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.start_new_hike_layout, container, false);

        //Listeners
        btnStartHike = (Button) currentView.findViewById(R.id.btn_startHike);
        btnStartHike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startHike(v);
            }
        });

        spinNumberOfHikers = (Spinner) currentView.findViewById(R.id.spinNumberOfHikers);
        ArrayAdapter<CharSequence> dataAdapter;
        dataAdapter = ArrayAdapter.createFromResource(currentView.getContext(), R.array.numberOfHikers_array, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinNumberOfHikers.setAdapter(dataAdapter);

        spinNumberOfHikers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Number of Hikers")) {
                    numberOfHikers = Integer.parseInt(parent.getItemAtPosition(position).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    public void startHike(View view){
        View textView =   currentView.findViewById(R.id.txtGoodLuck);
        View animationView = currentView.findViewById(R.id.animation_view);
        view.setEnabled(false);
        textView.setVisibility(View.VISIBLE);
        animationView.setVisibility(View.VISIBLE);
        activity.startTimerService();
        System.out.println("clicked");
    }
}