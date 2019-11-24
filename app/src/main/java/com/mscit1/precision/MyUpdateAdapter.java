package com.mscit1.precision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

class MyUpdateAdapter extends BaseAdapter {
    Context con;
    ArrayList<HashMap<String,Object>> list;
    LayoutInflater li;
    public MyUpdateAdapter(Update_stud update_stud, ArrayList<HashMap<String, Object>> array) {
        con=update_stud;
        list=array;
        li = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = li.inflate(R.layout.update_adapter,
                null);
        TextView tv_id = (TextView)convertView.findViewById(R.id.up_txt_no);
        TextView tv_name = (TextView)convertView.findViewById(R.id.up_txt_name);

        HashMap<String, Object> map = list.get(position);
        int int_id = Integer.parseInt(map.get("RNo").toString());
        String str_name = map.get("my_name").toString();

        tv_id.setText(int_id+"");
        tv_name.setText(str_name);

        return convertView;
    }
}
