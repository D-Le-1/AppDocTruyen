package com.example.appdoctruyen;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen.Model.Truyen;
import com.example.appdoctruyen.adapter.TruyenAdapter;
import com.example.appdoctruyen.database.Databasedoctruyen;

import java.util.ArrayList;

public class ManAdminActivity extends AppCompatActivity {
    ListView listviewAdmin;
    Button themtruyen_Btn;

    ArrayList<Truyen> truyens;
    TruyenAdapter adapter;

    Databasedoctruyen db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_admin);
        listviewAdmin=findViewById(R.id.listviewAdmin);
        themtruyen_Btn=findViewById(R.id.themtruyen_Btn);

        initList();
        themtruyen_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lấy id tài khoản để biết tài khoản admin nào đã vào chỉnh sửa
                Intent intent1=getIntent();
                int id = intent1.getIntExtra("id",0);

                //Tiếp tục gửi id qua màn hình thêm truyện
                Intent intent=new Intent(ManAdminActivity.this,ManDangBaiActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        //Click item Long sẽ xóa item
        listviewAdmin.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }
        });
    }

    //Hien thi dialog
    private void DialogDelete(int position){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialogdelete);
        dialog.setCanceledOnTouchOutside(false);
        Button yesBtn=dialog.findViewById(R.id.yesBtn);
        Button noBtn=dialog.findViewById(R.id.noBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtruyen = truyens.get(position).getId();
                //xóa dữ liệu
                db.Xoatruyen(idtruyen);
                //Cập nhật lại Activity
                Intent intent=new Intent(ManAdminActivity.this, ManAdminActivity.class);
                finish();
                startActivity(intent);
                Toast.makeText(ManAdminActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                }
            });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    //Gán dữ liệu cho listview
    private void initList() {
        truyens=new ArrayList<>();
        db=new Databasedoctruyen(this);
        Cursor res=db.getData2();
        while(res.moveToNext()){
            int id=res.getInt(0);
            String tentruyen=res.getString(1);
            String noidung=res.getString(2);
            String img=res.getString(3);
            int id_tk=res.getInt(4);

            truyens.add(new Truyen(id, tentruyen,noidung,img,id_tk));
            adapter=new TruyenAdapter(truyens, getApplicationContext());
            listviewAdmin.setAdapter(adapter);
        }
        res.moveToFirst();
        res.close();
    }
}