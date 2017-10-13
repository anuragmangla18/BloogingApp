package com.apkglobal.anurag.formula;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ANURAG on 24-06-2017.
 */

public class CustomList extends ArrayAdapter {
    private String[] ids;
    private String[] names;
    private String[] emails;
    private String[] ideas;
    private Activity context;

    public CustomList(Activity context, String[] ids, String[] names, String[] emails, String [] ideas) {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.emails = emails;
        this.ideas=ideas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewIdea = (TextView) listViewItem.findViewById(R.id.tv_idea);

        textViewId.setText(ids[position]);
        textViewName.setText(names[position]);
        textViewEmail.setText(emails[position]);
        textViewIdea.setText(ideas[position]);
        return listViewItem;
    }

}
