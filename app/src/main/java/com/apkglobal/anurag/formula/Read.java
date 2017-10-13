package com.apkglobal.anurag.formula;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Read extends Fragment {

    public static final String JSON_URL = "http://searchkero.com/Anurag/fetch.php";

    private Button buttonGet;

    private ListView listView;
    ProgressDialog p;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_read, container, false);
       // buttonGet = (Button) view.findViewById(R.id.buttonGet);
        listView = (ListView) view.findViewById(R.id.listView);

        sendRequest();
       /* FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .show();
                Intent asd=new Intent(getContext(),SqlShow.class);
                startActivity(asd);
            }
        });*/
        return view;
    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                        p.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Network Problem", Toast.LENGTH_LONG).show();
                        p.dismiss();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        p=new ProgressDialog(getContext());
        p.setMessage("Please Wait");
        p.setCancelable(false);
        p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        p.show();
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(getActivity(), ParseJSON.ids,ParseJSON.names,ParseJSON.emails,ParseJSON.ideas);
        listView.setAdapter(cl);
    }



}
