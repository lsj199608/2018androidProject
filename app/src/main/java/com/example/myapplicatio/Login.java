package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SQLiteDatabase sqlDB;
    EditText UserId, UserPw;
    TextView JoinMember;
    Button Loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        JoinMember = (TextView) findViewById(R.id.JoinMember);
        Loginbtn = (Button) findViewById(R.id.loginbtn);
        UserId = (EditText) findViewById(R.id.UserId);
        UserPw = (EditText) findViewById(R.id.UserPw);


        JoinMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strId, strPw;
                strId = UserId.getText().toString();
                strPw = UserPw.getText().toString();

                if (!strId.equals("")) {
                    sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.myapplicatio/database/MYDATA.db", null, SQLiteDatabase.OPEN_READONLY);
                    String sql = "SELECT Id,Password FROM userDB WHERE Id='" + strId + "'";
                    Cursor cursor;
                    cursor = sqlDB.rawQuery(sql, null);
                    cursor.moveToNext();
                    String sqlpw = cursor.getString(1);

                    if (strPw.equals(sqlpw)) {
                        Intent intent = new Intent(getApplicationContext(), Main.class);
                        intent.putExtra("User", strId);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                    sqlDB.close();
                }else{
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주십시오", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
