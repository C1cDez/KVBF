package com.cicdez.kvbf;

import java.io.*;

public class ByteArrayDataInputStream implements DataInput {
    private final byte[] bytes;
    private int pos;
    private final int count;

    public ByteArrayDataInputStream(byte[] bytes) {
        this(bytes, 0, bytes.length);
    }
    public ByteArrayDataInputStream(byte[] bytes, int off, int len) {
        this.bytes = bytes;
        this.pos = off;
        this.count = len;
    }

    public int read() {
        return (pos < count) ? (bytes[pos++] & 0xFF) : -1;
    }

    @Override
    public void readFully(byte[] b) throws IOException {
        readFully(b, pos, b.length);
        pos += b.length;
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
        if (off + len > count) throw new EOFException();
        if (len >= 0) System.arraycopy(bytes, off, b, 0, len);
    }

    @Override
    public int skipBytes(int n) throws IOException {
        pos += n;
        return n;
    }

    @Override
    public boolean readBoolean() throws IOException {
        int v = read();
        if (v < 0) throw new EOFException();
        return v != 0;
    }

    @Override
    public byte readByte() throws IOException {
        int v = read();
        if (v < 0) throw new EOFException();
        return (byte) v;
    }

    @Override
    public int readUnsignedByte() throws IOException {
        int v = read();
        if (v < 0) throw new EOFException();
        return v;
    }

    @Override
    public short readShort() throws IOException {
        int v1 = read();
        int v2 = read();
        return (short)((v1 << 8) + (v2));
    }

    @Override
    public int readUnsignedShort() throws IOException {
        int v1 = read();
        int v2 = read();
        return ((v1 << 8) + (v2));
    }

    @Override
    public char readChar() throws IOException {
        int v1 = read();
        int v2 = read();
        return (char) ((v1 << 8) + (v2));
    }

    @Override
    public int readInt() throws IOException {
        int v1 = read();
        int v2 = read();
        int v3 = read();
        int v4 = read();
        return ((v1 << 24) + (v2 << 16) + (v3 << 8) + (v4 << 0));
    }

    @Override
    public long readLong() throws IOException {
        int v1 = read();
        int v2 = read();
        int v3 = read();
        int v4 = read();
        int v5 = read();
        int v6 = read();
        int v7 = read();
        int v8 = read();
        return (((long) v1 << 56) +
                ((long) v2 << 48) +
                ((long) v3 << 40) +
                ((long) v4 << 32) +
                ((long) v5 << 24) +
                ((long) v6 << 16) +
                ((long) v7 << 8) +
                (v8)
        );
    }

    @Override
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override
    public String readLine() throws IOException {
        throw new EOFException();
    }

    @Override
    public String readUTF() throws IOException {
        int len = readUnsignedShort();
        byte[] b = new byte[len];
        readFully(b);
        return new String(b);
    }
}
