package com.webingate.paysmartbusinessapp.customerapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
public class RideLater_Dialoguebox extends Dialog implements
        View.OnClickListener {

    @BindView(R.id.date)
    AppCompatButton date;
    @BindView(R.id.time)
    AppCompatButton time;
    @BindView(R.id.book)
    Button book;
    @BindView(R.id.cancel)
    Button cancel;

    //UI References
    boolean dateset = false, timeset = false, issameday = false;
    private DatePickerDialog DatePickerDialog;
    private TimePickerDialog TimePickerDialog;

    private SimpleDateFormat dateFormatter;
    public Activity c;
    String bookingDate,bookingTime;
    RideLater rideLater;
    public interface RideLater{
        public void RideLater(String date,String time);
    }
    public RideLater_Dialoguebox(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        rideLater= (RideLater) a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ridelater_dialogue);
        ButterKnife.bind(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        book.setOnClickListener(this);
        cancel.setOnClickListener(this);
        setDateTimeField();
    }


    private void setDateTimeField() {


        Calendar newCalendar = Calendar.getInstance();
        final int year = newCalendar.get(Calendar.YEAR), month = newCalendar.get(Calendar.MONTH), day = newCalendar.get(Calendar.DAY_OF_MONTH);
        final int hours = newCalendar.get(Calendar.HOUR), minutes = newCalendar.get(Calendar.MINUTE);
        DatePickerDialog = new DatePickerDialog(c, R.style.Dialog_Theme, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                if (selectedYear < year || selectedMonth < month || selectedDay < day) {
                    Toast.makeText(c, "Please select Correct Date", Toast.LENGTH_SHORT).show();
                } else {
                    if (selectedDay == day)
                        issameday = true;
                    else
                        issameday = false;
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(selectedYear, selectedMonth, selectedDay);
                    bookingDate = selectedDay + "/" + selectedMonth + "/" + selectedYear;
                    date.setText(dateFormatter.format(newDate.getTime()));
                    dateset = true;
                }
            }

        }, year, month, day);

        TimePickerDialog = new TimePickerDialog(c, R.style.Dialog_Theme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                //if time cuurrent time is 23:00
                if ((((selectedHour * 60) + selectedMinute - 60) < ((hours * 60) + minutes)) && issameday) {
                    Toast.makeText(c, "Please select Correct Time ", Toast.LENGTH_SHORT).show();
                } else {
                    time.setText(selectedHour + ":" + selectedMinute);
                    bookingTime = selectedHour + ":" + selectedMinute + ":00";
                    timeset = true;
                }
            }
        }, hours, minutes, false);

    }

    @Override
    public void onClick(View view) {

        if (view == date) {
            DatePickerDialog.show();
        } else if (view == time) {
            if (!dateset) {
                Toast.makeText(c, "Please select Date First ", Toast.LENGTH_SHORT).show();
            } else {
                TimePickerDialog.show();
            }
        } else if (view == book) {
            if (dateset && timeset) {
                rideLater.RideLater(bookingDate,bookingTime);
                dismiss();
            } else
                Toast.makeText(c, "Plrease Select Date And Time", Toast.LENGTH_SHORT).show();
        } else if (view == cancel) {
            dismiss();
        }
    }
}