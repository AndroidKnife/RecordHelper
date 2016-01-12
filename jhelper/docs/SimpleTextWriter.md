# Introduction #

SimpleTextWriter class contains several methods to make file writing operations easy.
File writer class by default closes the file after each operation and it does not append by default. This behavior can be changed using the more advanced Builder class.

# Details #

**initializaiton:**
We can create a new SimpleTextWriter using either simple constructors, or the Builder. There are limited constructors.
```
new SimpleTextWriter("foo.txt");
```

Also, there are static factory methods such as:

```
public static SimpleTextWriter oneShotUTF8Writer(File file)
public static SimpleTextWriter keepOpenUTF8Writer(File file)
public static SimpleTextWriter oneShotWriter(OutputStream os, String encoding)
public static SimpleTextWriter keepOpenWriter(OutputStream os, String encoding)
```

Also, a Builder mechanism is provided but because input parameters are limited, users will probably not need it.

```
new SimpleTextWriter
  .Builder("foo.txt")
  .encoding("utf-8")
  .keepOpen() 
  .build();
```
will generate a SimpleTextWriter which will append to the foo.txt, and keep the writer open once an operation is finished.

**Write a String to a file:**
Writes a single String to a file.
```
new SimpleTextWriter("foo.txt").writeString("Hello");
```

Note that if you create the Writer using keepOpen, you need to close the writer. such as:

```
SimpleTextWriter smw = SimpleTextWriter.keepOpenUTF8Writer(new File("foo.txt"));
try{
  smw.writeString("Hello");
  smw.writeString("World");
} finally {
  IOs.closeSilently(smw);
}
```

**Write a String Collection:**
Writes a collection of Strings. appending a line seperator to each line.
```
new SimpleTextWriter("foo.txt").writeLines(Arrays.asList("Hello","World"));
```

**Get Writer:**
Returns a new BufferedWriter for the file.
```
BufferedWriter r = new SimpleFileWriter("foo.txt").getWriter();
```

**Copy from an InputStream:**
```
new SimpleTextWriter("foo.txt")
  .copyFromStream(new FileInputStream("bar.txt")));
```

**Copy from a URL:**
```
new SimpleTextWriter("foo.txt")
  .copyFromURL("http://www.google.com"));
```