package com.sko4.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sko4.R;
import com.sko4.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Event description card view.
 * Created by Mayboroda on 6/6/16.
 */
public class DescCard extends CardView implements View.OnClickListener{

    @Bind(R.id.desc_body) TextView desc;

    private LayoutInflater inflater;
    private AppCompatDialog dialog;
    private TextView fullDesc;
    private View alertView;

    public DescCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        desc.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        alertView = inflater.inflate(R.layout.alert_desc, null);
        fullDesc = (TextView) alertView.findViewById(R.id.full_desc);
        fullDesc.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
    }

    public void bind(String body) {
        if (TextUtils.isEmpty(body)) {
            setVisibility(GONE);
        } else {
            Spanned trimmed = Html.fromHtml(body.trim()
                    .replace("&nbsp;", "")
                    .replace("<p>&nbsp;</p>",""));
            desc.setText(trimmed);
            fullDesc.setText(trimmed);
            setVisibility(VISIBLE);
            dialog = new AlertDialog.Builder(getContext())
                    .setView(alertView)
                    .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        dialog.show();
    }
}
