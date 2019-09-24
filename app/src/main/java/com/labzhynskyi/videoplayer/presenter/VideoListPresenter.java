package com.labzhynskyi.videoplayer.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import com.labzhynskyi.videoplayer.model.VideoItem;
import com.labzhynskyi.videoplayer.model.VideoItemList;
import com.labzhynskyi.videoplayer.model.VideoUtils;
import com.labzhynskyi.videoplayer.view.IVideoListActivity;

import java.util.ArrayList;
import java.util.List;

public class VideoListPresenter implements IVideoListPresenter {


    private VideoUtils mVideoUtils;
    private List<VideoItem> mVideoItems;
    private VideoItemList mVideoItemLists;
    private IVideoListActivity mIVideoListActivity;

    @Override
    public void attachView(IVideoListActivity videoListActivity) {
        mIVideoListActivity = videoListActivity;
    }


    @Override
    public void detachView() {
        mIVideoListActivity = null;
    }

    @Override
    public void checkStorage(Activity activity, Context context){
        Log.d("checkStorage", "checkStorage");
        mVideoUtils = new VideoUtils();
        if (mVideoUtils.checkPermissionStorage()){
            mVideoItems = new ArrayList<>();
            mVideoItems = mVideoUtils.getlistVideo();
            mIVideoListActivity.updateUI(mVideoItems);
        }
    }
    @Override
    public void onClicked(VideoItem videoItem){
        mVideoItemLists = new VideoItemList();
        mVideoItemLists.addVideoItem(videoItem);
        mIVideoListActivity.startVideoActivity();
    }
}
