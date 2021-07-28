package com.yhl.arrayQueue;

import java.util.Scanner;

/**
 * 队列 数组实现
 * @author yhl
 * @create 2021-06-11 22:22
 */
public class ArrayQueue {
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            char key = scanner.next().charAt(0);//接受一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int i = scanner.nextInt();
                    queue.add(i);
                    break;
                case 'g':
                    try {

                        System.out.println("取出的数据为：" + queue.getNumber());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        queue.getHead();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }

    }

}

class Queue{
    private int maxSize;//最大存储数量
    private int [] queue;//存储数据的数组
    private int front;//头指针 指向第一数据的前一个
    private int rear;//尾指针 指向最后一个数据

    //构造器初始化列表的各个值
    public Queue(int maxSize){
        this.maxSize = maxSize;
        queue = new int[maxSize];
        front = -1;
        rear = -1;
    }


    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //向队列中添加数据
    public void add(int num){
        if(isFull()){
            System.out.println("队列已满！");
            return;
        }

        rear++;
        queue[rear] = num;
    }

    //显示队列中的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空!");
            return;
        }
        for (int i = 0; i < queue.length; i++) {
            System.out.printf("queue[%d]=%d\n",i,queue[i]);
        }
    }

    //从队列中取出数据
    public int getNumber(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }

        front++;
        return queue[front];
    }

    //获取头数据
    public int getHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }

        return queue[front + 1];
    }
}
