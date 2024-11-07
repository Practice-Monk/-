package com.bugfe.leetcode;

import java.util.Stack;

public class LeetCodeReverseLinkedList {
    private Node first;
    private static class Node {
        /**
         * 指向下一个节点
         */
        Node next;

        int item;

        public Node(int item) {
            this(null,item);
        }

        public Node(Node next, int item) {
            this.next = next;
            this.item = item;
        }

        void print(){
            System.out.print(item + "\t");
            //临时节点
            Node temp = next;
            //获取下一个node
            while (temp != null) {
                System.out.print(temp.item + "\t");
                //继续往下找
                temp = temp.next;
            }
        }
    }

    public Node reverseNode(Node node){
        //1. 创建一个栈
        Stack<Node> stack = new Stack();
        //1. 从传入的节点开始往后找节点，每找到一个就放到栈里面
        Node current = node;
        while (current != null) {
            //将current放入栈里面
            stack.push(current);

            current = current.next;
        }

        //栈顶元素出栈
        Node topNode = stack.pop();
        first = topNode;
        //2. 出栈
        while (!stack.isEmpty()){
            //栈顶元素的next要设置为新出栈的元素
            Node tempNode = stack.pop();
            topNode.next = tempNode;
            tempNode.next = null;

            topNode = tempNode;
        }



        return first;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;


        //反转前
        node1.print();

        System.out.println();
        LeetCodeReverseLinkedList linkedList = new LeetCodeReverseLinkedList();

        Node node = linkedList.reverseNode(node1);

        //反转后
        node.print();
    }
}
