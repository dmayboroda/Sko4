package com.sko4.view;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sko4.R;
import com.sko4.Utils;
import com.sko4.model.Event;
import com.sko4.model.EventsWrapper;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Items adapter for bindable.
 * Created by Mayboroda.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>
        implements Action1<EventsWrapper> {

    public interface Chooser {
        void onChoose(View view, Event event);
    }

    private List<Event> items = Collections.emptyList();
    private final Picasso picasso;
    private final Chooser chooser;

    public ItemsAdapter(Picasso picasso, Chooser chooser) {
        this.picasso = picasso;
        this.chooser = chooser;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() { return items.size(); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public void call(EventsWrapper wrapper) {
        List<Event> items = wrapper.getEvents();
        this.items = items;
        notifyDataSetChanged();
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_preview) ImageView preview;
        @Bind(R.id.item_plus)    TextView plus;
        @Bind(R.id.item_name)    TextView name;
        @Bind(R.id.item_cost)    TextView cost;
        @Bind(R.id.item_month)   TextView month;
        @Bind(R.id.item_day)     TextView day;

        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            name.setTypeface(Utils.typeface(itemView.getContext(), Utils.ROBOTO_CON_LIGHT));
            plus.setTypeface(Utils.typeface(itemView.getContext(), Utils.ROBOTO_CON_LIGHT));
            cost.setTypeface(Utils.typeface(itemView.getContext(), Utils.ROBOTO_CON_LIGHT));
            month.setTypeface(Utils.typeface(itemView.getContext(), Utils.ROBOTO_LIGHT));
            day.setTypeface(Utils.typeface(itemView.getContext(), Utils.ROBOTO_BLACK));
            this.itemView = itemView;
        }

        public void bind(final Event bindable) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chooser.onChoose(preview, bindable);
                }
            });
            name.setText(bindable.getName());
            if (TextUtils.isEmpty(bindable.getVendor())) {
                plus.setVisibility(View.GONE);
            } else {
                plus.setVisibility(View.VISIBLE);
                plus.setText(bindable.getVendor());
            }

            if (TextUtils.isEmpty(bindable.getPrice())) {
                cost.setVisibility(View.GONE);
            } else {
                String free = itemView.getResources().getString(R.string.free);
                cost.setText(bindable.getPrice().replace("Free", free));
                cost.setVisibility(View.VISIBLE);
            }
            picasso.load(bindable.getUrl())
                    .into(preview);
            DateTime time = bindable.getStartDate();
            if (time != null) {
                month.setVisibility(View.VISIBLE);
                day.setVisibility(View.VISIBLE);
                String monthStr = time.toString("MMM", Locale.US);
                int dayNum = time.getDayOfMonth();
                month.setText(monthStr.toUpperCase());
                day.setText(String.valueOf(dayNum));
            } else {
                month.setVisibility(View.GONE);
                day.setVisibility(View.GONE);
            }
        }

    }
}
