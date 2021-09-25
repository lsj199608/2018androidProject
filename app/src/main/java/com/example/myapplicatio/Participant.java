package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Participant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant);

        Intent intent = getIntent();

        int Gnum = intent.getIntExtra("Gnum",0); //상품번호를 인텐트로 받아오기
        int i=0;

        LinearLayout layout1 = (LinearLayout)findViewById(R.id.layout1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.layout2);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.layout3);
        LinearLayout layout4 = (LinearLayout)findViewById(R.id.layout4);
        LinearLayout layout5 = (LinearLayout)findViewById(R.id.layout5);
        LinearLayout layout6 = (LinearLayout)findViewById(R.id.layout6);

        TextView name1 = (TextView)findViewById(R.id.name1);
        TextView name2 = (TextView)findViewById(R.id.name2);
        TextView name3 = (TextView)findViewById(R.id.name3);
        TextView name4 = (TextView)findViewById(R.id.name4);
        TextView name5 = (TextView)findViewById(R.id.name5);
        TextView name6 = (TextView)findViewById(R.id.name6);


        TextView price1 = (TextView)findViewById(R.id.price1);
        TextView price2 = (TextView)findViewById(R.id.price2);
        TextView price3 = (TextView)findViewById(R.id.price3);
        TextView price4 = (TextView)findViewById(R.id.price4);
        TextView price5 = (TextView)findViewById(R.id.price5);
        TextView price6 = (TextView)findViewById(R.id.price6);

        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);
        layout4.setVisibility(View.INVISIBLE);
        layout5.setVisibility(View.INVISIBLE);
        layout6.setVisibility(View.INVISIBLE);

        SQLiteDatabase sqlDB;

        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READONLY); //데이터베이스 열기
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT Lnick,Lprice FROM listDB WHERE  Gnumber='"+ Gnum +"'", null); //리스트테이블 열기



        while (cursor.moveToNext()){
            String name = cursor.getString(0); //닉네임 받기
            String price = cursor.getString(1); //참가 가격 받기

            if(i==0){
                layout1.setVisibility(View.VISIBLE);
                name1.setText("    "+name);
                price1.setText(price+'원');
            }
            else if(i==1){
                layout2.setVisibility(View.VISIBLE);
                name2.setText("    "+name);
                price2.setText(price+'원');
            }
            else if(i==2){
                layout3.setVisibility(View.VISIBLE);
                name3.setText("    "+name);
                price3.setText(price+'원');
            }
            else if(i==3){
                layout4.setVisibility(View.VISIBLE);
                name4.setText("    "+name);
                price4.setText(price+'원');
            }
            else if(i==4){
                layout5.setVisibility(View.VISIBLE);
                name5.setText("    "+name);
                price5.setText(price+'원');
            }
            else if(i==5){
                layout6.setVisibility(View.VISIBLE);
                name6.setText("    "+name);
                price6.setText(price+'원');
            }
            i++;
        }

        cursor.close();
        sqlDB.close();

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back); //뒤로가기 버튼
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
