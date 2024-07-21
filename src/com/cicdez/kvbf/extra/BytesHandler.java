package com.cicdez.kvbf.extra;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class BytesHandler {
    public static void writeLength(int len, DataOutput output) throws IOException {
        if (len < 0) throw new KVBFException("Length < 0");

        if (len <= 0xFF) output.write(new byte[] {1, (byte) (len & 0xFF)});
        else if (len <= 0xFFFF) output.write(new byte[] {2, (byte) ((len >> 8) & 0xFF), (byte) (len & 0xFF)});
        else if (len <= 0xFFFFFF) output.write(new byte[] {3, (byte) ((len >> 16) & 0xFF), (byte) ((len >> 8) & 0xFF), (byte) (len & 0xFF)});
        else output.write(new byte[] {4, (byte) ((len >> 24) & 0xFF), (byte) ((len >> 16) & 0xFF), (byte) ((len >> 8) & 0xFF), (byte) (len & 0xFF)});
    }
    public static int readLength(DataInput input) throws IOException {
        int size = input.readByte();

        if (size == 1) return input.readUnsignedByte();
        else if (size == 2) return (input.readUnsignedByte() << 8) + input.readUnsignedByte();
        else if (size == 3) return (input.readUnsignedByte() << 16) + (input.readUnsignedByte() << 8) + input.readUnsignedByte();
        else return (input.readUnsignedByte() << 24) + (input.readUnsignedByte() << 16) + (input.readUnsignedByte() << 8) + input.readUnsignedByte();
    }
}
