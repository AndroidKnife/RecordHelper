package com.hwangjr.jhelper;

interface Filter<T> {
    boolean canPass(T t);
}
