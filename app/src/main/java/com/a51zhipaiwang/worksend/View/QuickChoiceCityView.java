package com.a51zhipaiwang.worksend.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.DensityUtil;


public class QuickChoiceCityView extends View{

    private final static String[] WORDS = {"当", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
    private Paint paint;

    //字体边框宽高
    private int cellWidth;
    private int cellHeight;

    //当前索引位置
    private int curIndex = -1;
    //监听索引
    private OnIndexChangeListener indexChangeListener;

    public QuickChoiceCityView(Context context) {
        this(context, null);
    }

    public QuickChoiceCityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickChoiceCityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.text_hui));
        paint.setAntiAlias(true);//抗锯齿
        paint.setTextSize(DensityUtil.dp2px(getContext(),8));//设置文字大小
        paint.setFakeBoldText(true);//设置文字粗体
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        cellWidth = getMeasuredWidth();
        cellHeight = getMeasuredHeight()/WORDS.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i =0;i<WORDS.length;i++){
            String word = WORDS[i];
            Rect bound = new Rect();
            //获取字符串的宽高
            paint.getTextBounds(word,0,word.length(),bound);
            //将文字水平居中
            int x = (cellWidth-bound.width())/2;
            //垂直距离
            int y = i * cellHeight + (cellWidth+bound.width())/2;
            canvas.drawText(word,x,y,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int y = (int) event.getY();
                int index = y / cellHeight;
                if(index>=0 && index<WORDS.length) {
                    if(index!=curIndex){
                        curIndex = index;
                        if(indexChangeListener!=null){
                            indexChangeListener.onIndexChange(WORDS[curIndex]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int y1 = (int) event.getY();
                int index1 = y1 / cellHeight;
                if(index1>=0 && index1<WORDS.length) {
                    if (index1 != curIndex) {
                        curIndex = index1;
                        if(indexChangeListener!=null){
                            indexChangeListener.onIndexChange(WORDS[curIndex]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                curIndex = -1;
                break;

        }
        return super.onTouchEvent(event);
    }

    public void setOnIndexChangeListener(OnIndexChangeListener indexChangeListener) {
        this.indexChangeListener = indexChangeListener;
    }

    public interface OnIndexChangeListener{
        void onIndexChange(String words);
    }


}
