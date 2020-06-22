package org.techtown.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PlanlistActivity extends AppCompatActivity {
    PlanlistAdapter adapter;

    ActionBar actionBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planlist);

        //toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setTitle("Plan List");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlanlistAdapter();

        //예시 나중에는 PlanActivity2 데이터 받아오기

        adapter.addItem(new Planlist("제주도","2020","06","12"));
      //  adapter.addItem(new Planlist());


        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPlanlistItemClickListener() {
            @Override
            public void onItemClick(PlanlistAdapter.ViewHolder holder, View view, int position) {
                Planlist item = adapter.getItem(position);
                //확인용 나중에 지움
                showToast("아이템 선택됨: " +item.getPlace());
            }
        });
    }
    //Toast메시지 부분 나중에 삭제
    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }

    //뒤로가기 버튼 누르면 마이페이지로
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
