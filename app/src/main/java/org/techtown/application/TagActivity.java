package org.techtown.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.application.R;

public class TagActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        //건너뛰기 버튼
        Button pre_skip = (Button)findViewById(R.id.pre_skip);
        pre_skip.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //선택창 출력: 이페이지를 건너뛰면 추천 기능 사용이 제한됩니다
                //if: yes->
                Intent main= new Intent(TagActivity.this, MainActivity.class);
                startActivity(main);

                //if: no-> 선택창 닫기
            }
        });

        //등록하기 버튼
        Button pre_submit = (Button)findViewById(R.id.pre_submit);
        pre_submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if (지역& 식사 태그에 text가 채워져 있다면)
                Intent main= new Intent(TagActivity.this,  MainActivity.class);
                startActivity(main);

                //else 빈칸채우세요 오류창

            }
        });



    }
}
