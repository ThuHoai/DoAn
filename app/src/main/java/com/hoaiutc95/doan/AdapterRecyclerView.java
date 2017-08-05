package com.hoaiutc95.doan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Thu Hoai on 4/4/2017.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.Contact> {
    private Integer[] listData;

    public AdapterRecyclerView(Integer[] listData) {
        this.listData = listData;
    }

    @Override
    public Contact onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item_recycleview, parent, false);
        return new Contact(itemview);
    }

    @Override
    public void onBindViewHolder(Contact holder, int position) {
        holder.img.setImageResource(listData[position]);
    }



    @Override
    public int getItemCount() {
        return listData.length;
    }




    private static OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener lis){
        this.listener = lis;
    }


    public interface OnItemClickListener{
        public void onItemClick(View v, int possition);
    }


    public class  Contact extends RecyclerView.ViewHolder{
        ImageView img;

        public Contact(final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        listener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });
        }


    }
}
