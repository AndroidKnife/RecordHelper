# Introduction #

SimpleTextReader class contains several methods to make text based file operations easy.


# Details #

**initializaiton:**
We create a new SimpleTextReader using file name or File object and an optional character encoding string.
```
new SimpleTextReader ("foo.txt");
new SimpleTextReader ("foo.txt", "utf-8");
new SimpleTextReader (new File(Systems.getUserHome()+"/foo.txt"))
```

There are static factory methods also provided. All those methods return Reader or Iterators that trims the lines and ignores the empty or white space lines.
```
static SimpleTextReader trimmingUTF8Reader(File file) 
static LineIterator trimmingUTF8LineIterator(File file)
static IterableLineReader trimmingUTF8IterableLineReader(File file)
static SimpleTextReader trimmingReader(InputStream is, String encoding)
static LineIterator trimmingLineIterator(InputStream is, String encoding)
static IterableLineReader trimmingIterableLineReader(InputStream is, String encoding)
```

Also, a Builder mechanism can be used for initiazlizing advanced SimpleFileReaders. Such as:

```
SimpleTextReader 
  .Builder("foo.text")
  .encoding("utf-8")
  .trim()
  .ignoreWhiteSpaceLines()
  .allowMatchingRegexp("INFO|WARNING")
  .build();
```
will generate a SimpleTextReader. But this time it will trim asString() result of lines read using multiline read methods. Also, only the lines with actual text will be processed. empty or whitespace lines will be ignored. And only the lines matching INFO or WARNING strings will be processed.

**Read a file as String:**
This loads a file's content into a single string.
```
String content = new SimpleTextReader("foo.txt").asString();
```

**Read as a String list:**
Reads the lines as a string list.
```
List<String> list = new SimpleTextReader("foo.txt").asStringList();
```

**Read as byte array:**
Reads the content as a byte array
```
byte[] bytes = new SimpleTextReader("foo.txt").asByteArray();
```

**Get a Reader:**
Returns a new BufferedReader for the file.
```
Reader r = new SimpleTextReader("foo.txt").getReader();
```

**Get a LineIterator:**
This method retruns a LineIterator.
```
LineIterator li = new SimpleTextReader("foo.txt").getLineIterator();
while(li.hasNext())
  out.println(li.next().toUpperCase());
li.close();
```
It is suggested to do the iteration in a try-finally block and close the LineITerator silently in the finally block. An example of suggested usage is shown below:
```
LineIterator li = new SimpleTextReader("foo.txt").getLineIterator();
try {
   while(li.hasNext())
      out.println(li.next().toUpperCase());
} finally {
   IOs.closeSilently(li);
}
```
This way input stream used in the LineITerator will be properly closed if the iteration is not done completely or interrupted.

**Get an iterable reader:**
It returns a IterableLineReader backed by a LineIterator. It is suitable for using in enhanced for loops. If iteration is completed, the underlying file rsources will automatically be closed.
```
for(String s: new SimpleTextReader("foo.txt").getIterableReader())
  out.println(s); 
```
However, for safety, it is suggested to close the IterableLineReader in a finally block.
```
IterableLineReader ilr = new SimpleTextReader("foo.txt").getIterableReader();
try {
  for(String s: ilr)
    out.println(s); 
} finally {
   IOs.closeSilently(ilr);
}
```

**Line Count:**
Counts the lines in a file. Note that it will not count lines which are ignored if a constraint is applied during SimpleTextReader creation.
```
int lineCount = new SimpleTextReader("foo.txt").countLines();
```

**Clone**
It is possible to clone a SimpleTextReader for another input stream or file.
```
public SimpleTextReader cloneForStream(InputStream is)
public SimpleTextReader cloneForFile(File file)
```
those methods will use the attributes of a SimpleTextReader and clone it for a provided input stream or file.