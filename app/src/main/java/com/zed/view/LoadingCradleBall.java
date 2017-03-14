package com.zed.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zed.zhihu.R;

/**
 * Created by hc on 17-3-1.
 *
 *  加载中的球
 */
public class LoadingCradleBall extends View {

    private int width;
    private int height;

    private Paint paint;

    private int loadingColor = Color.WHITE;

    public LoadingCradleBall(Context context) {
        this(context,null);
    }

    public LoadingCradleBall(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingCradleBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (null != attrs) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingCradleBall);
            //设置球的颜色为黑色
            loadingColor = typedArray.getColor(R.styleable.LoadingCradleBall_cradle_ball_color, Color.BLACK);
            typedArray.recycle();
        }
        paint = new Paint();
        paint.setColor(loadingColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(width / 2, height / 2, width / 2, paint);
    }

    public void setLoadingColor(int color) {
        loadingColor = color;
        paint.setColor(color);
        postInvalidate();
    }

    public int getLoadingColor() {
        return loadingColor;
    }
}