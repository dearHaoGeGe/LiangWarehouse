package com.dream.myliu.liangwarehouse.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dream.myliu.liangwarehouse.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amethyst on 16/1/17/15/11.
 */
public class NewGirdView extends GridView implements AbsListView.OnScrollListener {
    private final static int RELEASE_To_REFRESH = 0;

    private final static int PULL_To_REFRESH = 1;

    private final static int REFRESHING = 2;

    private final static int DONE = 3;

    private final static int LOADING = 4;

    // 实际的padding的距离与界面上偏移距离的比例
    private final static int RATIO = 2;

    private LayoutInflater inflater;

    private LinearLayout headView;

    private LinearLayout footerView;

    private TextView tipsTextview;

    private TextView fTipsTextview;

    private TextView lastUpdatedTextView;

    private TextView fLastUpdatedTextView;

    private ImageView arrowImageView;

    private ImageView fArrowImageView;

    private ProgressBar progressBar;

    private ProgressBar fProgressBar;

    private RotateAnimation animation;

    private RotateAnimation reverseAnimation;

    private Boolean isLastIndex = false;

    private Boolean isfirstIdex = false;

    // 用于保证startY的值在一个完整的touch事件中只被记录一次
    private boolean isRecored;

    private int headContentHeight;

    private int footerContentHeight;

    private int startY;

    private int firstItemIndex;

    private int state;

    private boolean isBack;

    private OnRefreshListener refreshListener;

    private boolean isRefreshable;

    private boolean headorfooter = false; // false means head, true means footer

    public NewGirdView(Context context)
    {
        super(context);
        init(context);
    }

    public NewGirdView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public View getHeadView()
    {
        return headView;
    }

    public View getFooterView()
    {
        return footerView;
    }


    private void init(Context context)
    {
        inflater = LayoutInflater.from(context);
        headView = (LinearLayout)inflater.inflate(R.layout.header_layout, null);
        footerView = (LinearLayout)inflater.inflate(R.layout.footer_layout, null);

        setHeadViews(headView);
        setFooterViews(footerView);

        setOnScrollListener(this);

        animation =
                new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);

        reverseAnimation =
                new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(200);
        reverseAnimation.setFillAfter(true);

        state = DONE;
        isRefreshable = false;
    }

    private void setHeadViews(LinearLayout headView)
    {
        arrowImageView = (ImageView)headView.findViewById(R.id.arrow);
        arrowImageView.setMinimumWidth(70);
        arrowImageView.setMinimumHeight(50);
        progressBar = (ProgressBar)headView.findViewById(R.id.progress);
        tipsTextview = (TextView)headView.findViewById(R.id.tip);
        lastUpdatedTextView = (TextView)headView.findViewById(R.id.lastupdate_time);

        measureView(headView);
        headContentHeight = headView.getMeasuredHeight();
        // headContentWidth = headView.getMeasuredWidth();

        headView.setPadding(0, -1 * headContentHeight, 0, 0); // 0,-1 * headContentHeight,0,0
        headView.invalidate();
    }

    private void setFooterViews(LinearLayout footerView)
    {
//        fArrowImageView = (ImageView)footerView.findViewById(R.layout.footer_layout);
//        fArrowImageView.setMinimumWidth(70);
//        fArrowImageView.setMinimumHeight(50);
        fProgressBar = (ProgressBar)footerView.findViewById(R.id.footBar);
        fTipsTextview = (TextView)footerView.findViewById(R.id.footTv);
//        fLastUpdatedTextView = (TextView)footerView.findViewById(R.id.footer_lastUpdatedTextView);

        measureView(footerView);
        footerContentHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, 0, 0, -1 * footerContentHeight); // 0,-1 * headContentHeight,0,0
        footerView.invalidate();
    }

    public void onScroll(AbsListView arg0, int firstVisiableItem, int arg2, int arg3)
    {
        firstItemIndex = firstVisiableItem;

        if (firstItemIndex == 0)
        {
            isfirstIdex = true;
            isLastIndex = false;
        }
    }

    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        if (firstItemIndex > 0)
        {
            isfirstIdex = false;
        }

        if (view.getLastVisiblePosition() == view.getCount() - 1)
        {
            isLastIndex = true;
            isfirstIdex = false;
        }

    }

    public boolean onTouchEvent(MotionEvent event)
    {

        if (isRefreshable)
        {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    if (firstItemIndex == 0 && !isRecored)
                    {
                        isRecored = true;
                        startY = (int)event.getY();
                    }
                    break;

                case MotionEvent.ACTION_UP:

                    if (state != REFRESHING && state != LOADING)
                    {
                        if (state == DONE)
                        {
                            // 什么都不做
                        }
                        if (state == PULL_To_REFRESH)
                        {
                            state = DONE;
                            if (headorfooter)
                            {
                                changeFooterViewByState();
                            }
                            else
                                changeHeaderViewByState();

                            // Log.v(TAG, "由下拉刷新状态，到done状态");
                        }
                        if (state == RELEASE_To_REFRESH)
                        {
                            state = REFRESHING;

                            // 向下拉
                            if (headorfooter)
                            {
                                changeFooterViewByState();
                                onMore();
                            }
                            else
                            {
                                changeHeaderViewByState();
                                onRefresh();
                            }

                            // Log.v(TAG, "由松开刷新状态，到done状态");
                        }
                    }

                    isRecored = false;
                    isBack = false;

                    break;

                case MotionEvent.ACTION_MOVE:
                    int tempY = (int)event.getY();
                    // Log.v("tempY", tempY + "");
                    if (tempY > startY) // pull down
                    {
                        headorfooter = false;
                        if (!isRecored && firstItemIndex == 0)
                        {
                            // Log.v(TAG, "在move时候记录下位置");
                            isRecored = true;
                            startY = tempY;
                        }
                        if (state != REFRESHING && isRecored && state != LOADING)
                        {

                            // 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动

                            // 可以松手去刷新了
                            if (state == RELEASE_To_REFRESH)
                            {

                                // setSelection(0);

                                // 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
                                if (((tempY - startY) / RATIO < headContentHeight) && (tempY - startY) > 0)
                                {
                                    state = PULL_To_REFRESH;
                                    changeHeaderViewByState();

                                    // Log.v(TAG, "由松开刷新状态转变到下拉刷新状态");
                                }
                                // 一下子推到顶了
                                else if (tempY - startY <= 0)
                                {
                                    state = DONE;
                                    changeHeaderViewByState();

                                    // Log.v(TAG, "由松开刷新状态转变到done状态");
                                }
                                // 往下拉了，或者还没有上推到屏幕顶部掩盖head的地步
                                else
                                {
                                    // 不用进行特别的操作，只用更新paddingTop的值就行了
                                }
                            }
                            // 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
                            if (state == PULL_To_REFRESH && isfirstIdex)
                            {

                                // setSelection(0);

                                // 下拉到可以进入RELEASE_TO_REFRESH的状态
                                if ((tempY - startY) / RATIO >= headContentHeight)
                                {
                                    state = RELEASE_To_REFRESH;
                                    isBack = true;
                                    changeHeaderViewByState();

                                    // Log.v(TAG, "由done或者下拉刷新状态转变到松开刷新");
                                }
                                // 上推到顶了
                                else if (tempY - startY <= 0)
                                {
                                    state = DONE;
                                    changeHeaderViewByState();

                                    // Log.v(TAG, "由DOne或者下拉刷新状态转变到done状态");
                                }
                            }

                            // done状态下
                            if (state == DONE)
                            {
                                if (tempY - startY > 0)
                                {
                                    state = PULL_To_REFRESH;
                                    changeHeaderViewByState();
                                }
                            }

                            if (isfirstIdex)
                            {
                                // 更新headView的size
                                if (state == PULL_To_REFRESH)
                                {
                                    headView.setPadding(0, -1 * headContentHeight + (tempY - startY) / RATIO, 0, 0);

                                }

                                // 更新headView的paddingTop
                                if (state == RELEASE_To_REFRESH)
                                {
                                    headView.setPadding(0, (tempY - startY) / RATIO - headContentHeight, 0, 0);
                                }
                            }

                        }
                    }

                    // 上拉
                    // else if (false)
                    else if (tempY < startY)
                    {
                        headorfooter = true;
                        if (!isRecored && isLastIndex)
                        {
                            // Log.v(TAG, "在move时候记录下位置");
                            isRecored = true;
                            startY = tempY;
                            // Log.v("StartY:", startY + "");
                        }

                        if (state != REFRESHING && isRecored && state != LOADING)
                        {

                            // 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动

                            // 可以松手去刷新了
                            if (state == RELEASE_To_REFRESH)
                            {

                                // setSelection(getCount() - 1);

                                // 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
                                if (((startY - tempY) / RATIO < footerContentHeight) && (startY - tempY) > 0)
                                {
                                    state = PULL_To_REFRESH;
                                    changeFooterViewByState();

                                    // Log.v(TAG, "由松开刷新状态转变到下拉刷新状态");
                                }
                                // 一下子推到顶了
                                else if (startY - tempY <= 0)
                                {
                                    state = DONE;
                                    changeFooterViewByState();

                                    // Log.v(TAG, "由松开刷新状态转变到done状态");
                                }
                                // 往下拉了，或者还没有上推到屏幕顶部掩盖head的地步
                                else
                                {
                                    // 不用进行特别的操作，只用更新paddingTop的值就行了
                                }
                            }
                            // 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
                            if (state == PULL_To_REFRESH && isLastIndex)
                            {

                                // setSelection(getCount() - 1);

                                // 下拉到可以进入RELEASE_TO_REFRESH的状态
                                if ((startY - tempY) / RATIO >= footerContentHeight)
                                {
                                    state = RELEASE_To_REFRESH;
                                    isBack = true;
                                    changeFooterViewByState();

                                    // Log.v(TAG, "由done或者下拉刷新状态转变到松开刷新");
                                }
                                // 上推到顶了
                                else if (startY - tempY <= 0)
                                {
                                    state = DONE;
                                    changeFooterViewByState();

                                    // Log.v(TAG, "由DOne或者下拉刷新状态转变到done状态");
                                }
                            }

                            // done状态下
                            if (state == DONE)
                            {
                                if (startY - tempY > 0)
                                {
                                    state = PULL_To_REFRESH;
                                    changeFooterViewByState();
                                }
                            }

                            if (isLastIndex)
                            {
                                // 更新footerView的size
                                if (state == PULL_To_REFRESH)
                                {
                                    footerView.setPadding(0, 0, 0, -1 * footerContentHeight + (startY - tempY) / RATIO);

                                }

                                // 更新footerView的paddingTop
                                if (state == RELEASE_To_REFRESH)
                                {
                                    footerView.setPadding(0, 0, 0, (startY - tempY) / RATIO - footerContentHeight);
                                }
                            }
                        }
                    }

                    break;
            }
        }

        return super.onTouchEvent(event);
    }

    private void changeFooterViewByState()
    {
        // TODO Auto-generated method stub
        switch (state)
        {
            case RELEASE_To_REFRESH:
                fArrowImageView.setVisibility(View.VISIBLE);
                fProgressBar.setVisibility(View.GONE);
                fTipsTextview.setVisibility(View.VISIBLE);
                fLastUpdatedTextView.setVisibility(View.VISIBLE);

                fArrowImageView.clearAnimation();
                fArrowImageView.startAnimation(reverseAnimation);

                fTipsTextview.setText("松开刷新");

                // Log.v(TAG, "当前状态，松开刷新");
                break;
            case PULL_To_REFRESH:
                fProgressBar.setVisibility(View.GONE);
                fTipsTextview.setVisibility(View.VISIBLE);
                fLastUpdatedTextView.setVisibility(View.VISIBLE);
                fArrowImageView.clearAnimation();
                fArrowImageView.setVisibility(View.VISIBLE);
                // 是由RELEASE_To_REFRESH状态转变来的
                if (isBack)
                {
                    isBack = false;
                    fArrowImageView.clearAnimation();
                    fArrowImageView.startAnimation(animation);

                    fTipsTextview.setText("下拉刷新");
                }
                else
                {
                    fTipsTextview.setText("下拉刷新");
                }
                // Log.v(TAG, "当前状态，下拉刷新");
                break;

            case REFRESHING:

                footerView.setPadding(0, 0, 0, 0);

                fProgressBar.setVisibility(View.VISIBLE);
                fArrowImageView.clearAnimation();
                fArrowImageView.setVisibility(View.GONE);
                fTipsTextview.setText("正在刷新...");
                fLastUpdatedTextView.setVisibility(View.VISIBLE);

                // Log.v(TAG, "当前状态,正在刷新...");
                break;
            case DONE:
                footerView.setPadding(0, 0, 0, -1 * footerContentHeight);

                fProgressBar.setVisibility(View.GONE);
                fArrowImageView.clearAnimation();
                fArrowImageView.setImageResource(R.mipmap.default_ptr_flip);
                fTipsTextview.setText("下拉刷新");
                fLastUpdatedTextView.setVisibility(View.VISIBLE);

                // Log.v(TAG, "当前状态，done");
                break;
        }
    }

    // 当状态改变时候，调用该方法，以更新界面
    private void changeHeaderViewByState()
    {
        switch (state)
        {
            case RELEASE_To_REFRESH:

                Log.e("test", "RELEASE_To_REFRESH");
                arrowImageView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tipsTextview.setVisibility(View.VISIBLE);
                lastUpdatedTextView.setVisibility(View.VISIBLE);

                arrowImageView.clearAnimation();
                arrowImageView.startAnimation(animation);

                tipsTextview.setText("松开刷新");

                // Log.v(TAG, "当前状态，松开刷新");
                break;
            case PULL_To_REFRESH:
                Log.e("test", "PULL_To_REFRESH");
                progressBar.setVisibility(View.GONE);
                tipsTextview.setVisibility(View.VISIBLE);
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();
                arrowImageView.setVisibility(View.VISIBLE);
                // 是由RELEASE_To_REFRESH状态转变来的
                if (isBack)
                {
                    isBack = false;
                    arrowImageView.clearAnimation();
                    arrowImageView.startAnimation(reverseAnimation);

                    tipsTextview.setText("下拉刷新");
                }
                else
                {
                    tipsTextview.setText("下拉刷新");
                }
                // Log.v(TAG, "当前状态，下拉刷新");
                break;

            case REFRESHING:
                Log.e("test", "REFRESHING");
                headView.setPadding(0, 0, 0, 0);

                progressBar.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();
                arrowImageView.setVisibility(View.GONE);
                tipsTextview.setText("正在刷新...");
                lastUpdatedTextView.setVisibility(View.VISIBLE);

                // Log.v(TAG, "当前状态,正在刷新...");
                break;
            case DONE:
                Log.e("test", "DONE");
                headView.setPadding(0, -1 * headContentHeight, 0, 0);

                progressBar.setVisibility(View.GONE);
                arrowImageView.clearAnimation();
                arrowImageView.setImageResource(R.mipmap.default_ptr_flip);
                tipsTextview.setText("下拉刷新");
                lastUpdatedTextView.setVisibility(View.VISIBLE);

                // Log.v(TAG, "当前状态，done");
                break;
        }
    }

    public void setonRefreshListener(OnRefreshListener refreshListener)
    {
        this.refreshListener = refreshListener;
        isRefreshable = true;
    }

    public interface OnRefreshListener
    {
        public void onRefresh();

        public void onMore();

    }

    public void onRefreshComplete()
    {
        state = DONE;
        lastUpdatedTextView.setText("最近更新:" + new Date().toLocaleString());
        fLastUpdatedTextView.setText("最近更新:" + new Date().toLocaleString());
        changeHeaderViewByState();
        changeFooterViewByState();
    }

    private void onRefresh()
    {
        if (refreshListener != null)
        {
            refreshListener.onRefresh();
        }
    }

    private void onMore()
    {
        if (refreshListener != null)
        {
            refreshListener.onMore();
        }
    }

    // 此方法直接照搬自网络上的一个下拉刷新的demo，此处是“估计”headView的width以及height
    private void measureView(View child)
    {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null)
        {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            // set the width and height of the child of the view
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0)
        {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        }
        else
        {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    public void setAdapter(BaseAdapter adapter)
    {
        lastUpdatedTextView.setText("最近更新:" + new Date().toLocaleString());
//        fLastUpdatedTextView.setText("最近更新:" + new Date().toLocaleString());
        super.setAdapter(adapter);
    }

}
