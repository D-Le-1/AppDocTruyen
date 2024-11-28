package com.example.appdoctruyen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appdoctruyen.Model.TaiKhoan;
import com.example.appdoctruyen.database.Databasedoctruyen;

public class DangKyActivity extends AppCompatActivity {

    EditText edtdkTaikhoan, edtdkMatkhau, edtdkEmail;
    Button signup_btn, trovedn_btn;
    Databasedoctruyen db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        db = new Databasedoctruyen(this);
        anhXa();
        signup_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String tenTaiKhoan = edtdkTaikhoan.getText().toString();
                String matKhau = edtdkMatkhau.getText().toString();
                String email = edtdkEmail.getText().toString();

                TaiKhoan tk = CreateTaiKhoan();
                if(tenTaiKhoan.equals("") || matKhau.equals("") || email.equals("")){
                    Log.e("Thông báo","Chưa nhập đầy đủ thông tin");
                }
                else{
                    db.AddTaiKhoan(tk);
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        trovedn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Phuong thuc tao tai khoan
    private TaiKhoan CreateTaiKhoan() {
        String tenTaiKhoan = edtdkTaikhoan.getText().toString();
        String matKhau = edtdkMatkhau.getText().toString();
        String email = edtdkEmail.getText().toString();
        int phanQuyen = 1;

        TaiKhoan tk= new TaiKhoan(tenTaiKhoan,matKhau,email,phanQuyen);
        return tk;
    }
    private void anhXa() {
        edtdkTaikhoan = findViewById(R.id.edtdkTaikhoan);
        edtdkMatkhau = findViewById(R.id.edtdkMatkhau);
        edtdkEmail = findViewById(R.id.edtdkEmail);
        signup_btn = findViewById(R.id.signup_btn);
        trovedn_btn = findViewById(R.id.trovedn_btn);
    }
}