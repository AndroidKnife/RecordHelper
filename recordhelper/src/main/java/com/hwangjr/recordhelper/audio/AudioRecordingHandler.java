package com.hwangjr.recordhelper.audio;

public interface AudioRecordingHandler {
    public void onFftDataCapture(byte[] bytes);

    public void onRecordSuccess();

    public void onRecordingError();

    public void onRecordSaveError();
}
