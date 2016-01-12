package com.hwangjr.jhelper;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.Reader;
import java.util.Iterator;

/**
 * This class wraps a LineIterator. It is useful to use this in an enhanced for loop.
 */
public class IterableLineReader implements Iterable<String>, Closeable {

    private final BufferedReader bufferedReader;
    private boolean trim;
    private Filter filters[] = new Filter[0];

    public IterableLineReader(Reader reader) {
        if (reader instanceof BufferedReader)
            this.bufferedReader = (BufferedReader) reader;
        else
            this.bufferedReader = new BufferedReader(reader);

    }

    public IterableLineReader(Reader reader, boolean trim, Filter[] filters) {
        if (reader instanceof BufferedReader)
            this.bufferedReader = (BufferedReader) reader;
        else
            this.bufferedReader = new BufferedReader(reader);
        this.filters = filters;
        this.trim = trim;

    }

    public void close() {
        IOs.closeSilently(bufferedReader);
    }

    public Iterator<String> iterator() {
        return new LineIterator(bufferedReader, trim, filters);
    }
}
