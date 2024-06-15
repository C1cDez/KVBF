package com.cicdez.kvbf;

import java.io.*;
import java.util.Arrays;

public class ByteArrayDataOutputStream implements DataOutput {
    private final byte[] bytes;
    private int count = 0;

    public ByteArrayDataOutputStream() {
        this(8192);
    }
    public ByteArrayDataOutputStream(int size) {
        if (size <= 0) throw new IllegalArgumentException("Buffer size <= 0");
        bytes = new byte[size];
    }

    @Override
    public void write(int b) throws IOException {
        bytes[count++] = (byte) b;
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if ((off | len | (b.length - (len + off)) | (off + len)) < 0) throw new IndexOutOfBoundsException();
        for (int i = 0 ; i < len ; i++) write(b[off + i]);
    }

    @Override
    public void writeBoolean(boolean v) throws IOException {
        write(v ? 1 : 0);
    }

    @Override
    public void writeByte(int v) throws IOException {
        write(v);
    }

    @Override
    public void writeShort(int v) throws IOException {
        write((v >> 8) & 0xFF);
        write((v >> 0) & 0xFF);
    }

    @Override
    public void writeChar(int v) throws IOException {
        write((v >> 8) & 0xFF);
        write((v >> 0) & 0xFF);
    }

    @Override
    public void writeInt(int v) throws IOException {
        write((v >> 24) & 0xFF);
        write((v >> 16) & 0xFF);
        write((v >>  8) & 0xFF);
        write((v >>  0) & 0xFF);
    }

    @Override
    public void writeLong(long v) throws IOException {
        write((byte) (v >> 56) & 0xFF);
        write((byte) (v >> 48) & 0xFF);
        write((byte) (v >> 40) & 0xFF);
        write((byte) (v >> 32) & 0xFF);
        write((byte) (v >> 24) & 0xFF);
        write((byte) (v >> 16) & 0xFF);
        write((byte) (v >>  8) & 0xFF);
        write((byte) (v >>  0) & 0xFF);
    }

    @Override
    public void writeFloat(float v) throws IOException {
        writeInt(Float.floatToIntBits(v));
    }

    @Override
    public void writeDouble(double v) throws IOException {
        writeLong(Double.doubleToLongBits(v));
    }

    @Override
    public void writeBytes(String s) throws IOException {
        int len = s.length();
        for (int i = 0 ; i < len ; i++) {
            write((byte) s.charAt(i));
        }
    }

    @Override
    public void writeChars(String s) throws IOException {
        int len = s.length();
        for (int i = 0 ; i < len ; i++) {
            int v = s.charAt(i);
            write((v >> 8) & 0xFF);
            write((v >> 0) & 0xFF);
        }
    }

    @Override
    public void writeUTF(String s) throws IOException {
        int len = s.length();
        if (len > 0xFFFF) throw new KVBFException("String size is too long (" + len + "), must be < " + 0xFFFF);
        writeShort(len);
        writeBytes(s);
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(bytes, count);
    }
}
