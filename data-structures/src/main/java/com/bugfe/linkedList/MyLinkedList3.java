package com.bugfe.linkedList;

/**
 * MyLinkedLIst
 * @param <E>
 */
public class MyLinkedList3<E> implements ILinkedList<E>{
    private Node<E> vHead;
    private Node<E> vTail;
    /**
     * 记录元素个数
     */
    private int size;
    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item) {
            this(item,null,null);
        }

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public MyLinkedList3() {
        size = 0;
        vHead = new Node<>(null,null,null);
        vTail = new Node<>(null,null,null);
        //将虚拟头节点的next指向虚拟尾节点
        vHead.next = vTail;
        //将虚拟尾节点的prev指向虚拟头节点
        vTail.prev = vHead;
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
    public void addFirst(E e) {
        //1. 创建一个新节点，该节点的prev就是虚拟头节点，该节点的next就是虚拟头节点的next
        Node<E> node = new Node<>(e,vHead.next,vHead);
        //2. 将虚拟头节点的next的prev属性指向node
        vHead.next.prev = node;
        //3. 将虚拟头节点的next指向node
        vHead.next = node;

        size ++;
    }

    @Override
    public void addLast(E e) {
        //1. 创建一个新节点，该节点的prev指向虚拟尾节点的prev，该节点的next指向虚拟尾节点
        Node<E> node = new Node<>(e,vTail,vTail.prev);
        //2. 将虚拟尾节点的prev的next属性指向node
        vTail.prev.next = node;
        //3. 将虚拟尾节点的prev属性指向node
        vTail.prev = node;

        size ++;
    }

    @Override
    public void addAfter(E pivot, E e) {
        //0. 根据pivot查找节点
        Node<E> pivotNode = findNode(pivot);
        if (pivotNode == null) {
            throw new RuntimeException("参照节点为空，不能添加");
        }
        //1. 创建一个新节点，该节点的next指向pivot节点的next，该节点的prev指向pivot节点
        Node<E> node = new Node<>(e,pivotNode.next,pivotNode);
        //2. pivot节点的next的prev属性指向node
        pivotNode.next.prev = node;
        //3. pivot的next属性指向node
        pivotNode.next = node;

        size ++;
    }

    /**
     * 根据元素查找节点
     * @param e
     * @return
     */
    private Node<E> findNode(E e) {
        //1. 从前往后找
        Node<E> cur = vHead.next;
        while (cur != null){
            if (cur.item.equals(e)) {
                return cur;
            }

            //继续往下找
            cur = cur.next;
        }
        return null;
    }


    @Override
    public void addBefore(E pivot, E e) {
        //0. 根据pivot查找节点
        Node<E> pivotNode = findNode(pivot);
        if (pivotNode == null) {
            throw new RuntimeException("参照节点为空，不能添加");
        }
        //1. 创建一个新节点，该节点的next指向pivot,该节点的prev属性指向pivot的prev
        Node<E> node = new Node<>(e,pivotNode,pivotNode.prev);
        //2. pivot节点的prev的next属性指向node
        pivotNode.prev.next = node;
        //3. pivot节点的prev属性指向node
        pivotNode.prev = node;

        size ++;
    }

    @Override
    public E getFirst() {
        return vHead.next.item;
    }

    @Override
    public E getLast() {
        return vTail.prev.item;
    }

    @Override
    public boolean contains(E e) {
        return findNode(e) != null;
    }

    @Override
    public E removeFirst() {
        //1. 判断链表是否为空
        if (isEmpty()) {
            throw new RuntimeException("链表为空");
        }
        //2. 将虚拟头节点的next的next的prev属性指向虚拟头节点
        vHead.next.next.prev = vHead;
        //3. 记录临时节点(虚拟头节点的下一个)
        Node<E> tempNode = vHead.next;
        E e = tempNode.item;
        //4. 将虚拟头节点的next属性指向临时节点的next
        vHead.next = tempNode.next;
        //5. 便于GC，我们要将临时节点的next指向null、prev指向null
        tempNode.next = null;
        tempNode.prev = null;


        size --;
        return e;
    }

    @Override
    public E removeLast() {
        //1. 判断链表是否为空
        if (isEmpty()) {
            throw new RuntimeException("链表为空");
        }
        //2. 将虚拟尾节点的prev的prev的next属性指向虚拟尾节点
        vTail.prev.prev.next = vTail;
        //3. 记录临时节点
        Node<E> tempNode = vTail.prev;
        E e = tempNode.item;
        //4. 虚拟尾节点的prev指向临时节点的prev
        vTail.prev = tempNode.prev;
        //5. 便于GC，将临时节点的prev和next属性置空
        tempNode.prev = null;
        tempNode.next = null;
        size --;
        return e;
    }


    @Override
    public void remove(E e) {
        //1. 查找节点
        Node<E> node = findNode(e);
        if (node == null) {
            throw new RuntimeException("元素" + e + "不存在");
        }
        //2. 将node的next的prev属性指向node的prev
        node.next.prev = node.prev;
        //3. 将node的prev的next属性指向node的next
        node.prev.next = node.next;
        //4. 将node的prev和next属性置空
        node.prev = null;
        node.next = null;

        size --;
    }

    @Override
    public void print() {
        //从头到尾遍历
        Node<E> curNode = vHead.next;
        while (curNode != null) {
            if (curNode != vTail) {
                System.out.print(curNode.item + "\t");
            }

            curNode = curNode.next;
        }
    }

    public void printReverse() {
        //从尾到头遍历
        Node<E> curNode = vTail.prev;
        while (curNode != null) {
            if (curNode != vHead) {
                System.out.print(curNode.item + "\t");
            }
            curNode = curNode.prev;
        }
    }


    public static void main(String[] args) {
        MyLinkedList3<Integer> myLinkedList3 = new MyLinkedList3<>();
        myLinkedList3.addFirst(1);
        myLinkedList3.addFirst(2);
        myLinkedList3.addFirst(3);
        myLinkedList3.addFirst(4);
        myLinkedList3.addFirst(5);

        myLinkedList3.addLast(11);
        myLinkedList3.addLast(22);
        myLinkedList3.addLast(33);
        myLinkedList3.addLast(44);
        myLinkedList3.addLast(55);

        myLinkedList3.addBefore(5,555);

        myLinkedList3.addAfter(33,333);

        myLinkedList3.print();

        System.out.println("链表的长度:"+myLinkedList3.getSize());

        myLinkedList3.printReverse();
        System.out.println();
        System.out.println(myLinkedList3.removeFirst());

        System.out.println(myLinkedList3.removeLast());

        myLinkedList3.remove(333);

        System.out.println("链表的长度:"+myLinkedList3.getSize());
    }
}
