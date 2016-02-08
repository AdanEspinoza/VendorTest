package com.mobile.apps.test.activitites;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobile.apps.test.R;
import com.mobile.apps.test.adapter.MovieAdapter;
import com.mobile.apps.test.beans.Result;
import com.mobile.apps.test.beans.ResultsResponse;
import com.mobile.apps.test.network.Retrofit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snakelogan on 2/8/16.
 */
public class MovieActivity extends AppCompatActivity {
    private static final String TAG = MovieActivity.class.getSimpleName();
    List<Result> mResultArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mRecyclerView = (RecyclerView) findViewById(R.id.movies_rv);
        new LoadRecyclerViewAsyncTask().execute();
    }

    private void getMoviesFromServer(){
        String path = Retrofit.path;
        ResultsResponse resultsResponse = Retrofit.getService().getMovies(path);
        mResultArrayList = resultsResponse.getResults();
    }


    public class LoadRecyclerViewAsyncTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            getMoviesFromServer();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result) {
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MovieActivity.this));
                mAdapter = new MovieAdapter(MovieActivity.this, mResultArrayList);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }
}
