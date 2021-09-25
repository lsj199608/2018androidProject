package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    ImageButton backbtn;
    EditText newnick, newname, newid, newpw;
    Button newlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        backbtn = (ImageButton) findViewById(R.id.backbtn);
        newnick = (EditText) findViewById(R.id.newnick);
        newname = (EditText) findViewById(R.id.newname);
        newid = (EditText) findViewById(R.id.newid);
        newpw = (EditText) findViewById(R.id.newpw);
        newlogin = (Button) findViewById(R.id.newlogin);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strnick, strname, strid, strpw;

                strnick = newnick.getText().toString();
                strname = newname.getText().toString();
                strid = newid.getText().toString();
                strpw = newpw.getText().toString();

                if(strnick.equals(null) | strname.equals(null) | strid.equals(null) | strpw.equals(null)) {
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    SQLiteDatabase sqlDB;
                    sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READWRITE);
                    Cursor cursor;
                    cursor = sqlDB.rawQuery("SELECT Nick, Name, ID, Password FROM userDB WHERE Id='" + strid + "'", null);

                    String sql = "INSERT INTO userDB " + "(Nick, Name, Id, Password) " +
                            "VALUES(" + "'" + newnick.getText().toString() + "'," + "'" + newname.getText().toString() + "'," + "'" +
                            newid.getText().toString() + "'," + "'" +newpw.getText().toString() + "'" + ")" ;

                    sqlDB.execSQL(sql);
                    finish();
                }

            }
        });
    }
}