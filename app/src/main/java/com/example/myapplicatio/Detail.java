package com.example.myapplicatio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Detail extends Activity {

    TextView cateName;
    TextView Gname;
    TextView Gprice;
    Button JoinAuction;
    ImageButton Goback;
    ImageButton SearchBtn;
    TextView UploadTime;
    TextView RemainTime;
    //TextView StartPrice;
    ImageView Gimage;
    TextView unit;
    Button participants;
    TextView Gtext;

    SQLiteDatabase sqlDB;
    //String SPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Intent intent = getIntent();
        final String strId = intent.getStringExtra("User");
        participants = (Button) findViewById(R.id.participants);
        cateName = (TextView) findViewById(R.id.cateName);
        JoinAuction = (Button) findViewById(R.id.JoinAuction);
        Goback = (ImageButton) findViewById(R.id.Goback);
        SearchBtn = (ImageButton) findViewById(R.id.searchbtn);
        UploadTime = (TextView) findViewById(R.id.uploadTime);
        RemainTime = (TextView) findViewById(R.id.remainTime);
        Gname = (TextView) findViewById(R.id.Gname);
        Gprice = (TextView) findViewById(R.id.Gprice);
        Gimage = (ImageView) findViewById(R.id.Gimage);
        Gtext = (TextView) findViewById(R.id.Gtext);
        //StartPrice = (TextView) findViewById(R.id.StartPrice);
        unit = (TextView) findViewById(R.id.unit);
        final int temp;

        final int Gnum = intent.getIntExtra("Gnum",0);  //DB에서 상품 번호를 받아와 그 상품에 맞는 이름 등을 표시한다


        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READWRITE);

        final Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM goodsDB WHERE Gnumber='" + Gnum + "'", null);
        cursor.moveToNext();

        cateName.setText(cursor.getString(3));
        Gname.setText(cursor.getString(2));
        Gtext.setText(cursor.getString(7));
        Gprice.setText(cursor.getString(4)+"원");
        byte[] image = cursor.getBlob(5); //사진 바이트 형태로 받아오는 변수 image
        Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);  //이미지 bitmap으로 받아옴
        Gimage.setImageBitmap(bm);  //이미지 설정
        //SPrice = cursor.getString(4);  //시작가격 저장할 변수. 시작가격 받는 애트리뷰트 추가해야 하는 문제로 시간관계상 구현 못함.
        //StartPrice.setText("시작가격 : "+SPrice+"원");  //경매 시작가격 세팅
        //temp = Integer.parseInt(SPrice)/20; // 경매 단위를 시작가격/20으로 정함.
        temp = 5000;
        unit.setText("단위 :"+ temp+"원"); // // 단위를 5천원으로 정함. 상품 금액에 따라 다르게 설정하려면 시작 가격을 받는 DB애트리뷰트 필요

        final String Gcate = cursor.getString(3); // 카테고리 인텐트 할 경우의 변수 저장
        final String GId = cursor.getString(1); // 검색 인텐트 할 경우의 변수 저장

        //0분 / 0시간 / 0일 전이라고 표시할 부분
        String temp1 = cursor.getString(6);  //상품 등록된 시간을 temp1에 string으로 저장
        long now=System.currentTimeMillis(); //  현재시간을 now에 받음
        Date today = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //데이터 포맷을 연-월-일 시간으로 설정
        try {
            Date uploaddate = dateFormat.parse(temp1);  // 상품이 등록된 시간 temp1을 설정한 형식으로 바꿈.
            long duration = today.getTime() - uploaddate.getTime();  //시간차이 구함
            if(duration>86400000) //1일 = 86400000 밀리세컨드
                UploadTime.setText(duration/86400000+"일 전 등록됨   ");
            if(3600000<duration&&duration<86400000) //1분 = 3600000 밀리세컨드
                UploadTime.setText(duration/3600000+"시간 전 등록됨   ");
            if(60000<duration&&duration<3600000) //1분 = 60000 밀리세컨드
                UploadTime.setText(duration/60000+"분 전 등록됨   ");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //여기까지

        //여기부터 경매종료까지 00:00:00 남음. 이라고 표시해주는 시간설정

        String timeout = cursor.getString(8);
        try {
            Date timeoutdate = dateFormat.parse(timeout);
            long duration = timeoutdate.getTime() - today.getTime();
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            String temp2 = dateFormat.format(duration);  //시간 이상하게 측정 되지만 해결하지 못함.
            RemainTime.setText("경매 종료까지 "+temp2+"남음");

        } catch (ParseException e) {
            e.printStackTrace();
        }


        //여기까지 시간설정

        SearchBtn.setOnClickListener(new View.OnClickListener() { // 검색버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });

        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Participant.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gnum",Gnum);
                startActivity(intent);
            }
        });

        cateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SmallCatePage.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gcategory",Gcate);
                startActivity(intent);
            }
        });
        JoinAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READWRITE);
                final Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM goodsDB WHERE Gnumber='" + Gnum + "'", null);
                cursor.moveToNext();

                final int temp1 = cursor.getInt(4)+temp;

                AlertDialog.Builder dlg = new AlertDialog.Builder(Detail.this);
                dlg.setTitle("경매참여");
                dlg.setMessage(cursor.getString(2)+"제품을 \n"+temp1+"원에 입찰하시겠습니까?");

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READWRITE);
                        String sql = "SELECT * FROM userDB WHERE Id='" + "lee" + "'"; //ID를 로그인부터 받아와야 하나 시간관계상 구현 x
                        Cursor cursor = sqlDB.rawQuery(sql,null);
                        cursor.moveToNext();

                        String Lnick = cursor.getString(0);
                        sqlDB.execSQL("INSERT INTO listDB VALUES (" +null+ ",'" + Gnum + "','"+Lnick+"','"+temp1+ "');");
                        sqlDB.execSQL("UPDATE goodsDB SET Gprice= '"+temp1+"' WHERE Gnumber = '"+Gnum+"'");
                        Toast.makeText(getApplicationContext(), temp1+"원에 경매에 입찰하였습니다.", Toast.LENGTH_SHORT).show();
                        cursor.close();
                        sqlDB.close();
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
        cursor.close();
        sqlDB.close();
    }
}