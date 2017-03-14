package com.zed.zhihu;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zed.adapter.MainAdapter;
import com.zed.bean.MainBean;
import com.zed.recycler.adapter.LRecyclerViewAdapter;
import com.zed.recycler.interfaces.OnLoadMoreListener;
import com.zed.recycler.interfaces.OnRefreshListener;
import com.zed.recycler.view.LRecyclerView;
import com.zed.utils.Constans;
import com.zed.view.LoadingPage;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, OnRefreshListener, OnLoadMoreListener {


    private Toolbar toolbar;
    private LRecyclerView mLRecycleView;
    private LinearLayoutManager recyclerViewLayoutManager;
    private List<MainBean> mMainBean = new ArrayList<>();
    private MainAdapter mainAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    public  LoadingPage.LoadResult stuan;

    @Override
    protected void initView() {
         LoadingPage loadingPage = new LoadingPage(this) {
            @Override
            public View createSuccessView() {
                return MainActivity.this.createSuccessView();
            }

            @Override
            protected LoadResult load() {
                return MainActivity.this.load();
            }
        };

        loadingPage.show();  //  必须调用show方法 才会请求服务器 加载新的界面

        setContentView(loadingPage);

    }


    /**
     * 请求服务器加载数据
     * @return
     */

    protected LoadingPage.LoadResult load() {

        OkGo.get(Constans.ZHI_HU_BASE_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (TextUtils.isEmpty(s)){


                    throw new IllegalArgumentException("判空了");

                }else{
                    parseJson(s);

                }


            }
        });

        return stuan;
    }

/*    @Override
    protected void initData() {
        OkGo.get(Constans.ZHI_HU_BASE_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (TextUtils.isEmpty(s)){
                    stuan=LoadingPage.LoadResult.empty;



                }

                parseJson(s);
            }
        });
    }*/

    private void parseJson(String s) {

    }


    /**
     * 加载成功的界面
     * @return
     */
    protected View createSuccessView() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        //View view= Utils.inflate(R.layout.activity_main);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(MainActivity.this);

        mLRecycleView = (LRecyclerView) view.findViewById(R.id.main_lr);

        //线性布局管理器
        recyclerViewLayoutManager = new LinearLayoutManager(MainActivity.this);
        //设置布局管理器
        mLRecycleView.setLayoutManager(recyclerViewLayoutManager);
        //设置adapter
        // recyclerAdapter = new RecyclerAdapter(mHotBean, getActivity());
        mainAdapter = new MainAdapter(MainActivity.this, mMainBean);

        lRecyclerViewAdapter = new LRecyclerViewAdapter(mainAdapter);

        mLRecycleView.setAdapter(lRecyclerViewAdapter);

        mLRecycleView.setOnRefreshListener(this);

        mLRecycleView.setOnLoadMoreListener(this);



        return view;
    }





    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Toast.makeText(MainActivity.this,"click Setting",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
