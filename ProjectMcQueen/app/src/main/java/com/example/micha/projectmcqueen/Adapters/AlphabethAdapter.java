package com.example.micha.projectmcqueen.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.micha.projectmcqueen.models.Alphabeth;
import com.example.micha.projectmcqueen.models.AlphabethItem;
import com.example.micha.projectmcqueen.R;

import java.util.List;

/**
 * Created by micha on 03.10.2017.
 */

public class AlphabethAdapter extends RecyclerView.Adapter<AlphabethAdapter.AlphabethAdapterViewHolder> {
    private final Context mContext;
    private final AlphabethAdapterOnClickHandler mClickHandler;
    private final List<AlphabethItem> alphabeth;

    public interface AlphabethAdapterOnClickHandler {
        void onLetterClick(int index);
    }

    public AlphabethAdapter(@NonNull Context context, AlphabethAdapterOnClickHandler handler) {
        mContext = context;
        mClickHandler = handler;
        alphabeth = Alphabeth.getAlphabeth();
    }

    @Override
    public AlphabethAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alphabeth_list_item, parent, false);
        view.setFocusable(true);
        return new AlphabethAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlphabethAdapterViewHolder holder, int position) {
        holder.LetterTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "GloriaHallelujah.ttf"));
        holder.LetterTextView.setText(alphabeth.get(position).letter);
    }

    @Override
    public int getItemCount() {
        return alphabeth.size();
    }

    public AlphabethItem getiItem(int index) {
        return alphabeth.get(index);
    }

    class AlphabethAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView LetterTextView;

        private AlphabethAdapterViewHolder(View view) {
            super(view);

            LetterTextView = (TextView) view.findViewById(R.id.tv_letter);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onLetterClick(getAdapterPosition());
        }
    }
}
