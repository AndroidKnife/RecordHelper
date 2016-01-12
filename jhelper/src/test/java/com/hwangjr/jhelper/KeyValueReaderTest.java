package com.hwangjr.jhelper;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class KeyValueReaderTest {

    @Test
    public void testReader() throws IOException {
        Map<String, String> map = new KeyValueReader(":")
                .loadFromFile(new File("test/key-value-colon-separator.txt"));
        Assert.assertEquals(map.size(), 4);
        Assert.assertTrue(TestUtil.containsAllKeys(map, "1", "2", "3", "4"));
        Assert.assertTrue(TestUtil.containsAllValues(map, "bir", "iki", "uc", "dort"));
    }
}
