package com.bugfe.datastructure.linkedList;

public class MyLinkedList2<E> implements ILinkedList<E> {
    Node<E> vHead;
    private static class Node<E>{
        /**
         * 当前节点存储的内容
         */
        E item;
        /**
         * 指向下一个节点
         */
        Node<E> next;

        public Node(E item) {
            this(item,null);
        }

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public MyLinkedList2() {
        vHead = new Node<>(null);
        size = 0;
    }

    /**
     * 元素个数
     */
    int size;
    /**
     * 尾节点
     */
    Node<E> last;
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(E e) {
        //维护尾节点
        //虚拟头节点的下一个是空，表示链表为空
        Node<E> node = new Node<>(e, vHead.next);
        if (vHead.next == null) {
            last = node;
        }
        vHead.next = node;

        //维护size
        size ++;
    }

    @Override
    public void addLast(E e) {
        if (last == null) {
            //当前没有尾节点,链表是空的,那么新元素就作为尾节点，也作为头节点
            last = new Node<>(e);
            vHead.next = last;
        }else {
            //尾节点不为空，那么就将新节点放到尾节点的后面
            Node<E> node = new Node<>(e);
            //将新节点放到尾节点的后面
            last.next = node;
            //将新节点变成尾节点
            last = node;
        }

        //维护size
        size ++;
    }

    @Override
    public void addAfter(E pivot, E e) {
        //1. 找到参照节点
        Node<E> pivotNode = findNode(pivot);
        //判断参照节点是否为空
        if (pivotNode == null) {
            throw new RuntimeException("未找到参照节点");
        }
        //2. 判断pivotNode是否是尾节点，那么就是尾插法
        if (pivotNode == last) {
            addLast(e);
        }else {
            //2. 将e创建成新节点，添加到参照节点的后面
            Node<E> node = new Node<>(e,pivotNode.next);
            pivotNode.next = node;
            //维护size
            size ++;
        }
    }

    /**
     * 根据元素查找对应的节点
     * @param e
     * @return
     */
    private Node<E> findNode(E e) {
        //从虚拟头结点的下一个开始找
        Node<E> current = vHead.next;
        while (current != null) {
            if (e.equals(current.item)) {
                //找到了
                return current;
            }
            //未找到，继续往下找
            current = current.next;
        }
        return null;
    }

    @Override
    public void addBefore(E pivot, E e) {
        //1. 找参照节点
        Node<E> pivotNode = findNode(pivot);
        if (pivotNode == null) {
            throw new RuntimeException("未找到参照节点");
        }
        //2. 找到参照节点的上一个节点
        Node<E> prevNode = findPrevNode(pivotNode);
        //2.1 新节点的next指向参照节点
        //2.2 prevNode的next指向新节点
        prevNode.next = new Node<>(e,pivotNode);

        //维护size
        size ++;
    }

    /**
     * 找上一个节点
     * @param node
     * @return
     */
    private Node<E> findPrevNode(Node<E> node) {
        //1. 从虚拟头节点往后找
        Node<E> prevNode = vHead;
        while (prevNode.next != null) {
            if (prevNode.next == node) {
                //找到了
                return prevNode;
            }
            //未找到，则继续往下找
            prevNode = prevNode.next;
        }

        throw new RuntimeException(node.item + "不存在");
    }

    @Override
    public E getFirst() {
        if (size == 0) {
            throw new RuntimeException("链表为空");
        }
        return vHead.next.item;
    }

    @Override
    public E getLast() {
        if (size == 0) {
            throw new RuntimeException("链表为空");
        }
        return last.item;
    }

    @Override
    public boolean contains(E e) {
        //从虚拟头节点开始遍历
        return findNode(e) != null;
    }

    @Override
    public E removeFirst() {
        if (size == 0) {
            throw new RuntimeException("链表为空");
        }
        //维护尾节点
        if (vHead.next == last) {
            last = null;
        }
        //1. 虚拟头节点的next指向虚拟头节点的next的next
        //1.1 记录临时头节点
        Node<E> temp = vHead.next;
        E e = temp.item;
        //1.2 将虚拟头节点的next指向虚拟头节点的next的next
        vHead.next = vHead.next.next;
        //1.3 将临时节点的next指向null
        temp.next = null;

        //维护size
        size --;
        return e;
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            throw new RuntimeException("链表为空");
        }
        E e = last.item;
        //找到尾节点的上一个节点
        Node<E> prevNode = findPrevNode(last);
        //设置prevNode的next为null
        prevNode.next = null;
        //设置prevNode为last
        last = prevNode;

        //维护size
        size --;
        return e;
    }

    @Override
    public void remove(E e) {
        //找到要删除的这个元素
        Node<E> node = findNode(e);
        if (node == null) {
            throw new RuntimeException(e + "在链表中不存在!!!");
        }
        //找到node的上一个节点
        Node<E> prevNode = findPrevNode(node);
        //将prevNode的next属性指向node 的next
        prevNode.next = node.next;
        //将node.next属性指向空
        node.next = null;

        //维护size
        size --;
    }

    @Override
    public void print() {
        //从头到尾打印
        Node<E> current = vHead.next;
        while (current != null) {
            System.out.print(current.item + "\t");
            current = current.next;
        }
    }


    public static void main(String[] args) {
        ILinkedList linkedList = new MyLinkedList2();

        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.addFirst(5);
        linkedList.addFirst(6);

        linkedList.print();

        System.out.println();
        //往尾节点添加元素

        linkedList.addLast(11);
        linkedList.addLast(22);
        linkedList.addLast(33);
        linkedList.addLast(44);
        linkedList.addLast(55);
        linkedList.addLast(66);
        linkedList.addLast(77);
        linkedList.addLast(88);

        linkedList.print();

        System.out.println();

        linkedList.addBefore(66,666);
        linkedList.addAfter(5,555);

        linkedList.print();


        System.out.println();

        //测试删除
        System.out.println("删除头部元素:" + linkedList.removeFirst());
        linkedList.print();
        System.out.println();
        System.out.println(linkedList.getSize());

        System.out.println("删除尾部元素:" + linkedList.removeLast());

        linkedList.print();
        System.out.println(linkedList.getSize());

        linkedList.remove(55);
        linkedList.print();
        System.out.println(linkedList.getSize());
        System.out.println("头部元素:" + linkedList.getFirst());
        System.out.println("尾部元素:" + linkedList.getLast());
    }
}
