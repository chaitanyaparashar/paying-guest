package com.example.chaitanya.pg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitanya on 19-Aug-17.
 */

public class filtr extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,AdapterView.OnItemSelectedListener{
    SeekBar sb;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtr);
        radioSexGroup=(RadioGroup)findViewById(R.id.rb1);
        SeekBar sb = (SeekBar)findViewById(R.id.seekBar);
        int selectedId=radioSexGroup.getCheckedRadioButtonId();
        radioSexButton=(RadioButton)findViewById(selectedId);
        sb.setMax(15000);


        sb.setProgress(0);


        sb.setOnSeekBarChangeListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Choose Accomadation");
        categories.add("Single Room");
        categories.add("2 sharing");
        categories.add("3 sharing");
        categories.add("4 sharing");
        categories.add("5 sharing");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }


        @Override
    public void onProgressChanged(SeekBar v, int progress, boolean isUser) {

        TextView tv = (TextView)findViewById(R.id.percent);

            progress=progress/50;
            progress=progress*50;

        tv.setText(Integer.toString(progress));


    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
// TODO Auto-generated method stub
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
// TODO Auto-generated method stub
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
