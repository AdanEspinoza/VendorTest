package com.mobile.apps.test.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.apps.test.R;
import com.mobile.apps.test.beans.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by snakelogan on 2/8/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Result> mResultList;
    private Context mContext;
    private final LayoutInflater mInflater;

    public MovieAdapter(Context context, List<Result> list){
        mResultList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.movie_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Result result = mResultList.get(i);
        String urlLogo = "https://image.tmdb.org/t/p/w370"+result.getPosterPath();
        Uri uri =  Uri.parse(urlLogo);
        Picasso.with(mContext).
                load(uri).
                fit().centerCrop().
                into(viewHolder.mLogo);
        viewHolder.mTitle.setText(result.getTitle());
        viewHolder.mDescription.setText(result.getOverview());

    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mLogo;
        public TextView mTitle, mDescription;

        public ViewHolder(View itemView){
            super(itemView);
            mLogo = (ImageView) itemView.findViewById(R.id.movie_iv_logo);
            mTitle = (TextView) itemView.findViewById(R.id.movie_tv_title);
            mDescription = (TextView) itemView.findViewById(R.id.movie_tv_description);
        }
    }
}