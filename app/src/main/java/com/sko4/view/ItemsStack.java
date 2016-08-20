package com.sko4.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class ItemsStack extends CardView {

    @Bind(R.id.items_container) LinearLayout container;
    @Bind(R.id.show_all)        TextView showAll;

    private LayoutInflater inflater;
    private AppCompatDialog dialog;
    private RecyclerView alertView;

    public ItemsStack(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        alertView = (RecyclerView) inflater.inflate(R.layout.alert_list, null);
        alertView.setLayoutManager(new LinearLayoutManager(getContext()));
        showAll.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        dialog = new AlertDialog.Builder(getContext())
                .setView(alertView)
                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                }).create();
    }

    public void bind(List<Details> details, OnClickListener listener) {
        if (details.isEmpty()) {
            setVisibility(GONE);
            return;
        }
        StackAdapter stackAdapter = new StackAdapter(details, listener);
        alertView.setAdapter(stackAdapter);
        int maxsize = 5;
        int listSize = details.size();
        int size = listSize >= maxsize ? maxsize : listSize;
        showAll.setVisibility(listSize > maxsize ? VISIBLE : GONE);

        for (int i = 0; i < size; i++) {
            StackItem stackItem = (StackItem)inflater
                    .inflate(R.layout.item_info, null);
            stackItem.setTag(details.get(i));
            stackItem.bind(details.get(i), listener);
            container.addView(stackItem);
        }

        setVisibility(VISIBLE);
    }

    @OnClick(R.id.show_all)
    public void showAll() {
        dialog.show();
    }
}
