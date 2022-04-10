package com.wolfaonliu.cardreader;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AboutAdapter extends BaseAdapter {

    private final ArrayList<String[]> mData;
    private final Activity activity;


    public AboutAdapter(ArrayList<String[]> data, Activity activity) {
        this.mData = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(activity).inflate(R.layout.about_item, parent, false);

        TextView setting = convertView.findViewById(R.id.setting);
        TextView settingsub = convertView.findViewById(R.id.settingsub);

//
        setting.setText(mData.get(position)[0]);
        settingsub.setText(mData.get(position)[1]);
        return convertView;

    }


}
