package com.zed.adapter;

import android.content.Context;

import com.zed.bean.MainBean;
import com.zed.recycler.adapter.BaseViewHolder;
import com.zed.recycler.adapter.SimpleAdapter;
import com.zed.zhihu.R;

import java.util.List;

/**
 * Created by hc on 17-3-14.
 */
public class MainAdapter extends SimpleAdapter<MainBean> {
    public MainAdapter(Context context,List<MainBean> datas) {
        super(context, R.layout.main_lr_item, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, MainBean item, int postion) {

    }
}
