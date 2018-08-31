package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.a51zhipaiwang.worksend.R;


public class VideoFrame extends BaseFragment {

    private VideoView videoView;
    private ImageView videoControlImage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_frame, null);
        init(view);
        setRegister();
        return view;
    }


    /**
     * 初始化数据
     * @param view
     */
    private void init(View view){
        videoView = (VideoView) view.findViewById(R.id.videoView);
        videoControlImage = (ImageView) view.findViewById(R.id.videoControlImage);

        //去掉VideoView 加载前黑屏
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                            @Override
                                            public void onPrepared(MediaPlayer mp) {
                                                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                                                    @Override
                                                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                                                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                                                            videoView.setBackgroundColor(Color.TRANSPARENT);
                                                        return true;
                                                    }
                                                });
                                            }
                                        });

        videoView.setMediaController(new MediaController(getActivity()));
        videoView.setVideoPath("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8");
        //videoView.setVideoPath("http://192.168.31.14:8081/#/peopleVideo");
        videoView.setMediaController(null);
        videoView.requestFocus();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setRegister(){
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (videoView.isPlaying()){
                    //如果正在播放则 显示暂停图片
                    videoControlImage.setVisibility(View.VISIBLE);
                    //视频暂停
                    videoView.pause();
                }
                return false;
            }
        });
        videoControlImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击暂停图片
                if (!videoView.isPlaying()){
                    //图片消失
                    videoControlImage.setVisibility(View.GONE);
                    //视频播放
                    videoView.start();
                }
            }
        });
    }



}
