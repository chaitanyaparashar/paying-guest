package com.example.chaitanya.pg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by chaitanya on 14-Oct-17.
 */

public class Pgdata extends AppCompatActivity{
    ViewPager viewPager;
    Bitmap bmp;
    MyCustomAdapter myCustomPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgdata);
        byte[] byteArray = getIntent().getByteArrayExtra("id");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        myCustomPagerAdapter = new MyCustomAdapter(this, bmp);
        viewPager.setAdapter(myCustomPagerAdapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }
}

