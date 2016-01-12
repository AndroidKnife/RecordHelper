package com.hwangjr.soundhelper.pcm;

import com.hwangjr.jhelper.Bytes;
import com.hwangjr.jhelper.IOs;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PcmMonoOutputStream extends OutputStream implements Closeable {

    final PcmAudioFormat format;
    final DataOutputStream dos;

    public PcmMonoOutputStream(PcmAudioFormat format, DataOutputStream dos) {
        this.format = format;
        this.dos = dos;
    }

    public PcmMonoOutputStream(PcmAudioFormat format, File file) throws IOException {
        this.format = format;
        this.dos = new DataOutputStream(new FileOutputStream(file));
    }

    public void write(int b) throws IOException {
        dos.write(b);
    }

    public void write(short[] shorts) throws IOException {
        dos.write(Bytes.toByteArray(shorts, shorts.length, format.isBigEndian()));
    }

    public void write(int[] ints) throws IOException {
        dos.write(Bytes.toByteArray(ints, ints.length, format.getBytePerSample(), format.isBigEndian()));
    }

    public void close() {
        IOs.closeSilently(dos);
    }
}
