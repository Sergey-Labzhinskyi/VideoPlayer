package com.labzhynskyi.videoplayer.view;

import com.labzhynskyi.videoplayer.model.VideoItem;

import java.util.List;

public interface IVideoListActivity {
    void updateUI(List<VideoItem> list);
    void startVideoActivity();
}
