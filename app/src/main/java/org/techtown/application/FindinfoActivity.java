package org.techtown.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.application.LoginActivity;
import org.techtown.application.R;


public class FindinfoActivity extends AppCompatActivity {
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findinfo);

        final LoginDB db= new LoginDB(getApplicationContext(),"LoginDB.db",null,1);

        final EditText editname = findViewById(R.id.Edit_name);
        final EditText editemail = findViewById(R.id.Edit_email);
        final TextView resultinfo= findViewById(R.id.find_result);

        //확인 버튼
        Button Edit_find_button = (Button)findViewById(R.id.Edit_find_button);
        Edit_find_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name= editname.getText().toString();
                String email=editemail.getText().toString();

               database=db.getReadableDatabase();

               Cursor cusor1= database.rawQuery("select id,pw from LoginDB where name='"+name+"'&"+"email='"+email+"';",null);
              // Cursor cusor2= database.rawQuery("select pw from LoginDB where name='"+name+"'&"+"email='"+email+"';",null);

                if(!name.equals(cusor1.getString(cusor1.getColumnIndex("id")))){ //(DB에 저장된 id와 이메일이 같지 않다면 or id존재X)
                    resultinfo.setText("등록된 ID가 존재하지 않습니다.");

                }
                else{ //id 이메일 일치
                    resultinfo.setText("id: "+ cusor1.getString(cusor1.getColumnIndex("id"))
                            +"\n pw: "+ cusor1.getString(cusor1.getColumnIndex("pw")) );
                }

            }
        });

        //처음으로 돌아가기 버튼
        Button Edit_gofirst_button = (Button)findViewById(R.id.Edit_gofirst_button);
        Edit_gofirst_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent first= new Intent(FindinfoActivity.this, LoginActivity.class);
                startActivity(first);
            }
        });
    }
}
