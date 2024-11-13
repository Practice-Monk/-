package com.bugfe.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 1. 定义一个Node类，表示二叉搜索树上的一个节点
 * 2. 二叉搜索树必须记录一个根节点
 * 3. 二叉搜索树记录元素个数
 * 4. 二叉搜索树要实现哪些功能?
 *    4.1 获取树的元素个数
 *    4.2 判断树是否为空
 *    4.3 往树中添加元素
 *    4.4 遍历打印树的元素
 *      4.4.1 前序遍历: 根、左、右
 *      4.4.2 中序遍历: 左、根、右
 *      4.4.3 后序遍历: 左、右、根
 */
public class MyBinarySearchTree2<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;
    private static class TreeNode<E>{
        E item;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E item) {
            this(item,null,null);
        }

        public TreeNode(E item, TreeNode<E> left, TreeNode<E> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 获取元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 添加元素
     * @param e
     */
    public void add(E e){
        this.root = add(this.root,e);
    }

    /**
     * 添加节点
     * @param node
     * @param e
     * @return
     */
    private TreeNode<E> add(TreeNode<E> node, E e) {
        //1. 如果传入的节点为空，则创建新节点返回
        if (node == null) {
            TreeNode<E> newNode = new TreeNode<>(e);
            size ++;
            return newNode;
        }

        //2. 如果传入的节点不为空,则比较e与node元素的大小
        if (e.compareTo(node.item) >= 0) {
            //2.1 e大于等于node元素，则将e添加到node的右边
            node.right = add(node.right,e);
        }else {
            //2.2 e小于node元素，则将e添加到node的左边
            node.left = add(node.left,e);
        }

        return node;
    }

    /**
     * 前序打印
     */
    public void printPrevOrd(){
        printPrevOrd(this.root);
    }

    /**
     * 前序打印，不使用递归
     */
    public void printPreOrdWithoutRecursion(){
        //1. 判断树是否为空
        if (isEmpty()) {
            return;
        }
        //2. 创建一个栈
        Stack<TreeNode<E>> stack = new Stack<>();
        //3. 将根节点入栈
        stack.push(this.root);
        //4. 判断，只要stack不为空，则一直循环
        while (!stack.isEmpty()){
            //5. 出栈
            TreeNode<E> topNode = stack.pop();
            //输出topNode的值
            System.out.print(topNode.item + "\t");
            //5.1 判断topNode的右子节点是否为空，如果不为空，则入栈
            if (topNode.right != null) {
                stack.push(topNode.right);
            }
            //5.2 判断topNode的左子节点是否为空，如果不为空，则入栈
            if (topNode.left != null) {
                stack.push(topNode.left);
            }
        }
    }

    /**
     * 中序打印
     */
    public void printMidOrd(){
        printMidOrd(this.root);
    }

    /**
     * 中序打印，不使用递归
     */
    public void printMidOdWithoutRecursion(){
        //1. 判断树是否为空
        if (isEmpty()) {
            return;
        }
        //2. 创建一个栈
        Stack<TreeNode<E>> stack = new Stack<>();
        //3. 将根节点入栈
        TreeNode<E> currentNode = this.root;
        while (!stack.isEmpty() || currentNode != null){
            while (currentNode != null){
                //将currentNode放入栈中
                stack.push(currentNode);
                //将当前节点的左节点赋给当前节点
                currentNode = currentNode.left;
            }

            //出栈
            TreeNode<E> topNode = stack.pop();
            //输出栈顶元素的值
            System.out.print(topNode.item + "\t");
            //将topNode的右子节点进行入栈
            if (topNode.right != null) {
                currentNode = topNode.right;
            }
        }
    }

    /**
     * 后序打印
     */
    public void printPostOrd(){
        printPostOrd(this.root);
    }

    /**
     * 广度优先遍历
     */
    public void printLevelOrder(){
        //1. 判断树是否为空
        if (isEmpty()) {
            return;
        }
        //2. 创建一个队列
        Queue<TreeNode<E>> queue = new LinkedList<>();
        //3. 将根节点入队
        queue.add(this.root);
        //4. 判断queue如果不为空，则进行出队
        while (!queue.isEmpty()) {
            //出队
            TreeNode<E> node = queue.remove();
            //打印node的值
            System.out.print(node.item + "\t");
            //将node的左子节点入队
            if (node.left != null) {
                queue.add(node.left);
            }
            //将node的右子节点入队
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * 后续打印，不使用递归
     */
    public void printPostOrdWithoutRecursion(){
        //1. 判断树是否为空
        if (isEmpty()) {
            return;
        }
        //2. 创建两个栈
        Stack<TreeNode<E>> stack = new Stack<>();
        Stack<TreeNode<E>> tempStack = new Stack<>();
        //3. 将root添加stack
        stack.push(this.root);
        //4. stack如果不为空，则出栈
        while (!stack.isEmpty()) {
            //出栈
            TreeNode<E> topNode = stack.pop();
            //将topNode入临时栈
            tempStack.push(topNode);
            //将topNode的左子节点入栈
            if (topNode.left != null) {
                stack.push(topNode.left);
            }
            //将topNode的右子节点入栈
            if (topNode.right != null) {
                stack.push(topNode.right);
            }
        }

        //遍历tempStack
        while (!tempStack.isEmpty()) {
            //出栈
            System.out.print(tempStack.pop().item + "\t");
        }
    }

    /**
     * 后序打印
     * @param node
     */
    private void printPostOrd(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        //1. 输出node的左子树
        printPostOrd(node.left);
        //2. 输出node的右子树
        printPostOrd(node.right);
        //3. 输出node的值
        System.out.print(node.item + "\t");
    }

    /**
     * 中序打印
     * @param node
     */
    private void printMidOrd(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        //1. 先输出node的左子树
        printMidOrd(node.left);
        //2. 再输出node
        System.out.print(node.item + "\t");
        //3. 再输出node的右子树
        printMidOrd(node.right);
    }

    /**
     * 前序打印
     * @param node
     */
    private void printPrevOrd(TreeNode<E> node) {
        //1. 判断node是否为空
        if (node == null) {
            return;
        }
        //2. 先打印node的值
        System.out.print(node.item + "\t");
        //3. 打印node的左子树
        printPrevOrd(node.left);
        //4. 打印node的右子树
        printPrevOrd(node.right);
    }

    /**
     * 查找二叉树中存储的最小的元素
     * @return
     */
    public E findMinItem(){
        return findMinTreeNode(this.root).item;
    }

    /**
     * 查找二叉树中存储的最大元素
     * @return
     */
    public E findMaxItem(){
        return findMaxTreeNode(this.root).item;
    }

    /**
     * 查找二叉树中最大元素的节点
     * @param node
     * @return
     */
    private TreeNode<E> findMaxTreeNode(TreeNode<E> node) {
        //1. 判断node是否有右子节点，如果没有则说明node是最大节点
        if (node.right == null) {
            return node;
        }
        //继续往右找
        return findMaxTreeNode(node.right);
    }

    /**
     * 查询二叉树中元素最小的那个节点
     * @return
     */
    private TreeNode<E> findMinTreeNode(TreeNode<E> node){
        //1. 判断node是否有左子节点，如果没有，则说明node最小
        if (node.left == null) {
            return node;
        }

        //继续往左找
        return findMinTreeNode(node.left);
    }


    /**
     * 删除最小元素
     * @return
     */
    public E removeMinItem(){
        //1. 获取要删除的最小元素
        E minItem = findMinItem();
        //2.
        this.root = removeMinItem(this.root);
        return minItem;
    }

    /**
     * 删除最小元素
     * @param node
     * @return
     */
    private TreeNode<E> removeMinItem(TreeNode<E> node) {
        //1. 判断node是否有左子节点
        //1.1 如果node有左子节点
        if (node.left != null) {
            //递归
            node.left = removeMinItem(node.left);
        }else {
            //如果node，没有左子节点了,则说明node是最小的节点
            //获取node的右子节点,作为后继节点
            TreeNode<E> successorNode = node.right;

            //维护size
            size --;
            return successorNode;
        }
        return node;
    }

    /**
     * 删除最大的元素
     * @return
     */
    public E removeMaxItem(){
        //1. 查找最大的元素
        E maxItem = findMaxItem();
        this.root = removeMaxItem(this.root);
        return maxItem;
    }

    /**
     * 删除最大的节点
     * @param node
     * @return
     */
    private TreeNode<E> removeMaxItem(TreeNode<E> node) {
        //1. 判断node是否有右子节点
        if (node.right != null) {
            node.right = removeMaxItem(node.right);
        }else {
            //2. 没有右子节点
            TreeNode<E> successorNode = node.left;

            //维护size
            size --;
            return successorNode;
        }
        return node;
    }

    /**
     * 删除指定节点
     * @param e
     */
    public void remove(E e){
        //1. 判断二叉树如果为空，则不能删除
        if (isEmpty()) {
            throw new RuntimeException("二叉树不能为空");
        }
        this.root = remove(this.root,e);
    }

    /**
     * 以node为基点，删除e
     * @param node
     * @param e
     * @return
     */
    private TreeNode<E> remove(TreeNode<E> node, E e) {
        //1. 先找到e元素所在的节点
        TreeNode<E> targetNode = findNode(node, e);
        //2. 找到后继节点:
        TreeNode<E> successorNode = null;
        if (targetNode.left != null) {
            //如果左边有元素，则在它的左边找最大的节点 作为后继节点
            successorNode = findMaxTreeNode(targetNode.left);
            //找到后继节点了
            //3. 将要删除的那个节点的值，替换成后继节点的值
            targetNode.item = successorNode.item;
            //4. 在targetNode的左边删除后继节点
            //删除targetNode左边的最大元素
            targetNode.left = removeMaxItem(targetNode.left);
        } else if (targetNode.right != null) {
            //5. 如果左边没有元素，则右边的节点就是后继节点
            successorNode = findMinTreeNode(targetNode.right);
            //找到后继节点
            //将要删除的那个节点的值，替换成后继节点的值
            targetNode.item = successorNode.item;
            //5. 在target的右边删除最小的元素
            targetNode.right = removeMinItem(targetNode.right);
        }else {
            //如果左右两边都没有元素，要删除target
            return null;
        }
        return node;
    }

    /**
     * 查找e所在的节点
     * @param node
     * @param e
     * @return
     */
    private TreeNode<E> findNode(TreeNode<E> node, E e) {
        if (node == null) {
            throw new RuntimeException(e + "在二叉树中不存在");
        }
        if (e.compareTo(node.item) == 0) {
            return node;
        } else if (e.compareTo(node.item) < 0) {
            //说明e小于node的值，所以往左找
            return findNode(node.left,e);
        }else {
            //说明e大于node的值，所以往右找
            return findNode(node.right,e);
        }
    }

    public static void main(String[] args) {
        MyBinarySearchTree2<Integer> bst = new MyBinarySearchTree2<>();

        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(5);
        bst.add(15);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(6);
        bst.add(12);
        bst.add(17);
        bst.add(11);
        bst.add(16);
        bst.add(18);
        bst.add(36);
        bst.add(33);
        bst.add(39);

        //System.out.println(bst.getSize());

        //bst.printPrevOrd();
        //bst.printPreOrdWithoutRecursion();

        //bst.printMidOrd();
        //bst.printMidOdWithoutRecursion();

        //bst.printPostOrd();
        //bst.printPostOrdWithoutRecursion();

        //bst.printLevelOrder();

        //System.out.println(bst.findMinItem());

        //删除最小的节点
        //System.out.println(bst.removeMinItem());
        //System.out.println(bst.removeMinItem());
        //System.out.println(bst.removeMinItem());

        //查找最小节点
        //System.out.println(bst.findMinItem());



        //查找最大节点
        //System.out.println(bst.findMaxItem());

        //System.out.println(bst.removeMaxItem());
        //System.out.println(bst.removeMaxItem());
        //System.out.println(bst.removeMaxItem());

        bst.remove(30);

        bst.printLevelOrder();
    }
}
