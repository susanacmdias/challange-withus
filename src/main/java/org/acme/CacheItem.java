package org.acme;

import java.math.BigInteger;

public class CacheItem<K, T> {
    private K key;
    private BigInteger value;

    private CacheItem prev, next;

    public CacheItem(K key, BigInteger value) {
        this.value = value;
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public BigInteger getValue() {
        return value;
    }
    public void setValue(BigInteger value) {
        this.value = value;
    }
    public CacheItem getPrev() {
        return prev;
    }

    public void setPrev(CacheItem prev) {
        this.prev = prev;
    }

    public CacheItem getNext() {
        return next;
    }

    public void setNext(CacheItem next) {
        this.next = next;
    }
}