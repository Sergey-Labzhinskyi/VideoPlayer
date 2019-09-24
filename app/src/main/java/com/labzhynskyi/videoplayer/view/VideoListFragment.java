package com.labzhynskyi.videoplayer.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.labzhynskyi.videoplayer.model.VideoItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VideoListFragment extends Fragment  {

    private static final String TAG = "VideoListFragment";
    private static final String ARG_PERMISSION = "VIDEO_PERMISSION";
    private RecyclerView mRecyclerView;
    private VideoAdapter mVideoAdapter;
    private CallbackOnClickList mCallbackOnClickList;
    private List<VideoItem> mVideoItems = new ArrayList<>();



    public static VideoListFragment newInstance(List<VideoItem> list) {
        final VideoListFragment fragment = new VideoListFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_PERMISSION, (Serializable) list);
        fragment.setArguments(arguments);
        return fragment;
    }


    interface CallbackOnClickList {
        void onClicked(VideoItem videoItem);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mCallbackOnClickList = (CallbackOnClickList) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement Callback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null && getArguments().containsKey(ARG_PERMISSION)) {
            mVideoItems = (List<VideoItem>) getArguments().getSerializable(ARG_PERMISSION);
            Log.d(TAG, String.valueOf(mVideoItems.size()));
        } else {
            throw new IllegalArgumentException("Must be created through newInstance(...)");
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.video_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(TAG, mRecyclerView.toString());

        updateUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        updateUI();
    }



    public void updateUI(){
        Log.d(TAG, "updateUI");
        mVideoAdapter = new VideoAdapter(mVideoItems);
        mRecyclerView.setAdapter(mVideoAdapter);
    }

    private class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTextView;
        private VideoItem mVideoItem;

        public VideoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_video, parent, false));
            itemView.setOnClickListener((View.OnClickListener) this);

            mTextView = itemView.findViewById(R.id.video_title);
        }
        // bind VideoHolder with ViedoItem
        public void bind(VideoItem videoItem){
            mVideoItem = videoItem;
            mTextView.setText(mVideoItem.getTitle());
        }

        @Override
        public void onClick(View v) {
            mCallbackOnClickList.onClicked(mVideoItem);


        }
    }


    private class VideoAdapter extends RecyclerView.Adapter{


        private List<VideoItem> mVideoItems;

        public VideoAdapter(List<VideoItem> videoItems) {
            mVideoItems = videoItems;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new VideoHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            VideoItem videoItem = mVideoItems.get(position);
            ((VideoHolder) holder).bind(videoItem);

        }

        @Override
        public int getItemCount() {
          return mVideoItems.size();


        }
    }


}
