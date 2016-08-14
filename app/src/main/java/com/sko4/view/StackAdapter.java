package com.sko4.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sko4.R;
import com.sko4.model.Details;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mayboroda on 8/10/16.
 */
public class StackAdapter extends RecyclerView.Adapter<StackAdapter.ViewHolder> {

    private List<Details> detailsList = Collections.emptyList();
    private View.OnClickListener clickListener;

    public StackAdapter(List<Details> detailsList,
                        View.OnClickListener clickListener) {
        this.detailsList = detailsList;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StackItem view = (StackItem) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(detailsList.get(position));
    }

    @Override
    public int getItemCount() { return detailsList.size(); }

    public final class ViewHolder extends RecyclerView.ViewHolder {

        private StackItem itemView;

        public ViewHolder(StackItem itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public void bind(Details details){
            itemView.bind(details, clickListener);
        }
    }
}
