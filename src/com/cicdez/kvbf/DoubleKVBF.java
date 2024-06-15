package com.cicdez.kvbf;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleKVBF extends AbstractObjectKVBF<Double> {
    public DoubleKVBF(Double object) {
        super(object);
    }
    public DoubleKVBF() {
        super();
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.DOUBLE_TYPE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeDouble(get());
    }

    @Override
    public void read(DataInput input) throws IOException {
        set(input.readDouble());
    }
}
