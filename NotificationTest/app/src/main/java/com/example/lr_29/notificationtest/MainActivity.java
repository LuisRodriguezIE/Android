package com.example.lr_29.notificationtest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int notificationId=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set On Listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        EditText editText=findViewById(R.id.editText);
        TimePicker timePicker =findViewById(R.id.timePicker);

        // Fijar la notificacion y el Texto
        Intent intent= new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId",notificationId);
        intent.putExtra("todo",editText.getText().toString());

        //getBroadcat(context, requestCode, intent, flags)
        PendingIntent alarmIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm=(AlarmManager) getSystemService(ALARM_SERVICE);
        switch(view.getId()){

            case R.id.setBtn:
                int hour=timePicker.getCurrentHour();
                int minute=timePicker.getCurrentMinute();

                //Create time.

                Calendar starTime= Calendar.getInstance();
                starTime.set(Calendar.HOUR_OF_DAY,hour);
                starTime.set(Calendar.MINUTE, minute);
                starTime.set(Calendar.SECOND,0);

                long alarmStartTime=starTime.getTimeInMillis();

                // Fija la alarma
                // set(type, milliseconds, intent)
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText(this,"Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarm.cancel(alarmIntent);
                Toast.makeText(this,"Canceled.", Toast.LENGTH_SHORT).show();
                break;


        }

    }
}





















