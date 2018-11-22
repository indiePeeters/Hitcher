package com.indigo.indie.hitcher;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Indie on 11/14/2018.
 */

public class StartNewHikeFragment  extends Fragment {

    View currentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.start_new_hike_layout, container, false);

        //Listeners
        Button button = (Button) currentView.findViewById(R.id.btn_startHike);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startHike(v);
            }
        });

        return currentView;
    }

    public void startHike(View view){
        View textView =   currentView.findViewById(R.id.txtGoodLuck);
        View animationView = currentView.findViewById(R.id.animation_view);
        view.setEnabled(false);
        textView.setVisibility(View.VISIBLE);
        animationView.setVisibility(View.VISIBLE);
        System.out.println("clicked");
    }
}