package com.hwangjr.soundhelper.pcm;

import com.hwangjr.soundhelper.dsp.DoubleVector;
import com.hwangjr.soundhelper.dsp.DoubleVectorFrameSource;
import com.hwangjr.soundhelper.dsp.DoubleVectorProcessor;
import com.hwangjr.soundhelper.dsp.WindowerFactory;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class IterableFrameReaderTest {

    public static final double EPSILON = 0.01;

    @Test
    public void testIterableFrameReader() throws IOException {
        PcmMonoInputStream pmis = new MonoWavFileReader("wav-samples/square-0_8amp-440hz-1000sample-16bit-mono.wav").getNewStream();
        DoubleVectorFrameSource dvfs = DoubleVectorFrameSource.fromSampleAmount(pmis, 10, 50);
        int frameCounter = 0;
        for (DoubleVector ddf : dvfs.getIterableFrameReader()) {
            frameCounter++;
            Assert.assertEquals(ddf.size(), 100);
            for (double d : ddf.getData()) {
                Assert.assertTrue("oops:" + d, d <= (0.8d + EPSILON) && d > (-0.8d - EPSILON));
            }
        }
        Assert.assertEquals(frameCounter, (1000 - 100) / 50 + 1);
        pmis.close();
    }

    public static void main(String[] args) throws IOException {
        PcmMonoInputStream pmis = new MonoWavFileReader("wav-samples/square-0_8amp-440hz-1000sample-16bit-mono.wav").getNewStream();
        DoubleVectorFrameSource dvfs = DoubleVectorFrameSource.fromSampleAmount(pmis, 10, 50);
        DoubleVectorProcessor windower = WindowerFactory.newHammingWindower(dvfs.getFrameSize());
        for (DoubleVector ddf : dvfs.getIterableFrameReader()) {
            System.out.println("orig:" + ddf);
            System.out.println("proc:" + windower.process(ddf));
        }
    }
}
