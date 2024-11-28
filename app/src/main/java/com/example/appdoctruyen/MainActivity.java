package com.example.appdoctruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appdoctruyen.Model.ChuyenMuc;
import com.example.appdoctruyen.Model.TaiKhoan;
import com.example.appdoctruyen.Model.Truyen;
import com.example.appdoctruyen.adapter.ChuyenmucAdapter;
import com.example.appdoctruyen.adapter.ThongtinAdapter;
import com.example.appdoctruyen.adapter.TruyenAdapter;
import com.example.appdoctruyen.database.Databasedoctruyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarmanhinhchinh;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView lv, listviewthongtin, listviewmanhinhchinh;
    DrawerLayout drawerlayout;

    Databasedoctruyen db;

    String email;
    String tentk;
    int id;
    int phanquyen;

    ArrayList<Truyen> truyenArraylist;
    TruyenAdapter adapter;

    ArrayList<TaiKhoan> taikhoans;
    ArrayList<ChuyenMuc> chuyenmucs;

    ThongtinAdapter adapter1;
    ChuyenmucAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new Databasedoctruyen(this);

        //Nhan du lieu o man hinh dang nhap
        Intent intent=getIntent();
        phanquyen=intent.getIntExtra("phanquyen",0);
        id=intent.getIntExtra("id",0);
        email=intent.getStringExtra("email");
        tentk=intent.getStringExtra("tentaikhoan");

        anhXa();
        ActionViewFlipper();
        ActionBar();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,NoiDungActivity.class);
                String tentruyen=truyenArraylist.get(position).getTentruyen();
                String noidung=truyenArraylist.get(position).getNoidung();
                intent.putExtra("tentruyen",tentruyen);
                intent.putExtra("noidung",noidung);
                startActivity(intent);
            }
        });
    }

    //Thanh actionbar với toolbar
    private void ActionBar() {
        //Hàm hỗ trợ toolbar
        setSupportActionBar(toolbarmanhinhchinh);
        //set nút cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tạo icon cho toolbar
        toolbarmanhinhchinh.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        //bắt sự kiện click
        toolbarmanhinhchinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //Phương thức chạy quảng cáo với ViewFlipper
    private void ActionViewFlipper() {
        //Mảng chứa ảnh cho quảng cáo
        ArrayList<String> quangcao = new ArrayList<>();
        quangcao.add("https://static.tramdoc.vn/image/img.news/0/0/0/6469.jpg?v=1&w=300&h=200&nocache=1");
        quangcao.add("https://img.tripi.vn/cdn-cgi/image/width=700,height=700/https://gcs.tripi.vn/public-tripi/tripi-feed/img/473762fJU/shimotsuki-wa-mob-ga-suki-937626.jpg");
        quangcao.add("https://genk.mediacdn.vn/2016/02-1482166836935.jpeg");
        quangcao.add("https://gcs.tripi.vn/public-tripi/tripi-feed/img/473762AWC/arifureta-shokugyou-de-sekai-saikyou-937590.jpg");

        //Chạy vòng lặp for gán ảnh vào imgView, rồi từ imgView lên app
        for(int i=0;i<quangcao.size();i++){
            ImageView imgView=new ImageView(getApplicationContext());
            //Sử dụng hàm thư viện picasso
            Picasso.get().load(quangcao.get(i)).into(imgView);
            //phương thức chỉnh tấm hình vừa khung quảng cáo
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            //thêm imgView vào viewFlipper
            viewFlipper.addView(imgView);
        }
        //Thiết lập tự động chạy cho viewFlipper
        viewFlipper.setFlipInterval(4000);
        //run auto cho viewFlipper
        viewFlipper.setAutoStart(true);
        //Gọi animation cho vào và ra
        Animation anim_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation anim_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        //Gọi animation vào viewFlipper
        viewFlipper.setInAnimation(anim_in);
        viewFlipper.setOutAnimation(anim_out);
    }

    private void anhXa() {
        toolbarmanhinhchinh = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewFlipper);
        navigationView = findViewById(R.id.navigationView);
        lv = findViewById(R.id.lv);
        listviewmanhinhchinh=findViewById(R.id.listviewmanhinhchinh);
        listviewthongtin=findViewById(R.id.listviewthongtin);
        drawerlayout=findViewById(R.id.drawerlayout);

        truyenArraylist = new ArrayList<>();

        Cursor res=db.getData1();
        while(res.moveToNext()){
            int id=res.getInt(0);
            String tentruyen=res.getString(1);
            String noidung=res.getString(2);
            String img=res.getString(3);
            int id_tk=res.getInt(4);

            truyenArraylist.add(new Truyen(id, tentruyen, noidung,img,id_tk));

            adapter=new TruyenAdapter(truyenArraylist, getApplicationContext());
            lv.setAdapter(adapter);
        }
        res.moveToFirst();
        res.close();

        //Thong tin
        taikhoans=new ArrayList<>();
        taikhoans.add(new TaiKhoan(tentk,email));

        adapter1=new ThongtinAdapter(this, R.layout.navigationthongtin, taikhoans);
        listviewthongtin.setAdapter(adapter1);
        //Chuyen muc
        chuyenmucs=new ArrayList<>();
        chuyenmucs.add(new ChuyenMuc(R.drawable.baseline_add_24, "Dang bai"));
        chuyenmucs.add(new ChuyenMuc(R.drawable.baseline_account, "Thong tin"));
        chuyenmucs.add(new ChuyenMuc(R.drawable.baseline_logout_24, "Dang Xuat"));

        adapter2=new ChuyenmucAdapter(this, R.layout.chuyenmuc, chuyenmucs);
        listviewmanhinhchinh.setAdapter(adapter2);
        listviewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //Ấn vào đăng bài để sang màn đăng bài với phân quyền admin
                    if(phanquyen==2){
                        Intent admin=new Intent(MainActivity.this, ManAdminActivity.class);
                        //gửi id tài khoản qua màn admin
                        admin.putExtra("id", id);
                        startActivity(admin);
                    }//Ấn đăng bài khi phân quyền user
                    else {
                        Toast.makeText(MainActivity.this, "Ban khong co quyen", Toast.LENGTH_LONG).show();
                        Log.e("Dang bai", "Ban khong co quyen");
                    }
                    //Ấn vào thông tin sẽ chuyển sang màn thông tin
                } else if(position==1){
                    Intent intent1= new Intent(MainActivity.this, ManThongTinActivity.class);
                    startActivity(intent1);
                    //Đăng xuất
                } else if(position==2){
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        //Click icon tim kiem se chuyen qua man tim kiem
        if(itemId==R.id.menu1){
            Intent intent1=new Intent(MainActivity.this, ManTimKiemActivity.class);
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }
}