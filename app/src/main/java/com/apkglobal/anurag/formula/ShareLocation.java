package com.apkglobal.anurag.formula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ANURAG on 26-06-2017.
 */

public class ShareLocation extends Fragment {
    GPSTracker gps;
    String url;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gps = new GPSTracker(getContext());
        if(gps.canGetLocation())
        {
            String       latitutes = Double.toString(gps.getLatitude());
            String      longitutes = Double.toString(gps.getLongitude());
            url="http://www.google.co.in/maps?f=q&q=" + latitutes + "," + longitutes;
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent
                    .putExtra(Intent.EXTRA_TEXT, url);

            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "send Location");

            sendIntent.setType("text/plain");


            startActivity(Intent.createChooser(sendIntent,"Share"));

        }
        else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        return null;

    }
    }
