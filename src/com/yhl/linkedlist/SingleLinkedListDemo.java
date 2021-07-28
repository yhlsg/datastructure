package com.yhl.linkedlist;

/**
 * 单链表的实现
 * @author yhl
 * @create 2021-07-22 22:25
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode h1 = new HeroNode(1, "林冲", "豹子头");
        HeroNode h2 = new HeroNode(2, "松江", "及时雨");
        HeroNode h3 = new HeroNode(3, "李逵", "黑旋风");
        HeroNode h4 = new HeroNode(4, "李逵", "黑旋风");

        SingleLinkedList s = new SingleLinkedList();
//        s.add(h1);
//        s.add(h2);
//        s.add(h3);

        s.addByOrder(h4);
        s.addByOrder(h1);
        s.addByOrder(h3);
        s.addByOrder(h2);
//        System.out.println("修改前：");
//        s.show();
//        s.update(new HeroNode(4, "李逵2", "黑旋风2"));
//
//        System.out.println("修改后：");

        System.out.println("删除前：");
        s.show();

        s.deleteByNo(1);
        System.out.println("删除后：");
        s.show();


    }


}

class SingleLinkedList{
    //头节点
    private HeroNode head = new HeroNode(0,"", "");

    //删除节点 (通过编号)
    public void deleteByNo(int no){

        HeroNode temp = head;//创建临时变量指向当前节点的前一个节点
        boolean isFind = false; //该编号的节点是否存在

        //遍历找到要删除节点的前一个节点
        while (true){
            if (temp.next == null){//到达末尾，没有找到对应的节点
                break;
            }else if (temp.next.no == no){//找到对应节点
                isFind = true;
                break;
            }

            temp = temp.next;//指针后移
        }

        //执行删除操作
        if (isFind){
            temp.next = temp.next.next;
        }else{
            System.out.println("链表中没有编号为"+ no + "的节点");
        }

    }

    //修改节点内的信息，通过编号
    public void update(HeroNode newHeroNode){
        HeroNode temp = head.next;//创建临时变量
        Boolean isFind = false;

        while (true){
            if (temp == null){//遍历到最后一个节点.next，没找到
                break;
            }else if (temp.no == newHeroNode.no){//找到了
                isFind = true;
                break;
            }

            temp = temp.next;//指针后移
        }

        if (isFind){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            System.out.println("链表中不存在与该节点编号相同的节点");
        }
    }

    //添加方法
    public void add(HeroNode heroNode){
        HeroNode temp = head;//创建临时变量 表示指向最后一个节点
        //遍历寻找到最后一个节点，并将最后一个节点赋值给temp
        while (true){
            //当节点的next值为null时退出
            if (temp.next == null){
                break;
            }

            temp = temp.next;
        }
        //向链表末尾添加新节点
        temp.next = heroNode;

    }

    //按照编号的顺序向列表中添加数据
    public void addByOrder(HeroNode heroNode){

        HeroNode temp = head;//一开始指向头节点的临时变量，表示要添加位置的前一个
        Boolean isFlag = false;//表示是否已经有重复的数据

        //遍历找出temp所指向的节点
        while (true){
            if (temp.next == null){//到了队列的末尾
                break;
            }else if (temp.next.no > heroNode.no){//找到temp的位置
                break;
            }else if (temp.next.no == heroNode.no){//队列中已经存在该编号
                isFlag = true;
                break;
            }

            temp = temp.next;//表示指针后移
        }

        if (isFlag){
            System.out.println("链表中已经存在" + temp.next.no +"编号了");
        }else{
            //向链表中添加数据
            heroNode.next = temp.next;
            temp.next = heroNode;
        }


    }

    //显示链表中的所有数据
    public void show(){
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }
        //如果不为空，则创建temp节点指向第一个节点
        HeroNode temp = head.next;
        while (true){

            System.out.println(temp);

            //判断是否为最后一个，如果是则退出
            if (temp.next == null){
                return;
            }

            //
            temp = temp.next;
        }
    }
}

class HeroNode{
    int no;
    String name;
    String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
