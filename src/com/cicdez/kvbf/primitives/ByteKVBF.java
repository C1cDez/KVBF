package com.cicdez.kvbf.primitives;

import com.cicdez.kvbf.extra.AbstractObjectKVBF;
import com.cicdez.kvbf.extra.KVBFTypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteKVBF extends AbstractObjectKVBF<Byte> {
    public ByteKVBF(Byte object) {
        super(object);
    }
    public ByteKVBF() {
        super();
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.BYTE_TYPE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeByte(get());
    }

    @Override
    public void read(DataInput input) throws IOException {
        set(input.readByte());
    }
}
