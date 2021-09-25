package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Category extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        Intent intent = getIntent();
        final String strId = intent.getStringExtra("User");

        Button GotoMain, GotoGU, GotoChat, GotoMygoods;
        GotoMain = (Button) findViewById(R.id.GotoMain);
        GotoGU = (Button) findViewById(R.id.GotoGU);
        GotoChat = (Button) findViewById(R.id.GotoChat);
        GotoMygoods = (Button) findViewById(R.id.GotoMygoods);

        final Button catebtn[] = new Button[13];
        Integer btnid[]= {R.id.catebtn1, R.id.catebtn2, R.id.catebtn3, R.id.catebtn4, R.id.catebtn5, R.id.catebtn6,
                R.id.catebtn7,R.id.catebtn8,R.id.catebtn9,R.id.catebtn10,
                R.id.catebtn11,R.id.catebtn12,R.id.catebtn13};


        for(int i=0; i<btnid.length; i++)
        {
            final int index;
            index = i;
            catebtn[index] = (Button) findViewById(btnid[index]);
            catebtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SmallCatePage.class);
                    String Gcategory = (String) catebtn[index].getText();
                    intent.putExtra("User", strId);
                    intent.putExtra("Gcategory",Gcategory);
                    startActivity(intent);
                }
            });
        }
        GotoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        GotoGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Goods_upload.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });
        GotoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        GotoMygoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), Mygoods.class);
                intent.putExtra("User", strId);
                startActivity(intent);
            }
        });

    }
}
