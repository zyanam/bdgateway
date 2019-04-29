package com.zjts.protocol808;

public interface Analysisable {
    boolean bytes2Entity(byte[] bytes);
    byte[] entity2Bytes();
}
