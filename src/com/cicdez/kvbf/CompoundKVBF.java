package com.cicdez.kvbf;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CompoundKVBF extends AbstractAccountedKVBF<Map<String, IKVBF<?>>> {
    private Map<String, IKVBF<?>> map;

    public CompoundKVBF(Map<String, IKVBF<?>> map) {
        this.map = map;
    }
    public CompoundKVBF() {
        this(new HashMap<>());
    }

    @Override
    public Map<String, IKVBF<?>> get() {
        return map;
    }

    @Override
    public void set(Map<String, IKVBF<?>> object) {
        this.map = object;
    }

    public void put(String key, IKVBF<?> ikvbf) throws KVBFException {
        if (key.length() > 0x7F) throw new KVBFException("Key length > " + 0x7F);
        map.put(key, ikvbf);
    }
    public void putByte(String key, int value) throws KVBFException {
        put(key, new ByteKVBF((byte) value));
    }
    public void putShort(String key, int value) throws KVBFException {
        put(key, new ShortKVBF((short) value));
    }
    public void putInteger(String key, int value) throws KVBFException {
        put(key, new IntegerKVBF(value));
    }
    public void putLong(String key, long value) throws KVBFException {
        put(key, new LongKVBF(value));
    }
    public void putFloat(String key, float value) throws KVBFException {
        put(key, new FloatKVBF(value));
    }
    public void putDouble(String key, double value) throws KVBFException {
        put(key, new DoubleKVBF(value));
    }
    public void putBoolean(String key, boolean value) throws KVBFException {
        put(key, new BooleanKVBF(value));
    }
    public void putString(String key, String value) throws KVBFException {
        put(key, new StringKVBF(value));
    }
    public void putCompound(String key, CompoundKVBF value) throws KVBFException {
        if (this == value) throw new KVBFException("Unable to put map to itself");
        put(key, value);
    }
    public void putList(String key, ListKVBF list) throws KVBFException {
        put(key, list);
    }
    public void putTypedList(String key, TypedListKVBS typedList) throws KVBFException {
        put(key, typedList);
    }

    public IKVBF<?> get(String key) {
        return map.get(key);
    }
    public byte getByte(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof ByteKVBF) return ((ByteKVBF) ikvbf).get();
        else throw new KVBFException("No such byte value '" + key + "'");
    }
    public short getShort(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof ShortKVBF) return ((ShortKVBF) ikvbf).get();
        else throw new KVBFException("No such short value '" + key + "'");
    }
    public int getInteger(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof IntegerKVBF) return ((IntegerKVBF) ikvbf).get();
        else throw new KVBFException("No such integer value '" + key + "'");
    }
    public long getLong(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof LongKVBF) return ((LongKVBF) ikvbf).get();
        else throw new KVBFException("No such long value '" + key + "'");
    }
    public float getFloat(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof FloatKVBF) return ((FloatKVBF) ikvbf).get();
        else throw new KVBFException("No such float value '" + key + "'");
    }
    public double getDouble(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof DoubleKVBF) return ((DoubleKVBF) ikvbf).get();
        else throw new KVBFException("No such double value '" + key + "'");
    }
    public boolean getBoolean(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof BooleanKVBF) return ((BooleanKVBF) ikvbf).get();
        else throw new KVBFException("No such boolean value '" + key + "'");
    }
    public String getString(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof StringKVBF) return ((StringKVBF) ikvbf).get();
        else throw new KVBFException("No such string value '" + key + "'");
    }
    public CompoundKVBF getCompound(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof CompoundKVBF) return (CompoundKVBF) ikvbf;
        else throw new KVBFException("No such compound '" + key + "'");
    }
    public ListKVBF getList(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof ListKVBF) return (ListKVBF) ikvbf;
        else throw new KVBFException("No such list '" + key + "'");
    }
    public TypedListKVBS getTypedList(String key) throws KVBFException {
        IKVBF<?> ikvbf = get(key);
        if (ikvbf instanceof TypedListKVBS) return (TypedListKVBS) ikvbf;
        else throw new KVBFException("No such list '" + key + "'");
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.COMPOUND_TYPE;
    }

    @Override
    public void writeContent(ByteArrayDataOutputStream output) throws IOException {
        for (String key : map.keySet()) {
            output.writeByte(key.length());
            output.writeBytes(key);
            IKVBF<?> value = map.get(key);
            output.writeByte(value.getTypeId());
            value.write(output);
        }
    }

    @Override
    public void readContent(ByteArrayDataInputStream input) throws IOException {
        int keyLength;
        while (true) {
            keyLength = input.read();
            if (keyLength < 0) break;
            byte[] keyBytes = new byte[keyLength];
            input.readFully(keyBytes);
            String key = new String(keyBytes);

            int type = input.read();
            IKVBF<?> ikvbf = KVBFTypes.getKVBF(type);
            ikvbf.read(input);

            map.put(key, ikvbf);
        }
    }
}
