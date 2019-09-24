package com.labzhynskyi.videoplayer.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class VideoItemList {

    private static final String TAG = "VideoItemList";

    public static List<VideoItem> sVideoItems;

    public void addVideoItem(VideoItem videoItem){
        if (sVideoItems == null){
            sVideoItems = new ArrayList<>();
            sVideoItems.add(videoItem);
        }else {
            sVideoItems.add(videoItem);
        }
        Log.d(TAG, String.valueOf(sVideoItems.size()));
    }

    public VideoItem loadVideoItem(){
        VideoItem videoItem = sVideoItems.get(sVideoItems.size() - 1);
        return videoItem;
    }


}
