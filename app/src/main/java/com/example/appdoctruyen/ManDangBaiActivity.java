package com.example.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen.Model.Truyen;
import com.example.appdoctruyen.database.Databasedoctruyen;

public class ManDangBaiActivity extends AppCompatActivity {

    EditText dbtruyen, dbnoidung, dbimage;
    Button dangbai_Btn;
    Databasedoctruyen db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_bai);
        db = new Databasedoctruyen(this);
        dbtruyen = findViewById(R.id.dbtruyen);
        dbnoidung = findViewById(R.id.dbnoidung);
        dbimage = findViewById(R.id.dbimage);
        dangbai_Btn=findViewById(R.id.dangbai_Btn);

        dangbai_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen=dbtruyen.getText().toString();
                String noidung=dbnoidung.getText().toString();
                String img=dbimage.getText().toString();

                Truyen truyen=CreateTruyen();

                if(tentruyen.equals("")||noidung.equals("")||img.equals("")){
                    Toast.makeText(ManDangBaiActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    Log.e("Error", "Vui lòng nhập đầy đủ thông tin");
                }//Neu nhap du thong tin se thuc hien them du lieu
                else {
                    db.Themtruyen(truyen);
                    //chuyen qua mang admin va cap nhat lai du lieu
                    Intent intent=new Intent(ManDangBaiActivity.this, ManAdminActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });

    }
    private Truyen CreateTruyen(){
        String tentruyen=dbtruyen.getText().toString();
        String noidung=dbnoidung.getText().toString();
        String img=dbimage.getText().toString();
        Intent intent=getIntent();
        int id=intent.getIntExtra("id",0);
        Truyen truyen=new Truyen(id, tentruyen, noidung, img, id);
        return truyen;
    }
}