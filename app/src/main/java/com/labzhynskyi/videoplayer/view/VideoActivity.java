package com.labzhynskyi.videoplayer.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.labzhynskyi.videoplayer.model.VideoItem;
import com.labzhynskyi.videoplayer.presenter.VideoPresenter;

public class VideoActivity extends AppCompatActivity implements VideoFragment.CallbackOnClick, IVideoActivity {

    private VideoPresenter mVideoPresenter;
    private CallbackVideoSelected mCallbackVideoSelected;
    private VideoFragment mVideoFragment;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mVideoPresenter = new VideoPresenter();

        if (savedInstanceState == null) {
             FragmentManager fm = getSupportFragmentManager();
             FragmentTransaction ft = fm.beginTransaction();
             mVideoFragment = VideoFragment.newInstance();
            ft.add(R.id.fragment_container, mVideoFragment);
            ft.commit();
        }

        if (mVideoFragment instanceof VideoFragment){
            mCallbackVideoSelected = (CallbackVideoSelected) mVideoFragment;
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStart() {
        super.onStart();
        videoSelect();
    }

    public void videoSelect(){
        mVideoPresenter.attachView(this);
        mVideoPresenter.videoIsSelected();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    //implements IVideoActivity
    @Override
    public void setTitleVideo(VideoItem videoItem){
        mCallbackVideoSelected.setTitle(videoItem);
    }
    @Override
    public void playVideo(VideoItem videoItem){
        mCallbackVideoSelected.videoSelected(videoItem);
    }
    @Override
    public int getPositionVideo(){
       return mCallbackVideoSelected.getPosition();
    }
    @Override
    public void setPositionVideo(int pos){
        mCallbackVideoSelected.setPositionVideo(pos);
    }
    @Override
    public void play(){
        mCallbackVideoSelected.playVideo();
    }
    @Override
    public void pauseVideo(){
        mCallbackVideoSelected.pauseVideo();
    }
    @Override
    public void setTime(String time) {
        mCallbackVideoSelected.setTime(time);
    }
    @Override
    public void setInVisibility() {
       mCallbackVideoSelected.setInVisibility();
    }
    @Override
    public void setVisibility() {
        mCallbackVideoSelected.setVisibility();
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //implements VideoFragment.CallbackOnClick
    @Override
    public void onClickBackward() {
        mVideoPresenter.backward();
    }

    @Override
    public void onClickPlay() {
        mVideoPresenter.play();
    }

    @Override
    public void onClickPause() {
        mVideoPresenter.pause();
    }

    @Override
    public void onClickForward() {
        mVideoPresenter.forward();
    }

    @Override
    public void onSetTime() {
        mVideoPresenter.setTime();
    }

    @Override
    public void timer() {
        mVideoPresenter.timer();
    }

    @Override
    public void onClickInVisibility() {
        mVideoPresenter.inVisibility();
    }

    @Override
    public void onClickVisibility() {
        mVideoPresenter.visibility();
    }




}
