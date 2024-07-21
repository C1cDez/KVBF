package com.cicdez.kvbf.lists;

import com.cicdez.kvbf.*;
import com.cicdez.kvbf.extra.AbstractAccountedKVBF;
import com.cicdez.kvbf.extra.KVBFException;
import com.cicdez.kvbf.extra.KVBFTypes;
import com.cicdez.kvbf.primitives.*;
import com.cicdez.kvbf.serializers.ByteArrayDataInputStream;
import com.cicdez.kvbf.serializers.ByteArrayDataOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListKVBF extends AbstractAccountedKVBF<List<IKVBF<?>>> {
    private List<IKVBF<?>> list;

    public ListKVBF(List<IKVBF<?>> list) {
        this.list = list;
    }
    public ListKVBF() {
        this(new ArrayList<>());
    }

    @Override
    public List<IKVBF<?>> get() {
        return list;
    }

    @Override
    public void set(List<IKVBF<?>> object) {
        this.list = object;
    }

    public void add(IKVBF<?> object) {
        list.add(object);
    }
    public void add(int index, IKVBF<?> object) {
        list.add(index, object);
    }
    public void addByte(int v) {
        add(new ByteKVBF((byte) v));
    }
    public void addShort(int v) {
        add(new ShortKVBF((short) v));
    }
    public void addInteger(int v) {
        add(new IntegerKVBF(v));
    }
    public void addLong(long v) {
        add(new LongKVBF(v));
    }
    public void addFloat(float v) {
        add(new FloatKVBF(v));
    }
    public void addDouble(double v) {
        add(new DoubleKVBF(v));
    }
    public void addBoolean(boolean v) {
        add(new BooleanKVBF(v));
    }
    public void addString(String v) {
        add(new StringKVBF(v));
    }
    public void addCompound(CompoundKVBF compound) {
        add(compound);
    }
    public void addList(ListKVBF list) {
        add(list);
    }
    public void addTypedList(TypedListKVBF typedList) {
        add(typedList);
    }

    public IKVBF<?> get(int index) {
        return list.get(index);
    }
    public byte getByte(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof ByteKVBF) return ((ByteKVBF) ikvbf).get();
        else throw new KVBFException("No such byte on " + index);
    }
    public short getShort(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof ShortKVBF) return ((ShortKVBF) ikvbf).get();
        else throw new KVBFException("No such short on " + index);
    }
    public int getInteger(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof IntegerKVBF) return ((IntegerKVBF) ikvbf).get();
        else throw new KVBFException("No such integer on " + index);
    }
    public long getLong(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof LongKVBF) return ((LongKVBF) ikvbf).get();
        else throw new KVBFException("No such long on " + index);
    }
    public float getFloat(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof FloatKVBF) return ((FloatKVBF) ikvbf).get();
        else throw new KVBFException("No such float on " + index);
    }
    public double getDouble(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof DoubleKVBF) return ((DoubleKVBF) ikvbf).get();
        else throw new KVBFException("No such double on " + index);
    }
    public boolean getBoolean(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof BooleanKVBF) return ((BooleanKVBF) ikvbf).get();
        else throw new KVBFException("No such boolean on " + index);
    }
    public String getString(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof StringKVBF) return ((StringKVBF) ikvbf).get();
        else throw new KVBFException("No such string on " + index);
    }
    public CompoundKVBF getCompound(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof CompoundKVBF) return (CompoundKVBF) ikvbf;
        else throw new KVBFException("No such compound on " + index);
    }
    public ListKVBF getList(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof ListKVBF) return (ListKVBF) ikvbf;
        else throw new KVBFException("No such list on " + index);
    }
    public TypedListKVBF getTypedList(int index) throws KVBFException {
        IKVBF<?> ikvbf = get(index);
        if (ikvbf instanceof TypedListKVBF) return (TypedListKVBF) ikvbf;
        else throw new KVBFException("No such list on " + index);
    }

    public int size() {
        return list.size();
    }

    @Override
    public void writeContent(ByteArrayDataOutputStream output) throws IOException {
        for (IKVBF<?> ikvbf : list) {
            output.writeByte(ikvbf.getTypeId());
            ikvbf.write(output);
        }
    }

    @Override
    public void readContent(ByteArrayDataInputStream input) throws IOException {
        int type;
        while (true) {
            type = input.read();
            if (type < 0) break;
            IKVBF<?> ikvbf = KVBFTypes.getKVBF(type);
            ikvbf.read(input);
            list.add(ikvbf);
        }
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.LIST_TYPE;
    }
}
