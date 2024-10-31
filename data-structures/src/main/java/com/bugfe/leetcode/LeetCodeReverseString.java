package com.bugfe.leetcode;

/**
 * 反转字符串
 */
public class LeetCodeReverseString {
    /**
     * 字符串的反转，可以转变为字符数组的反转
     * @param s
     */
    public static void reverseString01(char[] s) {
        // 1、创建新数组
        char[] newChar = new char[s.length];
        // 2、倒序遍历旧数组
        for (int i = s.length - 1; i >= 0; i--) {
            newChar[s.length - 1 - i] = s[i];
        }
        // 3、遍历newChar
        for (int i = 0; i < newChar.length; i++) {
            System.out.print(newChar[i] + "\t");
        }
    }

    /**
     * 原地反转
     * @param s
     */
    public static void reverseString02(char[] s) {
        // 1、遍历
        for (int i = 0; i < s.length / 2; i++) {
            // 2、将i位置 与s.length - 1 - i位置的元素互换
            // 2，1、将i位置的值赋值给临时变量
            char temp = s[i];
            // 2.2、将i位置的值改成s.length-1-i位置的值
            s[i] = s[s.length - 1 - i];
            // 2.3、将s.length-1-i位置的值改为i位置的值
            s[s.length - 1 - i] = temp;
        }

        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + "\t");
        }
    }

    public static void main(String[] args) {
        String s = "hello world!!!";
        char[] charArray = s.toCharArray();
        reverseString01(charArray);
        System.out.println();
        reverseString02(charArray);
    }

}
