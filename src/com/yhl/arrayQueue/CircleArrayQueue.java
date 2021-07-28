package com.yhl.arrayQueue;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Scanner;

/**
 * 用数组模拟一个环形队列
 *
 * @author yhl
 * @create 2021-07-22 21:15
 */
public class CircleArrayQueue {

    private int maxSize;
    private int[] queue;//队列中只能存储maxSize - 1个数
    private int front;//指向队列的第一个元素 默认值为 0
    private int rear;//指向队列最后一个元素的后一个 默认值为 0
    //注： 队尾空出一个空间作为约定 为了避免出现队列满时计算队列总数出现 5 % 5 = 0的场景


    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
    }

    //判断队列中是否已满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加元素
    public void add(int num){
        if (isFull()){
            System.out.println("队列已满！");
        }else{
            queue[rear] = num;
            //由于是环形队列 所以要让rear取模，使得rear的范围一直处于maxSize内
            rear = (rear + 1) % maxSize;
        }
    }

    //获取元素
    public int get(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }else {
            int value = queue[front];
            //由于是环形队列，所以要对front取模限定范围
            front = (front + 1) % maxSize;
            return value;
        }
    }

    //显示所有元素
    public void show(){
        if (isEmpty()){
            System.out.println("队列为空！");
            return;
        }else {
            for (int i = front; i < front + size(); i++) {

                System.out.println("queue[" + i % maxSize + "] = " + queue[i % maxSize]);
            }
        }
    }

    //获取队列中元素的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //获取头元素
    public int head(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }else{
            return queue[front];
        }
    }
}

class CircleArrayQueueTest {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
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
                    queue.show();
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

                        System.out.println("取出的数据为：" + queue.get());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        queue.head();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }

    }

}
