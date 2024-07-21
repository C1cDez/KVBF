package com.cicdez.kvbf.primitives;

import com.cicdez.kvbf.extra.AbstractObjectKVBF;
import com.cicdez.kvbf.extra.KVBFTypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongKVBF extends AbstractObjectKVBF<Long> {
    public LongKVBF(Long object) {
        super(object);
    }
    public LongKVBF() {
        super();
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.LONG_TYPE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeLong(get());
    }

    @Override
    public void read(DataInput input) throws IOException {
        set(input.readLong());
    }
}
