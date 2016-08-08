package com.sko4.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sko4.R;
import com.sko4.Utils;
import com.sko4.model.Details;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * artists stack in event view
 * Created by Mayboroda on 6/7/16.
 */
public class ItemStack extends CardView {

    @Bind(R.id.items_container) LinearLayout container;
    @Bind(R.id.show_all)        TextView showAll;

    private LayoutInflater inflater;

    public ItemStack(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(getContext());
        showAll.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void bind(List<Details> details, OnClickListener onClickListener) {
        if (details.isEmpty()) {
            setVisibility(GONE);
            return;
        }

        int maxsize = 5;
        int listSize = details.size();
        int size = listSize >= maxsize ? maxsize : listSize;
        showAll.setVisibility(listSize > maxsize ? VISIBLE : GONE);


        for (int i = 0; i < size; i++) {
            StackItem stackItem = (StackItem)inflater
                    .inflate(R.layout.item_info, null);
            stackItem.setTag(details.get(i));
            stackItem.bind(details.get(i), onClickListener);
            container.addView(stackItem);
        }

        setVisibility(VISIBLE);
    }

    @OnClick(R.id.show_all)
    public void showAll() {
        //TODO show alert with list of details items.
    }
}
