package org.techtown.application;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.application.R;


public class LoginActivity extends AppCompatActivity {

    public static Context context;
    SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LoginDB db= new LoginDB(getApplicationContext(),"LoginDB.db",null,1);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);

        //로그인버튼
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id= usernameEditText.getText().toString();
                String pw= passwordEditText.getText().toString();

                database=db.getReadableDatabase();

                if(id.length()==0||pw.length()==0){ //비어있을때
                   Toast.makeText(getApplicationContext(), "id와 pw을 입력하세요!", Toast.LENGTH_LONG).show();
                }

                Cursor cusor1= database.rawQuery("SELECT pw FROM LoginDB WHERE id = '" + id +"';",null);

                if(cusor1.getCount()==0){   //해당 id가 없을때
                    Toast.makeText(getApplicationContext(), "id를 다시확인하세요!", Toast.LENGTH_LONG).show();
                    usernameEditText.setText(""); //+ text clear
                    passwordEditText.setText("");
                }
                else {
                    if (cusor1.moveToFirst() && !pw.equals(cusor1.getString(cusor1.getColumnIndex("pw")))) { // 비번 틀렸을때
                        Toast.makeText(getApplicationContext(), "pw를 다시확인하세요!", Toast.LENGTH_LONG).show();
                        usernameEditText.setText(""); //+ text clear
                        passwordEditText.setText("");
                    }
                    else{ //id pw 둘다 맞으면
                        Cursor cusor3= database.rawQuery("SELECT area FROM LoginDB WHERE id = '" + id +"';",null);
                        cusor3.moveToFirst();
                        System.out.println(cusor3.getString(cusor3.getColumnIndex("area")));

                        //if (선호태그 등록x)
                        if(cusor3.moveToFirst() &&cusor3.getString(cusor3.getColumnIndex("area"))==null){
                            Intent tag = new Intent(LoginActivity.this, TagActivity.class);
                            tag.putExtra("id",id);
                            startActivity(tag);
                        }
                        else {//선호태그 등록O
                            Intent main = new Intent(LoginActivity.this, MainActivity.class);
                            main.putExtra("id",id);
                            startActivity(main);
                        }
                    }
                }
            }
        });

        //sign up 버튼
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signup);
            }
        });

        //ID PW 찾기 버튼
        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent find = new Intent(LoginActivity.this, FindinfoActivity.class);
               startActivity(find);
            }
        });



       // DB체크용 버튼 점검 후 삭제예정!
        //현재 등록된 유저-> name:ey / email:1234@gmail.com / id: manager1 / pw: 1234 [태그 등록X 유저]
        Button test = (Button)findViewById(R.id.button_dbtest);
        test.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String id= usernameEditText.getText().toString();

                //전체조회

                database=db.getReadableDatabase();
               // db.delete(id="ey");

                String a=db.getResult();
                System.out.println(a);
            }
        });

    }}