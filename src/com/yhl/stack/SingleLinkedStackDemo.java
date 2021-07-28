package com.yhl.stack;

import java.util.Scanner;

/**
 * 用单向链表模拟栈：
 * 注：
 * 1、在进行栈的遍历操作时，一定要对取出的节点进行复制，遍历会使得栈中的数据发生变化不然会使得
 * @author yhl
 * @create 2021-07-24 20:33
 */
public class SingleLinkedStackDemo {
    public static void main(String[] args) {

        SingleLinkedStack stack = new SingleLinkedStack();
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
                    stack.show();
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


class SingleLinkedStack{

    //链表头
    Node head = new Node(0);

    //栈满   单向链表不存在栈满

    //栈空
    public boolean isEmpty(){
        return (head.next == null) ? true : false;
    }

    //入栈(不存在满栈）
    public void push(int i){

        //遍历到链表尾部，得到最后一个节点
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        Node node = new Node(i);
        //入栈操作
        temp.next = node;
    }
    //出栈
    //找到最后一个节点的前一个节点，将最后一个节点输出并删除
    public int pop(){
        //判断是否为空
        if (isEmpty()){
            throw new RuntimeException("栈为空！");
        }

        //找到最后一个节点
        Node temp = head;
        while (true){
            //找到最后一个节点时
            if (temp.next.next == null){
                break;
            }
            temp = temp.next;
        }

        //删除最后一个节点
        int value = temp.next.number;
        temp.next = null;

        return value;
    }
    //遍历栈
    //反向输出单项链表
    public void show(){

        //判断栈栈中是否含有数据
        if (isEmpty()){
            System.out.println("栈中没有数据！");
            return;
        }

        //创建一个新的链表头
        Node newHead = new Node(0);

        //遍历取出链表的每个节点，并将其加入新的链表中去
        Node temp = head.next;
        Node cur = null;
        while (true){
            if (temp == null){
                break;
            }
            //由于要对temp进行修改，所以要事先保存下一个要遍历的节点
            cur = temp.next;

            //复制节点，并将新节点中添加到新链表的头部
            Node node = new Node(temp.number);
            node.next = newHead.next;
            newHead.next = node;

            //指针后移
            temp = cur;
        }

        //遍历新得到的单向链表即可
        temp = newHead.next;
        while (temp != null){
            System.out.println(temp.number);
            temp = temp.next;
        }

    }

}

class Node{
    int number;//存储在栈中的数据
    Node next;//指向下一个节点的指针

    public Node(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                '}';
    }
}