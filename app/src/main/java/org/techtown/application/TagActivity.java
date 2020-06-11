package org.techtown.application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.techtown.application.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.application.R;

public class TagActivity extends AppCompatActivity {

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        final LoginDB db= new LoginDB(getApplicationContext(),"LoginDB.db",null,1);
        final EditText Area=(EditText)findViewById(R.id.pre_area);
        final EditText Taste=(EditText)findViewById(R.id.pre_meal);
        final EditText Etc=(EditText)findViewById(R.id.pre_etc);



        //등록하기 버튼
        Button pre_submit = (Button)findViewById(R.id.pre_submit);
        pre_submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent tag= getIntent();
                String id= tag.getExtras().getString("id");
                String area= Area.getText().toString();
                String taste=Taste.getText().toString();
                String etc=Etc.getText().toString();

                database=db.getReadableDatabase();


                if(area.length()!=0&&taste.length()!=0){ //(지역& 식사 태그에 text가 채워져 있다면)
                    db.insertTag(id,area,taste,etc);
                    Toast.makeText(getApplicationContext(), "태그를 등록했습니다!", Toast.LENGTH_LONG).show();

                    Intent main= new Intent(TagActivity.this,  MainActivity.class);
                    startActivity(main);
                }
                else{ //else 빈칸채우세요 오류창
                    Toast.makeText(getApplicationContext(), "필수 태그를 기입하세요!", Toast.LENGTH_LONG).show();
                }
            }
        });


        //건너뛰기 버튼
        Button pre_skip = (Button)findViewById(R.id.pre_skip);
        pre_skip.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //선택창 출력: 이페이지를 건너뛰면 추천 기능 사용이 제한됩니다 건너뛸?
                AlertDialog.Builder builder = new AlertDialog.Builder(TagActivity.this);
                builder.setTitle("건너뛰기");
                builder.setMessage("태그를 등록하지 않으면 추천기능 사용이 제한됩니다 건너뛰시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent main= new Intent(TagActivity.this, MainActivity.class);
                                startActivity(main);
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();
            }
        });



    }
}
