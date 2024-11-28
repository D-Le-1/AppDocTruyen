package com.example.appdoctruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appdoctruyen.database.Databasedoctruyen;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtTenDangNhap, edtMatKhau;
    Button login_Btn, signupBtn;

    //Tao doi duong database
    Databasedoctruyen db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        //doi tuong database
        db=new Databasedoctruyen(DangNhapActivity.this);

        anhXa();
        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDangNhap=edtTenDangNhap.getText().toString();
                String matKhau=edtMatKhau.getText().toString();
                //Sử dụng con trỏ lấy dữ liệu
                Cursor cursor= db.getData();
                //Thực hiện vòng lặp để lấy dữ liệu từ Cursor
                while(cursor.moveToNext()){
                    //id 0, ten dang nhap 1, mat khau 2, email 3, phan quyen 4
                    String datatenTaikhoan=cursor.getString(1);
                    String datamatkhau=cursor.getString(2);
                    //Neu tai khoan va mat khau khop voi du lieu tu Database
                    if(tenDangNhap.equals(datatenTaikhoan) && matKhau.equals(datamatkhau)){
                        int phanquyen = cursor.getInt(4);
                        int idd=cursor.getInt(0);
                        String email=cursor.getString(3);
                        String tentk=cursor.getString(1);
                        //Chuyen sang man hinh MainActivity
                        Intent intent1=new Intent(DangNhapActivity.this,MainActivity.class);
                        //gui du lieu qua MainActivity
                        intent1.putExtra("id",idd);
                        intent1.putExtra("tentaikhoan",tentk);
                        intent1.putExtra("email",email);
                        intent1.putExtra("phanquyen",phanquyen);
                        startActivity(intent1);
                    }
                }
                //Thuc hien tra cursor ve dau
                cursor.moveToFirst();
                //Dong khi khong dung
                cursor.close();
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void anhXa() {
        edtMatKhau=findViewById(R.id.edtMatkhau);
        edtTenDangNhap=findViewById(R.id.edtTaikhoan);
        login_Btn=findViewById(R.id.login_Btn);
        signupBtn=findViewById(R.id.signupBtn);
    }
}