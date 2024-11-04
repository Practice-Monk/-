package com.bugfe.datastructure;

public class MyStack<E> implements IStack<E> {
    int DEFAULT_CAPACITY = 10;
    Object[] elementData;
    int size;
    int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    public MyStack() {
        elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void push(E e) {
        //我们以数组的尾部作为堆顶,所以压栈就是将e添加到数组的最后一个元素的后面
        //1. 判断数组是否满了
        if (size == elementData.length) {
            //扩容
            grow();
        }
        //2. 将e放到数组的size的位置
        elementData[size ++] = e;
    }

    /**
     * 扩容:扩大一半，但是这个一半最小1，并且数组的最大长度是Integer的最大值 - 8
     */
    private void grow() {
        //1. 获取数组的旧容量
        int oldCapacity = elementData.length;
        //如果旧数组的容量就已经是最大容量了，说明不能添加数数据了
        if (oldCapacity == MAX_CAPACITY) {
            throw new RuntimeException("集合满了，不能再添加数据");
        }
        int newSize = 0;
        //2. 判断，如果旧容量为0，那么直接扩到默认容量
        if (oldCapacity == 0) {
            newSize = DEFAULT_CAPACITY;
        }else {
            //3. 判断，如果旧容量不为零，则设置新容量为旧容量的1.5倍
            newSize = oldCapacity + (oldCapacity >> 1);
        }
        //4. 判断新容量如果大于最大容量，那么设置新容量为最大容量
        if (newSize > MAX_CAPACITY) {
            newSize = MAX_CAPACITY;
        }
        //5. 创建一个新数组，替换旧数组
        //5.1 创建一个新数组
        Object[] newElementData = new Object[newSize];
        //5.2 将旧数组内的元素拷贝到新数组
        System.arraycopy(elementData,0,newElementData,0,size);
        //5.3 用新数组替换旧数组
        elementData = newElementData;
    }
    private E elementData(int index){
        return (E) elementData[index];
    }
    @Override
    public E peek() {
        //1. 判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空!!!");
        }
        return elementData(size - 1);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空!!!");
        }
        //1. 获取size - 1位置的值
        E e = elementData(size - 1);
        //2. 删除size - 1位置的值
        elementData[size - 1] = null;
        //3. size --
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
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(elementData[i] + "\t");
        }
    }

    @Override
    public void printReverse() {
        for (int i = size - 1; i >= 0; i -- ) {
            System.out.print(elementData[i] + "\t");
        }
    }

    public static void main(String[] args) {
        IStack<Integer> stack = new MyStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        stack.print();
        System.out.println("-------------------------------");

        System.out.println(stack.peek());

        stack.printReverse();
    }
}
