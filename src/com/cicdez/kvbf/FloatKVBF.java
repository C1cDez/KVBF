package com.cicdez.kvbf;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FloatKVBF extends AbstractObjectKVBF<Float> {
    public FloatKVBF(Float object) {
        super(object);
    }
    public FloatKVBF() {
        super();
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.FLOAT_TYPE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeFloat(get());
    }

    @Override
    public void read(DataInput input) throws IOException {
        set(input.readFloat());
    }
}
