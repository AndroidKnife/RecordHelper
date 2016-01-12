package com.hwangjr.recordhelper.visualizer;

// Data class to explicitly indicate that these bytes are raw audio data
public class AudioData {
    public AudioData() {
    }

    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
