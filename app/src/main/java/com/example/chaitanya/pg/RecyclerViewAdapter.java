package com.example.chaitanya.pg;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

/**
 * Created by chaitanya on 12-Aug-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private String[] name;
    private String[] address;
    private String[] price;
    private Bitmap[] imageid;
    private Bitmap[] imageid2;
    private Bitmap[] imageid3;
    private Context context1;
    public ImageView img;
    TextView tx1;
    TextView tx2;
    TextView tx3;
    public RecyclerViewAdapter(Context context2,String[] address,String[] price,String[] name,Bitmap[] imageid,Bitmap[] imageid2,Bitmap[] imageid3){

        this.name = name;
        this.address = address;
        this.price = price;
        this.name = name;
        this.imageid = imageid;
        this.imageid2 = imageid2;
        this.imageid3 = imageid3;
        context1 = context2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View v){

            super(v);

            img = (ImageView) v.findViewById(R.id.imgview1);
            tx1 = (TextView) v.findViewById(R.id.tx1);
            tx2 = (TextView) v.findViewById(R.id.tx2);
            tx3 = (TextView) v.findViewById(R.id.tx3);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view1 = LayoutInflater.from(context1).inflate(R.layout.recycler_view_items,parent,false);

        ViewHolder viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder,final int position){
        if(imageid[position] != null) {
            img.setImageBitmap(imageid[position]);
        }
        else{
            img.setImageResource(R.drawable.tyu);
        }
        MainActivity.countt = 1;
        tx1.setText(""+address[position]+"");
        tx2.setText(""+price[position]+"");
        tx3.setText(""+name[position]+"");
        setFadeAnimation(Vholder.itemView);
        Vholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageid[position].compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                imageid2[position].compress(Bitmap.CompressFormat.PNG, 100, stream2);
                byte[] byteArray2 = stream2.toByteArray();

                ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                imageid3[position].compress(Bitmap.CompressFormat.PNG, 100, stream3);
                byte[] byteArray3 = stream3.toByteArray();

              Intent i = new Intent(context1,Pgdata.class);
                i.putExtra("position",position);
                i.putExtra("id", byteArray);
                i.putExtra("id2", byteArray2);
                i.putExtra("id3", byteArray3);
                context1.startActivity(i);
            }
        });

    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount(){

        return name.length;
    }

}