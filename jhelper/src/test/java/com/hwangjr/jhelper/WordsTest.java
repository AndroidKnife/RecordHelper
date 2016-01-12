package com.hwangjr.jhelper;

import org.junit.Test;

import static com.hwangjr.jhelper.Words.capitalize;
import static com.hwangjr.jhelper.Words.capitalizeFully;
import static com.hwangjr.jhelper.Words.initials;
import static com.hwangjr.jhelper.Words.uncapitalize;
import static com.hwangjr.jhelper.Words.wrap;
import static org.junit.Assert.assertEquals;

/**
 * these tests are copied from commons-lang 2.3
 */
public class WordsTest {


    //-----------------------------------------------------------------------
    @Test
    public void testWrap_StringInt() {
        assertEquals(null, wrap(null, 20));
        assertEquals(null, wrap(null, -1));

        assertEquals("", wrap("", 20));
        assertEquals("", wrap("", -1));

        // normal
        String systemNewLine = System.getProperty("line.separator");
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of" + systemNewLine + "text that is going"
                + systemNewLine + "to be wrapped after" + systemNewLine + "20 columns.";
        assertEquals(expected, wrap(input, 20));

        // long word at end
        input = "Click here to jump to the jakarta website - http://jakarta.apache.org";
        expected = "Click here to jump" + systemNewLine + "to the jakarta" + systemNewLine
                + "website -" + systemNewLine + "http://jakarta.apache.org";
        assertEquals(expected, wrap(input, 20));

        // long word in middle
        input = "Click here, http://jakarta.apache.org, to jump to the jakarta website";
        expected = "Click here," + systemNewLine + "http://jakarta.apache.org," + systemNewLine
                + "to jump to the" + systemNewLine + "jakarta website";
        assertEquals(expected, wrap(input, 20));
    }

    @Test
    public void testWrap_StringIntStringBoolean() {
        assertEquals(null, wrap(null, 20, "\n", false));
        assertEquals(null, wrap(null, 20, "\n", true));
        assertEquals(null, wrap(null, 20, null, true));
        assertEquals(null, wrap(null, 20, null, false));
        assertEquals(null, wrap(null, -1, null, true));
        assertEquals(null, wrap(null, -1, null, false));

        assertEquals("", wrap("", 20, "\n", false));
        assertEquals("", wrap("", 20, "\n", true));
        assertEquals("", wrap("", 20, null, false));
        assertEquals("", wrap("", 20, null, true));
        assertEquals("", wrap("", -1, null, false));
        assertEquals("", wrap("", -1, null, true));

        // normal
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertEquals(expected, wrap(input, 20, "\n", false));
        assertEquals(expected, wrap(input, 20, "\n", true));

        // unusual newline char
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";
        assertEquals(expected, wrap(input, 20, "<br />", false));
        assertEquals(expected, wrap(input, 20, "<br />", true));

        // short line length
        input = "Here is one line";
        expected = "Here\nis one\nline";
        assertEquals(expected, wrap(input, 6, "\n", false));
        expected = "Here\nis\none\nline";
        assertEquals(expected, wrap(input, 2, "\n", false));
        assertEquals(expected, wrap(input, -1, "\n", false));

        // system newline char
        String systemNewLine = System.getProperty("line.separator");
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
                + "to be wrapped after" + systemNewLine + "20 columns.";
        assertEquals(expected, wrap(input, 20, null, false));
        assertEquals(expected, wrap(input, 20, null, true));

        // with extra spaces
        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";
        assertEquals(expected, wrap(input, 20, "\n", false));
        assertEquals(expected, wrap(input, 20, "\n", true));

        // with tab
        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertEquals(expected, wrap(input, 20, "\n", false));
        assertEquals(expected, wrap(input, 20, "\n", true));

        // with tab at wrapColumn
        input = "Here is one line of\ttext that is going to be wrapped after 20 columns.";
        expected = "Here is one line\nof\ttext that is\ngoing to be wrapped\nafter 20 columns.";
        assertEquals(expected, wrap(input, 20, "\n", false));
        assertEquals(expected, wrap(input, 20, "\n", true));

        // difference because of long word
        input = "Click here to jump to the jakarta website - http://jakarta.apache.org";
        expected = "Click here to jump\nto the jakarta\nwebsite -\nhttp://jakarta.apache.org";
        assertEquals(expected, wrap(input, 20, "\n", false));
        expected = "Click here to jump\nto the jakarta\nwebsite -\nhttp://jakarta.apach\ne.org";
        assertEquals(expected, wrap(input, 20, "\n", true));

        // difference because of long word in middle
        input = "Click here, http://jakarta.apache.org, to jump to the jakarta website";
        expected = "Click here,\nhttp://jakarta.apache.org,\nto jump to the\njakarta website";
        assertEquals(expected, wrap(input, 20, "\n", false));
        expected = "Click here,\nhttp://jakarta.apach\ne.org, to jump to\nthe jakarta website";
        assertEquals(expected, wrap(input, 20, "\n", true));
//        System.err.println(expected);
//        System.err.println(Words.wrap(input, 20, "\n", false));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCapitalize_String() {
        assertEquals(null, capitalize(null));
        assertEquals("", capitalize(""));
        assertEquals("  ", capitalize("  "));

        assertEquals("I", capitalize("I"));
        assertEquals("I", capitalize("i"));
        assertEquals("I Am Here 123", capitalize("i am here 123"));
        assertEquals("I Am Here 123", capitalize("I Am Here 123"));
        assertEquals("I Am HERE 123", capitalize("i am HERE 123"));
        assertEquals("I AM HERE 123", capitalize("I AM HERE 123"));
    }

    @Test
    public void testCapitalizeWithDelimiters_String() {
        assertEquals(null, capitalize(null, null));
        assertEquals("", capitalize("", new char[0]));
        assertEquals("  ", capitalize("  ", new char[0]));

        char[] chars = new char[]{'-', '+', ' ', '@'};
        assertEquals("I", capitalize("I", chars));
        assertEquals("I", capitalize("i", chars));
        assertEquals("I-Am Here+123", capitalize("i-am here+123", chars));
        assertEquals("I Am+Here-123", capitalize("I Am+Here-123", chars));
        assertEquals("I+Am-HERE 123", capitalize("i+am-HERE 123", chars));
        assertEquals("I-AM HERE+123", capitalize("I-AM HERE+123", chars));
        chars = new char[]{'.'};
        assertEquals("I aM.Fine", capitalize("i aM.fine", chars));
        assertEquals("I Am.fine", capitalize("i am.fine", null));
    }

    @Test
    public void testCapitalizeFully_String() {
        assertEquals(null, capitalizeFully(null));
        assertEquals("", capitalizeFully(""));
        assertEquals("  ", capitalizeFully("  "));

        assertEquals("I", capitalizeFully("I"));
        assertEquals("I", capitalizeFully("i"));
        assertEquals("I Am Here 123", capitalizeFully("i am here 123"));
        assertEquals("I Am Here 123", capitalizeFully("I Am Here 123"));
        assertEquals("I Am Here 123", capitalizeFully("i am HERE 123"));
        assertEquals("I Am Here 123", capitalizeFully("I AM HERE 123"));
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String() {
        assertEquals(null, capitalizeFully(null, null));
        assertEquals("", capitalizeFully("", new char[0]));
        assertEquals("  ", capitalizeFully("  ", new char[0]));

        char[] chars = new char[]{'-', '+', ' ', '@'};
        assertEquals("I", capitalizeFully("I", chars));
        assertEquals("I", capitalizeFully("i", chars));
        assertEquals("I-Am Here+123", capitalizeFully("i-am here+123", chars));
        assertEquals("I Am+Here-123", capitalizeFully("I Am+Here-123", chars));
        assertEquals("I+Am-Here 123", capitalizeFully("i+am-HERE 123", chars));
        assertEquals("I-Am Here+123", capitalizeFully("I-AM HERE+123", chars));
        chars = new char[]{'.'};
        assertEquals("I am.Fine", capitalizeFully("i aM.fine", chars));
        assertEquals("I Am.fine", capitalizeFully("i am.fine", null));
    }

    @Test
    public void testUncapitalize_String() {
        assertEquals(null, uncapitalize(null));
        assertEquals("", uncapitalize(""));
        assertEquals("  ", uncapitalize("  "));

        assertEquals("i", uncapitalize("I"));
        assertEquals("i", uncapitalize("i"));
        assertEquals("i am here 123", uncapitalize("i am here 123"));
        assertEquals("i am here 123", uncapitalize("I Am Here 123"));
        assertEquals("i am hERE 123", uncapitalize("i am HERE 123"));
        assertEquals("i aM hERE 123", uncapitalize("I AM HERE 123"));
    }

    @Test
    public void testUncapitalizeWithDelimiters_String() {
        assertEquals(null, uncapitalize(null, null));
        assertEquals("", uncapitalize("", new char[0]));
        assertEquals("  ", uncapitalize("  ", new char[0]));

        char[] chars = new char[]{'-', '+', ' ', '@'};
        assertEquals("i", uncapitalize("I", chars));
        assertEquals("i", uncapitalize("i", chars));
        assertEquals("i am-here+123", uncapitalize("i am-here+123", chars));
        assertEquals("i+am here-123", uncapitalize("I+Am Here-123", chars));
        assertEquals("i-am+hERE 123", uncapitalize("i-am+HERE 123", chars));
        assertEquals("i aM-hERE+123", uncapitalize("I AM-HERE+123", chars));
        chars = new char[]{'.'};
        assertEquals("i AM.fINE", uncapitalize("I AM.FINE", chars));
        assertEquals("i aM.FINE", uncapitalize("I AM.FINE", null));
    }

    @Test
    public void testInitials_String() {
        assertEquals(null, initials(null));
        assertEquals("", initials(""));
        assertEquals("", initials("  "));

        assertEquals("I", initials("I"));
        assertEquals("i", initials("i"));
        assertEquals("BJL", initials("Ben John Lee"));
        assertEquals("BJ", initials("Ben J.Lee"));
        assertEquals("BJ.L", initials(" Ben   John  . Lee"));
        assertEquals("iah1", initials("i am here 123"));
    }

    @Test
    public void testInitials_String_charArray() {
        char[] array = null;
        assertEquals(null, initials(null, array));
        assertEquals("", initials("", array));
        assertEquals("", initials("  ", array));
        assertEquals("I", initials("I", array));
        assertEquals("i", initials("i", array));
        assertEquals("S", initials("SJC", array));
        assertEquals("BJL", initials("Ben John Lee", array));
        assertEquals("BJ", initials("Ben J.Lee", array));
        assertEquals("BJ.L", initials(" Ben   John  . Lee", array));
        assertEquals("KO", initials("Kay O'Murphy", array));
        assertEquals("iah1", initials("i am here 123", array));

        array = new char[0];
        assertEquals(null, initials(null, array));
        assertEquals("", initials("", array));
        assertEquals("", initials("  ", array));
        assertEquals("", initials("I", array));
        assertEquals("", initials("i", array));
        assertEquals("", initials("SJC", array));
        assertEquals("", initials("Ben John Lee", array));
        assertEquals("", initials("Ben J.Lee", array));
        assertEquals("", initials(" Ben   John  . Lee", array));
        assertEquals("", initials("Kay O'Murphy", array));
        assertEquals("", initials("i am here 123", array));

        array = " ".toCharArray();
        assertEquals(null, initials(null, array));
        assertEquals("", initials("", array));
        assertEquals("", initials("  ", array));
        assertEquals("I", initials("I", array));
        assertEquals("i", initials("i", array));
        assertEquals("S", initials("SJC", array));
        assertEquals("BJL", initials("Ben John Lee", array));
        assertEquals("BJ", initials("Ben J.Lee", array));
        assertEquals("BJ.L", initials(" Ben   John  . Lee", array));
        assertEquals("KO", initials("Kay O'Murphy", array));
        assertEquals("iah1", initials("i am here 123", array));

        array = " .".toCharArray();
        assertEquals(null, initials(null, array));
        assertEquals("", initials("", array));
        assertEquals("", initials("  ", array));
        assertEquals("I", initials("I", array));
        assertEquals("i", initials("i", array));
        assertEquals("S", initials("SJC", array));
        assertEquals("BJL", initials("Ben John Lee", array));
        assertEquals("BJL", initials("Ben J.Lee", array));
        assertEquals("BJL", initials(" Ben   John  . Lee", array));
        assertEquals("KO", initials("Kay O'Murphy", array));
        assertEquals("iah1", initials("i am here 123", array));

        array = " .'".toCharArray();
        assertEquals(null, initials(null, array));
        assertEquals("", initials("", array));
        assertEquals("", initials("  ", array));
        assertEquals("I", initials("I", array));
        assertEquals("i", initials("i", array));
        assertEquals("S", initials("SJC", array));
        assertEquals("BJL", initials("Ben John Lee", array));
        assertEquals("BJL", initials("Ben J.Lee", array));
        assertEquals("BJL", initials(" Ben   John  . Lee", array));
        assertEquals("KOM", initials("Kay O'Murphy", array));
        assertEquals("iah1", initials("i am here 123", array));

        array = "SIJo1".toCharArray();
        assertEquals(null, initials(null, array));
        assertEquals("", initials("", array));
        assertEquals(" ", initials("  ", array));
        assertEquals("", initials("I", array));
        assertEquals("i", initials("i", array));
        assertEquals("C", initials("SJC", array));
        assertEquals("Bh", initials("Ben John Lee", array));
        assertEquals("B.", initials("Ben J.Lee", array));
        assertEquals(" h", initials(" Ben   John  . Lee", array));
        assertEquals("K", initials("Kay O'Murphy", array));
        assertEquals("i2", initials("i am here 123", array));
    }

}
