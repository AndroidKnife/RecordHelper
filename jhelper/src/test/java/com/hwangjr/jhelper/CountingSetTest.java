package com.hwangjr.jhelper;

import junit.framework.Assert;

import org.junit.Test;

public class CountingSetTest {

    @Test
    public void testGenerate() {
        CountingSet<String> histogram = new CountingSet<String>();
        histogram.add("Apple", "Pear", "Plum", "Apple", "Apple", "Grape", "Pear");
        Assert.assertEquals(3, histogram.getCount("Apple"));
        Assert.assertEquals(2, histogram.getCount("Pear"));
        Assert.assertEquals(1, histogram.getCount("Plum"));
    }
}
