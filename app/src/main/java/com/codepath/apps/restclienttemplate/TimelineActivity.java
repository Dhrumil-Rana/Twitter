package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class TimelineActivity extends AppCompatActivity {
    TwitterClient client;
    RecyclerView rvTweets;
    List<Tweet> tweets;
    tweetsAdapter adapter;

    public static final String TAG = "TimeLineActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_timeline );

        client = TwitterApp.getRestClient( this );

        // Find the recylce view
        rvTweets = findViewById( R.id.rvTweets );
        // init the list of tweets and adapter
        tweets = new ArrayList<>();
        adapter = new tweetsAdapter(  this, tweets);

        // recylce view setup: layout manager and the adpter
        rvTweets.setLayoutManager( new LinearLayoutManager( this ) );
        rvTweets.setAdapter( adapter );
        populateHomeTimeline();
        

    }

    private void populateHomeTimeline() {
        client.getHomeTimeLine( new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.i(TAG, "onSuccess!" + json.toString());
                JSONArray jsonArray = json.jsonArray;
                try {
                    tweets.addAll( Tweet.fromJsonArray( jsonArray ));
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "json exception", e);

                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.i(TAG, "onFailure!"+ response, throwable);
            }
        } );
    }

}