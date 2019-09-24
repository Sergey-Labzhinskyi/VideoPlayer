package com.labzhynskyi.videoplayer.view;

import com.labzhynskyi.videoplayer.model.VideoItem;

public interface IVideoActivity {
     void setTitleVideo(VideoItem videoItem);
     void playVideo(VideoItem videoItem);
     int getPositionVideo();
     void setPositionVideo(int pos);
     void play();
     void pauseVideo();
     void setTime(String time);
     void setInVisibility();
     void setVisibility();
}
