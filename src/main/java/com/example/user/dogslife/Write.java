package com.example.user.dogslife;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Write extends Activity {

    private CheckBox Beauty, Vaccinate , Etc , Hosptial ;
    private TextView Date, Note ;
    private Button Save ;
    static final int DATE_DIALOG_ID = 0;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_write);

        Beauty = (CheckBox) findViewById(R.id.Beauty);
        Vaccinate = (CheckBox) findViewById(R.id.Vaccinate);
        Etc = (CheckBox) findViewById(R.id.Etc);
        Hosptial = (CheckBox) findViewById(R.id.Hosptial);
        Date = (TextView) findViewById(R.id.Date);
        Note = (TextView) findViewById(R.id.Note);
        Save = (Button) findViewById(R.id.Save);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Date.setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        }) ;

        Save.setOnClickListener(new Button.OnClickListener() { // 중복선택 불가능.
            public void onClick(View v) {
                if (CheckBox_Limit(Beauty, Vaccinate, Etc, Hosptial) == true) {
                    Toast.makeText(getApplicationContext(), "You must select just one category", Toast.LENGTH_LONG).show();
                }
                else {
                    Calendar oCalendar = Calendar.getInstance();
                    String  Time = oCalendar.get(Calendar.YEAR) +  "/" + (oCalendar.get(Calendar.MONTH)+1) + "/" + oCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
                            oCalendar.get(Calendar.HOUR_OF_DAY) + ":" + oCalendar.get(Calendar.MINUTE)  ;
                    Intent intent = new Intent(getApplicationContext(), Diary.class);
                    intent.putExtra("DATE", Date.getText().toString());
                    intent.putExtra("NOTE", Note.getText().toString());
                    intent.putExtra("TIME", Time) ;
                    intent.putExtra("CATEGORY",CheckBox_Return(Beauty, Vaccinate, Hosptial, Etc));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case DATE_DIALOG_ID:
            {
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            }
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    Date.setText(String.valueOf(mYear)+ "년" + " " + String.valueOf(mMonth+1) + "월" + " " + String.valueOf(mDay) + "일"); // 날짜 수정후 수정.
                }
            };

    public boolean CheckBox_Limit(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4) { // 카테고리 하나만 선택하게 하기.
        if ( (ch1.isChecked() && !ch2.isChecked() && !ch3.isChecked() && !ch4.isChecked()) ||  (!ch1.isChecked() && ch2.isChecked() && !ch3.isChecked() && !ch4.isChecked()) ||
                (!ch1.isChecked() && !ch2.isChecked() && ch3.isChecked() && !ch4.isChecked()) ||  (!ch1.isChecked() && !ch2.isChecked() && !ch3.isChecked() && ch4.isChecked()))
            return false ;
        else
            return true ;
    }

    public String CheckBox_Return(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4) { // 카테고리 리턴값 정하기
        if (ch1.isChecked())
            return "1" ;
        else if (ch2.isChecked())
            return "2" ;
        else if (ch3.isChecked())
            return "3" ;
        else
            return "4" ;
    }

}
