package com.zed.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.zed.zhihu.R;

/**
 * Created by hc on 17-3-1.
 *
 * 真正实现加载时的动画
 */
public class LoadingView extends LinearLayout {

    private LoadingCradleBall loadingCradleBallOne;
    private LoadingCradleBall loadingCradleBallTwo;
    private LoadingCradleBall loadingCradleBallThree;
    private LoadingCradleBall loadingCradleBallFour;
    private LoadingCradleBall loadingCradleBallFive;

    private static final int DURATION = 400;
    private static final int SHAKE_DISTANCE = 2;
    private static final float PIVOT_X = 0.5f;
    private static final float PIVOT_Y = -3f;
    private static final int DEGREE = 30;


    private boolean isStart = false;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.loading_ball, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadingCradleBallOne = (LoadingCradleBall) findViewById(R.id.ball_one);
        loadingCradleBallTwo = (LoadingCradleBall) findViewById(R.id.ball_two);
        loadingCradleBallThree = (LoadingCradleBall) findViewById(R.id.ball_three);
        loadingCradleBallFour = (LoadingCradleBall) findViewById(R.id.ball_four);
        loadingCradleBallFive = (LoadingCradleBall) findViewById(R.id.ball_five);

        initAnim();
    }

    RotateAnimation rotateLeftAnimation;//cradleBallOne left to right
    RotateAnimation rotateRightAnimation;//cradleBallFive right to left
    TranslateAnimation shakeLeftAnimation;
    TranslateAnimation shakeRightAnimation;


    private void initAnim() {
        rotateRightAnimation = new RotateAnimation(0, -DEGREE, RotateAnimation.RELATIVE_TO_SELF, PIVOT_X, RotateAnimation.RELATIVE_TO_SELF, PIVOT_Y);
        rotateRightAnimation.setRepeatCount(1);
        rotateRightAnimation.setRepeatMode(Animation.REVERSE);
        rotateRightAnimation.setDuration(DURATION);
        rotateRightAnimation.setInterpolator(new LinearInterpolator());
        rotateRightAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isStart)
                    startRightAnim();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        shakeLeftAnimation = new TranslateAnimation(0, SHAKE_DISTANCE, 0, 0);
        shakeLeftAnimation.setDuration(DURATION);
        shakeLeftAnimation.setInterpolator(new CycleInterpolator(2));

        rotateLeftAnimation = new RotateAnimation(0, DEGREE, RotateAnimation.RELATIVE_TO_SELF, PIVOT_X, RotateAnimation.RELATIVE_TO_SELF, PIVOT_Y);
        rotateLeftAnimation.setRepeatCount(1);
        rotateLeftAnimation.setRepeatMode(Animation.REVERSE);
        rotateLeftAnimation.setDuration(DURATION);
        rotateLeftAnimation.setInterpolator(new LinearInterpolator());
        rotateLeftAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isStart) {
                    loadingCradleBallTwo.startAnimation(shakeLeftAnimation);
                    loadingCradleBallThree.startAnimation(shakeLeftAnimation);
                    loadingCradleBallFour.startAnimation(shakeLeftAnimation);

                    loadingCradleBallFive.startAnimation(rotateRightAnimation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        shakeRightAnimation = new TranslateAnimation(0, -SHAKE_DISTANCE, 0, 0);
        shakeRightAnimation.setDuration(DURATION);
        shakeRightAnimation.setInterpolator(new CycleInterpolator(2));
        shakeRightAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isStart)
                    startLeftAnim();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // start animation
        start();
        //setLoadingColor(Color.RED);
    }

    private void startLeftAnim() {
        loadingCradleBallOne.startAnimation(rotateLeftAnimation);
    }

    private void startRightAnim() {
        loadingCradleBallTwo.startAnimation(shakeRightAnimation);
        loadingCradleBallThree.startAnimation(shakeRightAnimation);
        loadingCradleBallFour.startAnimation(shakeRightAnimation);
    }

    public void start() {
        if (!isStart) {
            isStart = true;
            startLeftAnim();
        }
    }

    public void stop() {
        isStart = false;
        loadingCradleBallOne.clearAnimation();
        loadingCradleBallTwo.clearAnimation();
        loadingCradleBallThree.clearAnimation();
        loadingCradleBallFour.clearAnimation();
        loadingCradleBallFive.clearAnimation();
    }

    public boolean isStart() {
        return isStart;
    }

    public void setLoadingColor(int color) {
        loadingCradleBallOne.setLoadingColor(color);
        loadingCradleBallTwo.setLoadingColor(color);
        loadingCradleBallThree.setLoadingColor(color);
        loadingCradleBallFour.setLoadingColor(color);
        loadingCradleBallFive.setLoadingColor(color);
    }
}
