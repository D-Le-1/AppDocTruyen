package com.example.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NoiDungActivity extends AppCompatActivity {
    TextView tenTruyen, noidungTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung);
        tenTruyen=findViewById(R.id.tenTruyen);
        noidungTxt=findViewById(R.id.noidungTxt);

        //Lấy dữ liệu
        Intent intent=getIntent();
        String tentruyen=intent.getStringExtra("tentruyen");
        String noidung=intent.getStringExtra("noidung");
        tenTruyen.setText(tentruyen);
        noidungTxt.setText(noidung);
        //Cho phép cuộn nội dung truyện
        noidungTxt.setMovementMethod(new ScrollingMovementMethod());
    }
}