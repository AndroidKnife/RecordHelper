# Introduction #

Files class has static methods for File related operations.

# Details #

```
void deleteFiles(File... files)
```
deletes an arbitrary number of files. It only deletes files, not Directories.

```
void deleteFilesAndDirs(File... files)
```
Deletes all files and directories.

```
byte[] calculateMD5(File file)
```
Calculates MD5 value of a file.

```
void copy(File src, File dst)
```
Copies a file.

```
void copyDirectory(File srcDir, File dstDir)
```
copies content of a directory to another directory. if target directory does not exist, it creates target directory.

```
boolean contentEquals(File file1, File file2)
```
checks if two files content are exactly equal.

```
boolean containsUTF8Bom(File file)
```
checks if File contains UTF-8 BOM information.

```
void appendFiles(File target, File... filesToAppend)
```
appends several files to a file. if the target file does not exist, it creates it. if it exists, it appends the source files.

```
void hexDump(File f, long amount)
```
Usually used for debugging. it dumps a Files content as hex values to Console.

```
void hexDump(File f, File out, long amount)
```
same as above but values are saved to file this time.