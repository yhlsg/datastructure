package com.yhl.linkedlist;


import java.util.Stack;

/**
 * @author yhl
 * @create 2021-07-23 11:16
 */
public class SingleLinkedListDemo2 {
    public static void main(String[] args) {

        Node n1 = new Node(1, "1", "1");
        Node n2 = new Node(4, "2", "2");
        Node n3 = new Node(7, "3", "3");

        SingleLinkedList2 s = new SingleLinkedList2();

        s.add(n1);
        s.add(n2);
        s.add(n3);

        Node n4 = new Node(3, "4", "4");
        Node n5 = new Node(5, "5", "5");
        Node n6 = new Node(6, "6", "6");

        SingleLinkedList2 s2 = new SingleLinkedList2();

        s2.add(n4);
        s2.add(n5);
        s2.add(n6);

//        s.show();

//        System.out.println();

//        s.del(1);
//        s.show();

//        int length = getLinkedListLength(s.getHead());
//        System.out.println(length);
//
//        Node lastHeroNode = getLastHeroNode(s.getHead(), 1);
//        System.out.println( lastHeroNode);

//        System.out.println("反转前：");
//        s.show();
//
//        reverseList(s.getHead());
//
//        System.out.println("反转后：" );
//        s.show();

//        reversePrint(s.getHead());

        Node newNode = unionList(s.getHead(), s2.getHead());

        SingleLinkedList2 s3 = new SingleLinkedList2();
        s3.setHead(newNode);

        s3.show();

    }

    /**
     * 面试题5：
     * 合并两个由于链表，要求合并后依然有序
     * 步骤：
     * 1、创建一个链表N=newNode，指向一个链表
     * 2、将遍历另一个链表的每个节点，将其按顺序插入到newNode中即可
     *
     */
    public static Node unionList(Node head1, Node head2){

        //如果链表1为空
        if (head1.next == null){
            return head2;
        }

        //如果链表2为空
        if(head2.next == null){
            return head1;
        }

        //当链表1和链表2非空时
        Node newHead = new Node(0, "", "");
        newHead.next = head1.next;

        //遍历链表2，并将所有节点按顺序加入到newNode链表中
        //辅助变量
        Node cur = head2.next;
        //由于要修改cur值，所以用next先表示cur后一个节点
        Node next = null;
        while (cur != null){

            //记录后一个节点
            next = cur.next;

            //将cur放入到newHead链表中
            Node temp = newHead;
            //遍历链表找到要插入的位置
            while (temp.next != null){
                if (temp.next.no >= cur.no){
                    break;
                }
                temp = temp.next;
            }

            //执行插入操作
            cur.next = temp.next;
            temp.next = cur;

            //指针后移
            cur = next;
        }

        return newHead;


    }

    /**
     * 百度面试题：
     * 从头到尾打印单链表
     * 方法一：
     * 先将链表反转，然后在遍历输出
     * 方法二：
     * 使用栈，先将节点按照顺序压入栈中，然后一个个弹出即可
     */
    public static void reversePrint(Node head){
        //空链表
        if (head.next == null ){
            return;
        }
        //只有一个节点
        if (head.next.next == null){
            System.out.println(head.next);
            return;
        }

        //链表中有多个节点时
        Stack<Node> stack = new Stack<>();

        //辅助节点
        Node cur = head.next;
        //遍历所有节点，并将其压入栈中
        while (cur != null){
            //将节点压入栈中
            stack.push(cur);

            cur = cur.next;
        }

        //将stack中的所有节点出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
    /**
     * 腾讯面试题：
     * 单链表的反转
     * 步骤：
     * 1、创建一个新的链表头
     * 2、遍历要反转的链表，将节点一个个加入到新链表的头部
     *
     */
    public static void reverseList(Node head){

        //如果链表为空或者节点的个数为1，则不需要倒转，跳出方法
        if (head.next == null || head.next.next == null){
            return;
        }

        //新表头
        Node reverseHead = new Node(0, "", "");
        //创建辅助指针
        Node cur = head.next;
        //创建cur指针的下一个节点的指针
        Node next = null;

        //遍历原来的链表
        while (true){
            if (cur == null){//链表遍历结束
                break;
            }

            //由于要修改cur中的next值，所以在操作cur之前先将cur的指向的后一个节点保存到next中
            next = cur.next;
            //将取出的节点加入新链表的表头
            cur.next = reverseHead.next;
            reverseHead.next = cur;

            //cur指针后移
            cur = next;
        }

        //将原先链表的表头指向新链表的表头
        head.next = reverseHead.next;

    }

    /**
     * 新浪面试题：
     * 查找单链表中的倒数第n个节点
     *步骤：
     * 1、先用 getLinkedListLength 方法获取链表的长度 size
     * 2、再用 size - index 获取要遍历的个数
     * 3、从头节点遍历到相对应的位置处
     */
    public static Node getLastHeroNode(Node head, int index){
        //获取链表长度
        int size = getLinkedListLength(head);

        //获取要遍历的个数
        int len = size - index;

        //链表为空
        if (head.next == null){
            return null;
        }

        //链表不为空时，创建辅助节点
        Node temp = head.next;

        //遍历到指定的位置
        int i = 0;
        while (i < len){

            i++;

            temp = temp.next;
        }

        return temp;

    }


    /**
     * 获取链表中的节点个数(不包括头节点)
     * @param head 链表的头节点
     * @return 链表节点的个数
     */
    public static int getLinkedListLength(Node head){
        //创建辅助节点
        Node temp = head.next;

        //记录链表中节点的个数
        int len = 0;
        while (true){
            if (temp == null){//链表遍历结束，跳出循环
             break;
            }

            len++;
            //temp后移
            temp = temp.next;
        }


        return len;
    }
}


class SingleLinkedList2{
    //创建一个头节点
    private Node head = new Node(0, "", "");

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    //删除节点
    public void del(int no){
        //创建临时变量，用于遍历
        Node temp = head;
        boolean isFind = false;

        //循环遍历，找要删除节点的前一个节点
        while (true){
            if (temp.next == null){//遍历结束，没有找到该编号的节点
                break;
            }else if (temp.next.no == no){//找到该节点
                isFind = true;
                break;
            }
            temp = temp.next;//temp后移
        }

        //删除操作
        if (isFind){
            temp.next = temp.next.next;
        }else {
            System.out.println("链表中没有指定编号的节点");
        }
    }

    //修改节点中的数据
    public void update(Node newNode){

        //创建一个临时变量
        Node temp  = head.next;
        boolean isFind = false;//判断是否已经找到该节点

        //遍历寻找该节点
        while (true){
            if (temp == null){//遍历结束，没有找到跳出循环
                break;
            }else if (temp.no == newNode.no){//找到，跳出循环
                isFind = true;
                break;
            }

            temp = temp.next;
        }

        //修改该节点
        if (isFind){
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        }else {
            System.out.println("没有该编号的节点");
        }

    }

    //显示所有数据
    public void show(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }

        //创建临时变量
        Node temp = head.next;

        //遍历并显示所有节点数据
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);

            temp = temp.next;//temp后移
        }
    }

    //添加方法(末尾添加)
    public void add(Node node){
        //创建临时变量
        Node temp = head;

        //遍历找到最后一个节点
        while (true){
            if (temp.next == null){//到temp等于末尾时,跳出循环
                break;
            }

            temp = temp.next;
        }

        //在末尾上添加新节点
        temp.next = node;
    }

    //按编号顺序添加
    public void addByOrder(Node node){
        //创建一个临时变量
        Node temp  = head;
        boolean isAlive = false;//判断节点编号是否已经存在

        //遍历找出要添加位置的前一个节点
        while (true){
            if (temp.next == null){//链表到达末尾，跳出循环
                break;
            }else if (temp.next.no > node.no){//找到所处的位置
                break;
            }else if (temp.next.no == node.no){//判断节点编号是否已经存在
                isAlive = true;
                break;
            }

            temp = temp.next;//temp后移
        }

        if (isAlive){
            System.out.println("该编号节点已经存在！");
        }else{
            //实行添加操作
            node.next = temp.next;
            temp.next = node;
        }
    }
}



//定义节点类
class Node{
    int no;
    String name;
    String nickName;
    Node next;

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}