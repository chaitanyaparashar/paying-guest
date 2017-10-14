package com.example.chaitanya.pg;

/**
 * Created by chaitanya on 14-Oct-17.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by Jaspreet on 13-Aug-17.
 */

public class MyCustomAdapter extends PagerAdapter {
    Context context;
    Bitmap images;
    LayoutInflater layoutInflater;


    public MyCustomAdapter(Context context, Bitmap images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageBitmap(images);

        container.addView(itemView);

        //listening to image click

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SwipeActivity.class);
                // passing array index
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                images.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                i.putExtra("id", byteArray);
                context.startActivity(i);
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}