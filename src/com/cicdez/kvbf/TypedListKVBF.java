package com.cicdez.kvbf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TypedListKVBF extends AbstractAccountedKVBF<List<IKVBF<?>>> {
    private int componentType;
    private List<IKVBF<?>> list;

    public TypedListKVBF(int componentType, List<IKVBF<?>> list) {
        this.componentType = componentType;
        this.list = list;
    }
    public TypedListKVBF(int componentType) {
        this(componentType, new ArrayList<>());
    }
    public TypedListKVBF() {
        this(KVBFTypes.NO_TYPE, new ArrayList<>());
    }

    public void setComponentType(int componentType) {
        this.componentType = componentType;
    }
    public int getComponentType() {
        return componentType;
    }

    @Override
    public void set(List<IKVBF<?>> object) {
        this.list = object;
    }

    @Override
    public List<IKVBF<?>> get() {
        return list;
    }

    private void isRightType(IKVBF<?> ikvbf) throws KVBFException {
        if (componentType == KVBFTypes.NO_TYPE)
            throw new KVBFException("Typed List has no type");
        if (ikvbf.getTypeId() != componentType)
            throw new KVBFException("Typed List of '" + componentType + "' can't support '" + ikvbf.getTypeId() + "'");
    }
    public void add(IKVBF<?> ikvbf) throws KVBFException {
        isRightType(ikvbf);
        list.add(ikvbf);
    }
    public void add(int index, IKVBF<?> ikvbf) throws KVBFException {
        isRightType(ikvbf);
        list.add(ikvbf);
    }

    public IKVBF<?> get(int index) throws KVBFException {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    @Override
    public void writeContent(ByteArrayDataOutputStream output) throws IOException {
        output.writeByte(componentType);
        int count = size();
        BytesHandler.writeLength(count, output);
        for (IKVBF<?> ikvbf : list) {
            ikvbf.write(output);
        }
    }

    @Override
    public void readContent(ByteArrayDataInputStream input) throws IOException {
        int type = input.read();
        if (type == KVBFTypes.NO_TYPE) return;
        setComponentType(type);
        int count = BytesHandler.readLength(input);
        for (int index = 0; index < count; index++) {
            IKVBF<?> ikvbf = KVBFTypes.getKVBF(type);
            ikvbf.read(input);
            list.add(ikvbf);
        }
    }

    @Override
    public int getTypeId() {
        return KVBFTypes.TYPED_LIST_TYPE;
    }
}
