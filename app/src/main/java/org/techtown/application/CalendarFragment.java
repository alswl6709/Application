package org.techtown.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CalendarFragment extends Fragment {
    PlanActivity planActivity;
    private static final String TAG = "CalendarFragment";
    private CalendarView mCalendarView;
    private Button btnGoToPlan;
    private TextView txtPlace;

    //프래그먼트가 액티비티에 attach될 때 호출
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        planActivity = (PlanActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        planActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.calendar,container,false);

        txtPlace = (TextView)rootView.findViewById(R.id.txtPlace);

        if(getArguments() != null){
            String place = getArguments().getString("place");
            txtPlace.setText(place);
        }


        mCalendarView = (CalendarView)rootView.findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                final String date = year + "/" + (month+1) + "/" +dayOfMonth;

                btnGoToPlan = rootView.findViewById(R.id.btnGoToPlan);
                btnGoToPlan.setText(date+"/ 등록하기");
                btnGoToPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(),PlanActivity2.class);
                        intent.putExtra("date", date);
                        intent.putExtra("place",txtPlace.getText().toString());

                        startActivity(intent);
                    }
                });

            }
        });

        return rootView;
    }
}
