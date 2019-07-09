package com.example.jsonparse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.models.Item;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Item> itemList = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_images, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        Picasso.with(mContext).load(item.getLink())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(Flickr flickr) {
        this.itemList = flickr.getItems();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgView);
        }
    }
}
