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

public class Shareapp extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // View view = inflater.inflate(R.layout.activity_read, container, false);
        Intent share=new Intent(Intent.ACTION_SEND);
        share.putExtra(Intent.EXTRA_TEXT,
                "Please Download kare"+
                        "https://play.google.com/store/apps/details?id=com.apkglobal.anurag.formula");
        share.setType("text/plain");
        startActivity(Intent.createChooser(share,"share app via"));
        return null;
    }

}
