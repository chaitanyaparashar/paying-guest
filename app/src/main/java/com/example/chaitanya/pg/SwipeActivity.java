package com.example.chaitanya.pg;

/**
 * Created by chaitanya on 14-Oct-17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by chaitanya on 14-Oct-17.
 */


public class SwipeActivity extends Activity
{
    Bitmap[] bmp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_view);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        bmp = new Bitmap[3];
        // get intent data
        Intent i = getIntent();

        // Selected image id
        byte[] byteArray = getIntent().getByteArrayExtra("id");

        bmp[0] = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        byte[] byteArray2 = getIntent().getByteArrayExtra("id2");
        bmp[1] = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);

        byte[] byteArray3 = getIntent().getByteArrayExtra("id3");
        bmp[2] = BitmapFactory.decodeByteArray(byteArray3, 0, byteArray3.length);
        int position = i.getExtras().getInt("position");

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpg);
        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        rl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class ImagePagerAdapter extends PagerAdapter
    {

      //  int[] icons = Pgdata.images;

        @Override
        public int getCount()
        {
            //return icons.length();
            return bmp.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            Context context = SwipeActivity.this;
            ImageView imageView = new ImageView(context);
//      int padding = context.getResources().getDimensionPixelSize(
//          R.dimen.padding_large);
//      imageView.setPadding(padding, padding, padding, padding);


                /*
                    requestLayout()
                        Call this when something has changed which has
                        invalidated the layout of this view.
                */


                /*
                    getLayoutParams()
                        Get the LayoutParams associated with this view.
                */

            // Apply the new height for ImageView programmatically


            // Apply the new width for ImageView programmatically

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageBitmap(bmp[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}