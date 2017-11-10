package com.example.micha.projectmcqueen.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.micha.projectmcqueen.R;

/**
 * Created by micha on 03.10.2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {
    private final Context mContext;
    private final MainAdapterOnClickHandler mClickHandler;

    public interface MainAdapterOnClickHandler {
        void onClick(int index);
    }

    public MainAdapter(@NonNull Context context, MainAdapterOnClickHandler handler) {
        mContext = context;
        mClickHandler = handler;
    }

    @Override
    public MainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_list_item, parent, false);
        view.setFocusable(true);
        return new MainAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapterViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.titleTextView.setText(R.string.alphabeth_title);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    class MainAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView titleTextView;
        public final ImageView mImageView;

        MainAdapterViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.tv_title);
            mImageView = (ImageView) view.findViewById(R.id.imageView);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onClick(getAdapterPosition());
        }
    }
}


