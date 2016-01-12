package com.hwangjr.jhelper;

import java.util.regex.Pattern;

import static com.hwangjr.jhelper.Preconditions.checkArgument;
import static com.hwangjr.jhelper.Preconditions.checkNotNull;

class StringFilters {

    public static final Filter PASS_ALL = new AllPassFilter();
    public static final Filter PASS_NON_NULL_OR_EMPTY = new NullOrEmptyFilter();
    public static final Filter PASS_ONLY_TEXT = new HasNoTextFilter();

    public static Filter newRegexpFilter(String regexp) {
        return new RegexpFilter(regexp, false);
    }

    public static Filter newRegexpFilterIgnoreCase(String regexp) {
        return new RegexpFilter(regexp, true);
    }

    public static Filter newRegexpFilter(Pattern pattern) {
        return new RegexpFilter(pattern);
    }

    public static Filter newPrefixFilter(String prefix) {
        return new PrefixFilter(prefix);
    }

    private static class AllPassFilter implements Filter<String> {
        public boolean canPass(String str) {
            return true;
        }
    }

    private static class NullOrEmptyFilter implements Filter<String> {
        public boolean canPass(String str) {
            return !Strings.isNullOrEmpty(str);
        }
    }

    private static class HasNoTextFilter implements Filter<String> {
        public boolean canPass(String str) {
            return Strings.hasText(str);
        }
    }

    private static class PrefixFilter implements Filter<String> {
        String token;

        private PrefixFilter(String token) {
            checkNotNull(token, "Cannot initialize Filter with null string.");
            this.token = token;
        }

        public boolean canPass(String s) {
            return s != null && s.startsWith(token);
        }
    }

    private static class RegexpFilter implements Filter<String> {
        final Pattern pattern;

        public RegexpFilter(String regExp, boolean ignoreCase) {
            checkNotNull(regExp, "regexp String cannot be null.");
            checkArgument(!Strings.isNullOrEmpty(regExp), "regexp String cannot be empty");
            if (ignoreCase)
                this.pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
            else
                this.pattern = Pattern.compile(regExp);
        }

        public RegexpFilter(Pattern pattern) {
            this.pattern = pattern;
        }

        public boolean canPass(String s) {
            return s != null && pattern.matcher(s).find();
        }
    }

    public static boolean canPassAll(String s, Filter<String>... filters) {

        for (Filter filter : filters) {
            if (!filter.canPass(s))
                return false;
        }
        return true;
    }

    public static boolean canPassAny(String s, Filter<String>... filters) {
        for (Filter filter : filters) {
            if (filter.canPass(s))
                return true;
        }
        return false;
    }


}
