package com.labzhynskyi.videoplayer.model;

import java.util.Formatter;
import java.util.Locale;

public class Time {

    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;

    public String getTime(int pos){
        int totalSeconds = pos / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours   = totalSeconds / 3600;

        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        String time;
        mFormatBuilder.setLength(0);

        if (hours > 0) {
            time = mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            time = mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
        return time;
    }
}
