package com.hwangjr.recordhelper.visualizer;

// Data class to explicitly indicate that these bytes are the FFT of audio data
public class FFTData {
    public FFTData() {
    }

    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
