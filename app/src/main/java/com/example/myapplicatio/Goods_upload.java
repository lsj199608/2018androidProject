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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Goods_upload extends AppCompatActivity {
    ImageButton backbtn, mypic;
    Button mygoods;
    RadioButton check1, check2, check3, check4;
    EditText myname, mycate, mytag, mywrite, myprice, mycheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_upload);

        Intent intent = getIntent();
        final String strId = intent.getStringExtra("User");

        backbtn = (ImageButton) findViewById(R.id.backbtn);
        mypic = (ImageButton) findViewById(R.id.mypic);

        mygoods = (Button) findViewById(R.id.mygoods);
        check1 = (RadioButton) findViewById(R.id.check1);
        check2 = (RadioButton) findViewById(R.id.check2);
        check3 = (RadioButton) findViewById(R.id.check3);
        check4 = (RadioButton) findViewById(R.id.check4);

        myname = (EditText) findViewById(R.id.myname);
        mycate = (EditText) findViewById(R.id.mycate);
        mytag = (EditText) findViewById(R.id.mytag);
        mywrite = (EditText) findViewById(R.id.mywrite);
        myprice = (EditText) findViewById(R.id.myprice);
        mycheck = (EditText) findViewById(R.id.mycheck);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mygoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase sqlDB;
                sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READWRITE);
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM goodsDB WHERE Gnumber='" + "1" + "'", null);
                cursor.moveToNext();
                byte[] image = cursor.getBlob(5); //사진 바이트로 받아올 변수 image
                Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
                cursor.close();

                String sql = "INSERT INTO goodsDB " + "VALUES(" +null+ ",'"+ strId +"', '"+ myname.getText().toString() + "',"
                        + "'" + mycate.getText().toString() + "'," + "'" +
                        Integer.parseInt(myprice.getText().toString()) + "','" +bm+ "','" +"2020-06-20 23:53:20"+"','"+
                        mywrite.getText().toString() + "','"+"2020-06-21 23:53:20"+"')" ;

                sqlDB.execSQL(sql);

                sqlDB.close();
                finish();
            }
        });
    }
}