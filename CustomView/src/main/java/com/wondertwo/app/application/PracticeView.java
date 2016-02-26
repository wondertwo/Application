package com.wondertwo.app.application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 *
 * Created by Allenieo on 2016/2/24.
 */
public class PracticeView extends View {

    public PracticeView(Context context) {
        super(context);
    }

    public PracticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        int width = getWidth();
        int height = getHeight();
        Log.d("xys", "width : " + width + " height : " + height);
    }

    /**
     * 下面是自定义的measureHeight()方法、measureWidth()方法实现对宽、高值的自定义
     *
     * @param measureSpec
     * @return
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        // MeasureSpec.getMode()提取measureSpec对象中包含的测量模式
        int specMode = MeasureSpec.getMode(measureSpec);
        // MeasureSpec.getSize()提取measureSpec对象中包含的测量值大小
        int specSize = MeasureSpec.getSize(measureSpec);

        // 判断测量类型，根据不同的测量模式specMode来指定不同的测量值specSize
        if (specMode == MeasureSpec.EXACTLY) {
            // 模式为EXACTLY，直接使用指定的specSize
            result = specSize;
        } else {
            // 否则，需要制定默认的测量值为200
            result = 200;
            // 判断，如果测量模式为AT_MOST，测量值指定为result, specSize中的最小值
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * measureHeight()方法的含义同上面的measureWidth()一样
     *
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
