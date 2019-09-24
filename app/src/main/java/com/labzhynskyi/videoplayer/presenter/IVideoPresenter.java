package com.labzhynskyi.videoplayer.presenter;

import com.labzhynskyi.videoplayer.view.IVideoActivity;

public interface IVideoPresenter {

     void attachView(IVideoActivity ivideoActivity);
     void videoIsSelected();
     void backward();
     void play();
     void pause();
     void forward();
     void setTime() ;
     void timer();
     void inVisibility();
     void visibility();
}
