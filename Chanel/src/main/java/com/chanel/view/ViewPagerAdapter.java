package com.chanel.view;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * View pager adapter. Used for views.
 */
public abstract class ViewPagerAdapter extends PagerAdapter {

    private View convertView;

    public abstract View getView(int position, ViewPager pager, View convertView);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewPager pager = (ViewPager) container;
        View view = getView(position, pager, convertView);
        pager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    public void setConvertView(View convertView) {
        this.convertView = convertView;
    }
}
