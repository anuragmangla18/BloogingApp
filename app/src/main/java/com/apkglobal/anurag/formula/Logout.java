package com.apkglobal.anurag.formula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ANURAG on 26-06-2017.
 */

public class Logout extends Fragment {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fAuth.signOut();

        Intent asd=new Intent(getContext(),LoginActivity.class);
        startActivity(asd);
        return null;
    }
    }
