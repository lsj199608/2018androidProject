package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Searchpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpage);

        Intent intent = getIntent();
        final String strId = intent.getStringExtra("User");
        final int Gnumber[]={0,0,0,0,0,0,0,0,0,0};
        LinearLayout item1 = (LinearLayout)findViewById(R.id.item1);
        TextView price1 = (TextView)findViewById(R.id.price1);
        TextView name1 = (TextView)findViewById(R.id.name1);
        ImageView pic1 = (ImageView)findViewById(R.id.pic1);


        final String Name = intent.getStringExtra("Gname");
        SQLiteDatabase sqlDB;

        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READONLY); //데이터베이스 열기
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT Gname,Gprice,Gnumber,Gpic FROM goodsDB WHERE  Gname='"+ Name +"'", null); //테이블 값 받아오기



        while (cursor.moveToNext()){
            String name = cursor.getString(0); //상품명 받아오기
            String price = cursor.getString(1); //상품 가격 받아오기
            int number = cursor.getInt(2); //상품번호 받아오기
            byte[] image = cursor.getBlob(3); //상품사진 받아오기
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length); //비트맵을 이미지로 변환

            pic1.setImageBitmap(bm);
            name1.setText("상품명 : "+name);
            price1.setText(price+'원');
            Gnumber[0] = number;

        }

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Detail.class); //상세페이지로 이동
                intent.putExtra("User", strId);
                intent.putExtra("Gnum", Gnumber[0]); //상품번호를 상세페이지로 넘겨주기
                startActivity(intent);
            }
        });

        TextView mainname = (TextView)findViewById(R.id.mainname);
        mainname.setText(Name);
        mainname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Searchpage.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gname", Name);
                finish();
                startActivity(intent);
            }
        });

        ImageButton Goback  = (ImageButton)findViewById(R.id.Goback); //뒤로가기 버튼
        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}