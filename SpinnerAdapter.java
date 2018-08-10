package com.example.sanzharaubakir.bereke;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<CountryItemData> {
    int groupid;
    Activity context;
    ArrayList<CountryItemData> list;
    LayoutInflater inflater;
    public SpinnerAdapter(Activity context, int groupid, int id, ArrayList<CountryItemData>
            list){
        super(context,id,list);
        this.list = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = groupid;
    }

    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView = inflater.inflate(groupid,parent,false);
        ImageView flagImageView = (ImageView) itemView.findViewById(R.id.flag);
        flagImageView.setImageResource(list.get(position).getImageId());
        TextView nameTextView = (TextView) itemView.findViewById(R.id.name);
        nameTextView.setText(list.get(position).getName());
        TextView dialCodeTextView = (TextView) itemView.findViewById(R.id.dial_code);
        dialCodeTextView.setText(list.get(position).getDialCode());
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }
}