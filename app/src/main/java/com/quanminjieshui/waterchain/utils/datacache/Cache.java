package com.quanminjieshui.waterchain.utils.datacache;

/**
 * Created by WanghongHe on 2018/12/29 18:22.
 */

public interface Cache {
    String get(final String key);
    void put(final String key, final String value);
    boolean remove(final String key);
}
