package com.cicdez.kvbf;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface IKVBF<T> {
    T get();
    void set(T object);

    int getTypeId();

    void write(DataOutput output) throws IOException;
    void read(DataInput input) throws IOException;
}
