package org.techtown.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.techtown.application.LoginActivity;
import org.techtown.application.R;


public class FindinfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findinfo);

        //확인 버튼
        Button Edit_find_button = (Button)findViewById(R.id.Edit_find_button);
        Edit_find_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if (DB에 저장된 id와 이메일이 같다면) -> textview:find_result 에 id/pw 로 변경

                //else(DB에 저장된 id와 이메일이 같지 않다면 or id존재X) -> textview:find_result 에 다시 확인하세요 로 변경
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
