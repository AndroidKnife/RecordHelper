package com.hwangjr.jhelper;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.System.getProperty;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

public class SimpleTextReaderTest {

    private static String curDir = getProperty("user.dir");

    @Test
    public void testUtf8() throws IOException {
        String content = new SimpleTextReader("test/turkish_utf8_with_BOM.txt", "utf-8").asString();
        assertEquals(content, "\u015fey");
    }

    @Test
    public void multilineTest() throws IOException {
        List<String> list = new SimpleTextReader("test/multi_line_text_file.txt").asStringList();
        assertEquals(list.size(), 17);
        assertEquals(list.get(1), "uno");
        //test trim
        assertEquals(list.get(2), "  dos");
    }

    @Test
    public void multilineConstarintTest() throws IOException {
        List<String> list = new SimpleTextReader.Builder("test/multi_line_text_file.txt")
                .allowMatchingRegexp("^[^#]")
                .ignoreWhiteSpaceLines()
                .trim()
                .build()
                .asStringList();
        assertEquals(list.size(), 12);
        assertEquals(list.get(0), "uno");
        assertEquals(list.get(1), "dos");
    }

    public void templateTest() throws IOException {
        SimpleTextReader.Template template = new SimpleTextReader.Template()
                .allowMatchingRegexp("^[^#]")
                .ignoreWhiteSpaceLines()
                .trim();
        List<File> files = Files.crawlDirectory(new File("blah"));
        for (File file : files) {
            SimpleTextReader sr = template.generateReader(file);
            //....
        }

    }

    @Test
    public void asStringTest() throws IOException {
        String a = new SimpleTextReader("test/multi_line_text_file.txt").asString();
        System.out.println(a);
    }

    @Test
    public void iterableTest() throws IOException {
        int i = 0;
        for (String s : new SimpleTextReader("test/multi_line_text_file.txt").getIterableReader()) {
            if (i == 1) assertEquals(s.trim(), "uno");
            if (i == 2) assertEquals(s.trim(), "dos");
            if (i == 3) assertEquals(s.trim(), "tres");
            i++;
        }
        assertEquals(i, 17);
    }

    @Test
    public void lineIteratorTest2() throws IOException {
        LineIterator li = new SimpleTextReader("test/multi_line_text_file.txt").getLineIterator();
        while (li.hasNext())
            out.println(li.next().toUpperCase());
        IOs.closeSilently(li);

    }

    @Test
    public void lineIteratorWithConstraint() throws IOException {
        LineIterator li = new SimpleTextReader
                .Builder("test/multi_line_text_file.txt")
                .ignoreWhiteSpaceLines()
                .trim()
                .build().getLineIterator();

        int i = 0;
        while (li.hasNext()) {
            String s = li.next();
            if (i == 0) assertEquals(s, "uno");
            if (i == 1) assertEquals(s, "dos");
            i++;
        }
        IOs.closeSilently(li);
    }

    public static void main(String[] args) {
        Pattern patten = Pattern.compile("^[#]+");

        System.out.println(" ### sdd".replaceAll("^[^#]", "ass"));
    }

}