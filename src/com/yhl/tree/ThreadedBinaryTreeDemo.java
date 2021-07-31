package com.yhl.tree;

/**
 * 线索化二叉树的实现：
 *
 * @author yhl
 * @create 2021-07-31 20:52
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        //测试一把中序线索二叉树的功能
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "smith");
        Node node4 = new Node(8, "mary");
        Node node5 = new Node(10, "king");
        Node node6 = new Node(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNodes();

        Node left = node5.getLeft();
        Node right = node5.getRight();

//        System.out.println("前驱结点为：" + left);
//        System.out.println("后继结点为：" + right);

        threadedBinaryTree.threadList();//8, 3, 10, 1, 14, 6


    }
}


//线索化二叉树，实现了中序线索化二叉树
class ThreadedBinaryTree{
    //根结点
    private Node root;

    public ThreadedBinaryTree() {
    }

    public ThreadedBinaryTree(Node root) {
        this.root = root;
    }

    //线索化二叉树的遍历
    public void threadList(){

        Node node = root;

        while (node != null){
            //遍历找到第一个leftType为1的结点，即第一个结点

            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            //输出第一个结点
            System.out.println(node);

            //如果当前结点的右指针指向的是后继结点,就一直输出，直至相对应的左子树遍历完
            while (node.getRightType() == 1){
                //向后移动，输出
                node = node.getRight();
                System.out.println(node);
            }

            //如果不指向后继结点，就向后中序遍历
            node = node.getRight();
        }

    }

    //threadedNodes方法的重载
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //中序遍历时的前一个结点
    private Node pre;

    //中序线索化二叉树
    public void threadedNodes(Node node){
        //node是否为空
        if (node == null){
            return;
        }

        //1、向左子树递归线索化
        threadedNodes(node.getLeft());
        //2、将该结点线索化
        //处理当前结点的前驱结点
        if (node.getLeft() == null){
            node.setLeft(pre);
            //修改标记
            node.setLeftType(1);
        }
        //处理前一个结点的后继结点
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }

        //将pre指针后移
        pre = node;
        //3、向右子树递归线索化
        threadedNodes(node.getRight());
    }

}

//结点
class Node{
    private int no;
    private String name;
    private Node left;
    private Node right;

    //leftType和rightType表示left和right指针的类型
    //0表示子树结点，1表示前驱和后继结点
    private int leftType;
    private int rightType;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

}