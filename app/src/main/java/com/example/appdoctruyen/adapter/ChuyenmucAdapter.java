package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen.Model.ChuyenMuc;
import com.example.appdoctruyen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChuyenmucAdapter extends BaseAdapter {
    private Context context;
    int layout;
    List<ChuyenMuc> chuyenmucs;

    public ChuyenmucAdapter(Context context, int layout, List<ChuyenMuc> chuyenmucs) {
        this.context = context;
        this.layout = layout;
        this.chuyenmucs = chuyenmucs;
    }

    @Override
    public int getCount() {
        return chuyenmucs.size();
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
        ImageView img=(ImageView) convertView.findViewById(R.id.imgchuyenmuc);
        TextView txt=(TextView) convertView.findViewById(R.id.txttenchuyenmuc);
        ChuyenMuc chuyenmuc=chuyenmucs.get(position);

        txt.setText(chuyenmuc.getTenChuyenMuc());
        Picasso.get().load(chuyenmuc.getHinhAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(img);
        return convertView;
    }
}
