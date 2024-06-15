package com.cicdez.kvbf;

public final class KVBFTypes {
    public static final int
            NO_TYPE = 0,
            BYTE_TYPE = 1,
            SHORT_TYPE = 2,
            INTEGER_TYPE = 3,
            LONG_TYPE = 4,
            FLOAT_TYPE = 5,
            DOUBLE_TYPE = 6,
            BOOLEAN_TYPE = 7,
            STRING_TYPE = 8,
            COMPOUND_TYPE = 9,
            LIST_TYPE = 10,
            TYPED_LIST_TYPE = 11
                    ;

    public static IKVBF<?> getKVBF(int type) throws KVBFException {
        switch (type) {
            case BYTE_TYPE: return new ByteKVBF();
            case SHORT_TYPE: return new ShortKVBF();
            case INTEGER_TYPE: return new IntegerKVBF();
            case LONG_TYPE: return new LongKVBF();
            case FLOAT_TYPE: return new FloatKVBF();
            case DOUBLE_TYPE: return new DoubleKVBF();
            case BOOLEAN_TYPE: return new BooleanKVBF();
            case STRING_TYPE: return new StringKVBF();
            case COMPOUND_TYPE: return new CompoundKVBF();
            case LIST_TYPE: return new ListKVBF();
            case TYPED_LIST_TYPE: return new TypedListKVBF();

            default: throw new KVBFException("No such KVBF of type: " + type);
        }
    }
    public static int getType(Class<? extends IKVBF<?>> kvbfClass) {
        return NO_TYPE;
    }
}
