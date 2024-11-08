package com.bugfe.leetcode;

import java.util.HashMap;

public class LeetCodeTwoSum {
    /**
     * 暴力求解,两轮遍历
     * @param arr
     * @param target
     * @return
     */
    public static int[] twoSum01(int[] arr,int target){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    //找到了
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * 使用HashMap实现
     * @param arr
     * @param target
     * @return
     */
    public static int[] twoSum02(int[] arr,int target){
        //1. 创建一个hashMap, key就是arr数组中的每一个元素，值就是元素的下标
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(target - arr[i])) {
                //找到了
                return new int[]{hashMap.get(target - arr[i]),i};
            }else {
                //将arr[i]作为key，i作为value存储到HashMap中
                hashMap.put(arr[i],i);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        int[] ints = twoSum02(arr, 4);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


}
