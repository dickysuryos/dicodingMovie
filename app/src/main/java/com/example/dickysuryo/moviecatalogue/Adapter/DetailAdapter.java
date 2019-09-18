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
import com.example.dickysuryo.moviecatalogue.Constant;
import com.example.dickysuryo.moviecatalogue.DetailActivity;
import com.example.dickysuryo.moviecatalogue.Model.DetailMovie_Model;
import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;
import com.example.dickysuryo.moviecatalogue.R;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {


    private DetailMovie_Model model;
    private Context context;
    List<MovieNewest_Model.Result>  items;

    public DetailAdapter(Context context, DetailMovie_Model model) {
        this.context = context;
        this.model = model;
        items = Constant.items;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        if (i == Constant.CONTENT_TYPE_MOVIE) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_layout, viewGroup, false);
            MyViewHolder vh = new MyViewHolder(view);
            return vh;
        } else if (i == Constant.CONTENT_TYPE_MISC_INFORMATION){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.misc_layout, viewGroup, false);
            MyViewHolder vh = new MyViewHolder(view);
            return vh;
        }
        else if (i == Constant.CONTENT_TYPE_INFORMATION ){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_cell, viewGroup, false);

            MyViewHolder vh = new MyViewHolder(view);
            return vh;
        }
        else if (i == Constant.CONTENT_TYPE_MISC_OTHER){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.misc_layout, viewGroup, false);
            MyViewHolder vh = new MyViewHolder(view);
            return vh;
        }


        else {

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_cell, viewGroup, false);
            MyViewHolder vh = new MyViewHolder(view);


            return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (position == 0) {
            holder.txt_title.setText(model.getTitle());
            holder.txt_description.setText(model.getOverview());
            holder.rating.setText(model.getVoteAverage().toString());
            Glide
                    .with(context)
                    .load(Constant.BASE_URL_IMAGE + model.getBackdropPath())
                    .into(holder.image_header);
        }
        else if (position==1){

            holder.misc.setText(context.getResources().getString(R.string.wording_informasi));

        }
        else  if (position == 2){
            holder.txt_release.setText("Release : "+model.getReleaseDate().toString());
            Glide
                    .with(context)
                    .load(Constant.BASE_URL_IMAGE + model.getPosterPath())
                    .into(holder.img_poster);
            holder.txt_duration.setText(String.valueOf(model.getRuntime()/60) + " hours");
            holder.txt_like.setText(model.getVoteCount().toString());
        }

        else if (position == 3){
            holder.misc.setText(context.getResources().getString(R.string.wording_otherMovie));

        }
        else  {

            setupOtherMovie(holder,position);

        }
    }
    @Override
    public long getItemId(int position) {
        return getItemId(position);
    }

    @Override
    public int getItemCount() {

    if (getItemViewType(1) == Constant.CONTENT_TYPE_MOVIE){
        return 1;
        } else if (getItemViewType(2) == Constant.CONTENT_TYPE_MISC_INFORMATION){
        return 2;
        } else if (getItemViewType(3) == Constant.CONTENT_TYPE_INFORMATION){
        return 3;
        }else if (getItemViewType(4) == Constant.CONTENT_TYPE_MISC_OTHER){
        return 2;
    }

        else return items.size();

    }



    @Override
    public int getItemViewType(int position) {
        if (position == 0) {return Constant.CONTENT_TYPE_MOVIE;}
         else if (position ==1) {return Constant.CONTENT_TYPE_MISC_INFORMATION;}
             else if (position ==2) {return Constant.CONTENT_TYPE_INFORMATION;}
        else if (position ==3) {return Constant.CONTENT_TYPE_MISC_OTHER;}
      else {return 5;}



    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_header,image_movie,img_poster;
        TextView txt_title, txt_description,rating,misc,txt_release,txt_duration,txt_like;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            image_header = (ImageView) itemView.findViewById(R.id.image_header);
            txt_title = (TextView) itemView.findViewById(R.id.txtTitle);
            txt_description = (TextView) itemView.findViewById(R.id.txtDescription);
            rating = (TextView)itemView.findViewById(R.id.rating);
            image_movie = (ImageView)itemView.findViewById(R.id.image_movie);
            misc = (TextView)itemView.findViewById(R.id.txt_misc);
            txt_release = (TextView)itemView.findViewById(R.id.txt_released);
            img_poster = (ImageView)itemView.findViewById(R.id.img_poster);
            txt_duration = (TextView)itemView.findViewById(R.id.txtDuration);
            txt_like = (TextView)itemView.findViewById(R.id.txtLike);
        }
    }

    public void setupOtherMovie(MyViewHolder holder, final int position){
        holder.txt_title.setText(items.get(position).getTitle());
        holder.txt_description.setText(items.get(position).getOverview());
        holder.rating.setText(items.get(position).getVoteAverage().toString());
        Glide
                .with(context)
                .load(Constant.BASE_URL_IMAGE + items.get(position).getPosterPath())
                .into(holder.image_movie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detailMovieNewest", (Parcelable) items.get(position));
                context.startActivity(intent);
            }
        });
    }
}
