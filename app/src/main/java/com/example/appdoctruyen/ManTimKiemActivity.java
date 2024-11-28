package com.example.appdoctruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen.Model.Truyen;
import com.example.appdoctruyen.adapter.TruyenAdapter;
import com.example.appdoctruyen.database.Databasedoctruyen;

import java.util.ArrayList;

public class ManTimKiemActivity extends AppCompatActivity {

    ListView listtimkiem;
    EditText timkiem;

    ArrayList<Truyen> truyenArraylist;

    ArrayList<Truyen> truyens;

    TruyenAdapter adapter;

    Databasedoctruyen db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);
        db=new Databasedoctruyen(this);
        anhXa();
        initList();
        listtimkiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ManTimKiemActivity.this,NoiDungActivity.class);
                String tentruyen=truyens.get(position).getTentruyen();
                String noidung=truyens.get(position).getNoidung();
                intent.putExtra("tentruyen",tentruyen);
                intent.putExtra("noidung",noidung);
                startActivity(intent);
            }
        });
        //editText search
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    //search
    private void filter(String text){
        //Xoa du lieu mang
        truyens.clear();
        ArrayList<Truyen> filteredList = new ArrayList<>();
        for(Truyen item:truyenArraylist){
            if(item.getTentruyen().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                truyens.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    //Phuong thuc lay du lieu va gan vao listview
    private void initList() {
        truyenArraylist=new ArrayList<>();

        truyens=new ArrayList<>();

        Cursor res=db.getData2();
        while(res.moveToNext()){
            int id=res.getInt(0);
            String tentruyen = res.getString(1);
            String noidung = res.getString(2);
            String anh = res.getString(3);
            int id_tk=res.getInt(4);

            truyenArraylist.add(new Truyen(id, tentruyen,noidung,anh,id_tk));

            truyens.add(new Truyen(id, tentruyen,noidung,anh,id_tk));

            adapter = new TruyenAdapter(truyenArraylist, getApplicationContext());

            listtimkiem.setAdapter(adapter);
        }
        res.moveToFirst();
        res.close();
    }

    private void anhXa() {
        listtimkiem=findViewById(R.id.listtimkiem);
        timkiem=findViewById(R.id.timkiem);
    }
}