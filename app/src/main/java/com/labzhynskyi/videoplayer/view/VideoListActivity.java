package com.labzhynskyi.videoplayer.view;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.labzhynskyi.videoplayer.model.VideoItem;
import com.labzhynskyi.videoplayer.presenter.VideoListPresenter;

import java.util.List;


public class VideoListActivity extends AppCompatActivity implements IVideoListActivity, VideoListFragment.CallbackOnClickList {

    private static final String TAG = "VideoListActivity";
    private VideoListPresenter mVideoListPresenter;
    private List<VideoItem> mVideoItems;
    private Context mContext;
    private Toolbar mToolbar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mVideoListPresenter = new VideoListPresenter();
        Log.d(TAG, "onCreate ");
        mContext = this;
        getVideoList(this, mContext);
        if (savedInstanceState == null) {
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction ft = fm.beginTransaction();
            final VideoListFragment fragment = VideoListFragment.newInstance(mVideoItems);
            ft.add(R.id.fragment_container, fragment);
            ft.commit();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoListPresenter.detachView();
    }

    public void getVideoList(Activity activity, Context context) {
        Log.d(TAG, "getVideoList start");
        mVideoListPresenter.attachView(this);
        mVideoListPresenter.checkStorage(activity, context);
        Log.d(TAG, "permission and");

    }


   //implements  VideoListFragment.CallbackOnClickList
    @Override
    public void onClicked(VideoItem videoItem) {
        mVideoListPresenter.onClicked(videoItem);
    }

   // implements IVideoListActivity
    @Override
    public void updateUI(List<VideoItem> list){
        mVideoItems =  list;
    }
    @Override
    public void startVideoActivity(){
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);

    }
}
