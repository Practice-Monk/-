package com.bugfe.arraylist;


public class MyArrayList<E> implements IList<E>{
    Object[] elementData;
    static final int DEFAULT_CAPACITY = 10;
    int size;
    static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementData = new Object[]{};
        }else {
            throw new IllegalArgumentException("initialCapacity:"+initialCapacity);
        }
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
    public boolean contains(E e) {
        //遍历数组
        return indexOf(e) != -1;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(e)){
                return i;
            }
        }
        //如果不存在则返回-1
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(E e) {
        //1. 判断集合是否满了:size是否等于数组的长度,如果满则则扩容
        if (size == elementData.length) {
            //集合满了
            grow();
        }
        //2. 将e添加到数组的size位置
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

    @Override
    public void add(int index, E e) {
        //1. 判断index是否越界
        checkIndexRangeForSet(index);
        //2. 判断数组是否满了
        if (size == elementData.length) {
            grow();
        }
        //3. 将index及以后的元素，往后挪一位
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        //4. 将e设置到index位置
        elementData[index] = e;
        //5. size ++
        size ++;
    }

    @Override
    public E get(int index) {
        //判断index是否越界
        checkIndexRangeForGet(index);
        //2. 返回elementDate下标为index处的元素
        return elementData(index);
    }

    /**
     * 获取元素的时候检查下标
     * @param index
     */
    private void checkIndexRangeForGet(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
    }

    private void checkIndexRangeForSet(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
    }

    private E elementData(int index){
        return (E) (E) elementData[index];
    }

    @Override
    public E set(int index, E newValue) {
        //1. 检查下标是否越界
        checkIndexRangeForSet(index);
        //2. 获取旧数据
        E oldValue = elementData(index);
        //3. 替换新数据
        elementData[index] = newValue;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        //1. 检查下标是否越界
        checkIndexRangeForGet(index);
        //2. 获取就数据
        E oldValue = elementData(index);
        //2. 拷贝元素
        System.arraycopy(elementData,index + 1,elementData,index,size - index - 1);
        //3. 将最后一个元素置空
        elementData[size - 1] = null;
        //4. size --
        size -- ;

        //判断size是否大于容量的一半，如果不大于，则设置新容量为原容量的3/4
        return oldValue;
    }

    @Override
    public void print() {
        //1. [ 开头
        StringBuffer stringBuffer = new StringBuffer("[");
        //2. 从前到后遍历出每一个元素
        for (int i = 0; i < size; i++) {
            stringBuffer.append(elementData[i]);
            if (i != size - 1) {
                stringBuffer.append(",");
            }
        }
        //3. ] 结尾
        stringBuffer.append("]");

        //输出stringBuffer
        System.out.println(stringBuffer.toString());
    }

    @Override
    public void printReverse() {
        //1. [ 开头
        StringBuffer stringBuffer = new StringBuffer("[");
        //2. 从前到后遍历出每一个元素
        for (int i = size - 1; i >= 0; i--) {
            stringBuffer.append(elementData[i]);
            if (i != 0) {
                stringBuffer.append(",");
            }
        }
        //3. ] 结尾
        stringBuffer.append("]");

        //输出stringBuffer
        System.out.println(stringBuffer.toString());
    }

    public static void main(String[] args) {
        //测试ArrayList
        IList list = new MyArrayList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        list.add(3,110);
        list.add(9,120);


        list.print();

        list.remove(3);
        list.remove(7);

        list.print();
        list.printReverse();
    }
}
