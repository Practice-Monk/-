package com.bugfe.algorithms.recursion;

/**
 * 递归
 * for循环求和1+2+3+4+...+100
 * 目标：输入n，计算1-n（1到n）的累加和
 * 方法一：循环
 * 方法二：递归
 *  sum(n) = sum(n-1) + n  分而治之
 *  退出递归的条件是sum(1) = 1
 */
public class RecursionSum {

    public static void main(String[] args) {
        System.out.println(recursionSum1(5)); // 1+2+3+4+5=15
        System.out.println(recursionSum2(100));
    }


    /**
     * 采用循环的方式进行累加
     * @param n
     * @return
     */
    public static int recursionSum1(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * 采用递归的方式进行累加
     * @param n
     * @return
     */
    public static int recursionSum2(int n) {
        if (n <= 0) {
            throw new RuntimeException("必须大于0");
        }
        if (n == 1) {
            return 1;
        }
        return recursionSum2(n - 1) + n;
    }

}
