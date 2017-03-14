package com.zed.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zed.utils.Constans;
import com.zed.view.LoadingPage;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hc on 17-3-14.
 */
public class Http {

    public MyListener getChangedListener() {
        return myChangedListener;
    }
    public void setOnChangedListener(MyListener listener) {
        this.myChangedListener = listener;
    }
    private MyListener myChangedListener;

    public interface MyListener {

        LoadingPage.LoadResult onCursorChanged(String s);
    }

    public void HttpGet(){

        OkGo.get(Constans.ZHI_HU_BASE_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                if (myChangedListener !=  null){
                    myChangedListener.onCursorChanged(s);
                }
            }



            
        });
    }
}
