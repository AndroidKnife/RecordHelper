package com.hwangjr.soundhelper.dsp;

public interface DoubleVectorProcessor {

    DoubleVector process(DoubleVector input);

    void processInPlace(DoubleVector input);
}
