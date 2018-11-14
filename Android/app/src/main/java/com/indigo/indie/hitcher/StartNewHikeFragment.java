package com.indigo.indie.hitcher;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Indie on 11/14/2018.
 */

public class StartNewHikeFragment  extends Fragment {

    View currentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.start_new_hike_layout, container, false);
        return currentView;
    }
}