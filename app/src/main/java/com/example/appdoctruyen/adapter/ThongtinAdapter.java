package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appdoctruyen.Model.ChuyenMuc;
import com.example.appdoctruyen.Model.TaiKhoan;
import com.example.appdoctruyen.R;

import java.util.ArrayList;
import java.util.List;

public class ThongtinAdapter extends BaseAdapter {

    private Context context;
    int layout;
    List<TaiKhoan> taikhoans;

    public ThongtinAdapter(Context context, int layout, List<TaiKhoan> taikhoans) {
        this.context = context;
        this.layout = layout;
        this.taikhoans = taikhoans;
    }

    @Override
    public int getCount() {
        return taikhoans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        TextView txttentk=(TextView) convertView.findViewById(R.id.text_name);
        TextView txtemail=(TextView) convertView.findViewById(R.id.text_email);

        TaiKhoan taikhoan=taikhoans.get(position);

        txttentk.setText(taikhoan.getTentaikhoan());
        txtemail.setText(taikhoan.getEmail());

        return convertView;
    }
}
