package com.example.micha.projectmcqueen.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.micha.projectmcqueen.Models.AlphabethItem;
import com.example.micha.projectmcqueen.R;

/**
 * Created by micha on 03.10.2017.
 */

public class AlphabethAdapter extends RecyclerView.Adapter<AlphabethAdapter.AlphabethAdapterViewHolder> {
    private final Context mContext;
    private final AlphabethAdapterOnClickHandler mClickHandler;
    public final AlphabethItem[] mAlphabeth;

    public int ListIndex;
    public int ItemIndex = -1;

    public interface AlphabethAdapterOnClickHandler {
        void onClick(int index);
    }

    public AlphabethAdapter(@NonNull Context context, AlphabethAdapterOnClickHandler handler) {
        mContext = context;
        mClickHandler = handler;
        mAlphabeth = AlphabethItem.GetAlphabeth();
    }

    public void LoadSelectedItem(){
        mClickHandler.onClick(ListIndex);
    }

    @Override
    public AlphabethAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alphabeth_list_item, parent, false);
        view.setFocusable(true);
        return new AlphabethAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlphabethAdapterViewHolder holder, int position) {
        holder.LetterTextView.setText(mAlphabeth[position].Letter);
    }

    @Override
    public int getItemCount() {
        return mAlphabeth.length;
    }

    class AlphabethAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView LetterTextView;

        public AlphabethAdapterViewHolder(View view) {
            super(view);

            LetterTextView = (TextView) view.findViewById(R.id.tv_letter);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ListIndex = getAdapterPosition();
            ItemIndex = -1;
            mClickHandler.onClick(ListIndex);
        }
    }
}
