IoOperations are handled by IOs class

# Introduction #

IOs class provides many IO related operation with static methods. Methods in the IOs class are lower level than other high level helpers such as SimpleTextReader, SimpleTextWriter.

# Details #

**Silent close operations**
In Java, many times in finally blocks we will need to close resources. If those close operations are throwing checked exceptions, it makes the code look cluttered. For making the code cleaner, closeSilently(Closeable... closeables) method can be used. For exmaple:

```
InputStream is = ...
OutputStream os = ...
try{
  .... 
} finally {
  IOs.closeSilently(is, os);
}
```

this method will accept zero or more, null or non-null Closeable objects. if an exception occurs during close, it will only produce a System.err(..) output.

**Copy Streams** Can copy an input stream to a given output stream. It keeps the output stream open once the operation is finished. For this operation, SimpleFileWriter class may be preferred because it can close the streams by default.
```
  URL url= new URL("http://www.google.com");
  IOs.copy(url.openStream(), new FileOutputStream("google.txt"));
```

```
String readAsString(BufferedReader reader)
```
reads content from a BufferedReader to a single string.

```
List<String> readAsStringList(BufferedReader reader)
```
reads content from a BufferedReader to a String List.

```
List<String> readAsStringList(BufferedReader reader, boolean trim, Filter... filters)
```
reads content from BufferedReader to a string list. It applieas trimming or input Filters to the input.

```
BufferedReader getReader(InputStream is)
```
gets a BufferedReader from a stream.

```
BufferedReader getReader(InputStream is, String charset)
```
gets a BufferedReader from a stream using the charset. However, if charset is UTF-8 it checks explicitly if it contains  optional UTF-8 BOM data. if it exist, it will skip those bytes. Java libraries by default assumes UTF-8 BOM data does not exist.

```
BufferedWriter getWriter(OutputStream os)
```
gets a BufferedReader for an output stream.

```
BufferedWriter getWriter(OutputStream os, String charset)
```
returns a BufferedWriter for the output stream.

```
IterableLineReader getIterableReader(InputStream is)
```
gets an IterableReader from an input stream. IterableReaders are suitable for using in while loops. it uses default character encoding.

```
LineIterator getLineIterator(InputStream is)
```
gets a LineIterator from an input stream.  LineIterators are suitable using in enhanced For loops. it uses default character encoding.

```
IterableLineReader getIterableReader(InputStream is, String charset)
```
Same as above but for a given character encoding.

```
LineIterator getLineIterator(InputStream is, String charset)
```
same as above. but for a given character encoding.

```
void copy(InputStream is, OutputStream os)
```
copies one stream to another. it closes both stream after copy operation is finished.

```
boolean contentEquals(InputStream is1, InputStream is2)
```
checks two input streams content is exactly equal. it closes both streams after the check.

```
byte[] calculateMD5(InputStream is)
```
calculates MD5 hash value of an input stream.

```
boolean containsUTF8Bom(InputStream is)
```
checks if stream's beginning contains UTF-8 BOM data.

```
byte[] readAsByteArray(InputStream is)
```
reads a stream as byte array.

```
void writeLines(Collection<String> lines, OutputStream output)
```
writes a collection of a string to an output String. output stream will not be closed after the write operation.

```
void writeLines(Collection<String> lines, OutputStream output, String encoding)
```
same as above but for a given character encoding.

```
void writeString(String s, OutputStream output)
```
Writes a string to an output stream.

```
void writeString(String s, OutputStream output, String encoding)
```
Writes a string to an output stream with given encoding.

```
InputStream getClassPathResourceAsStream(String resource)
```
gets a classpath resource as an input stream.

```
void writeToStringLines( Collection<?> lines, OutputStream os, String encoding)
```
writes toString values for a Collections of items to an output stream using the given encoding.

```
hexDump(InputStream is, OutputStream os, int column, long amount)
```
creates hex values for the bytes of an input stream and writes them 'amount' of them together as a line to an output stream.