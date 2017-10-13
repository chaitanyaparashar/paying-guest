package com.example.chaitanya.pg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitanya on 19-Aug-17.
 */

public class filtr extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,AdapterView.OnItemSelectedListener,View.OnClickListener{
    SeekBar sb;
    int seekvalue;
    private RadioGroup radiGroup;
    private RadioGroup radiGroup2;
    private RadioGroup radiGroup3;
    private RadioButton radib;
    String radibres;
    private RadioButton radib2;
    String radibres2;
    private RadioButton radib3;
    String radibres3;
    Button submit;
    private ProgressDialog pDialog;
    JSONArray code;
    String spinnervalue;
    List<String> categories;
    JSONParser jParser = new JSONParser();
    private static String url ="https://podgier-woman.000webhostapp.com/filter.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtr);

        radiGroup=(RadioGroup)findViewById(R.id.rb1);
        radiGroup2=(RadioGroup)findViewById(R.id.db1);
        radiGroup3=(RadioGroup)findViewById(R.id.cb1);
        sb = (SeekBar)findViewById(R.id.seekBar);
        int selectedId=radiGroup.getCheckedRadioButtonId();
        int selectedId2=radiGroup2.getCheckedRadioButtonId();
        int selectedId3=radiGroup3.getCheckedRadioButtonId();
        radib=(RadioButton)findViewById(selectedId);
        radib2=(RadioButton)findViewById(selectedId2);
        radib3=(RadioButton)findViewById(selectedId3);
        submit = (Button)findViewById(R.id.btn1);
        sb.setMax(15000);

        radiGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId)
                                                  {
                                                      radib = (RadioButton) findViewById(checkedId);
                                                      radibres = radib.getText().toString();
                                                  }
                                              }
        );
        radiGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                 @Override
                                                 public void onCheckedChanged(RadioGroup group, int checkedId)
                                                 {
                                                     radib2 = (RadioButton) findViewById(checkedId);
                                                     radibres2 = radib2.getText().toString();
                                                 }
                                             }
        );
        radiGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                 @Override
                                                 public void onCheckedChanged(RadioGroup group, int checkedId)
                                                 {
                                                     radib3 = (RadioButton) findViewById(checkedId);
                                                     radibres3 = radib3.getText().toString();
                                                 }
                                             }
        );

        submit.setOnClickListener(this);
        sb.setProgress(0);


        sb.setOnSeekBarChangeListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        categories = new ArrayList<String>();
        categories.add("Choose Accomadation");
        categories.add("Single accomodation");
        categories.add("2-person sharing");
        categories.add("3-person sharing");
        categories.add("4-person sharing");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
               spinnervalue =  categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }


        @Override
    public void onProgressChanged(SeekBar v, int progress, boolean isUser) {

        TextView tv = (TextView)findViewById(R.id.percent);

            progress=progress/100;
            progress=progress*100;
            seekvalue = progress;
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

    @Override
    public void onClick(View view) {
        if(seekvalue==0){
            Toast.makeText(this,"Please Enter Price Range",Toast.LENGTH_LONG).show();
        }
        else if(spinnervalue.equalsIgnoreCase("Choose Accomadation")){
            Toast.makeText(this,"Please Select Type of accomodation",Toast.LENGTH_LONG).show();
        }
        else if(radibres == null){
            Toast.makeText(this,"Please Select Food facility Choice",Toast.LENGTH_LONG).show();
        }
        else if(radibres3 == null){
            Toast.makeText(this,"Please Enter Pg for choice",Toast.LENGTH_LONG).show();
        }
        else if(radibres2 == null){
            Toast.makeText(this,"Please Enter Air Conditioner facility Choice",Toast.LENGTH_LONG).show();
        }

        else{
            gro g1 = new gro();
            g1.execute();
        }
        Log.d("seeeeeeeeeekbaaaar",""+seekvalue);
        Log.d("seeeeeeeeeekbaaaar",""+spinnervalue);
        Log.d("seeeeeeeeeekbaaaar",""+radibres);
        Log.d("seeeeeeeeeekbaaaar",""+radibres2);
        Log.d("seeeeeeeeeekbaaaar",""+radibres3);
        }


    class gro extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            java.util.List<NameValuePair> pr = new ArrayList<NameValuePair>();

            pr.add(new BasicNameValuePair("price", String.valueOf(seekvalue)));
            pr.add(new BasicNameValuePair("accomodation",spinnervalue ));
            pr.add(new BasicNameValuePair("food",radibres));
            pr.add(new BasicNameValuePair("ac",radibres2 ));
            pr.add(new BasicNameValuePair("gender",radibres3 ));

            JSONObject json = jParser.makeHttpRequest(url, "GET", pr);
            try {

                code = json.getJSONArray("code");



            }catch(JSONException e){
                Log.d("fjf","fgf"+e);
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            if(code == null)
            {
                    Toast.makeText(getApplicationContext(),"Sorry no pg available as per your requirements",Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent_name = new Intent();
                intent_name.setClass(getApplicationContext(), MainActivity.class);
                Bundle b = new Bundle();
                b.putString("daattaa", code.toString());
                intent_name.putExtras(b);
                startActivity(intent_name);

            }
        }
    }
}