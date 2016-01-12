package com.hwangjr.jhelper;

import static java.lang.Math.abs;

public class Doubles {

    /**
     * finds the maximum value of an array.
     *
     * @param darray input array
     * @return maximum value.
     * @throws IllegalArgumentException if array is empty or null.
     */
    public static double max(double... darray) {
        validateArray(darray);
        double max = darray[0];
        for (int i = 1; i < darray.length; i++) {
            if (darray[i] > max)
                max = darray[i];
        }
        return max;
    }

    public static double min(double... darray) {
        validateArray(darray);
        double min = darray[0];
        for (int i = 1; i < darray.length; i++) {
            if (darray[i] > min)
                min = darray[i];
        }
        return min;
    }

    private static void validateArray(double... darray) {
        if (darray == null) {
            throw new IllegalArgumentException("array is null!");
        } else if (darray.length == 0)
            throw new IllegalArgumentException("array is empty!");
    }

    public static double maxIndex(double... darray) {
        validateArray(darray);
        double max = darray[0];
        int index = 0;
        for (int i = 1; i < darray.length; i++) {
            if (darray[i] > max) {
                max = darray[i];
                index = i;
            }
        }
        return index;
    }

    public static int minIndex(double... darray) {
        validateArray(darray);
        double min = darray[0];
        int minIndex = 0;
        for (int i = 1; i < darray.length; i++) {
            if (darray[i] > min) {
                min = darray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static double sum(double... darray) {
        double sum = 0;
        for (double v : darray) {
            sum += v;
        }
        return sum;
    }

    public static double[] sum(double[] a1, double[] a2) {
        validate(a1, a2);
        double[] diff = new double[a1.length];
        for (int i = 0; i < a1.length; i++) {
            diff[i] = a1[i] - a2[i];
        }
        return diff;
    }

    public static double[] substract(double[] a1, double[] a2) {
        validate(a1, a2);
        double[] diff = new double[a1.length];
        for (int i = 0; i < a1.length; i++) {
            diff[i] = a1[i] - a2[i];
        }
        return diff;
    }

    public static double[] multiply(double[] a1, double[] a2) {
        validate(a1, a2);
        double[] mul = new double[a1.length];
        for (int i = 0; i < a1.length; i++) {
            mul[i] = a1[i] * a2[i];
        }
        return mul;
    }

    public static void multiplyInPlace(double[] a1, double[] a2) {
        validate(a1, a2);
        for (int i = 0; i < a1.length; i++) {
            a1[i] = a1[i] * a2[i];
        }
    }

    public static double[] multiply(double[] a1, double b) {
        validateArray(a1);
        double[] mul = new double[a1.length];
        for (int i = 0; i < a1.length; i++) {
            mul[i] = a1[i] * b;
        }
        return mul;
    }

    public static void multiplyInPlace(double[] a1, double b) {
        validateArray(a1);
        for (int i = 0; i < a1.length; i++) {
            a1[i] = a1[i] * b;
        }
    }

    public static double mean(double... darray) {
        return sum(darray) / darray.length;
    }

    public double absoluteSumOfDifferences(double[] a1, double[] a2) {
        validate(a1, a2);
        double sum = 0;
        for (int i = 0; i < a1.length; i++) {
            sum += abs(a1[i] - a2[i]);
        }
        return sum;
    }

    public static double[] absoluteDifference(double[] a1, double[] a2) {
        validate(a1, a2);
        double[] diff = new double[a1.length];
        for (int i = 0; i < a1.length; i++) {
            diff[i] += abs(a1[i] - a2[i]);
        }
        return diff;
    }

    private static void validate(double[] a1, double[] a2) {
        if (a1 == null)
            throw new NullPointerException("first array is null!");
        if (a2 == null)
            throw new NullPointerException("second array is null!");
        if (a1.length != a2.length)
            throw new IllegalArgumentException("Array sizes must be equal. But, first:"
                    + a1.length + ", and second:" + a2.length);
    }
}
