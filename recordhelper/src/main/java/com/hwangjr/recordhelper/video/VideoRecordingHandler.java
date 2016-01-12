package com.hwangjr.recordhelper.video;

import android.hardware.Camera.Size;

public interface VideoRecordingHandler {
    public boolean onPrepareRecording();

    public Size getVideoSize();

    public int getDisplayRotation();
}
