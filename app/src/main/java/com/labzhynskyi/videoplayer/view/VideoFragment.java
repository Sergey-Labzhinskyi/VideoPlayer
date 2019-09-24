package com.labzhynskyi.videoplayer.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.labzhynskyi.videoplayer.model.VideoItem;




public class VideoFragment extends Fragment implements CallbackVideoSelected {


    private static final String TAG = "VideoFragment";
    private VideoView mVideoView;
    private View mView;
    private ImageButton mBackwardButton;
    private ImageButton mPlayButton;
    private ImageButton mPauseButton;
    private ImageButton mForwardButton;
    private TextView mTextView;
    private boolean touch = true;
    private CallbackOnClick mCallbackOnClick;

    interface CallbackOnClick {
        void onClickBackward();
        void onClickPlay();
        void onClickPause();
        void onClickForward();
        void onSetTime();
        void timer();
        void onClickInVisibility();
        void onClickVisibility();
    }


    public static VideoFragment newInstance() {
        final VideoFragment fragment = new VideoFragment();
        final Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mCallbackOnClick = (VideoFragment.CallbackOnClick) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement Callback");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        mView = inflater.inflate(R.layout.fragment_video_item, container, false);



        mBackwardButton = (ImageButton) mView.findViewById(R.id.backward_button);
        mBackwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOnClick.onClickBackward();
                mCallbackOnClick.onSetTime();
                Log.d(TAG, "backward");
            }
        });
        mPlayButton = (ImageButton) mView.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOnClick.onClickPlay();
                mCallbackOnClick.onSetTime();
                Log.d(TAG, "start");
            }
        });
        mPauseButton = (ImageButton) mView.findViewById(R.id.pause_button);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mCallbackOnClick.onClickPause();

            Log.d(TAG, "pause");
            }
        });
        mForwardButton = (ImageButton) mView.findViewById(R.id.forward_button);
        mForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOnClick.onClickForward();
                mCallbackOnClick.onSetTime();

                Log.d(TAG, "forward");
            }
        });

        return mView;
    }



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void videoSelected(VideoItem videoItem) {
        Log.d(TAG, "videoSelected");
        mVideoView = (VideoView) mView.findViewById(R.id.videoView);
        mVideoView.setVideoPath(videoItem.getVideoUri());
        mVideoView.requestFocus(0);
        mVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch");
                if (touch){
                    mCallbackOnClick.onClickVisibility();
                    touch = false;
                }else {
                    mCallbackOnClick.onClickInVisibility();
                    touch = true;
                }
                return false;
            }
        });

        mVideoView.start();
        mTextView = (TextView) mView.findViewById(R.id.duration);
        mCallbackOnClick.timer();
        mCallbackOnClick.onClickInVisibility();

    }

    @Override
    public void setTitle(VideoItem videoItem) {
        getActivity().setTitle(videoItem.getTitle() );
    }


    @Override
    public int getPosition(){
        int pos = mVideoView.getCurrentPosition();
        return pos;
    }

    @Override
    public void setPositionVideo(int pos) {
        mVideoView.seekTo(pos);
    }

    @Override
    public void playVideo() {
        mVideoView.start();
    }

    @Override
    public void pauseVideo() {
        mVideoView.pause();
    }

    @Override
    public void setTime(String time) {
        mTextView.setText(time);
    }

    @Override
    public void setInVisibility() {
        mTextView.setVisibility(View.INVISIBLE);
        mBackwardButton.setVisibility(View.INVISIBLE);
        mPlayButton.setVisibility(View.INVISIBLE);
        mPauseButton.setVisibility(View.INVISIBLE);
        mForwardButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setVisibility() {
        mTextView.setVisibility(View.VISIBLE);
        mBackwardButton.setVisibility(View.VISIBLE);
        mPlayButton.setVisibility(View.VISIBLE);
        mPauseButton.setVisibility(View.VISIBLE);
        mForwardButton.setVisibility(View.VISIBLE);
    }
}
