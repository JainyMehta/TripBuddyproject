package com.example.androidproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Plan_a_DayActivity extends AppCompatActivity {
    Button btn_start, btn_end;
    EditText et_start, et_end,et_name;
    Button btn;
    String str_start, str_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_a__day);

        btn_start=findViewById(R.id.btn_start);
        btn_end=findViewById(R.id.btn_end);
        btn=findViewById(R.id.btn_submit);
        et_start=findViewById(R.id.et_start);
        et_end=findViewById(R.id.et_end);
        et_name=findViewById(R.id.et_name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThingsToDoDatabaseAccess db1 =ThingsToDoDatabaseAccess.getInstance(getApplicationContext());

                Boolean result=db1.insertPlan(1,et_name.getText().toString(),et_start.getText().toString(),et_end.getText().toString());
                int i=db1.getPlanID();
                if (result == true) {

                    Toast.makeText(Plan_a_DayActivity.this, "Inserted Successfull!!"+i, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Plan_a_DayActivity.this,ScheduleDayActivity.class);
                    intent.putExtra("plan_id",i);
                    intent.putExtra("start_date",str_start);
                    intent.putExtra("end_date",str_end);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Plan_a_DayActivity.this, "Insert UnSuccessfull!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(2);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(final int id) {
        DatePickerDialog dp=new DatePickerDialog(Plan_a_DayActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(id==1)
                {

                    str_start=dayOfMonth + "/" + (month + 1) + "/" + year;
                    et_start.setText(str_start);
                }
                else if(id==2)
                {
                    str_end=dayOfMonth + "/" + (month + 1) + "/" + year;
                    et_end.setText(str_end);
                }
            }
        },2018,10,19);
        return dp;
    }
}
