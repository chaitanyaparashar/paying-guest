package com.example.chaitanya.pg;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by chaitanya on 12-Aug-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[] values;
    Context context1;
    Integer [] imag;
    public ImageView img;
    TextView tx1;
    TextView tx2;
    public RecyclerViewAdapter(Context context2,String[] values2,Integer[] imags){

        values = values2;
        this.imag = imags;
        context1 = context2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View v){

            super(v);

            img = (ImageView) v.findViewById(R.id.imgview1);
            tx1 = (TextView) v.findViewById(R.id.tx1);
            tx2 = (TextView) v.findViewById(R.id.tx2);
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

        img.setImageResource(imag[position]);

        Vholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //........................
            }
        });

    }

    @Override
    public int getItemCount(){

        return imag.length;
    }

}