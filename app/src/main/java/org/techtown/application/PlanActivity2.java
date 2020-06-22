package org.techtown.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PlanActivity2 extends AppCompatActivity {

    private TextView txtDate;
    private TextView txtPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan2);

        txtDate = findViewById(R.id.txtDate);
        txtPlace = findViewById(R.id.txtPlace);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        txtDate.setText(date);

        String place = incomingIntent.getStringExtra("place");
        txtPlace.setText(place+" 여행");

        //리싸이클러뷰
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        DayAdapter adapter = new DayAdapter();

        adapter.addItem(new Day(date));

        recyclerView.setAdapter(adapter);

    }
}