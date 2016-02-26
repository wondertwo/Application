package com.wondertwo.app.application;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Allenieo on 2016/2/25.
 */
public class TopBar extends RelativeLayout {

    // 包含topbar上的元素：左按钮、右按钮、标题
    private Button mLeftButton, mRightButton;
    private TextView mTitleView;

    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mLeftParams, mTitlepParams, mRightParams;

    // 左按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    // 右按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    // 标题的属性值，即我们在atts.xml文件中定义的属性
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    // 映射传入的接口对象
    private topbarClickListener mListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(0xFFF59563);
        // 通过这个方法，将在attrs中定义的declare-styleable的所有属性的值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        // 从TypedArray中取出相应的值来为要设置的属性赋值
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor_1, 0);
        mTitle = ta.getString(R.styleable.TopBar_title_1);

        // 获取完TypedArray的属性值后，一般要调用recyle方法来避免重新创建的时候发生错误
        ta.recycle();

        // 开始组合控件
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        // 用我们引用的属性值来为左、右的点击按钮和中间的标题栏三个组件元素赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mLeftTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        // 为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 添加到ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitlepParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitlepParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitlepParams);

        // 按钮的点击事件，不需要具体的实现，只需调用接口的方法，回调时再具体去实现
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });
    }

    /**
     * 设置按钮的显示与否，通过id区分按钮，flag区分是否显示
     *
     * @param id
     * @param flag
     */
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(View.GONE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 暴露一个方法给调用者来实现接口的抽象方法，也就是按钮的点击监听
     *
     * @param mListener
     */
    public void setOntopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 接口对象，实现回调机制，通过映射的接口对象调用接口中的
     * 方法，而不用考虑具体实现，具体实现由回调者自己去实现
     */
    public interface topbarClickListener {
        // 左按钮点击事件
        void leftClick();
        // 右按钮点击事件
        void rightClick();
    }

}

