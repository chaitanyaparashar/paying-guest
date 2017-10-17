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
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by Jaspreet on 13-Aug-17.
 */

public class MyCustomAdapter extends PagerAdapter {
    Context context;
    Bitmap[] images;
    LayoutInflater layoutInflater;


    public MyCustomAdapter(Context context, Bitmap[] images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageBitmap(images[position]);

        container.addView(itemView);

        //listening to image click

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SwipeActivity.class);
                // passing array index
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                images[0].compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                i.putExtra("id", byteArray);

                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                images[1].compress(Bitmap.CompressFormat.PNG, 100, stream2);
                byte[] byteArray2 = stream2.toByteArray();
                i.putExtra("id2", byteArray2);

                ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                images[2].compress(Bitmap.CompressFormat.PNG, 100, stream3);
                byte[] byteArray3 = stream3.toByteArray();
                i.putExtra("id3", byteArray3);
                context.startActivity(i);
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}