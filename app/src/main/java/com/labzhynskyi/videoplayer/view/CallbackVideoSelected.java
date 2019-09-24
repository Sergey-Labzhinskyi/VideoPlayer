package com.labzhynskyi.videoplayer.view;

import com.labzhynskyi.videoplayer.model.VideoItem;


interface CallbackVideoSelected {
    void videoSelected(VideoItem videoItem);
    void setTitle(VideoItem videoItem);
    int getPosition();
    void setPositionVideo(int pos);
    void playVideo();
    void pauseVideo();
    void setTime(String time);
    void setInVisibility();
    void setVisibility();}
