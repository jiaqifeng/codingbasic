package com.jack;

/**
 * Created by jack on 17-2-22.
 */
public interface Rule {
    String getType();
    boolean legal();
    boolean match(String data);
}
