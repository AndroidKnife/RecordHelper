package com.hwangjr.soundhelper.pcm;

import junit.framework.Assert;

import org.junit.Test;

public class WavAuidoFormatTests {

    @Test
    public void testWavBuilders() {
        PcmAudioFormat wavFormat = new WavAudioFormat.Builder().
                sampleRate(8000).
                channels(1).
                sampleSizeInBits(16).
                build();

        Assert.assertTrue(wavFormat.getChannels() == 1 &&
                wavFormat.getSampleRate() == 8000 &&
                wavFormat.getSampleSizeInBits() == 16 &&
                !wavFormat.isBigEndian() &&
                wavFormat.isSigned() &&
                wavFormat.getBytePerSample() == 2);

        wavFormat = WavAudioFormat.wavFormat(8000, 8, 1);

        // notice the !isSigned() because 8 bit data is unsigned in wav
        Assert.assertTrue(wavFormat.getChannels() == 1 &&
                wavFormat.getSampleRate() == 8000 &&
                wavFormat.getSampleSizeInBits() == 8 &&
                !wavFormat.isBigEndian() &&
                !wavFormat.isSigned() &&
                wavFormat.getBytePerSample() == 1);

        wavFormat = WavAudioFormat.mono16Bit(44100);

        Assert.assertTrue(wavFormat.getChannels() == 1 &&
                wavFormat.getSampleRate() == 44100 &&
                wavFormat.getSampleSizeInBits() == 16 &&
                !wavFormat.isBigEndian() &&
                wavFormat.getBytePerSample() == 2);
    }

}
