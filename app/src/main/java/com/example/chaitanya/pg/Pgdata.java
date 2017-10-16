package com.example.chaitanya.pg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Created by chaitanya on 14-Oct-17.
 */

public class Pgdata extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;
    Bitmap bmp;
    MyCustomAdapter myCustomPagerAdapter;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgdata);
        byte[] byteArray = getIntent().getByteArrayExtra("id");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        b1 = (Button) findViewById(R.id.btt1);
        b2 = (Button) findViewById(R.id.btt2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        myCustomPagerAdapter = new MyCustomAdapter(this, bmp);
        viewPager.setAdapter(myCustomPagerAdapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            //prev button
            case R.id.btt2:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                break;
            //nextbutton, 1st argument : pageindex, 2nd argument : Enable smooth
            case R.id.btt1:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                break;
        }
    }
}

