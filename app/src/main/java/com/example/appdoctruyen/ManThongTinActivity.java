package com.example.appdoctruyen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ManThongTinActivity extends AppCompatActivity {
    TextView textviewthongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_thong_tin);
        textviewthongtin = findViewById(R.id.textviewthongtin);
        String thongtin="Đây là app đọc truyện để luyện code Android";
        textviewthongtin.setText(thongtin);
    }
}