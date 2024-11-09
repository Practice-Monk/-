package com.bugfe.map;


public class MyHashMap<K,V> implements IMap<K,V> {
    /**
     * 哈希表
     */
    private Node<K,V>[] elementData;

    static final Integer DEFAULT_CAPACITY = 1 << 4;

    static final Double DEFAULT_LOAD_FACTOR = 0.75;

    static final Integer MAX_CAPACITY = Integer.MAX_VALUE - 8;

    int size;
    public MyHashMap() {
        elementData = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;

        public Node(K key, V value) {
            this(key,value,null);
        }

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    @Override
    public void put(K key, V value) {
        //判断数组满了（负载达到了负载因子的大小）
        if (size >= elementData.length * DEFAULT_LOAD_FACTOR) {
            //扩容
            resize(2 * elementData.length);
        }
        //0. 创建Node
        Node<K,V> node = new Node<>(key,value);
        //1. 计算key的哈希值
        int hashCode = hash(key);
        //2. 计算key在数组中的下标:hashCode & (数组长度 - 1)
        int index = hashCode & (elementData.length - 1);
        //3. 将key添加到下标位置的链表中
        //3.1 判断当前下标位置是否为null
        Node<K, V> first = elementData[index];
        if (first == null) {
            //3.2 如果当前下标位置还未添加过元素，则将当前Node设置到index位置
            elementData[index] = node;
            size ++;
        }else {
            //3.3 如果当前下标位置已经添加了元素，则先判断链表中是否存在当前key，如果存在则替换
            Node<K,V> existNode = findNode(first,key);
            if (existNode != null) {
                existNode.value = value;
            }else {
                //3.4 key不存在，那么将node添加到链表的头部
                //3.4.1 node的next属性指向first
                node.next = first;
                //3.4.2 将node设置到数组的index位置
                elementData[index] = node;
                size ++;
            }
        }
    }

    /**
     * 改变容量
     * @param newSize
     */
    private void resize(int newSize) {
        if (newSize >= MAX_CAPACITY) {
            newSize = MAX_CAPACITY;
        }
        //1. 创建一个新数组
        Node<K,V>[] newElementData = new Node[newSize];
        //2. 将旧数组中的元素全部重新进行hash存储到新数组中
        for (Node<K, V> kvNode : elementData) {
            //2.1 遍历出旧数组中的每一个元素
            if (kvNode != null) {
                //从头到尾遍历节点
                Node<K,V> curNode = kvNode;
                while (curNode != null) {
                    //将curNode放到新数组中
                    int index = hash(curNode.key) & (newSize - 1);
                    Node<K, V> first = newElementData[index];
                    Node<K, V> node = new Node<>(curNode.key, curNode.value);
                    if (first == null) {
                        newElementData[index] = node;
                    }else {
                        //判断key是否存在
                        Node<K, V> existNode = findNode(first, curNode.key);
                        if (existNode != null) {
                            existNode.value = curNode.value;
                        }else {
                            //不存在，则添加到头节点
                            node.next = first;
                            newElementData[index] = node;
                        }
                    }

                    //继续往下找
                    curNode = curNode.next;
                }
            }
        }

        //3. 用新数组替换旧数组
        elementData = newElementData;
    }

    /**
     * 根据key找Node
     * @param node
     * @param key
     * @return
     */
    private Node<K, V> findNode(Node<K, V> node, K key) {
        while (node != null) {
            if (node.key.equals(key)) {
                //找到了
                return node;
            }
            //继续往下找
            node = node.next;
        }
        return null;
    }

    /**
     * 计算哈希值
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Override
    public V get(K key) {
        //1. 对key进行哈希运算,然后计算出下标
        int index = hash(key) & (elementData.length - 1);
        //2. 获取下标处的元素
        Node<K, V> kvNode = elementData[index];
        //3. 遍历
        Node<K,V> curNode = kvNode;
        while (curNode != null){
            if (curNode.key.equals(key)) {
                return curNode.value;
            }

            curNode = curNode.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        //1. 对key进行哈希运算,然后计算出下标
        int index = hash(key) & (elementData.length - 1);
        //2. 获取下标处的元素
        Node<K, V> kvNode = elementData[index];
        //3. 先判断要删除的元素，是否是头节点
        V value = null;
        if (kvNode.key.equals(key)) {
            value = kvNode.value;
            elementData[index] = kvNode.next;

            size --;
        } else {
            //要删除的不是偷节点
            Node<K, V> prevNode = kvNode;
            Node<K, V> curNode = prevNode.next;
            while (curNode != null) {
                if (curNode.key.equals(key)) {
                    value = curNode.value;
                    //找到了
                    prevNode.next = curNode.next;
                    curNode.next = null;

                    size --;
                }

                //继续往下找
                prevNode = prevNode.next;
                curNode = prevNode.next;
            }
        }


        return value;
    }

    @Override
    public boolean containsKey(K key) {
        //1. 计算下标
        int index = hash(key) & (elementData.length - 1);
        //2. 获取下标处的元素
        Node<K, V> kvNode = elementData[index];
        return findNode(kvNode,key) != null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        //遍历数组
        for (Node<K, V> kvNode : elementData) {
            //判断kvNode是否是空，不是空才答应
            if (kvNode != null) {
                //遍历kvNode
                Node<K,V> curNode = kvNode;
                while (curNode != null) {
                    K key = curNode.key;
                    stringBuilder.append(key);
                    stringBuilder.append("=");
                    V value = curNode.value;
                    stringBuilder.append(value);
                    stringBuilder.append(",");
                    curNode = curNode.next;
                }
            }
        }

        String str = stringBuilder.append("}").toString();
        //去掉最后一个 ,
        str = str.substring(0,str.lastIndexOf(",")) + str.substring(str.lastIndexOf(",") + 1);

        System.out.println(str);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {
        IMap<String,String> iMap = new MyHashMap<>();
        iMap.put("k1","v1");
        iMap.put("k2","v2");
        iMap.put("k3","v3");
        iMap.put("k4","v4");
        iMap.put("k1","v11");
        iMap.put("k5","v11");
        iMap.put("k6","v11");
        iMap.put("k7","v11");
        iMap.put("k8","v11");
        iMap.put("k9","v11");
        iMap.put("k10","v11");
        iMap.put("k11","v11");
        iMap.put("k12","v11");
        iMap.put("k13","v11");
        iMap.put("k14","v11");
        iMap.put("k15","v11");
        iMap.put("k16","v11");
        iMap.put("k17","v11");
        iMap.put("k18","v11");

        iMap.print();

        System.out.println(iMap.containsKey("k1"));

        System.out.println(iMap.get("k1"));

        System.out.println(iMap.remove("k1"));
        iMap.print();
        System.out.println(iMap.getSize());
    }
}
