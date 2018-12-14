package com.indigo.indie.hitcher;

import android.text.Html;

import com.here.android.mpa.search.AutoSuggest;
import com.here.android.mpa.search.AutoSuggestPlace;
import com.here.android.mpa.search.AutoSuggestSearch;
import com.here.android.mpa.search.DiscoveryRequest;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.ResultListener;
import com.here.android.mpa.search.TextAutoSuggestionRequest;

import java.util.List;

/**
 * Created by Indie on 12/3/2018.
 */

public class AutoPlaceSuggestion {

    public AutoPlaceSuggestion(String term){


        class AutoSuggestionQueryListener implements ResultListener<List<AutoSuggest>> {
            @Override
            public void onCompleted(List<AutoSuggest> data, ErrorCode error) {
                for (AutoSuggest r : data) {
                    try {
                        String term = "rest";
                        TextAutoSuggestionRequest request = null;
                        //request = new TextAutoSuggestionRequest(term).setSearchCenter(myMap.getCenter());
                       // if (request.execute(new AutoSuggestionQueryListener()) !=
                        //        ErrorCode.NONE ) {
                            //Handle request error
                            //...
                       // }
                    } catch (IllegalArgumentException ex) {
                        //Handle invalid create search request parameters
                    }
                }
            }
        }
    }
}
