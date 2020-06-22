package org.techtown.application;

import android.view.View;

public interface OnPlanlistItemClickListener {
    public void onItemClick(PlanlistAdapter.ViewHolder holder, View view, int position);
}
