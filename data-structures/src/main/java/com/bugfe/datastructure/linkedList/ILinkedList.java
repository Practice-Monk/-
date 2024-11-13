package com.bugfe.datastructure.linkedList;

public interface ILinkedList<E> {
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
     * 在链表头部添加元素
     * @param e
     */
    void addFirst(E e);

    /**
     * 在链表尾部添加元素
     * @param e
     */
    void addLast(E e);

    /**
     * 在参照物的后面添加元素
     * @param pivot
     * @param e
     */
    void addAfter(E pivot,E e);

    /**
     * 在参照物的前面添加元素
     * @param pivot
     * @param e
     */
    void addBefore(E pivot,E e);

    /**
     * 获取头节点元素
     * @return
     */
    E getFirst();

    /**
     * 获取尾节点元素
     * @return
     */
    E getLast();

    /**
     * 判断是否存在
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 删除头节点元素
     * @return
     */
    E removeFirst();

    /**
     * 删除尾节点元素
     * @return
     */
    E removeLast();

    /**
     * 删除指定元素
     * @param e
     */
    void remove(E e);

    /**
     * 打印
     */
    void print();
}
