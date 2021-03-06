package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class tweetsAdapter extends RecyclerView.Adapter<tweetsAdapter.ViewHolder> {
    // Bind values based on the postiion of the element
    Context context;
    List<Tweet> tweets;

    public tweetsAdapter (Context context, List<Tweet> tweets){
        this.context = context;
        this.tweets = tweets;
    }


    // for each row. inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate(R.layout.item_tweet, parent , false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data at position
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
        //Bind the tweet with view holder
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }
    // pass in the context and list of tweets

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }





    // define a viewholder

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            ivProfileImage = itemView.findViewById( R.id.ivProfileImage );
            tvBody = itemView.findViewById( R.id.tvBody );
            tvScreenName = itemView.findViewById( R.id.tvScreenName );

        }

        public void bind(Tweet tweet) {
            tvBody.setText( tweet.body );
            tvScreenName.setText( tweet.user.screenName );
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
        }
    }

}
