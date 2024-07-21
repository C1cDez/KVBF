package com.cicdez.kvbf.extra;

import com.cicdez.kvbf.IKVBF;

public abstract class AbstractObjectKVBF<T> implements IKVBF<T> {
    private T object;
    protected AbstractObjectKVBF(T object) {
        this.object = object;
    }
    protected AbstractObjectKVBF() {
        this(null);
    }

    public T get() {
        return object;
    }
    public void set(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return get().toString();
    }
}
