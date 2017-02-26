package com.example.puzzle;

import android.content.Context;

/**
 * Created by sephirothus on 19.02.17.
 */
public class Location {

    public static int getImgByLocation(Context context, String location) {
        return context.getResources().getIdentifier("location_" + location, "drawable", context.getPackageName());
    }
}
