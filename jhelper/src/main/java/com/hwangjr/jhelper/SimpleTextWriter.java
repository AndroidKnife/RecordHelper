package com.hwangjr.jhelper;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;

import static com.hwangjr.jhelper.Preconditions.checkNotNull;

/**
 * SimpleTextWriter is generally used for writing information to the files easily.
 * <p/>
 * This class will close the file interally after the operation is done by default.
 * However, if it is initiated using the Builder and keepOpen() or static "keepOpen"
 * factories are used, Writer neeeds to be closed by calling close() preferrably in a try-finally block.
 */
public final class SimpleTextWriter implements Closeable {

    private final String encoding;
    private final boolean keepOpen;
    private final BufferedWriter writer;
    private final OutputStream os;

    /**
     * This class provides a flexible way of constructing a SimpleFileWriter instance. Caller can set the encoding, if write
     * operations will append to the file or if the underlying output stream needs to be kept open after operations.
     * build() method will create a SimpleFileWriter with the set parameters.
     */
    public static class Builder {
        private String _encoding;
        private boolean _keepOpen;
        private BufferedWriter _writer;
        private OutputStream _os;

        public Builder(String fileName) throws IOException {
            checkNotNull(fileName, "File name cannot be null..");
            _encoding = Charset.defaultCharset().name();
            _os = new FileOutputStream(fileName);
            this._writer = IOs.getBufferedWriter(_os, _encoding);
        }

        public Builder(OutputStream os) {
            checkNotNull(os, "File name cannot be null..");
            _encoding = Charset.defaultCharset().name();
            this._os = os;
            this._writer = IOs.getBufferedWriter(os, _encoding);
        }

        public Builder(File file) throws IOException {
            checkNotNull(file, "File name cannot be null..");
            _encoding = Charset.defaultCharset().name();
            _os = new FileOutputStream(file);
            this._writer = IOs.getBufferedWriter(_os, _encoding);
        }

        public Builder encoding(String encoding) {
            if (encoding == null)
                this._encoding = Charset.defaultCharset().name();
            this._encoding = encoding;
            return this;
        }

        public Builder keepOpen() {
            this._keepOpen = true;
            return this;
        }

        public SimpleTextWriter build() {
            return new SimpleTextWriter(_writer, _os, _encoding, _keepOpen);
        }
    }

    /**
     * creates a one shot writer, meaning that writer will be closed automatically after any wrte method call.
     *
     * @param file file to write
     * @return a SimpleTextWriter
     * @throws IOException if a problem occurs while creating file.
     */
    public static SimpleTextWriter oneShotUTF8Writer(File file) throws IOException {
        return new Builder(file).encoding("utf-8").build();
    }

    /**
     * creates a one shot writer, meaning that writer will be closed automatically after any wrte method call.
     *
     * @param file file to write
     * @return a SimpleTextWriter
     * @throws IOException if a problem occurs while creating file.
     */
    public static SimpleTextWriter keepOpenUTF8Writer(File file) throws IOException {
        return new Builder(file).encoding("utf-8").keepOpen().build();
    }

    /**
     * creates a one shot writer, meaning that writer will be closed automatically after any wrte method call.
     *
     * @param os       output stream
     * @param encoding encoding. if null, default encoding is used.
     * @return a SimpleTextWriter
     * @throws IOException if a problem occurs while creating file.
     */
    public static SimpleTextWriter keepOpenWriter(OutputStream os, String encoding) throws IOException {
        return new Builder(os).encoding(encoding).keepOpen().build();
    }

    /**
     * creates a one shot writer, meaning that writer will be closed automatically after any wrte method call.
     *
     * @param os       output stream
     * @param encoding encoding. if null, default encoding is used.
     * @return a SimpleTextWriter
     * @throws IOException if a problem occurs while creating file.
     */
    public static SimpleTextWriter oneShotWriter(OutputStream os, String encoding) throws IOException {
        return new Builder(os).encoding(encoding).build();
    }

    private SimpleTextWriter(
            BufferedWriter writer,
            OutputStream os,
            String encoding,
            boolean keepOpen) {
        this.writer = writer;
        this.os = os;
        this.encoding = encoding;
        this.keepOpen = keepOpen;
    }

    /**
     * creates a one shot writer, meaning that writer will be closed automatically after any wrte method call.
     * Uses the default character encoding
     *
     * @param os output stream
     * @return a SimpleTextWriter
     * @throws IOException if a problem occurs while creating file.
     */
    public static SimpleTextWriter keepOpenWriter(OutputStream os) throws IOException {
        return new Builder(os).keepOpen().build();
    }

    /**
     * Creates a SimpleFileWriter using default encoding. it does not append to the File by default
     * and it closes the underlying output stream once any class method is called by default. If a different
     * behavior is required, SimpleFileWriter.Builder class needs to be used.
     * Please note that this constructor throws a runtime exception if file is not found instead of a FileNotFoundException
     *
     * @param fileName name of the file to be written.
     * @throws IOException if a problem occurs while creating file.
     */
    public SimpleTextWriter(String fileName) throws IOException {
        this(fileName, Charset.defaultCharset().name());
    }

    /**
     * Creates a SimpleFileWriter using default encoding. it does not append to the File by default
     * and it closes the underlying output stream once any class method is called by default. If a different
     * behavior is required, SimpleFileWriter.Builder class needs to be used.
     * Please note that this constructor throws a runtime exception if file is not found instead of a FileNotFoundException
     *
     * @param fileName name of the file to be written.
     * @param encoding encoding
     * @throws IOException if an error occurs while accessing fileName
     */
    public SimpleTextWriter(String fileName, String encoding) throws IOException {
        checkNotNull(fileName, "File name cannot be null..");
        this.os = new FileOutputStream(fileName);
        this.writer = IOs.getBufferedWriter(os, encoding);
        this.encoding = encoding;
        keepOpen = false;
    }

    /**
     * Creates a SimpleFileWriter using given encoding. it does not append to the File by default
     * and it closes the underlying output stream once any of the method is called by default. If a different
     * behavior is required, SimpleFileWriter.Builder class needs to be used.
     * Please note that this constructor throws a runtime exception if file is not found instead of a FileNotFoundException
     *
     * @param file     : file to be written.
     * @param encoding encoding.
     * @throws IOException if an error occurs while accessing fileName
     */
    public SimpleTextWriter(File file, String encoding) throws IOException {
        checkNotNull(file, "File cannot be null..");
        this.os = new FileOutputStream(file);
        this.writer = IOs.getBufferedWriter(os, encoding);
        this.encoding = encoding;
        keepOpen = false;
    }

    /**
     * Creates a SimpleFileWriter using default encoding. it does not append to the File by default
     * and it closes the underlying output stream once any of the method is called by default. If a different
     * behavior is required, SimpleFileWriter.Builder class needs to be used.
     * Please note that this constructor throws a runtime exception if file is not found instead of a FileNotFoundException
     *
     * @param file : file to be written.
     * @throws IOException if an error occurs while accessing fileName
     */
    public SimpleTextWriter(File file) throws IOException {
        this(file, Charset.defaultCharset().name());
    }

    /**
     * returns the current encoding.
     *
     * @return current encoding.
     */
    public String getEncoding() {
        return encoding;
    }

    public boolean isKeepOpen() {
        return keepOpen;
    }

    /**
     * Writes value of each String in a collection to
     *
     * @param lines : lines to write, null entries produce blank lines
     * @return returns the current instance. keep in mind that if instance is not constructed
     * with keepopen, chaining other write methods will throw an exception.
     * @throws IOException if an I/O error occurs
     */
    public SimpleTextWriter writeLines(Collection<String> lines) throws IOException {
        try {
            IOs.writeLines(lines, writer);
            return this;
        } finally {
            if (!keepOpen)
                close();
        }
    }

    /**
     * Writes the value of each item in a String Array with the writer.
     *
     * @param lines : lines to write, null entries produce blank lines
     * @return returns the current instance. keep in mind that if instance is not constructed
     * with keepopen, chaining other write methods will throw an exception.
     * @throws IOException if an I/O error occurs
     */
    public SimpleTextWriter writeLines(String... lines) throws IOException {
        return writeLines(Arrays.asList(lines));
    }

    /**
     * Writes the <code>toString()</code> value of each item in a collection
     *
     * @param objects : lines to write, null entries produce blank lines
     * @return returns the current instance. keep in mind that if instance is not constructed
     * with keepopen, chaining other write methods will throw an exception.
     * @throws IOException if an I/O error occurs
     */
    public SimpleTextWriter writeToStringLines(Collection<?> objects) throws IOException {
        try {
            IOs.writeToStringLines(objects, writer);
            return this;
        } finally {
            if (!keepOpen)
                close();
        }
    }

    /**
     * Writes a String to the file.
     *
     * @param s : string to write.
     * @return returns the current instance. keep in mind that if instance is not constructed
     * with keepOpen(), chaining other write methods will throw an exception.
     * @throws IOException if an I/O error occurs
     */
    public SimpleTextWriter write(String s) throws IOException {
        try {
            if (s == null || s.length() == 0)
                return this;
            writer.write(s);
            return this;
        } finally {
            if (!keepOpen)
                close();
        }
    }

    /**
     * Writes a String to the file after appending a line separator to it.
     *
     * @param s : string to write.
     * @return returns the current instance. keep in mind that if instance is not constructed
     * with keepOpen(), chaining other write methods will throw an exception.
     * @throws IOException if an I/O error occurs
     */
    public SimpleTextWriter writeLine(String s) throws IOException {
        return write(s + IOs.LINE_SEPARATOR);
    }

    /**
     * Writes a LINE_SEPERATOR.
     *
     * @return returns the current instance.
     * @throws IOException if an I/O error occurs
     */
    public SimpleTextWriter writeLine() throws IOException {
        return write("" + IOs.LINE_SEPARATOR);
    }

    /**
     * Writes toString() of an object the file after appending a line separator to it.
     *
     * @param obj : object to write.
     * @return returns the current instance. keep in mind that if instance is not constructed
     * with keepOpen(), chaining other write methods will throw an exception.
     * @throws IOException if an I/O error occurs
     */
    public SimpleTextWriter writeLine(Object obj) throws IOException {
        return write(obj.toString() + IOs.LINE_SEPARATOR);
    }

    /**
     * copies an input stream contents to the writer target.
     *
     * @param is input stream
     * @return the text writer.
     * @throws IOException if an I/O error occurs
     */
    public SimpleTextWriter copyFromStream(InputStream is) throws IOException {
        IOs.copy(is, os, keepOpen);
        return this;
    }

    /**
     * copies an input stream contents to the writer target.
     *
     * @param urlStr URL string.
     * @return this
     * @throws IOException if an I/O errro occurs.
     */
    public SimpleTextWriter copyFromURL(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        IOs.copy(url.openStream(), os, keepOpen);
        return this;
    }

    /**
     * closes the output stream opened for this writer. if the stream is already closed, it returns silently.
     *
     * @throws IOException
     */
    public void close() throws IOException {
        writer.flush();
        IOs.closeSilently(writer);
    }

}
