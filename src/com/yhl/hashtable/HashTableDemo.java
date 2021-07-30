package com.yhl.hashtable;

import java.util.Arrays;

/**
 * 哈希表的实现：数组 + 链表
 *
 * @author yhl
 * @create 2021-07-30 9:24
 */
public class HashTableDemo {
    public static void main(String[] args) {

        EmpHashTable empHashTable = new EmpHashTable(10);
        empHashTable.add(new Emp(1, "千反田"));
        empHashTable.add(new Emp(2, "唯"));
        empHashTable.add(new Emp(3, "薇尔莉特"));
        empHashTable.add(new Emp(11, "薇尔莉特2"));


        empHashTable.update(new Emp(11, "薇尔莉特3"));

        System.out.println(empHashTable.find(11));
        empHashTable.del(11);
//        System.out.println(empHashTable);
    }

}

//哈希表
class EmpHashTable{
    //链表数组
    private EmpLinkedList[] empLinkedLists;
    //数组的长度
    private int size;

    public EmpHashTable(int size) {
        this.size = size;
        //初始化链表数组
        empLinkedLists = new EmpLinkedList[size];
        //创建size个链表，放入数组中
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    @Override
    public String toString() {
        return "EmpHashTable{" +
                "empLinkedLists=" + Arrays.toString(empLinkedLists) +
                '}';
    }


    //查找指定id的员工
    public Emp find(int id){
        int address = address(id);

        Emp emp = empLinkedLists[address].find(id);
        return emp;

    }

    //删除指定id员工
    public boolean del(int id){
        //找到该id应当存储的链表
        EmpLinkedList empLinkedList = empLinkedLists[address(id)];
        //在链表上删除指定id的员工
        return empLinkedList.del(id);
    }

    //修改员工信息
    public boolean update(Emp emp){
        //根据员工id找到哈希表中所对应的链表
        EmpLinkedList empLinkedList = empLinkedLists[address(emp.id)];
        return empLinkedList.update(emp);
    }

    //显示所有员工
    public void list(){
        //遍历所有链表，显示所有链表中的数据
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list();
        }
    }

    //添加员工
    public void add(Emp emp){
        //先计算存储位置，并找出对应的链表
        int address = address(emp.id);
        //判断数组中该地址上的链表是否创建
        if (empLinkedLists[address] == null){
            empLinkedLists[address] = new EmpLinkedList();
        }
        //向链表中添加员工
        empLinkedLists[address].add(emp);

    }

    //根据id计算存储位置
    public int address(int id){
        return id % size;
    }
}



//单向链表
class EmpLinkedList{
    //头节点,head为第一个结点
    private Emp head;

    public EmpLinkedList() {
    }

    @Override
    public String toString() {

        //链表为空
        if (head == null){
            return null;
        }

        String res = "";
        //链表不为空
        //遍历输出
        Emp curEmp = head;
        while (true){
            String s = "{id: " + curEmp.id + ", name：" + curEmp.name + "} ";
            res += s;
            if (curEmp.next == null){
                break;
            }
            //遍历的指针后移
            curEmp = curEmp.next;
        }

        return res;
    }

    //查找指定id的员工
    public Emp find(int id){
        if (head == null){
            return null;
        }

        //链表不为空
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){
                break;
            }

            if (curEmp.next == null){
                curEmp = null;
            }

            curEmp = curEmp.next;
        }

        return curEmp;

    }

    //删除指定id的结点
    public boolean del(int id){
        //链表为空
        if (head == null){
            throw new RuntimeException("链表为空！");
        }

        //链表不为空

        //由于单链表，结点不能实现自我删除，需要找到待删除结点的前一个结点
        boolean isFind = false;
        //判断头结点head是否为待删除结点
        if (head.id == id){
            isFind = true;
            //删除头结点
            head = head.next;
            return isFind;
        }
        Emp curEmp = head;
        while (!isFind){
            //到达末尾
            if (curEmp.next == null){
                break;
            }

            //找到待删除结点的前一个
            if (curEmp.next.id == id){
                isFind = true;
                break;
            }

            curEmp = curEmp.next;
        }

        //删除操作
        if (isFind){
            curEmp.next = curEmp.next.next;
        }

        //返回是否找到
        return isFind;
    }

    /**
     * 修改(根据id)
     * @param emp 需要修改的员工
     * @return 修改成功返回true，修改失败返回false
     */
    public boolean update(Emp emp){
        //链表为空
        if (head == null){
            throw  new RuntimeException("链表为空");
        }

        //链表不为空
        Emp curEmp = head;
        //是否有该id的员工
        boolean isFind = false;
        while (true){
            //找到要修改的员工
            if (emp.id == curEmp.id){
                curEmp.name = emp.name;
                isFind = true;
                break;
            }

            //链表遍历完，没有该id的员工
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }

        return isFind;
    }

    //添加方法
    public void add(Emp emp){
        //链表为空
        if (head == null){
            head = emp;
        //链表不为空
        }else {
            //找到最后一个结点
            Emp curEmp = head;
            while (true){
                if (curEmp.next == null){
                    break;
                }

                curEmp = curEmp.next;
            }

            //将emp加到curEmp后
            curEmp.next = emp;
        }
    }

    //显示链表中的各个结点
    public void list(){
        //链表为空
        if (head == null){
            System.out.println("链表为空");
            return;
        }

        //链表不为空
        //遍历输出
        Emp curEmp = head;
        while (true){
            System.out.println("员工id: " + curEmp.id + ", name： " + curEmp.name);
            if (curEmp.next == null){
                break;
            }
            //遍历的指针后移
            curEmp = curEmp.next;
        }
    }

}

//员工
class Emp{
    public int id;//编号
    public String name;//姓名
    public Emp next;//下个节点的地址

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}