package com.bugfe.datastructure.linkedList;

public class MyLinkedList1<E> implements ILinkedList<E> {
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

    /**
     * 元素个数
     */
    int size;

    /**
     * 头节点
     */
    Node<E> first;

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
        //1. 判断是否存在头节点
        if (first == null) {
            //2. 如果不存在头节点，那么也就是说链表为空
            //2.1 创建一个节点，将该节点设置为头节点
            first = new Node<>(e);

            //如果只有一个元素，那么头节点就是尾节点
            last = first;
        }else {
            //3. 如果存在头节点，那么也就是说要将新节点添加到头节点的前面
            //3.1 创建一个新节点，新节点的next指向头节点
            //3.2 将新创建的节点设置为头节点
            first = new Node<>(e,first);
        }
        //4. 变化size
        size ++;
    }

    @Override
    public void addLast(E e) {
        //1. 判断是否有头节点
        if (first == null) {
            //2. 如果没有头节点，说明链表为空，那么就创建一个节点作为头节点
            first = new Node<>(e);
            //3. 头节点就是尾节点
            last = first;
        }else {
            //3. 说明存在头节点，那么就从头节点开始往后找尾节点
            /*Node<E> last = first;
            while (last.next != null) {
                last = last.next;
            }
            //4. 创建一个新节点作为尾节点的next
            last.next = new Node<>(e);*/

            //如果已经记录了尾节点，那么就直接将新节点放到尾节点的后面
            Node<E> node = new Node<>(e);
            last.next = node;
            //将新节点变成尾节点
            last = node;
        }
        //5. 改变size
        size ++;
    }

    @Override
    public void addAfter(E pivot, E e) {
        //1. 找到pivot对应的节点
        Node<E> pivotNode = findNode(pivot);
        if (pivotNode == null) {
            throw new RuntimeException("未找到参照节点");
        }
        //如果参照节点的next是null，那么表示参照节点是尾节点
        if (pivotNode.next == null) {
            addLast(e);
        }else {
            //2. 新建一个节点，将新节点的next指向pivot的next
            Node<E> node = new Node<>(e, pivotNode.next);
            //3. 设置pivot对应节点的next指向
            pivotNode.next = node;

            //改变size
            size ++;
        }
    }

    /**
     * 根据元素查找对应的节点
     * @param e
     * @return
     */
    private Node<E> findNode(E e) {
        if (first == null) {
            throw new RuntimeException("链表为空");
        }
        //从头开始遍历
        Node<E> current = first;
        while (current != null){
            if (current.item.equals(e)) {
                return current;
            }
            //继续往下找
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
        //2. 如果参照节点是first，那么就是添加头节点
        if (pivotNode == first) {
            addFirst(e);
        }else {
            //按照正常的在pivotNode的前面添加节点
            Node<E> prevNode = findPrevNode(pivotNode);
            //新节点的next指向参照节点。参照节点上一个指向新节点
            prevNode.next = new Node<>(e,pivotNode);

            //改变size
            size ++;
        }
    }

    /**
     * 找上一个节点
     * @param node
     * @return
     */
    private Node<E> findPrevNode(Node<E> node) {
        if (node != first) {
            //从头开始遍历
            Node<E> prev = first;
            while (prev.next != null){
                if (prev.next == node) {
                    //说明找到了
                    return prev;
                }
                //继续往下找
                prev = prev.next;
            }
        }
        return null;
    }

    @Override
    public E getFirst() {
        if (first == null) {
            throw new RuntimeException("链表为空");
        }
        return first.item;
    }

    @Override
    public E getLast() {
        if (last == null) {
            throw new RuntimeException("链表为空");
        }
        return last.item;
    }

    @Override
    public boolean contains(E e) {
        //从头节点开始遍历
        Node<E> current = first;
        while (current != null){
            if (current.item.equals(e)) {
                return true;
            }
            //找下一个元素
            current = current.next;
        }
        return false;
    }

    @Override
    public E removeFirst() {
        if (first == null) {
            throw new RuntimeException("链表为空");
        }
        //1. 获取头节点的内容
        E e = first.item;


        //2. 判断是否只有一个节点
        if (first == last) {
            //删除后尾节点要置空
            last = null;
        }
        //3. 将first指向first的下一个节点
        //3.1 记录临时头节点
        Node<E> temp = first;
        //3.2 将first的下一个节点设置为first
        first = first.next;
        //3.3 断开之前first对下一个的指向
        temp.next = null;
        //4. 改变size
        size --;
        return e;
    }

    @Override
    public E removeLast() {
        if (last == null) {
            throw new RuntimeException("链表为空");
        }

        E e = last.item;
        //1. 从first开始往后遍历，找到尾节点的上一个节点
        //1.1 定义指针记录上一个节点，当上一个节点的next指向last，就说明找到上一个节点
        if (last == first) {
            //说明只有一个节点,那么直接置空
            first = null;
            last = null;
        }else {
            Node<E> prev = first;
            while (prev.next != last) {
                //继续找下一个
                prev = prev.next;
            }
            //停止循环的时候，prev就是last的上一个节点
            //将prev的next置为空
            prev.next = null;
            //将prev设置为last
            last = prev;
        }
        //改变size
        size --;
        return e;
    }

    @Override
    public void remove(E e) {
        if (first == null) {
            throw new RuntimeException("链表为空");
        }
        //如果只有一个节点
        if (first.item.equals(e)) {
            //删除头节点
            removeFirst();
        } else if (last.item.equals(e)) {
            //删尾节点
            removeLast();
        } else {
            //从头开始遍历
            Node<E> prev = first;
            Node<E> current = first.next;
            while (current != null) {
                if (current.item.equals(e)) {
                    //找到了要删除的节点
                    prev.next = current.next;
                    current.next = null;
                    //改变size
                    size --;
                    return;
                }
                //继续往下找
                prev = current;
                current = current.next;
            }
        }
    }

    @Override
    public void print() {
        //从头到尾打印
        Node<E> current = first;
        while (current != null) {
            System.out.print(current.item + "\t");
            current = current.next;
        }
    }


    public static void main(String[] args) {
        ILinkedList linkedList = new MyLinkedList1();

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

        linkedList.remove(6);
        linkedList.remove(8);

        System.out.println(linkedList.getSize());
        System.out.println("头部元素:" + linkedList.getFirst());
        System.out.println("尾部元素:" + linkedList.getLast());
    }
}
