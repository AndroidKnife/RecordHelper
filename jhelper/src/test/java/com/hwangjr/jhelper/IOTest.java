package com.hwangjr.jhelper;

import org.junit.Ignore;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;


public class IOTest {

    @Ignore("Not a unit test")
    @Test
    public void readURI() throws IOException {
        URL url = new URL("http://www.google.com");
        IOs.copy(url.openStream(), new FileOutputStream("blah.txt"));
    }
}
