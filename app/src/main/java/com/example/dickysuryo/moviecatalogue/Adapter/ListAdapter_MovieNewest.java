package com.example.dickysuryo.moviecatalogue.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dickysuryo.moviecatalogue.Constant;
import com.example.dickysuryo.moviecatalogue.DetailActivity;
import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;
import com.example.dickysuryo.moviecatalogue.R;

import java.util.List;

public class ListAdapter_MovieNewest extends RecyclerView.Adapter<ListAdapter_MovieNewest.MyViewHolder>

{

    private List<MovieNewest_Model.Result> dataList;
    int TYPE_CONTENT_HEADER = 1;
    private Context context;
    public ListAdapter_MovieNewest(Context context, List<MovieNewest_Model.Result> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v ;

        if (viewType == TYPE_CONTENT_HEADER){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_cell, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ; // pass the view to View Holder

        }
        else{
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cell, parent, false);
            // set the view's size, margins, paddings and layout parameters
            // pass the view to View Holder

        }
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (position == 0) {
            setupHeadline(holder, position);
        } else {
            holder.rating.setText(dataList.get(position).getVoteAverage().toString());
            holder.description.setText(dataList.get(position).getOverview());
            holder.title.setText(dataList.get(position).getTitle());
            Glide
                    .with(context)
                    .load(Constant.BASE_URL_IMAGE+dataList.get(position).getPosterPath())
                    .into(holder.image);
            // implement setOnClickListener event on item view.
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // display a toast with person name on item click
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("detailMovieNewest", (Parcelable) dataList.get(position));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return getItemId(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name, title, description,rating;
        ImageView image, imageHeader;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            imageHeader = (ImageView) itemView.findViewById(R.id.imgHeadline);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            image = (ImageView) itemView.findViewById(R.id.image_movie);
            description = (TextView) itemView.findViewById(R.id.txtDescription);
            rating = (TextView) itemView.findViewById(R.id.rating);

        }


    }

    public void setupHeadline(@NonNull MyViewHolder holder, final int position) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        holder.title.setText(dataList.get(position).getTitle());

        holder.description.setText(dataList.get(position).getOverview());
        Glide
                .with(context)

                .load(Constant.BASE_URL_IMAGE+dataList.get(position).getPosterPath())
                .into(holder.imageHeader);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detailMovieNewest", (Parcelable) dataList.get(position));
                context.startActivity(intent);
            }
        });
    }
}