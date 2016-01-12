package com.hwangjr.jhelper;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An Iterator over the lines in a <code>Reader</code>.
 * <p/>
 * <code>LineIterator</code> holds a reference to an open <code>Reader</code>.
 * if there hasNext() returns false, it automatically closes the reader. if somewhat
 * an early return is possible iterator or the reader should be closed by calling {@link #close()} method.
 * <p/>
 * The recommended usage pattern is:
 * <pre>
 * LineIterator it = new LineIterator(Files.getReader("filename", "UTF-8"));
 * try {
 *   while (it.hasNext()) {
 *     String line = it.next();
 *     /// do something with line
 *   }
 * } finally {
 *   it.close();
 * }
 * </pre>
 * <p/>
 * This class uses code from Apache commons io LineIterator class. however, it's behavior is slightly different.
 */
public class LineIterator implements Iterator<String>, Closeable {

    private final BufferedReader bufferedReader;
    /**
     * The current line.
     */
    private String cachedLine;
    /**
     * A flag indicating if the iterator has been fully read.
     */
    private boolean finished = false;

    private boolean trim = false;

    private Filter filters[] = new Filter[0];

    public LineIterator(InputStream is) {
        Preconditions.checkNotNull(is, "InputStream cannot be null!");
        this.bufferedReader = IOs.getReader(is);
    }

    public LineIterator(Reader reader) {
        Preconditions.checkNotNull(reader, "Reader cannot be null!");
        if (reader instanceof BufferedReader)
            this.bufferedReader = (BufferedReader) reader;
        else
            this.bufferedReader = new BufferedReader(reader);
    }

    public LineIterator(Reader reader, boolean trim, Filter... filters) {
        Preconditions.checkNotNull(reader, "Reader cannot be null!");
        if (reader instanceof BufferedReader) {
            this.bufferedReader = (BufferedReader) reader;
        } else
            this.bufferedReader = new BufferedReader(reader);
        if (filters != null && filters.length > 0)
            this.filters = filters;
        this.trim = trim;
    }

    public boolean hasNext() {
        if (cachedLine != null) {
            return true;
        } else if (finished) {
            close();
            return false;
        } else {
            try {
                String line;
                do {
                    line = bufferedReader.readLine();
                    if (line != null && trim) {
                        line = line.trim();
                    }
                }
                while (line != null && filters.length > 0 && !StringFilters.canPassAll(line, filters));

                if (line == null) {
                    finished = true;
                    close();
                    return false;
                } else {
                    cachedLine = line;
                    return true;
                }
            } catch (IOException ioe) {
                close();
                throw new IllegalStateException(ioe.toString());
            }
        }
    }

    public String next() {
        if (!hasNext()) {
            close();
            throw new NoSuchElementException("No more lines");
        }
        String currentLine = cachedLine;
        cachedLine = null;
        return currentLine;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is not implemented in LineIterator class.");
    }

    public void close() {
        IOs.closeSilently(bufferedReader);
    }
}
