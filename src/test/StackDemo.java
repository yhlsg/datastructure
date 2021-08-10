package test;

import java.util.Stack;

/**
 * @author yhl
 * @create 2021-08-10 22:18
 */
public class StackDemo {
    public static void main(String[] args) {
        //栈的使用
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        Integer i = stack.pop();
        System.out.println(i);
    }
}
