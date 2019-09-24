package com.labzhynskyi.videoplayer.presenter;

import android.app.Activity;
import android.content.Context;

import com.labzhynskyi.videoplayer.model.VideoItem;
import com.labzhynskyi.videoplayer.view.IVideoListActivity;

public interface IVideoListPresenter {
    void attachView(IVideoListActivity videoListActivity);
    void detachView();
    void checkStorage(Activity activity, Context context);
    void onClicked(VideoItem videoItem);
}
