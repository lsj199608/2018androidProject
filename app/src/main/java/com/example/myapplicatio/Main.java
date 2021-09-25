package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends AppCompatActivity {

    LinearLayout items;

    TextView Searchtxt1;
    Button imagebtn1, imagebtn2, imagebtn3, imagebtn4, imagebtn5;
    ImageButton searchbtn;

    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent = getIntent();
        final String strId = intent.getStringExtra("User");

        items = (LinearLayout)findViewById(R.id.items);
        Searchtxt1 = (TextView) findViewById(R.id.Searchtxt1);
        searchbtn = (ImageButton) findViewById(R.id.Searchbtn);
        imagebtn1 = (Button) findViewById(R.id.ImageBtn1);
        imagebtn2 = (Button) findViewById(R.id.ImageBtn2);
        imagebtn3 = (Button) findViewById(R.id.ImageBtn3);
        imagebtn4 = (Button) findViewById(R.id.ImageBtn4);
        imagebtn5 = (Button) findViewById(R.id.ImageBtn5);



        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM goodsDB ", null);

        for(int i=1; i<=cursor.getCount();i++){  //자동 생성 부분
            cursor.moveToNext();

            LayoutInflater inflater =  (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //인플레이터 객체 생성
            items.addView(inflater.inflate(R.layout.goodsinflater, null));  //items에 goodsinflater를 추가하겠다.

            LinearLayout itemlist = (LinearLayout)findViewById(R.id.item1);
            ImageView Gimg = (ImageView) findViewById(R.id.Gimg);
            TextView gprice1 = (TextView)findViewById(R.id.gprice1);
            TextView gname1 = (TextView)findViewById(R.id.Gname1);
            TextView rmtime = (TextView)findViewById(R.id.remainTime);

            itemlist.setId(i); // 동적 생성한 레이아웃의 아이디를 바꾸어 클릭 가능하게 바꿈.
            Gimg.setId(i);
            gprice1.setId(i);
            gname1.setId(i);
            rmtime.setId(i);

            byte[] image = cursor.getBlob(5); //사진 바이트로 받아올 변수 image
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);  //이미지 bitmap으로 받아옴

            Gimg.setImageBitmap(bm);  //이미지 설정
            gprice1.setText(cursor.getString(4)+"원"); //가격 설정
            gname1.setText(cursor.getString(2)); //상품 이름 설정

            final int curGnum = cursor.getInt(0); // 디테일 인텐트 할 때 putextra사용할 변수에 해당 상품의 Gnumber저장


            itemlist.setOnClickListener(new LinearLayout.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Detail.class);
                    intent.putExtra("User", strId);
                    intent.putExtra("Gnum",curGnum);
                    startActivity(intent);
                }
            });
            final TextView heart = (TextView) findViewById(R.id.heart);
            heart.setId(i);
            heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(heart.getText().toString() == "♡"){
                        heart.setText("♥");
                        heart.setTextSize(15);}
                    else{
                        heart.setText("♡");
                    }
                }
            });

        }

        cursor.close();
        sqlDB.close();



        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });

        Searchtxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });



        imagebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                intent.putExtra("User", strId);
                finish();
                startActivity(intent);
            }
        });

        imagebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });

        imagebtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Goods_upload.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });

        imagebtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mygoods.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });
    }
}