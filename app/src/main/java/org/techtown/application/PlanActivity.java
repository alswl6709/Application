package org.techtown.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlanActivity extends AppCompatActivity {
    ActionBar actionBar;
    Toolbar toolbar;

    CalendarFragment calendarFragment;
    EditText txtPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setTitle("Plan");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //달력
        calendarFragment = new CalendarFragment();
        txtPlace = findViewById(R.id.txtPlace);

        Button btnCalendar = findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.calendarContainer,calendarFragment).commit();
                Bundle bundle = new Bundle();
                bundle.putString("place",txtPlace.getText().toString());
                calendarFragment.setArguments(bundle);
            }
        });
    }

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
