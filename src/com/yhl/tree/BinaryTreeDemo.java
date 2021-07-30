package com.yhl.tree;

/**
 * 二叉树的实现，以及前序、中序和后序遍历的递归实现
 * @author yhl
 * @create 2021-07-30 21:25
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {

        //测试三种遍历
        /*
        前：1、2、3、4
        中：2、1、3、4
        后：2、4、3、1
         */

        //创建树
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode h1 = new HeroNode(2, "吴用");
        HeroNode h2 = new HeroNode(3, "卢俊义");
        HeroNode h3 = new HeroNode(4, "林冲");

        root.setLeft(h1);
        root.setRight(h2);
        h2.setRight(h3);

        binaryTree.setRoot(root);

        //前序遍历
//        binaryTree.preOrder();//1、2、3、4

        //中序遍历
//        binaryTree.infixOrder();//2、1、3、4

        //后序遍历
        binaryTree.postOrder();//2、4、3、1



    }
}

//树
class BinaryTree{
    //根结点
    private HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public BinaryTree() {
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        //树不为空
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("树为空！");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("树为空！");
        }
    }

    //后序遍历
    public void postOrder(){
        if (root != null){
            root.postOrder();
        }else {
            System.out.println("树为空！");
        }
    }
}

//结点
class HeroNode{
    private int no;//编号
    private String name;
    private HeroNode left;//左侧的下一个结点，默认为null
    private HeroNode right;//右侧的下一个结点，默认为null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        //输出父结点
        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }

        //输出父结点
        System.out.println(this);

        //递归向右子树中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }

    }

    //后序遍历
    public void postOrder(){
        //递归向左子树后序遍历
        if (this.left != null){
            this.left.postOrder();
        }

        //递归向右子树后序遍历
        if (this.right != null){
            this.right.postOrder();
        }

        //输出父结点
        System.out.println(this);
    }
}