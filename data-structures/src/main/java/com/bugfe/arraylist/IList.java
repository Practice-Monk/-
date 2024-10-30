package com.bugfe.arraylist;


public interface IList<E> {
    /**
     * 获取元素个数
     * @return
     */
    int getSize();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 判断是否存在某个元素
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 获取某个元素下标
     * @param e
     * @return
     */
    int indexOf(E e);

    /**
     * 获取某个元素最后一次出现的下标
     * @param e
     * @return
     */
    int lastIndexOf(E e);

    /**
     * 添加元素
     * @param e
     */
    void add(E e);

    /**
     * 指定下标添加元素
     * @param index
     * @param e
     */
    void add(int index,E e);

    /**
     * 指定下标获取元素
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 将指定下标处的元素替换成新元素
     * @param index
     * @param newValue
     * @return
     */
    E set(int index,E newValue);

    /**
     * 指定下标删除元素
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 正向打印
     */
    void print();

    /**
     * 反向打印
     */
    void printReverse();
}
