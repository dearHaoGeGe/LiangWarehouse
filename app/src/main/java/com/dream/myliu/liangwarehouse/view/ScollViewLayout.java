package com.dream.myliu.liangwarehouse.view;

/**
 * Created by Amethyst on 16/1/17/19/31.
 */
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ScollViewLayout extends RelativeLayout {
    private static final String TAG = "ScrollLayout";
    private Context _context;
    private Scroller _scroller;

    /** 所有的子视图(正常的排序) */
    private ArrayList<View> _childViewList;
    /** 所有的子视图(为了显示层叠效果而生成的排序方式) */
    private ArrayList<View> _sortChildViewList;
    /** 屏幕上可以看到的视图+2, 例如:屏幕上有5个屏幕外有2个 */
    private ArrayList<View> _visiableChildViewList;
    /** 上移标识 */
    private static final int MOVETOUP = 0x100;
    /** 下移标识 */
    private static final int MOVETODOWN = 0x200;
    /** 默认屏幕上视图的个数(自动+2) */
    private int _visiableChildViewNumber = 7;
    /** 所有可见子视图缩放比例 列表 */
    private ArrayList<Float> _childScaleList;
    /** 所有可见子视图透明度列表 */
    private ArrayList<Float> _childAlphaList;
    /** 移动时候的缩放列表 */
    private ArrayList<Float> _childMoveToScaleList;
    /** 移动时候的透明度列表 */
    private ArrayList<Float> _childMoveToAlphaList;
    /** 所有可见子视图height */
    private ArrayList<Float> _childHeightList;
    /** 到中心的距离列表(中心以上是到中心上标,以下是到中心下标) */
    private ArrayList<Float> _childYList;
    /** 最大放大比例 */
    private float _maxScale = 1.5f;
    /** 相邻比例间差值 */
    private float _scaleDVale = 0.2f;
    /** 最大的透明度 */
    private float _maxAlpha = 1f;
    /** 相邻透明度间差值 */
    private float _alphaDVale = 0.2f;
    /** 最后一次滑动的距离 */
    private float _lastionMotionY = 0;
    /** 移动距离 */
    private int move = 0;
    /** 跳转activity需要 */
    private Activity _fromActivity = null;
    /** 跳转activity需要 */
    private Class _toClass = null;
    /** 跳转传参标识 */
    private String _flag = null;
    /** 跳转要传递的参数 */
    private int _value = 0;
    /** 屏幕的高度 */
    private float _screenHeight = 1230f;

    public ScollViewLayout(Context context) {
        super(context);
        _context = context;
        _scroller = new Scroller(context);
    }

    public ScollViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        _context = context;
        _scroller = new Scroller(context);
    }

    public ScollViewLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        _context = context;
        _scroller = new Scroller(context);
    }

    /** 自定义屏幕上显示子视图的个数(默认为5) */
    public void setVisiableViewNumber(int visiableViewNumber) {
        if (visiableViewNumber % 2 == 0) {
            Toast.makeText(_context, "显示在屏幕上的子视图个数不能为偶数", Toast.LENGTH_SHORT).show();
        } else {
            this._visiableChildViewNumber = visiableViewNumber + 2;
        }
    }

    /** 设置屏幕的高度 */
    public void setScreenHeight(float sh) {
        _screenHeight = sh;
    }

//    /**
//     * 初始化
//     *
//     * @param viewList
//     *            子视图列表
//     * @param activity
//     *            跳转activity需要
//     * @param clzz
//     *            跳转activity需要
//     * @param flag
//     *            跳转传参标识
//     * @param value
//     *            跳转要传递的参数
//     */
    public void init(ArrayList<View> viewList, Activity fromActivity, Class toClass, String flag, int value) {
        _fromActivity = fromActivity;
        _toClass = toClass;
        _flag = flag;
        _value = value;
        _childViewList = viewList;
        initView();
    }

    /**
     * 初始化
     *
     * @param viewList
     *            子视图列表
     * @param fromActivity
     *            跳转activity需要
     * @param toClass
     *            跳转activity需要
     */
    public void init(ArrayList<View> viewList, Activity fromActivity, Class toClass) {
        _fromActivity = fromActivity;
        _toClass = toClass;
        _childViewList = viewList;
        initView();
    }

    /**
     * 初始化
     *
     * @param viewList
     *            子视图列表
     */
    public void init(ArrayList<View> viewList) {
        _childViewList = viewList;
        initView();
    }

    /**
     * 初始化子视图
     */
    private void initView() {
        sortView();
        createVisiableChildView();
        addVisiableChildView();
        createVisiableChildScaleList();
        createVisiableChildAlphaList();
    }

    /**
     * 设置可见视图(_visiableChildViewList)的缩放比例和透明度
     *
     * @param scale
     *            最大放大比例
//     * @param dValue
     *            相邻比例间差值
     * @param maxAlpha
     *            最大透明度
     * @param alphaDVale
     *            相邻透明度间差值
     */
    public void setVisiableChildScale(float scale, float scaleDValue, float maxAlpha, float alphaDVale) {
        _maxScale = scale;
        _scaleDVale = _alphaDVale;
        _maxAlpha = maxAlpha;
        _alphaDVale = alphaDVale;
    }

    /**
     * 生成可见视图(_visiableChildViewList)透明度列表(中间为1)
     */
    private void createVisiableChildAlphaList() {
        _childAlphaList = new ArrayList<Float>();
        for (int i = _visiableChildViewList.size() / 2; i >= 0; i--) {
            _childAlphaList.add(_maxAlpha - (float) i * _alphaDVale);
            _childAlphaList.add(_maxAlpha - (float) i * _alphaDVale);
        }
        _childAlphaList.remove(_childAlphaList.size() - 1);
    }

    /**
     * 生成可见视图(_visiableChildViewList)的缩放列表
     */
    private void createVisiableChildScaleList() {
        _childScaleList = new ArrayList<Float>();
        for (int i = _visiableChildViewList.size() / 2; i >= 0; i--) {
            _childScaleList.add(_maxScale - (float) i * _scaleDVale);
            _childScaleList.add(_maxScale - (float) i * _scaleDVale);
        }
        _childScaleList.remove(_childScaleList.size() - 1);
    }

    /**
     * 生成可见视图(_visiableChildViewList)高度列表
     */
    private void createVisiableChildHeightList(ArrayList<Float> childScaleList) {
        _childHeightList = new ArrayList<Float>();
        float height = getChildAt(0).getMeasuredHeight();
        for (int i = 0; i < _visiableChildViewList.size(); i++) {
            _childHeightList.add(height * childScaleList.get(i));
        }
    }

    /**
     * 生成可见视图(_visiableChildViewList)Y坐标
     */
    private void createVisiableChildYList() {
        _childYList = new ArrayList<Float>();
        float value = Math.abs(_screenHeight - getY() - getMeasuredHeight() - getY());

        float centerChildUpY = ((float) getMeasuredHeight() - _childHeightList.get(_childHeightList.size() - 1)) / 2f;
        float centerChildDownY = centerChildUpY + _childHeightList.get(_childHeightList.size() - 1);
        for (int i = 0; i < _visiableChildViewList.size() - 1; i = i + 2) {
            float height = 0;
            for (int j = i; j < _visiableChildViewList.size() - 1; j = j + 2) {
                height += _childHeightList.get(j) * 3 / 4;
            }
            _childYList.add(centerChildUpY - height + getY());
            height = 0;
            for (int j = i + 1; j < _visiableChildViewList.size() - 1; j = j + 2) {
                height += _childHeightList.get(j) * 3 / 4;
            }
            _childYList.add(centerChildDownY + height - _childHeightList.get(i + 1) + value);
        }
        _childYList.add(centerChildUpY + getY());
    }

    /**
     * 将子视图列表(_childViewList)重新排序,生成(_sortChildViewList)
     */
    private void sortView() {
        _sortChildViewList = new ArrayList<View>();
        ArrayList<View> temp = new ArrayList<View>();
        copyList(_childViewList, temp);
        // 如果子视图列表size是偶数
        if (temp.size() % 2 == 0) {
            _sortChildViewList.add(temp.get(0));
            temp.remove(0);
        }
        while (temp.size() > 1) {
            _sortChildViewList.add(temp.get(0));
            _sortChildViewList.add(temp.get(temp.size() - 1));
            temp.remove(0);
            temp.remove(temp.size() - 1);
        }
        if (temp.size() == 1) {
            _sortChildViewList.add(temp.get(0));
        }
    }

    /**
     * 生成可见视图(_visiableChildViewList)
     */
    private void createVisiableChildView() {
        _visiableChildViewList = new ArrayList<View>();
        int start = _sortChildViewList.size() - _visiableChildViewNumber;
        for (int i = start; i < _sortChildViewList.size(); i++) {
            _visiableChildViewList.add(_sortChildViewList.get(i));
        }
    }

    /**
     * 删除原有子视图,装载可见视图(_visiableChildViewList)到父视图中
     */
    private void addVisiableChildView() {
        removeAllViews();
        for (int i = 0; i < _visiableChildViewList.size(); i++) {
            addView(_visiableChildViewList.get(i));
        }
    }

    /**
     * 循环排列子视图列表(_childViewList)重新生成可见视图并装载到父视图中
     *
     * @param moveDirection
     *            移动方向
     */
    private void cycleSortChildViews(int moveDirection) {
        ArrayList<View> temp = new ArrayList<View>();
        View view;
        if (moveDirection == MOVETOUP) {
            view = _childViewList.get(0);
            _childViewList.remove(0);
            _childViewList.add(view);
        }
        if (moveDirection == MOVETODOWN) {
            view = _childViewList.get(_childViewList.size() - 1);
            _childViewList.remove(_childViewList.size() - 1);
            temp.add(view);
            copyList(_childViewList, temp);
            _childViewList = temp;
        }
    }

    /**
     * 子视图放大或缩小
     *
     * @param scale
     */
    private void scaleChildView(int position, float scale) {
        getChildAt(position).setScaleX(scale);
        getChildAt(position).setScaleY(scale);
    }

    /** 获取子视图左面的坐标 */
    private int getChildViewLeft(int position) {
        return (getMeasuredWidth() - getChildAt(position).getMeasuredWidth()) / 2;
    }

    /** 获取子视图右面的坐标 */
    private int getChildViewRight(int position) {
        return getChildViewLeft(position) + getChildAt(position).getMeasuredWidth();
    }

    /** 获得子视图的高度 */
    private int getChildViewHeight(int position) {
        return getChildAt(position).getMeasuredHeight();
    }

    /** 获取中间子视图上面的坐标 */
    private int getCenterChildViewTop() {
        return (getMeasuredHeight() - getChildAt(getChildCount() - 1).getMeasuredHeight()) / 2;
    }

    /** 获取中间子视图下面的坐标 */
    private int getCenterChildViewBottom() {
        return getCenterChildViewTop() + getChildAt(getChildCount() - 1).getMeasuredHeight();
    }

    /**
     * 实现移动
     *
     * @param dy
     */
    private void moveTo(int dy) {
        scrollBy(0, dy);
        move += dy;
        // 下移
        if (move < 0) {
            _childMoveToScaleList = createMoveToProperty(move, _childScaleList, _scaleDVale, 0f);
            _childMoveToAlphaList = createMoveToProperty(move, _childAlphaList, _alphaDVale, 1f);
            scaleAlphaAndMoveChild();
            if (_childMoveToScaleList.get(_childMoveToScaleList.size() - 3) > _maxScale) {
                cycleSortChildViews(MOVETODOWN);
                sortView();
                createVisiableChildView();
                addVisiableChildView();
                createVisiableChildScaleList();
                createVisiableChildAlphaList();
                scrollBy(0, -move);
                move = 0;
                requestLayout();
            }
        }
        // 上移
        if (move >= 0) {
            _childMoveToScaleList = createMoveToProperty(move, _childScaleList, _scaleDVale, 0f);
            _childMoveToAlphaList = createMoveToProperty(move, _childAlphaList, _alphaDVale, 1f);
            scaleAlphaAndMoveChild();
            if (_childMoveToScaleList.get(_childMoveToScaleList.size() - 2) > _maxScale) {
                cycleSortChildViews(MOVETOUP);
                sortView();
                createVisiableChildView();
                addVisiableChildView();
                createVisiableChildScaleList();
                createVisiableChildAlphaList();
                scrollBy(0, -move);
                move = 0;
                requestLayout();
            }
        }
    }

    /**
     * 实现动画(移动,透明度变化,缩放)
     */
    private void scaleAlphaAndMoveChild() {
        if (_childMoveToScaleList.size() > 2 && _childMoveToAlphaList.size() > 2) {
            for (int i = 0; i < getChildCount(); i++) {
                scaleChildView(i, _childMoveToScaleList.get(i));
                getChildAt(i).setAlpha(_childMoveToAlphaList.get(i));
            }
            createVisiableChildHeightList(_childMoveToScaleList);
            createVisiableChildYList();
            toLayout();
        }
    }

    /**
     * 生成移动时候的属性(透明度列表或缩放列表)
     *
     * @param move
     *            移动距离
     * @param propertyList
     *            _childScaleList或者_childAlphaList
     * @param dValue
     *            差值
     * @param centerProperty
     *            中间的值
     * @return 移动时候需要生成的列表
     */
    private ArrayList<Float> createMoveToProperty(int move, ArrayList<Float> propertyList, float dValue,
                                                  float centerProperty) {
        ArrayList<Float> moveToList = new ArrayList<Float>();
        ArrayList<Float> tempA = new ArrayList<Float>();
        ArrayList<Float> tempB = new ArrayList<Float>();
        int tempMove = Math.abs(move);
        float property = 0f;
        int end = propertyList.size() - 1;

        // 向下移动
        if (move < 0) {
            for (int i = 0; i < _childHeightList.size() - 1; i = i + 2) {
                property = dValue / (_childHeightList.get(i + 2) - _childHeightList.get(i) / 4) * (float) tempMove
                        + propertyList.get(i);
                tempA.add(property);
            }
            for (int i = 1; i < _childHeightList.size(); i = i + 2) {
                if (i == 1) {
                    property = propertyList.get(1);
                } else {
                    property = propertyList.get(i) - dValue
                            / (_childHeightList.get(i - 2) - _childHeightList.get(i) / 4) * (float) tempMove;
                }
                tempB.add(property);
            }
            centerProperty = propertyList.get(end) - dValue
                    / (_childHeightList.get(end - 1) - _childHeightList.get(end) / 4) * (float) tempMove;
        }
        // 向上移动
        if (move >= 0) {
            for (int i = 0; i < _childHeightList.size() - 1; i = i + 2) {
                if (i == 0) {
                    property = propertyList.get(0);
                } else {
                    property = propertyList.get(i) - dValue
                            / (_childHeightList.get(i - 2) - _childHeightList.get(i) / 4) * (float) tempMove;
                }
                tempA.add(property);
                // Log.e(TAG, scale+"");
            }
            for (int i = 1; i < _childHeightList.size(); i = i + 2) {
                if (i + 2 < _childHeightList.size()) {
                    property = dValue / (_childHeightList.get(i + 2) - _childHeightList.get(i) / 4) * (float) tempMove
                            + propertyList.get(i);
                } else {
                    property = dValue / (_childHeightList.get(i + 1) - _childHeightList.get(i) / 4) * (float) tempMove
                            + propertyList.get(i);
                }
                tempB.add(property);
            }
            centerProperty = propertyList.get(end) - dValue
                    / (_childHeightList.get(end) - _childHeightList.get(end - 1) / 4) * (float) tempMove;
        }

        for (int i = 0; i < tempA.size(); i++) {
            moveToList.add(tempA.get(i));
            moveToList.add(tempB.get(i));
        }
        moveToList.add(centerProperty);

        return moveToList;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 手指位置地点
        float Y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 如果屏幕的动画还没结束，你就按下了，我们就结束该动画
                if (_scroller != null) {
                    if (!_scroller.isFinished()) {
                        _scroller.abortAnimation();
                    }
                }
                _lastionMotionY = Y;
                break;
            case MotionEvent.ACTION_MOVE:
                moveTo((int) (_lastionMotionY - Y));
                _lastionMotionY = Y;
                break;
            case MotionEvent.ACTION_UP:
                if (_fromActivity != null && _toClass != null) {
                    if (Math.abs(Y - event.getY()) < 5) {
                        if ((event.getY() > getCenterChildViewTop() && event.getY() < getCenterChildViewBottom())
                                && (event.getX() > getChildViewLeft(getChildCount() - 1) && event.getX() < getChildViewRight(getChildCount() - 1))) {
                            if (_flag != null) {
                                openActivity(_fromActivity, _toClass, _flag, _value);
                            } else {
                                openActivity(_fromActivity, _toClass);
                            }
                        }
                    }
                }

                // 下移
                if (move < 0) {
                    scrollBy(0, -move);
                    move = 0;
                    requestLayout();
                }
                // 上移
                if (move > 0) {
                    scrollBy(0, -move);
                    move = 0;
                    requestLayout();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    /**
     * 只有当前LAYOUT中的某个CHILD导致SCROLL发生滚动，才会致使自己的COMPUTESCROLL被调用
     */
    @Override
    public void computeScroll() {
        // 如果返回true，表示动画还没有结束
        // 因为前面startScroll，所以只有在startScroll完成时 才会为false
        if (_scroller.computeScrollOffset()) {
            // 产生了动画效果 每次滚动一点
            scrollTo(_scroller.getCurrX(), _scroller.getCurrY());
            // 刷新View 否则效果可能有误差
            postInvalidate();
        }
    }

    // measure过程
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        createVisiableChildHeightList(_childScaleList);
        createVisiableChildYList();
        toLayout();
        for (int i = 0; i < getChildCount(); i++) {
            scaleChildView(i, _childScaleList.get(i));
        }
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setAlpha(_childAlphaList.get(i));
        }
    }

    /**
     * 重新刷新子视图的位置
     */
    private void toLayout() {
        for (int i = 0; i < getChildCount(); i += 2) {
            getChildAt(i).layout(getChildViewLeft(i), (int) Math.rint(_childYList.get(i)), getChildViewRight(i),
                    (int) Math.rint(_childYList.get(i)) + getChildViewHeight(i));
            if (i + 1 < getChildCount()) {
                getChildAt(i + 1).layout(getChildViewLeft(i + 1), (int) Math.rint(_childYList.get(i + 1)),
                        getChildViewRight(i + 1), (int) Math.rint(_childYList.get(i + 1)) + getChildViewHeight(i + 1));
            }
        }
    }

    /**
     * 测试输出用
     *
     * @param list
     * @param str
     */
    private void LOG(ArrayList list, String str) {
        for (int i = 0; i < list.size(); i++) {
            Log.e(TAG, list.get(i) + str);
        }
    }

    /**
     * 拷贝列表
     *
     * @param from
     * @param to
     */
    private void copyList(ArrayList from, ArrayList to) {
        for (int i = 0; i < from.size(); i++) {
            to.add(from.get(i));
        }
    }

    /**
     * 打开新的页面
     *
     * @param activity
     * @param clzz
     * @param strValue
     * @param value
     */
    private void openActivity(Activity activity, Class clzz, String strValue, int value) {
        Intent intent = new Intent(activity, clzz);
        intent.putExtra(strValue, value);
        activity.startActivity(intent);
        System.gc();
    }

    /**
     * 打开新的页面
     *
     * @param activity
     * @param clzz
     */
    private void openActivity(Activity activity, Class clzz) {
        activity.startActivity(new Intent(activity, clzz));
    }


}