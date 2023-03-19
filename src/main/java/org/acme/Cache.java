package org.acme;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Cache<K, T> {
    private Map<K, CacheItem> map;
    private CacheItem first, last;
    private int size;
    private final int CAPACITY;
    public Cache(int capacity) {
        CAPACITY = capacity;  //
        map = new HashMap<>(CAPACITY);
    }

    public void put(K key, BigInteger value) {
        CacheItem node = new CacheItem(key, value);

        if(map.containsKey(key) == false) {
            if(size() >= CAPACITY) {
                deleteNode(first);
            }
            addNodeToLast(node);
        }
        map.put(key, node);
    }

    public T get(K key) {
        if(map.containsKey(key) == false) {
            return null;
        }
        CacheItem node = (CacheItem) map.get(key);
        reorder(node);
        return (T) node.getValue();
    }

    public int size() {
        return size;
    }

    private void deleteNode(CacheItem node) {
        if(node == null) {
            return;
        }
        if(last == node) {
            last = node.getPrev();
        }
        if(first == node) {
            first = node.getNext();
        }
        map.remove(node.getKey());
        node = null; // Optional, collected by GC
        size--;
    }

    private void addNodeToLast(CacheItem node) {
        if(last != null) {
            last.setNext(node);
            node.setPrev(last);
        }

        last = node;
        if(first == null) {
            first = node;
        }
        size++;
    }

    private void reorder(CacheItem node) {
        if(last == node) {
            return;
        }
        if(first == node) {
            first = node.getNext();
        } else {
            node.getPrev().setNext(node.getNext());
        }
        last.setNext(node);
        node.setPrev(last);
        node.setNext(null);
        last = node;
    }

}