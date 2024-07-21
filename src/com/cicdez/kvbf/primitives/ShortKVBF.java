package com.cicdez.kvbf.primitives;

import com.cicdez.kvbf.extra.AbstractObjectKVBF;
import com.cicdez.kvbf.extra.KVBFTypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortKVBF extends AbstractObjectKVBF<Short> {
    public ShortKVBF(Short object) {
        super(object);
    }
    public ShortKVBF() {
        super();
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.SHORT_TYPE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeShort(get());
    }

    @Override
    public void read(DataInput input) throws IOException {
        set(input.readShort());
    }
}
