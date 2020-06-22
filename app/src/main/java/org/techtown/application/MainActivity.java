package org.techtown.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //ActionBar actionBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


       // actionBar = getSupportActionBar();
        //actionBar.setTitle("main menu");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
       // actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("main");
       // actionBar.setDisplayShowTitleEnabled(false);
       // actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        MenuItem item1 = menu.findItem(R.id.action_mypage);
        item1.setVisible(true);
        MenuItem item2 = menu.findItem(R.id.action_setting);
        item2.setVisible(true);
        return true; //메뉴아이템 화면에 표시
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        Intent main= getIntent();
        String id= main.getStringExtra("id");

        switch (curId){
            case R.id.action_mypage:
                Intent mypageIntent = new Intent(this,MypageActivity.class);
                mypageIntent.putExtra("id",id); //로그인 시 입력한 id 정보 mypage로 넘김
                startActivity(mypageIntent);

                showToast("마이페이지 메뉴 선택");//잘동작하는지 확인 나중에 지움

                break;
            case R.id.action_setting:
                Intent settingIntent = new Intent(this,SettingActivity.class);
                startActivity(settingIntent);
                showToast("설정 메뉴 선택");
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //잘동작하면 나중에 지움
    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));

    }
}
