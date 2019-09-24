package com.labzhynskyi.videoplayer.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class VideoUtils {
    private static final String DIRECTORY_DCIM = "/DCIM/Camera";
    public static final String CAMERA_VIDEO_BUCKET_NAME = Environment.getExternalStorageDirectory().toString() + DIRECTORY_DCIM;
    private static final int RC_READ_STORAGE = 1;

    private  List<VideoItem> mVideoItems;
    private Context mContext;

    // get id of video bucket from path
    public static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }

    //get listVideo
    public  List<VideoItem> getlistVideo() {
        final String[] projection = {MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA};
        final String selection = MediaStore.Video.Media.BUCKET_ID + " = ?";
        final String[] selectionArgs = {VideoUtils.getBucketId(CAMERA_VIDEO_BUCKET_NAME)};
        final Cursor cursor = mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);
        mVideoItems = new ArrayList<>(cursor.getCount());
        if (cursor.moveToFirst()) {
            final int titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            do {
                VideoItem videoItem = new VideoItem(cursor.getString(titleColumn), cursor.getString(dataColumn));
                Log.d("URI", cursor.getString(dataColumn));
                mVideoItems.add(videoItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("COUNT", String.valueOf(mVideoItems.size()));

        return mVideoItems;
    }

    public boolean checkPermissionStorage(){
        Log.d("checkStorage", "checkStorage");
        mContext = MyApplication.getAppContext();
        boolean permission;
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            permission = true;
        } else {
            //request permission
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_READ_STORAGE);
            permission = false;
        }
        return permission;
    }
}
