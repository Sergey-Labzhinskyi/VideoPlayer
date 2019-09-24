package com.labzhynskyi.videoplayer.model;


import java.io.Serializable;

public class VideoItem implements Serializable {

    private String title;
    private String videoUri;

    public VideoItem(String title, String videoUri) {
        this.title = title;
        this.videoUri = videoUri;
    }

    public String getTitle() {
        return title;
    }


    public String getVideoUri() {
        return videoUri;
    }

}
