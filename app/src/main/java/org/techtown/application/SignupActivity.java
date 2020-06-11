package org.techtown.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.techtown.application.R;


public class SignupActivity extends AppCompatActivity {

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final LoginDB db= new LoginDB(getApplicationContext(),"LoginDB.db",null,1);
        final EditText ename= (EditText) findViewById(R.id.Edit_name);
        final EditText eemail=(EditText) findViewById(R.id.Edit_email);
        final EditText eid= (EditText) findViewById(R.id.Edit_id);
        final EditText epw=(EditText) findViewById(R.id.Edit_pw);


        //가입하기 버튼
        Button  Edit_signup = (Button)findViewById(R.id.Edit_signup);
        Edit_signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name= ename.getText().toString();
                String email=eemail.getText().toString();
                String id= eid.getText().toString();
                String pw=epw.getText().toString();

                database=db.getReadableDatabase();

                Cursor cusor = database.rawQuery("SELECT id FROM LoginDB WHERE id ='" + id + "';", null);


                if(cusor.getCount()!=0){ //id가 기존 db에 있으면
                    Toast.makeText(getApplicationContext(), "다른 id를 사용하세요", Toast.LENGTH_LONG).show();
                }
                else if(id.equals(pw)){ //id랑 pw 같으면
                    Toast.makeText(getApplicationContext(), "id와 pw는 같을 수 없습니다.", Toast.LENGTH_LONG).show();
                }
                else if(name.length()==0||email.length()==0||id.length()==0||pw.length()==0){
                    Toast.makeText(getApplicationContext(), "id와 pw를 입력하세요", Toast.LENGTH_LONG).show();
                }
                else { //+db에 insert
                    db.insert(name,email,id,pw);

                    Toast.makeText(getApplicationContext(), "가입완료!", Toast.LENGTH_LONG).show();
                    Intent first= new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(first);
                }

            }
        });
    }

}
