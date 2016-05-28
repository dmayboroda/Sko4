package com.chanel.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Endless scroll listener
 * Created by Mayboroda on 6/24/15.
 */
public abstract class EndlessScroll extends RecyclerView.OnScrollListener {

    /** All items in data structure that were loaded. */
    private int total = 0;
    /** if loading flag. */
    private boolean loading = true;
    /**Loading times counter .*/
    private int loadingTimes = 1;
    /** Layout manager to get visible position. */
    private LinearLayoutManager layoutManager;

    public EndlessScroll(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        /* Amount of all visible items. */
        int allVisible = recyclerView.getChildCount();
        /* All items amount. */
        int allAmount = layoutManager.getItemCount();
        /* Position of first visible item position. */
        int firstVisible = layoutManager.findFirstVisibleItemPosition();

        if (loading && (allAmount > total)) {
            loading = false;
            total = allAmount;
        }

        int difference = total - allVisible;
        /* Items that need to be scrolled before loading starts. */
        int threshold = 5;
        int distance = firstVisible + threshold;
        if (!loading && (difference <= distance)) {
            loadingTimes++;
            onLoad(loadingTimes);
            loading = true;
        }
    }

    public abstract void onLoad(int loadingTimes);

    public int getLoadingTimes() { return loadingTimes; }

    public void resetTotal() { total = 0; }
}
