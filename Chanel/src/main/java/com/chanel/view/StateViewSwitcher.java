package com.chanel.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

/**
 * Copied from BetterViewAnimator Jake Whartones U2020. Thanks him for that.
 */
public class StateViewSwitcher extends ViewAnimator {
  public StateViewSwitcher(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setDisplayedChildId(int id) {
    if (getDisplayedChildId() == id) { return; }
    for (int i = 0, count = getChildCount(); i < count; i++) {
      if (getChildAt(i).getId() == id) {
        setDisplayedChild(i);
        return;
      }
    }
    String name = getResources().getResourceEntryName(id);
    throw new IllegalArgumentException("No view with ID " + name);
  }

  public int getDisplayedChildId() {
    return getChildAt(getDisplayedChild()).getId();
  }
}
