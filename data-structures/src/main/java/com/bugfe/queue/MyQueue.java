package com.bugfe.queue;

/**
 * 队
 * @param <E>
 */
public class MyQueue<E> implements IQueue<E> {
    Object[] elementData;
    int DEFAULT_CAPACITY = 10;
    int size;

    public MyQueue() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyQueue(int capacity) {
        elementData = new Object[capacity];
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        //1. 判断队列是否满了，如果满了则不能入队
        if (isFull()) {
            throw new RuntimeException("队列满了!!!");
        }
        //2. 以数组的头和尾作为队列的头和尾
        //添加元素其实就是往数组的尾部进行添加
        elementData[size ++] = e;
    }

    private E elementData(int index){
        return (E) elementData[index];
    }
    @Override
    public E peek() {

        return elementData(0);
    }

    @Override
    public E dequeue() {
        //1. 获取队列头部的元素
        E e = elementData(0);
        //2. 删除队列头部的元素
        //2.1 将1以及后续位置的元素向前挪一位
        System.arraycopy(elementData,1,elementData,0,size - 1);
        //2.2 将size - 1位置的值置为null
        elementData[size - 1] = null;
        size --;
        return e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == elementData.length;
    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(elementData[i] + "\t");
        }
    }


    public static void main(String[] args) {
        IQueue<Integer> queue = new MyQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        System.out.println(queue.peek());

        queue.print();
    }
}
