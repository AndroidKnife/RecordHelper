package com.hwangjr.soundhelper.pcm;

import junit.framework.Assert;

import org.junit.Test;

public class PcmAudioFormatTest {

    @Test
    public void testBuilders() {

        PcmAudioFormat paf = new PcmAudioFormat.Builder(16000).build();
        Assert.assertTrue(paf.getChannels() == 1 &&
                paf.getSampleRate() == 16000 &&
                paf.getSampleSizeInBits() == 16 &&
                paf.getBytePerSample() == 2 &&
                !paf.isBigEndian());

        paf = new PcmAudioFormat.Builder(8000).
                bigEndian().
                channels(2).
                sampleSizeInBits(12).
                unsigned().
                build();

        Assert.assertTrue(paf.getChannels() == 2 &&
                paf.getSampleRate() == 8000 &&
                paf.getSampleSizeInBits() == 12 &&
                paf.getBytePerSample() == 2 &&
                paf.isBigEndian());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderException() {
        new PcmAudioFormat.Builder(0).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderException2() {
        new PcmAudioFormat.Builder(1000).channels(0).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderException3() {
        new PcmAudioFormat.Builder(1000).channels(3).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderException4() {
        new PcmAudioFormat.Builder(-1).build();
    }


}
