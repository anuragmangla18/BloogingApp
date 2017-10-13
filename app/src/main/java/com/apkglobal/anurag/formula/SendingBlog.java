package com.apkglobal.anurag.formula;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class SendingBlog extends Fragment {
    Button btn_send,speak;
    EditText et_name,et_email,et_blog;
    int pid=1;
    SQLiteDatabase sd;

    //ProgressBar progress;
    ProgressDialog p;
    String url="http://searchkero.com/Anurag/insertidea.php";
    // As server does not understand editText so convert into the String
    String sname,semail,sidea;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sending_blog, container, false);

        btn_send=(Button)view.findViewById(R.id.btn_send);
        speak=(Button)view.findViewById(R.id.speak);
        et_name=(EditText)view.findViewById(R.id.et_name);
        et_email=(EditText)view.findViewById(R.id.et_email);
        et_blog=(EditText)view.findViewById(R.id.et_blog);
      // createdatabase();

        // progress=(ProgressBar)view.findViewById(R.id.progressbar);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progress.setVisibility(View.VISIBLE);
                sname=et_name.getText().toString();
                semail=et_email.getText().toString();
                sidea=et_blog.getText().toString();
               // progress.setVisibility(View.VISIBLE);
               /* p=new ProgressDialog(getContext());
               *//* p.setMessage("Please Wait");
                p.setCancelable(false);
                p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                p.show();*/
                shareidea();
               //progress.setVisibility(View.GONE);
               // p.dismiss();
               // insertintotable();




            }
        });
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voicetotext();
            }
        });
        return view;
    }

    private void insertintotable() {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String name=currentFirebaseUser.getEmail();
        String sname1=et_name.getText().toString();
         String semail1=et_email.getText().toString();
        String sblog1=et_blog.getText().toString();
        String insert="insert into '"+name+"'(name,email,idea) values('"+sname1+"','"+semail1+"','"+sblog1+"');";
        sd.execSQL(insert);
        Toast.makeText(getContext(),"insert done Successfully",Toast.LENGTH_LONG).show();
    }

    private void createdatabase() {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String name=currentFirebaseUser.getEmail();
        sd=getActivity().openOrCreateDatabase("Dbname", Context.MODE_PRIVATE,null);
        //to create new table in database
       /* sd.execSQL("create table if not exists '"+name+"'(id integer primary key autoincrement,"+
                "name varchar(100),email varchar(13),idea varchar(450));");*/
        sd.execSQL("create table if not exists '"+name+"'(name varchar(100),email varchar(13),idea varchar(450));");
        Toast.makeText(getContext(),"table created",Toast.LENGTH_LONG).show();
    }

    private void voicetotext() {
        Intent voice=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT,"say Something");
        startActivityForResult(voice,pid);
    }

    private void shareidea() {
       // progress.setVisibility(View.VISIBLE);

        StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              /*  p=new ProgressDialog(getContext());
                p.setMessage("Please Wait");
                p.setCancelable(false);
                p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                p.show();*/
             p.dismiss();
                Toast.makeText(getContext(),"Send Successfully",Toast.LENGTH_LONG).show();
                et_blog.setText(" ");
                et_name.setText(" ");
                et_email.setText(" ");
               // progress.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                p.dismiss();


            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //progress.setVisibility(View.VISIBLE);
                Map<String,String> map=new HashMap<String,String>();
                map.put("nameid",sname);
                map.put("emailid",semail);
                map.put("ideaid",sidea);
                return map;


            }
        };
        //progress.setVisibility(View.VISIBLE);
        RequestQueue requestqueue= Volley.newRequestQueue(getContext());
        requestqueue.add(sr);
        p=new ProgressDialog(getContext());
        p.setMessage("Please Wait");
        p.setCancelable(false);
        p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        p.show();
       // progress.setVisibility(View.VISIBLE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==pid)
        {
            if(resultCode==RESULT_OK && data!=null)
            {
                ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                et_blog.setText(result.get(0));
            }
        }
    }
}
