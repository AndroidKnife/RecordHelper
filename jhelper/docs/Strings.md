# Introduction #

Strings class has static helper methods for easing several String operations. some methods in this class are copied and slightly modified from Apache commons-lang library.

# Details #

## Verification ##
**isNullOrEmpty** checks if a string is null or emprty (zero length String).
```
Strings.isNullOrEmpty("");    // true
Strings.isNullOrEmpty(null);  // true
```

**allNullOrEmpty** checks if all the strings sent is null or empty
```
Strings.allNullOrEmpty("", null, null); //true
Strings.allNullOrEmpty(null, "aaa");    //false
```

**hasText** returns true if there is any character in the string other than white space. if String is null, empty or consist of only white space characters, returns false
```
Strings.hasText(null);   // false
Strings.hasText("");     // false
Strings.hasText(" \t");  // false
Strings.hasText("a ");   // true
```

**allHasText** checks if all the Strings send to the method has text in it. it fails if any is empty, null, or contain only white space.
```
Strings.allHasText(" ", "hello", "world"); // false
Strings.hasText("a","b");                  // true
```

## Manipulation ##
**leftTrim** and **rightTrim** are self explanatory. Trims the white space from the beginning or the end of the String.

**containsNone** checks if string has any of the given invalid characters. if string is null, returns true.

```
Strings.containsNone(null, *)       // true
Strings.containsNone(*, null)       // true
Strings.containsNone("", *)         // true
Strings.containsNone("ab", "")      // true
Strings.containsNone("ab1", "xyz")  // true
Strings.containsNone("abz", "xyz")  // false
```

**reverse** reverses the string. if null, returns null.
```
Strings.reverse(null)       // null
Strings.reverse("hello")    // "olleh"
```

**insertFromLeft** inserts a string with given intervals to the string. Negative interval will result an IllegalArgumentException.
```
insertFromLeft("0123456789",0,"-")  // "0123456789"
insertFromLeft("0123456789",1,"-")  // "0-1-2-3-4-5-6-7-8-9"
insertFromLeft("0123456789",2,"-")  // "01-23-45-67-89"
insertFromLeft("0123456789",3,"-")  // "012-345-678-9"
insertFromLeft("0123456789",2,"--") // "01--23--45--67--89"
```

**insertFromRight** inserts a string with given interval.
```
insertFromRight("0123456789",3,"-")  // "0-123-456-789"
insertFromRight("0123456789",3,"--") // "0--123--456--789"
```

**eliminateWhiteSpaces** eliminates ALL white spaces in a string.
```
eliminateWhiteSpaces("as d ")           // "asd"
eliminateWhiteSpaces("as \t \r  \f d ") // "asd"
```

**whiteSpacesToSingleSpace** reduces white space character(s) to a single "space" character.

```
whiteSpacesToSingleSpace("as    d   ") // "as d "
whiteSpacesToSingleSpace("as \t d")    // "as d"
```

**repeat** creates a String by repeating desired String or character.

```
repeat('c', -1)  // ""
repeat('c', 3)   // "ccc"
repeat('c', 0)   // ""

repeat("ab", -1) // ""
repeat("ab", 3)  // "ababab"
repeat("ab", 0)  // ""
```

**subStringUntilFirst** gets the sub string of a string until the first ocurrance of a given string.

```
subStringUntilFirst("hello", "el")      // "h"
subStringUntilFirst("hellohello", "el") // "h"
subStringUntilFirst("hello", "")        // "hello"
subStringUntilFirst("hello", "el")      // ""
subStringUntilFirst("", "el")           // ""
subStringUntilFirst(null, "el")         // null
```

**subStringUntilLast** gets the sub string of a string after the first ocurrance of a given string.

```
subStringUntilLast("hello", "el")      // "h"
subStringUntilLast("hellohello", "el") // "helloh"
```

**subStringAfterFirst** gets the sub string of a string until the last ocurrance of a given string.

```
subStringAfterFirst("hello", "el")      // "lo"
subStringAfterFirst("hellohello", "el") // "lohello"
```

**subStringAfterLast** gets the sub string of a string after the last ocurrance of a given string.

```
subStringAfterLast("hello", "el")      // "lo"
subStringAfterLast("hellohello", "el") // "lo"
```

**separateGrams**  this is a special method. it separates a String into neighbouring characters sequences.

```
separateGrams("hello", 2)      // {"he", "el", "ll", "lo"}
separateGrams("hello", 3)      // {"hel", "ell", "llo"}
```