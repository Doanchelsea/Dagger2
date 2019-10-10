package com.example.dagger2_api_login.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.example.dagger2_api_login.R;

public class CustomLoadMore extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.item_history_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.item_history_load_more_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.item_history_load_more_fail;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
