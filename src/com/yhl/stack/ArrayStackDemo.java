package com.yhl.stack;

import java.util.Scanner;

/**
 * 用数组模拟栈
 * @author yhl
 * @create 2021-07-24 16:27
 */
public class ArrayStackDemo {
    public static void main(String[] args) {

//        ArrayStack stack = new ArrayStack(3);

//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        try {
//            stack.pop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        stack.showStack();
//
//        stack.pop();

        //创建一个栈
        ArrayStack stack = new ArrayStack(4);

        Scanner scanner = new Scanner(System.in);

        //
        boolean loop = true;

        while (loop){
            System.out.println("show表示显示栈中的数据");
            System.out.println("push表示入栈");
            System.out.println("pop表示出栈");
            System.out.println("exit表示退出(请输入):");

            String s = scanner.next();

            switch (s){
                case "show":
                    stack.showStack();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int i = scanner.nextInt();
                    stack.push(i);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.println(pop);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    //关闭文件流，避免内存泄露
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入指令不正确，请重新输入！");
                    break;
            }

        }
        System.out.println("程序退出！");
    }
}

//模拟栈类
class ArrayStack{
    private int maxSize;//栈的最大容量
    private int[] stack;//存储数据的数组
    private int top = -1;//栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize -1;

    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int num){
        //入栈条件，判断栈是否已满
        if (isFull()){
            System.out.println("栈满！");
            return;
        }

        top++;
        stack[top] = num;
    }

    //出栈
    public int pop(){
        //出栈条件，判断是栈否为空
        if (isEmpty()){
            throw new RuntimeException("栈为空！");
        }

        int value = stack[top];
        top--;

        return value;
    }

    //显示栈的数据(遍历栈)
    public void showStack(){

        //栈为空时
        if (isEmpty()){
            System.out.println("栈为空，没有数据！");
            return;
        }

        //从上向下遍历数据，输出
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}

