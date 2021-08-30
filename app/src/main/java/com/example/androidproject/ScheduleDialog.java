package com.example.androidproject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ScheduleDialog extends AppCompatDialogFragment {

    private EditText et_time,et_place;
    private ScheduleDialogListner listner;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_scheduledialog, null);

        builder.setView(view)
                .setTitle("Schedule Details")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Schedule", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String time=et_time.getText().toString();
                        String place=et_place.getText().toString();
                        listner.applyTexts(time,place);
                    }
                });
        et_time=view.findViewById(R.id.et_time);
        et_place=view.findViewById(R.id.et_place);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listner=(ScheduleDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement ExampleDialogListner");
        }
    }

    public interface ScheduleDialogListner{
        void applyTexts(String time, String place);
    }
}