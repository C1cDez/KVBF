package com.cicdez.kvbf;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public abstract class AbstractAccountedKVBF<T> extends AbstractObjectKVBF<T> {
    @Override
    public void write(DataOutput output) throws IOException {
        ByteArrayDataOutputStream stream = new ByteArrayDataOutputStream();
        writeContent(stream);
        byte[] buffer = stream.toByteArray();
        BytesHandler.writeLength(buffer.length, output);
        output.write(buffer);
    }

    public abstract void writeContent(ByteArrayDataOutputStream output) throws IOException;

    @Override
    public void read(DataInput input) throws IOException {
        int len = BytesHandler.readLength(input);
        byte[] bytes = new byte[len];
        input.readFully(bytes);
        readContent(new ByteArrayDataInputStream(bytes));
    }

    public abstract void readContent(ByteArrayDataInputStream input) throws IOException;
}
