package com.yhl.arrayQueue;

/**
 * @author yhl
 * @create 2021-07-22 19:41
 */
public class ArrayQueue2 {

    private  int maxSize;
    private int[] queue;
    private int front;
    private int rear;

    public ArrayQueue2(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        front = -1;//指向队列第一个元素的前一个
        rear = -1;//指向队列的最后一个元素
    }

    /**
     * 添加操作
     * @param num
     */
    public void addNum(int num){
        if (rear != maxSize - 1){
            queue[rear + 1] = num;
            rear++;
        }
    }

    /**
     * 删除方法
     * 删除成功返回 1
     * 失败返回 0
     */
    public int deleteNum(){
        if (front != rear){
            front++;
            return 1;
        }

        return 0;
    }

    //遍历列表元素
    public void queueToString(){
        if (front != rear){
            for (int i = front + 1; i < rear + 1; i++) {
                System.out.println(queue[i]);
            }
        }
    }

    //获取队列长度
    public int getQueueLength(){
        return rear - front;
    }
}


class ArrayQueue2Test{
    public static void main(String[] args) {
        ArrayQueue2 queue = new ArrayQueue2(10);

        queue.addNum(1);
        queue.addNum(2);
        queue.addNum(3);
        queue.addNum(4);

        queue.deleteNum();
        queue.addNum(5);
        queue.deleteNum();
        queue.deleteNum();

        int len = queue.getQueueLength();
        System.out.println("数组长度为：" + len);

        queue.queueToString();
    }
}
