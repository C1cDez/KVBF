package com.cicdez.kvbf;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public final class KVBFileIO {
    public static void write(OutputStream stream, CompoundKVBF compound) throws IOException {
        compound.write(new DataOutputStream(stream));
        stream.close();
    }
    public static void write(File file, CompoundKVBF compound) throws IOException {
        FileOutputStream stream = new FileOutputStream(file);
        write(stream, compound);
    }
    public static void write(Path path, CompoundKVBF compound) throws IOException {
        OutputStream stream = Files.newOutputStream(path);
        write(stream, compound);
    }

    public static CompoundKVBF read(InputStream stream) throws IOException {
        CompoundKVBF compound = new CompoundKVBF();
        compound.read(new DataInputStream(stream));
        stream.close();
        return compound;
    }
    public static CompoundKVBF read(File file) throws IOException {
        return read(new FileInputStream(file));
    }
    public static CompoundKVBF read(Path path) throws IOException {
        return read(Files.newInputStream(path));
    }
}
