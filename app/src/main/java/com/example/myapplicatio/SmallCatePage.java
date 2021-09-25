package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplicatio.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmallCatePage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smallcatepage);
        Intent intent = getIntent();
        final String strId = intent.getStringExtra("User");
        final String DisplayGcate = intent.getStringExtra("Gcategory");


        Button lineup = (Button) findViewById(R.id.lineup);
        final TextView remainTime = (TextView) findViewById(R.id.remainTime);
        ImageButton Goback = (ImageButton) findViewById(R.id.Goback);
        TextView Ctgname1 = (TextView)findViewById(R.id.Ctgname);
        TextView Ctgname2 = (TextView)findViewById(R.id.Ctgname2);
        TextView goodscount = (TextView)findViewById(R.id.goodscount);
        LinearLayout items = (LinearLayout)findViewById(R.id.items);  // 이 레이아웃에 자동 생성 될 부분을 추가한다.



        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SQLiteDatabase sqlDB;
        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM goodsDB WHERE Gcategory='" + DisplayGcate + "'", null); //카테고리 입력받아야함


        goodscount.setText("상품 갯수 : "+cursor.getCount());
        Ctgname1.setText(DisplayGcate);
        Ctgname2.setText(DisplayGcate);

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

            String timeout = cursor.getString(8);
            try {
                String temp1 = cursor.getString(6);  //상품 등록된 시간을 temp1에 string으로 저장
                long now=System.currentTimeMillis(); //  현재시간을 now에 받음
                Date today = new Date(now);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //데이터 포맷을 연-월-일 시간으로 설정
                Date timeoutdate = dateFormat.parse(timeout);
                long duration = timeoutdate.getTime() - today.getTime();
                dateFormat = new SimpleDateFormat("HH:mm:ss");
                String temp2 = dateFormat.format(duration);  //시간 이상하게 측정 되지만 해결하지 못함.
                rmtime.setText("경매 종료까지 "+temp2+"남음");

            } catch (ParseException e) {
                e.printStackTrace();
            }

            Gimg.setImageBitmap(bm);  //이미지 설정
            gprice1.setText(cursor.getString(4)+"원"); //가격 설정
            gname1.setText(cursor.getString(2)); //상품 이름 설정

            final int curGname = cursor.getInt(0); // 디테일 인텐트 할 때 putextra사용할 변수에 해당 상품의 Gnumber저장


            itemlist.setOnClickListener(new LinearLayout.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Detail.class);
                    intent.putExtra("Gnum",curGname);
                    intent.putExtra("User", strId);
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

        TextView Ctgname = (TextView)findViewById(R.id.Ctgname);
        Ctgname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SmallCatePage.class);
                intent.putExtra("Gcategory",DisplayGcate);
                intent.putExtra("User", strId);
                finish();
                startActivity(intent);
            }
        });


    }
}

