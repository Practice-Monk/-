package com.bugfe.datastructure.map;

public interface IMap<K,V> {
    /**
     * 存储键值对
     * @param key
     * @param value
     */
    void put(K key,V value);

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 删除键值对
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 判断某个key是否存在
     * @param key
     * @return
     */
    boolean containsKey(K key);

    /**
     * 获取元素个数
     * @return
     */
    int getSize();

    /**
     * 打印
     */
    void print();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();
}
