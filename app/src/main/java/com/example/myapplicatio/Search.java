package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Intent intent = getIntent();
        final String strId = intent.getStringExtra("User");

        int i=0;
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.layout1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.layout2);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.layout3);
        LinearLayout layout4 = (LinearLayout)findViewById(R.id.layout4);
        LinearLayout layout5 = (LinearLayout)findViewById(R.id.layout5);
        final TextView text1 = (TextView)findViewById(R.id.text1);
        final TextView text2 = (TextView)findViewById(R.id.text2);
        final TextView text3 = (TextView)findViewById(R.id.text3);
        final TextView text4 = (TextView)findViewById(R.id.text4);
        final TextView text5 = (TextView)findViewById(R.id.text5);


        final EditText Edit1 = (EditText)findViewById(R.id.Edit1);
        final SQLiteDatabase sqlDB;
        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READWRITE); //데이터베이스 열기
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT Stext FROM searchDB WHERE Sid='"+ strId +"'", null); //자신이 검색한 검색기록만 받아오기

        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);
        layout4.setVisibility(View.INVISIBLE);
        layout5.setVisibility(View.INVISIBLE);

        while (cursor.moveToNext()){ //각 텍스트뷰에 검색기록 띄우기
            String text = cursor.getString(0);

            if(i==0){
                layout1.setVisibility(View.VISIBLE);
                text1.setText(text);

            }
            else if(i==1){
                layout2.setVisibility(View.VISIBLE);
                text2.setText(text);
            }
            else if(i==2){
                layout3.setVisibility(View.VISIBLE);
                text3.setText(text);
            }
            else if(i==3){
                layout4.setVisibility(View.VISIBLE);
                text4.setText(text);
            }
            else if(i==4){
                layout5.setVisibility(View.VISIBLE);
                text5.setText(text);
            }
            i++;
        }

        cursor.close();

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back); //뒤로가기 버튼입니다.
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB.close();
                finish();
            }
        });

        Button btn_search = (Button)findViewById(R.id.btn_search); //입력한 값을 검색하는 버튼입니다.
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String sql = "INSERT INTO searchDB " + "(Snumber, Sid, Stext) VALUES (" + null + "," + "'" + strId + "'," + "'" + Edit1.getText().toString() + "')" ; //입력한 검색어를 데이터베이스에 저장합니다.
                sqlDB.execSQL(sql);
                sqlDB.close();

                Intent intent = new Intent(getApplicationContext(), Searchpage.class); //검색후 결과화면으로 이동
                intent.putExtra("User", strId);
                intent.putExtra("Gname", Edit1.getText().toString()); //검색어를 넘겨줍니다.
                finish();
                startActivity(intent);
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //검색기록을 눌렀을때 그 검색어로 검색을 합니다.
                Intent intent = new Intent(getApplicationContext(), Searchpage.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gname", text1.getText().toString());
                finish();
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //검색기록을 눌렀을때 그 검색어로 검색을 합니다.
                Intent intent = new Intent(getApplicationContext(), Searchpage.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gname", text2.getText().toString());
                finish();
                startActivity(intent);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //검색기록을 눌렀을때 그 검색어로 검색을 합니다.
                Intent intent = new Intent(getApplicationContext(), Searchpage.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gname", text3.getText().toString());
                finish();
                startActivity(intent);
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //검색기록을 눌렀을때 그 검색어로 검색을 합니다.
                Intent intent = new Intent(getApplicationContext(), Searchpage.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gname", text4.getText().toString());
                finish();
                startActivity(intent);
            }
        });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //검색기록을 눌렀을때 그 검색어로 검색을 합니다.
                Intent intent = new Intent(getApplicationContext(), Searchpage.class);
                intent.putExtra("User", strId);
                intent.putExtra("Gname", text5.getText().toString());
                finish();
                startActivity(intent);
            }
        });
    }
}
