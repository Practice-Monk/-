package com.bugfe.algorithms.recursion;

/**
 * 阶乘 n!= （n-1）!* n
 * n = 1  1
 * n > 1
 * 使用递归要设置跳出递归的条件，限制次数，否则很容易出现栈溢出
 */
public class Factorial {

    /**
     * 阶乘
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n < 0) {
            throw new RuntimeException("必须大于0");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }


}
