package com.example.rentasavari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomePage extends AppCompatActivity {
    TextView startDate, startDay, startTime,startAM_PM, endDate, endDay, endTime,endAM_PM;
    RelativeLayout startLayout, endLayout;
    Button findcarbtn,filterbtn;
    RecyclerView recyclerView;
    boolean startCheck = false,endCheck=false;
    String []arr={"Item1","Item2","Item3","Item4","Item5","Item6","Item7","Item8","Item9","Item10","Item2","Item3","Item4","Item5","Item6","Item7","Item8","Item9","Item10"};
    CarListAdapter clAdapter = new CarListAdapter(arr);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startDate = findViewById(R.id.startdate);
        startDay = findViewById(R.id.startday);
        startTime = findViewById(R.id.starttime);
        startAM_PM = findViewById(R.id.startAM_PM);
        endDate = findViewById(R.id.enddate);
        endDay = findViewById(R.id.endday);
        endTime = findViewById(R.id.endtime);
        endAM_PM = findViewById(R.id.endAM_PM);
        startLayout = findViewById(R.id.startLayout);
        endLayout = findViewById(R.id.endLayout);
        findcarbtn = findViewById(R.id.findcarbtn);
        filterbtn = findViewById(R.id.filterbtn);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        startLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCheck = true;
                startDateTimeDialog();
            }
        });
        endLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endCheck = true;
                endDateTimeDialog();
            }
        });
        findcarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!startCheck || !endCheck)
                {
                    Toast.makeText(HomePage.this,"Please Schedule Your Pickup And Drop Timings",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    showCarList();
                }
            }
        });
        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!startCheck || !endCheck)
                {
                    Toast.makeText(HomePage.this,"Please Schedule Your Pickup And Drop Timings",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    showCarList();
                }
            }
        });
    }

    private void startDateTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay==12)
                        {
                            startAM_PM.setText("PM");
                        }
                        else if(hourOfDay>12)
                        {
                            startAM_PM.setText("PM");
                            hourOfDay = hourOfDay -12;
                        }
                        else
                        {
                            startAM_PM.setText("AM");
                        }
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        SimpleDateFormat startDayFormat = new SimpleDateFormat("EEEE");
                        startDay.setText(startDayFormat.format(calendar.getTime()));
                        SimpleDateFormat startDateFormat = new SimpleDateFormat("MMMM  dd  yyyy");
                        startDate.setText(startDateFormat.format(calendar.getTime()));
                        SimpleDateFormat starttimeFormat = new SimpleDateFormat("HH:mm");
                        startTime.setText(starttimeFormat.format(calendar.getTime()));

                    }
                };
                new TimePickerDialog(HomePage.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(HomePage.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void endDateTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay==12)
                        {
                            endAM_PM.setText("PM");
                        }
                        else if(hourOfDay>12)
                        {
                            endAM_PM.setText("PM");
                            hourOfDay = hourOfDay -12;
                        }
                        else
                        {
                            endAM_PM.setText("AM");
                        }
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        SimpleDateFormat endDayFormat = new SimpleDateFormat("EEEE");
                        endDay.setText(endDayFormat.format(calendar.getTime()));
                        SimpleDateFormat endDateFormat = new SimpleDateFormat("MMMM  dd  yyyy");
                        endDate.setText(endDateFormat.format(calendar.getTime()));
                        SimpleDateFormat endtimeFormat = new SimpleDateFormat("HH:mm");
                        endTime.setText(endtimeFormat.format(calendar.getTime()));

                    }
                };
                new TimePickerDialog(HomePage.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(HomePage.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showCarList() {
        recyclerView.setAdapter(clAdapter);
    }
}