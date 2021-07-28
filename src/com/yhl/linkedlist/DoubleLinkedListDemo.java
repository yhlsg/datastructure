package com.yhl.linkedlist;

/**
 * 双向链表实现
 * @author yhl
 * @create 2021-07-24 9:11
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        DNode d1 = new DNode(1, "1", "1");
        DNode d2 = new DNode(2, "2", "2");
        DNode d3 = new DNode(3, "3", "3");

        DoubleLinkedList s = new DoubleLinkedList();

        s.add(d1);
        s.add(d2);
        s.add(d3);

        s.list();
        System.out.println("********************");
        s.update(new DNode(1,"11", "11"));
        s.list();

        System.out.println("*********************");
        s.del(3);
        s.list();

        System.out.println("********************");
        DoubleLinkedList s2 = new DoubleLinkedList();

        s2.addByOrder(new DNode(6, "", ""));
        s2.addByOrder(new DNode(1, "", ""));
        s2.addByOrder(new DNode(4, "", ""));

        s2.list();
    }
}



class DoubleLinkedList{
    //头节点
    DNode head = new DNode(0, "", "");

    public DNode getHead() {
        return head;
    }

    public void setHead(DNode head) {
        this.head = head;
    }

    public DoubleLinkedList() {
    }

    //按编号顺序添加
    public void addByOrder(DNode dNode){
        //遍历链表，找出指定位置
        DNode temp = head;
        while (temp.next != null){

            //找到指定的位置，跳出循环
            if (temp.next.no > dNode.no){
                break;
            }

            temp = temp.next;
        }

        //执行添加操作
        dNode.next = temp.next;
        temp.next = dNode;
        dNode.pre = temp;

        //当不是最后一个节点时
        if (temp.next != null){
            temp.next.pre = dNode;
        }

    }

    //删除指定节点
    public void del(int no){
        //判断链表是否为空
        if (head.next == null){
            return;
        }

        //遍历链表找到指定节点
        DNode temp = head.next;
        boolean isFind = false;//记录是否找到指定节点
        while (temp != null){

            //找到跳出循环
            if (temp.no == no){
                isFind = true;
                break;
            }

            temp = temp.next;
        }

        //执行删除操作
        if (isFind){
            temp.pre.next = temp.next;

            //当节点不为最后一个，将该节点的后一个节点指向前一个
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.println("没有该编号的节点！");
        }
    }

    //修改指定节点
    public void update(DNode dNode){
        //链表为空时，退出方法
        if (head.next == null){
            return;
        }

        //循环遍历寻找该编号的节点
        DNode temp = head.next;
        boolean isFind = false;
        while (temp != null){
            //找到指定的节点跳出循环
            if (temp.no == dNode.no){
                isFind = true;
                break;
            }

            temp = temp.next;
        }

        //执行修改操作
        if (isFind){
            temp.name = dNode.name;
            temp.nickName = dNode.nickName;
        }else{
            System.out.println("没有指定编号的节点！");
        }

    }

    //显示所有节点
    public void list(){
        if (head.next == null){
            System.out.println("链表为空！");
        }

        //遍历并输出所有节点
        DNode temp = head.next;
        while (temp != null){

            //输出节点
            System.out.println(temp);

            //指针后移
            temp = temp.next;
        }
    }

    //添加方法，向末尾添加节点
    public void add(DNode dNode){
        //遍历得到链表的末尾节点
        DNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        //添加操作
        temp.next = dNode;
        dNode.pre = temp;
    }
}

class DNode{
    int no;
    String name;
    String nickName;
    DNode next;//该节点的后一个节点
    DNode pre;//该节点的前一个节点

    public DNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}