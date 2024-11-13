package com.bugfe.datastructure;

public interface IStack<E> {
    /**
     * 压栈
     * @param e
     */
    void push(E e);

    /**
     * 获取栈顶元素
     * @return
     */
    E peek();

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 获取栈中元素的个数
     * @return
     */
    int getSize();

    /**
     * 栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 正向打印
     */
    void print();

    /**
     * 逆向打印
     */
    void printReverse();
}
