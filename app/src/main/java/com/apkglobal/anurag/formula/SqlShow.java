package com.apkglobal.anurag.formula;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class SqlShow extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter mAdapter;
    Cursor c;

    SQLiteDatabase sd;

    //List<Data> malllist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_show);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager
                (new LinearLayoutManager(SqlShow.this));
        createdatabase();

        MyAdapter myAdapter=new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        //toshowdatafromtable();

    }

    private void toshowdatafromtable() {
        String sid1, sname1, sblog1, semail1;
        Integer id;
        //malllist=new ArrayList<Data>();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String name = currentFirebaseUser.getEmail();
        String fetch = "select * from'" + name + "'";
        //String fetch="select * from anurag";
// to fetch the data from table
        c = sd.rawQuery(fetch, null);
       /* c.moveToLast();
        id=c.getInt(0);
        //sid1=c.getString(0);
        sname1=c.getString(1);
        semail1=c.getString(2);
        sblog1=c.getString(3);
        Toast.makeText(getApplicationContext(),sname1,Toast.LENGTH_LONG).show();
*/
        if (c.moveToFirst()) {
            do {
                Data contact = new Data();
                contact.setNumber(c.getInt(0));
                contact.setName(c.getString(1));
                contact.setEmail(c.getString(2));
                contact.setIdea(c.getString(3));
                // Adding contact to list
                //malllist.add(contact);
            } while (c.moveToNext());
        }


    }

    private void createdatabase() {


        // create new database
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String name = currentFirebaseUser.getEmail();

        sd = openOrCreateDatabase("Dbname", Context.MODE_PRIVATE, null);
        //to create new table in database
       /* sd.execSQL("create table if not exists '"+name+"'(id integer primary key autoincrement," +
                "name varchar(100),mobile varchar(13),email varchar(150));");
*/      //  Toast.makeText(getApplicationContext(), "table created", Toast.LENGTH_LONG).show();
    }

    private class MyAdapter extends RecyclerView.Adapter<MeraHolder> {
        List<Data> malllist = new ArrayList<Data>();

        public MyAdapter() {
            super();
            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String name = currentFirebaseUser.getEmail();
            String fetch = "select * from'" + name + "'";
// to fetch the data from table
            c = sd.rawQuery(fetch, null);
       /* c.moveToLast();
        id=c.getInt(0);
        //sid1=c.getString(0);
        sname1=c.getString(1);
        semail1=c.getString(2);
        sblog1=c.getString(3);
        Toast.makeText(getApplicationContext(),sname1,Toast.LENGTH_LONG).show();
*/
            if (c.moveToFirst()) {
                do {
                    Data contact = new Data();
                    //contact.setNumber(c.getInt(0));
                    contact.setName(c.getString(1));
                    contact.setEmail(c.getString(2));
                    contact.setIdea(c.getString(3));
                    // Adding contact to list
                    malllist.add(contact);
                } while (c.moveToNext());
            }


        }

        @Override
        public MeraHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sql, parent, false);
            MeraHolder meraholder = new MeraHolder(view);
            return meraholder;
        }

        @Override
        public void onBindViewHolder(MeraHolder holder, int position) {
            Data item = malllist.get(position);
           // holder.tid.setText(item.getNumber());
            holder.tname.setText(item.getName());
            holder.temail.setText(item.getEmail());
            holder.tblog.setText(item.getIdea());

        }

        @Override
        public int getItemCount() {
            return malllist.size();
        }
    }

    private class MeraHolder extends RecyclerView.ViewHolder {
        TextView tid, tname, temail, tblog;

        protected MeraHolder(View itemView) {
            super(itemView);
            //tid = (TextView) itemView.findViewById(R.id.tv_id);
            tname = (TextView) itemView.findViewById(R.id.tv_name);
            temail = (TextView) itemView.findViewById(R.id.tv_email);
            tblog = (TextView) itemView.findViewById(R.id.tv_idea);


        }
    }
}
