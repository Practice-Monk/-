package com.bugfe.queue;

/**
 *  队
 * @param <E>
 */
public interface IQueue<E> {
    /**
     * 入队
     * @param e
     */
    void enqueue(E e);

    /**
     * 获取队列头部的元素
     * @return
     */
    E peek();

    /**
     * 出队
     * @return
     */
    E dequeue();

    /**
     * 获取队列中元素个数
     * @return
     */
    int getSize();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 是否满了
     * @return
     */
    boolean isFull();

    /**
     * 打印
     */
    void print();
}
