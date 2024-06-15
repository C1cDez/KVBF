package com.cicdez.kvbf;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class BooleanKVBF extends AbstractObjectKVBF<Boolean> {
    public BooleanKVBF(Boolean object) {
        super(object);
    }
    public BooleanKVBF() {
        super();
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.BOOLEAN_TYPE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeBoolean(get());
    }

    @Override
    public void read(DataInput input) throws IOException {
        set(input.readBoolean());
    }
}
