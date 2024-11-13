package com.bugfe.datastructure.tree;

/**
 * 1. 定义一个Node类，表示二叉搜索树上的一个节点
 * 2. 二叉搜索树必须记录一个根节点
 * 3. 二叉搜索树记录元素个数
 * 4. 二叉搜索树要实现哪些功能?
 *    4.1 获取树的元素个数
 *    4.2 判断树是否为空
 *    4.3 往树中添加元素
 */
public class MyBinarySearchTree<E extends Comparable<E>> {
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
        //1. 判断树是否为空
        if (this.root == null) {
            //2. 如果树为空，则将元素添加到根节点
            this.root = new TreeNode<>(e);
             size ++;
        }else {
            //3. 如果树不为空
            add(this.root,e);
        }
    }

    private void add(TreeNode<E> node, E e) {
        //1. 判断e与node的值的大小
        if (e.compareTo(node.item) >= 0) {
            //1.1 e大于等于node的值，所以将e添加到node的右边
            //1.1.1 如果node.right不为null，则继续递归
            if (node.right != null) {
                add(node.right,e);
            }else {
                node.right = new TreeNode<>(e);
                size ++;
            }
        }else {
            //1.2 e小于no的的值，则将e添加到node的左边
            if (node.left != null) {
                add(node.left,e);
            }else {
                node.left = new TreeNode<>(e);
                size ++;
            }
        }
    }


    public static void main(String[] args) {
        MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<>();

        bst.add(5);
        bst.add(4);
        bst.add(9);
        bst.add(6);
        bst.add(2);
        bst.add(3);
        bst.add(1);

        System.out.println(bst.getSize());
    }
}
