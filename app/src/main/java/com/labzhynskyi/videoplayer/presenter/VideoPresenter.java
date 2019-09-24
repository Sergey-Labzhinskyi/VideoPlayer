package com.labzhynskyi.videoplayer.presenter;


import android.os.Handler;

import com.labzhynskyi.videoplayer.model.Time;
import com.labzhynskyi.videoplayer.model.VideoItemList;
import com.labzhynskyi.videoplayer.view.IVideoActivity;

public class VideoPresenter implements IVideoPresenter {


    private static final String TAG = "VideoPresenter";
    private VideoItemList mVideoItemList;
    private Time mTime;
    private IVideoActivity mIVideoActivity;


    {
        mTime = new Time();
    }


    @Override
    public void attachView(IVideoActivity ivideoActivity) {
        mIVideoActivity = ivideoActivity;
    }



    @Override
    public void videoIsSelected(){
        mVideoItemList = new VideoItemList();
        mIVideoActivity.playVideo(mVideoItemList.loadVideoItem());
        mIVideoActivity.setTitleVideo(mVideoItemList.loadVideoItem());
    }
    @Override
    public void backward(){
        int pos  = mIVideoActivity.getPositionVideo();
        pos -= 1000;
        mIVideoActivity.setPositionVideo(pos);
    }

    @Override
    public void play(){
        mIVideoActivity.play();
    }
    @Override
    public void pause() {
        mIVideoActivity.pauseVideo();
    }
    @Override
    public void forward() {
        int pos  = mIVideoActivity.getPositionVideo();
        pos += 1000;
        mIVideoActivity.setPositionVideo(pos);
    }
    @Override
    public void setTime() {
        int pos  = mIVideoActivity.getPositionVideo();
        mIVideoActivity.setTime(mTime.getTime(pos));
    }
    @Override
    public void timer() {
        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable(){
            public void run(){
                int pos  = mIVideoActivity.getPositionVideo();
                mIVideoActivity.setTime(mTime.getTime(pos));
                handler.postDelayed(this, delay);
            }
        }, delay);

    }
    @Override
    public void inVisibility() {
        mIVideoActivity.setInVisibility();
    }
    @Override
    public void visibility() {
        mIVideoActivity.setVisibility();
    }
}
