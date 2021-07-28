package com.yhl.linkedlist;

import java.util.Stack;

/**
 * @author yhl
 * @create 2021-07-23 21:21
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.add("1");
        stack.add("2");
        stack.add("3");

        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
