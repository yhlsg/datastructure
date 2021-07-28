package com.yhl.linkedlist;

/**
 * 约瑟夫问题的解决：
 * 使用环形链表的实现
 * @author yhl
 * @create 2021-07-24 14:37
 */
public class CircleLinkedListDemo {
    public static void main(String[] args) {

        CircleLinkedList cl = new CircleLinkedList();

        cl.add(5);

        cl.list();

        System.out.println("**************************");

        cl.countBoy(1, 2, 5);
    }
}


//环形链表
class CircleLinkedList{
    //指向第一节点的指针
    Boy first = null;


    /**
     * 约瑟夫问题的解决
     *
     * @param startNo 表示开始的编号
     * @param countNum 表示要数几下(相对应的移动少一下)
     * @param num 表示节点个数
     */
    public void countBoy(int startNo,int countNum, int num){

        //条件检测
        if (countNum <= 0 || startNo > num){
            System.out.println("传入的数据不合法！");
            return;
        }

        //由于是单向循环链表，不能自身删除，需要辅助指针helper指向first节点的前一个
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        //将first和helper移动到指定的节点startNo处
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //遍历环形链表开始出圈操作
        while (true){
            //当helper和first相等时，跳出循环
            if (helper == first){
                break;
            }

            //寻找需要出圈的节点
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.println(first.getNo() + "号学生出圈");

            //执行出圈操作
            first = first.getNext();//先将first指针后移
            helper.setNext(first);//再将helper的next指向first即可
        }

        System.out.println("最后一个学生是：" + first.getNo() + "号");



    }

    //向链表添加节点
    public void add(int num){

        //num小于1时，退出方法
        if (num < 1){
            return;
        }

        //指向当前遍历的节点
        Boy curBoy = null;

        //遍历num个数向链表中添加
        for (int i = 1; i <= num; i++) {

            Boy boy = new Boy(i);
            //第一个创建时
            if (first == null){
                first = boy;
                boy.setNext(boy);
                curBoy = boy;
            }else{
                //将新创建的节点加入到环形链表中，并将curBoy指针后移
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }


        }

    }

    //遍历显示环形链表
    public void list(){
        //链表为空
        if (first == null){
            return;
        }

        //辅助节点，表示当前节点
        Boy curBoy = first;
        while (true){

            System.out.println("小孩的编号是：" + curBoy.getNo());

            //当当前节点的下一个节点为first时，退出循环
            if (curBoy.getNext() == first){
                break;
            }

            //指针后移
            curBoy = curBoy.getNext();
        }
    }
}

//节点类
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点的指针

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
