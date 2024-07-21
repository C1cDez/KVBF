package com.cicdez.kvbf.primitives;

import com.cicdez.kvbf.extra.AbstractObjectKVBF;
import com.cicdez.kvbf.extra.KVBFTypes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringKVBF extends AbstractObjectKVBF<String> {
    public StringKVBF(String object) {
        super(object);
    }
    public StringKVBF() {
        super();
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.STRING_TYPE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeUTF(get());
    }

    @Override
    public void read(DataInput input) throws IOException {
        set(input.readUTF());
    }
}
