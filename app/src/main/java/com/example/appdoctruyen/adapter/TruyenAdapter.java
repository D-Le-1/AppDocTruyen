package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen.Model.Truyen;
import com.example.appdoctruyen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TruyenAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> truyens;

    public TruyenAdapter(ArrayList<Truyen> truyens, Context context) {
        this.truyens = truyens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return truyens.size();
    }

    @Override
    public Object getItem(int position) {
        return truyens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //filter
    public void filterList(ArrayList<Truyen> filteredList) {
        truyens = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView txtTenTruyen;
        ImageView imgtruyen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        holder=new ViewHolder();

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.item_truyen,null);

        holder.txtTenTruyen=convertView.findViewById(R.id.txtTenTruyen);
        holder.imgtruyen=convertView.findViewById(R.id.imgNewTruyen);
        convertView.setTag(holder);

        Truyen truyen=(Truyen) getItem(position);
        holder.txtTenTruyen.setText(truyen.getTentruyen());
        Picasso.get().load(truyen.getImg()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtruyen);
        return convertView;
    }
}
