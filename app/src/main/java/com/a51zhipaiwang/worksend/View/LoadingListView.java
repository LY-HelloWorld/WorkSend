package com.a51zhipaiwang.worksend.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.R;

/**
 * Created by 罗怡 on 2017/9/3.
 */

public class LoadingListView extends ListView implements AbsListView.OnScrollListener{


    private View foot_view;
    private View foot_layout;
    private View no_more_layout;
    private TextView no_more_info;
    private int last_visble_count;
    private int totalItemCount;
    private LoadInterface loadInterface;
    private boolean is_scrolling;
    private int move_y;
    private int down_y;
    private int up_y;

    public LoadingListView(Context context) {
        super(context);
        init(context);
    }

    public LoadingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //添加上拉刷新提示信息布局
    private void init(Context context) {
        foot_view = LayoutInflater.from(context).inflate(R.layout.more_layout, null);
        foot_layout = foot_view.findViewById(R.id.load_layout);
        no_more_layout = foot_view.findViewById(R.id.no_more_layout);
        no_more_info = (TextView) foot_view.findViewById(R.id.no_more_info);
        foot_layout.setVisibility(GONE);
        no_more_layout.setVisibility(VISIBLE);
        no_more_layout.setClickable(false);
        foot_layout.setClickable(false);
        this.addFooterView(foot_view);
        this.setOnScrollListener(this);
    }


    //加载更多
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (last_visble_count == totalItemCount && scrollState == SCROLL_STATE_TOUCH_SCROLL) {
            //MyLog.e("LoadingListView", "onScrollStateChanged(LoadingListView.java:68)" + no_more_layout.getHeight() + "  " + move_y);
            if (!is_scrolling && move_y > 0) {
                is_scrolling = true;
                no_more_layout.setVisibility(GONE);
                foot_layout.setVisibility(VISIBLE);
                if (loadInterface != null) {
                    loadInterface.load();
                }
            }/*else {
                no_more_info.setPadding(0, 0, 0, move_y);
            }*/
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        last_visble_count = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_y = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                up_y = (int) ev.getY();
                break;
        }
        move_y = down_y - up_y;
        //MyLog.e("LoadingListView", "onTouchEvent(LoadingListView.java:95)" + down_y + "  " + up_y + "  " + move_y);
        return super.onTouchEvent(ev);
    }

    public void loadComplete() {
        no_more_layout.setVisibility(VISIBLE);
        foot_layout.setVisibility(GONE);
        //no_more_info.setPadding(0, 0, 0, 0);
        is_scrolling = false;

    }

    //设置加载回调
    public void setLoadInterface(LoadInterface loadInterface) {
        this.loadInterface = loadInterface;
    }


    public interface LoadInterface {

        void load();

    }

    public TextView getNo_more_info(){
        return no_more_info;
    }

}
