package org.techtown.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MypageActivity extends AppCompatActivity {
    public ExpandableListView listView; //ExpandableListView 변수선언
    public ExpandableListViewAdapter adapter;
    public ArrayList<Parent> listParent;

    ActionBar actionBar;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setTitle("My page");
        //actionBar.setIcon(R.drawable.menu_settings);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Expadable
        listView = (ExpandableListView)findViewById(R.id.expandableList);
        listParent = new ArrayList<Parent>();

        //load parent
        loadParentData();

        adapter = new ExpandableListViewAdapter(this,listView,listParent);
        listView.setAdapter(adapter);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(MypageActivity.this, "parent_list: "+groupPosition, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        //차일드 클릭 시 이벤트
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
              //  adapter = (ExpandableListViewAdapter) parent.getExpandableListAdapter();
              //  int gp = (int) adapter.getGroup(groupPosition);
              //  int cp = (int) adapter.getChild(groupPosition,childPosition);
              //  Toast.makeText(MypageActivity.this, "child_list", Toast.LENGTH_LONG).show();
              //  return false;
              //  Toast.makeText(getApplicationContext(),"child_list: "+childPosition,Toast.LENGTH_LONG).show();
              //  return false;
                if(groupPosition == 0){
                    switch(childPosition){
                        case 0:
                            Toast.makeText(getApplicationContext(),"child_list: "+childPosition,Toast.LENGTH_LONG).show();
                            Intent planIntent = new Intent(getApplicationContext(), PlanActivity.class);
                            startActivity(planIntent);
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(),"child_list: "+childPosition,Toast.LENGTH_LONG).show();
                            Intent listIntent = new Intent(getApplicationContext(), PlanlistActivity.class);
                            startActivity(listIntent);
                            break;
                    }
                }else if(groupPosition == 1){
                    switch(childPosition){
                        case 0:
                            Toast.makeText(getApplicationContext(),"child_list: "+childPosition,Toast.LENGTH_LONG).show();
                            //이렇게하면 툴바 이름 못바꿈...그럼 프래그먼트 이용..?
                            //아니면 Planlistactivity 정보 intent 이용해서 가져오기..?
                            Intent listIntent = new Intent(getApplicationContext(), PlanlistActivity.class);
                            startActivity(listIntent);
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(),"child_list: "+childPosition,Toast.LENGTH_LONG).show();
                           // Intent PlistIntent = new Intent(getApplicationContext(), PlanlistActivity.class);
                           // startActivity(PlistIntent);
                            break;
                    }
                }else{
                    switch (childPosition ){
                        case 0:
                            Toast.makeText(getApplicationContext(),"child_list: "+childPosition,Toast.LENGTH_LONG).show();
                            Intent mywishIntent = new Intent(getApplicationContext(), MywishActivity.class);
                            startActivity(mywishIntent);
                            break;
                    }
                }
                return true;



            }
        });
        //그룹이 열릴 경우 이벤트
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),groupPosition+" 그룹이 열림",Toast.LENGTH_LONG).show();
                int groupCount = adapter.getGroupCount();

                //한 그룹을 클릭하면 나머지 그룹들은 닫힌다
                for(int i = 0; i < groupCount; i++){
                    if(!(i == groupPosition))
                        listView.collapseGroup(i);
                }
            }
        });


    }
    private void loadParentData(){
        Parent parent = new Parent();

        List<Child> listChild = new ArrayList<Child>();
        Child child = new Child();

        //1
        parent.setData("나의여행계획");
        child.setChildData("여행계획세우기");
        listChild.add(child);

        child = new Child();
        child.setChildData("나의 지난 계획");
        listChild.add(child);

        parent.setItems(listChild);
        listParent.add(parent);

        //2
        parent = new Parent();
        listChild = new ArrayList<Child>();
        child = new Child();

        parent.setData("나의여행기록");
        child.setChildData("여행기록하기");
        listChild.add(child);

        child = new Child();
        child.setChildData("나의 지난 기록");
        listChild.add(child);

        parent.setItems(listChild);
        listParent.add(parent);

        //3
        parent = new Parent();
        listChild = new ArrayList<Child>();
        child = new Child();

        parent.setData("나의위시리스트");
        child.setChildData("나의위시리스트");
        listChild.add(child);


        parent.setItems(listChild);
        listParent.add(parent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        MenuItem item = menu.findItem(R.id.action_setting2);
        item.setVisible(true);

        return true; //메뉴아이템 화면에 표시
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.home:
                finish();
             //   return true;
            case R.id.action_setting2:
                Intent settingIntent = new Intent(this,SettingActivity.class);
                startActivity(settingIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }




}
