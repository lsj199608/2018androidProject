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
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mygoods extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygoods);

        Intent intent = getIntent();
        final String strId = intent.getStringExtra("User");

        int i=0;
        final int Gnumber[]={0,0,0,0,0,0,0,0,0,0};
        SQLiteDatabase sqlDB;
        FrameLayout layout1 = (FrameLayout)findViewById(R.id.layout1);
        FrameLayout layout2 = (FrameLayout)findViewById(R.id.layout2);
        FrameLayout layout3 = (FrameLayout)findViewById(R.id.layout3);
        FrameLayout layout4 = (FrameLayout)findViewById(R.id.layout4);
        FrameLayout layout5 = (FrameLayout)findViewById(R.id.layout5);
        ImageView pic1 = (ImageView)findViewById(R.id.pic1);
        ImageView pic2 = (ImageView)findViewById(R.id.pic2);
        ImageView pic3 = (ImageView)findViewById(R.id.pic3);
        ImageView pic4 = (ImageView)findViewById(R.id.pic4);
        ImageView pic5 = (ImageView)findViewById(R.id.pic5);
        TextView goodsname1 = (TextView) findViewById(R.id.goodsname1);
        TextView price1 = (TextView) findViewById(R.id.price1);
        TextView goodsname2 = (TextView) findViewById(R.id.goodsname2);
        TextView price2 = (TextView) findViewById(R.id.price2);
        TextView goodsname3 = (TextView) findViewById(R.id.goodsname3);
        TextView price3 = (TextView) findViewById(R.id.price3);
        TextView goodsname4 = (TextView) findViewById(R.id.goodsname4);
        TextView price4 = (TextView) findViewById(R.id.price4);
        TextView goodsname5 = (TextView) findViewById(R.id.goodsname5);
        TextView price5 = (TextView) findViewById(R.id.price5);
        TextView time1 = (TextView)findViewById(R.id.time1);
        TextView time2 = (TextView)findViewById(R.id.time2);
        TextView time3 = (TextView)findViewById(R.id.time3);
        TextView time4 = (TextView)findViewById(R.id.time4);
        TextView time5 = (TextView)findViewById(R.id.time5);

        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);
        layout4.setVisibility(View.INVISIBLE);
        layout5.setVisibility(View.INVISIBLE);

        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READONLY); //데이터베이스를 연다.
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT Gname,Gprice,Gnumber,Gpic,Gtime FROM goodsDB WHERE  Gid='"+ strId +"'", null); //상품 테이블에서 각 칼럼를 뽑아옵니다.



        while (cursor.moveToNext()){
            String name = cursor.getString(0); //상품명 받아오기
            String price = cursor.getString(1); //상품가격 받아오기
            int number = cursor.getInt(2); //상품번호 받아오기
            byte[] image = cursor.getBlob(3); //이미지 받아오기
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
            String temp1 = cursor.getString(4);  //상품 등록된 시간을 temp1에 string으로 저장
            long now=System.currentTimeMillis(); //  현재시간을 now에 받음
            Date today = new Date(now);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //데이터 포맷을 연-월-일 시간으로 설정

            if(i==0){
                layout1.setVisibility(View.VISIBLE);
                pic1.setImageBitmap(bm);
                goodsname1.setText("상품명 : "+name);
                price1.setText(price+'원');
                Gnumber[0]=number;
                try {
                    Date uploaddate = dateFormat.parse(temp1);  // 상품이 등록된 시간 temp1을 설정한 형식으로 바꿈.
                    long duration = today.getTime() - uploaddate.getTime();  //시간차이 구함
                    if(duration>86400000) //1일 = 86400000 밀리세컨드
                        time1.setText(duration/86400000+"일 전 등록됨   ");
                    if(3600000<duration&&duration<86400000) //1분 = 3600000 밀리세컨드
                        time1.setText(duration/3600000+"시간 전 등록됨   ");
                    if(60000<duration&&duration<3600000) //1분 = 60000 밀리세컨드
                        time1.setText(duration/60000+"분 전 등록됨   ");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if(i==1){
                layout2.setVisibility(View.VISIBLE);
                pic2.setImageBitmap(bm);
                goodsname2.setText("상품명 : "+name);
                price2.setText(price+'원');
                Gnumber[1]=number;
                try {
                    Date uploaddate = dateFormat.parse(temp1);  // 상품이 등록된 시간 temp1을 설정한 형식으로 바꿈.
                    long duration = today.getTime() - uploaddate.getTime();  //시간차이 구함
                    if(duration>86400000) //1일 = 86400000 밀리세컨드
                        time2.setText(duration/86400000+"일 전 등록됨   ");
                    if(3600000<duration&&duration<86400000) //1분 = 3600000 밀리세컨드
                        time2.setText(duration/3600000+"시간 전 등록됨   ");
                    if(60000<duration&&duration<3600000) //1분 = 60000 밀리세컨드
                        time2.setText(duration/60000+"분 전 등록됨   ");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if(i==2){
                layout3.setVisibility(View.VISIBLE);
                pic3.setImageBitmap(bm);
                goodsname3.setText("상품명 : "+name);
                price3.setText(price+'원');
                Gnumber[2]=number;
                try {
                    Date uploaddate = dateFormat.parse(temp1);  // 상품이 등록된 시간 temp1을 설정한 형식으로 바꿈.
                    long duration = today.getTime() - uploaddate.getTime();  //시간차이 구함
                    if(duration>86400000) //1일 = 86400000 밀리세컨드
                        time3.setText(duration/86400000+"일 전 등록됨   ");
                    if(3600000<duration&&duration<86400000) //1분 = 3600000 밀리세컨드
                        time3.setText(duration/3600000+"시간 전 등록됨   ");
                    if(60000<duration&&duration<3600000) //1분 = 60000 밀리세컨드
                        time3.setText(duration/60000+"분 전 등록됨   ");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if(i==3){
                layout4.setVisibility(View.VISIBLE);
                pic4.setImageBitmap(bm);
                goodsname4.setText("상품명 : "+name);
                price4.setText(price+'원');
                Gnumber[3]=number;
                try {
                    Date uploaddate = dateFormat.parse(temp1);  // 상품이 등록된 시간 temp1을 설정한 형식으로 바꿈.
                    long duration = today.getTime() - uploaddate.getTime();  //시간차이 구함
                    if(duration>86400000) //1일 = 86400000 밀리세컨드
                        time3.setText(duration/86400000+"일 전 등록됨   ");
                    if(3600000<duration&&duration<86400000) //1분 = 3600000 밀리세컨드
                        time3.setText(duration/3600000+"시간 전 등록됨   ");
                    if(60000<duration&&duration<3600000) //1분 = 60000 밀리세컨드
                        time3.setText(duration/60000+"분 전 등록됨   ");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if(i==4){
                layout5.setVisibility(View.VISIBLE);
                pic5.setImageBitmap(bm);
                goodsname5.setText("상품명 : "+name);
                price5.setText(price+'원');
                Gnumber[4]=number;
                try {
                    Date uploaddate = dateFormat.parse(temp1);  // 상품이 등록된 시간 temp1을 설정한 형식으로 바꿈.
                    long duration = today.getTime() - uploaddate.getTime();  //시간차이 구함
                    if(duration>86400000) //1일 = 86400000 밀리세컨드
                        time3.setText(duration/86400000+"일 전 등록됨   ");
                    if(3600000<duration&&duration<86400000) //1분 = 3600000 밀리세컨드
                        time3.setText(duration/3600000+"시간 전 등록됨   ");
                    if(60000<duration&&duration<3600000) //1분 = 60000 밀리세컨드
                        time3.setText(duration/60000+"분 전 등록됨   ");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }

        cursor.close();
        sqlDB.close();

        Button btn_main = (Button) findViewById(R.id.btn_main);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }); //메인으로 가기
        Button btn_category = (Button) findViewById(R.id.btn_category); //카테고리로 가기
        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });
        Button btn_mygoods = (Button) findViewById(R.id.btn_mygoods); //새로고침
        btn_mygoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mygoods.class);
                intent.putExtra("User", strId);
                finish();
                startActivity(intent);
            }
        });
        Button btn_upload = (Button) findViewById(R.id.btn_upload); //상품올리기로 가기
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Goods_upload.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //첫번째 상품 상세페이지로 가기
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gnum", Gnumber[0]); //상품 번호 넘겨주기
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //두번째 상품 상세페이지로 가기
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gnum", Gnumber[1]); //상품 번호 넘겨주기
                startActivity(intent);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //세번째 상품 상세페이지로 가기
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gnum", Gnumber[2]); //상품 번호 넘겨주기
                startActivity(intent);
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //세번째 상품 상세페이지로 가기
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gnum", Gnumber[3]); //상품 번호 넘겨주기
                startActivity(intent);
            }
        });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //세번째 상품 상세페이지로 가기
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gnum", Gnumber[4]); //상품 번호 넘겨주기
                startActivity(intent);
            }
        });



    }
}