package com.bugfe.algorithms.recursion;

/**
 * 递归经典案例
 * 斐波那契数列： 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377......
 * n=0 fib(0),
 * n=1 或者 n=2  fib(1),
 * n > 2   fib(n-1)+fib(n-2)
 */
public class Fibonacci {

    public static int fib(int n) {
        if (n < 0) {
            throw new RuntimeException("必须大于0");
        }
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fib(8));  // fib(8) = fib(6) + fib(7) = fib(4) + fib(5) + fib(5) + fib(6)
    }


}
