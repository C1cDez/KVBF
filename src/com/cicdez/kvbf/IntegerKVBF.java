package com.cicdez.kvbf;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntegerKVBF extends AbstractObjectKVBF<Integer> {
    public IntegerKVBF(Integer object) {
        super(object);
    }
    public IntegerKVBF() {
        super();
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.INTEGER_TYPE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(get());
    }

    @Override
    public void read(DataInput input) throws IOException {
        set(input.readInt());
    }
}
