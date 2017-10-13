package com.apkglobal.anurag.formula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ANURAG on 26-06-2017.
 */

public class Sendingemail extends Fragment {
    EditText et_to,et_from,et_message,et_subject;
    Button btn_email;
    String sto,sfrom,smessage,ssubject;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.email, container, false);
        et_from=(EditText)view.findViewById(R.id.et_from);
        et_to=(EditText)view.findViewById(R.id.et_to);
        et_message=(EditText)view.findViewById(R.id.et_message);
        et_subject=(EditText)view.findViewById(R.id.et_subject);
        btn_email=(Button)view.findViewById(R.id.btn_email);
        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             sto=et_to.getText().toString();
                sfrom=et_from.getText().toString();
                smessage=et_message.getText().toString();
                ssubject=et_subject.getText().toString();

                sendemail();
            }
        });
        return  view;
    }

    private void sendemail() {
        Intent send =new Intent(Intent.ACTION_SEND);
        send.putExtra(Intent.EXTRA_EMAIL,new String  []{sfrom,sto});//emails will send on this mails
        send.putExtra(Intent.EXTRA_SUBJECT,ssubject);
        send.putExtra(Intent.EXTRA_TEXT,smessage);
        send.setType("message/rfc822");   // code fro email
        startActivity(Intent.createChooser(send,"Select Email to Send:"));   // this will show you no. of apps
       // you want to use for sending the email

    }
}
