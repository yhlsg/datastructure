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
        HeroNode h4 = new HeroNode(5, "关胜");

        root.setLeft(h1);
        root.setRight(h2);
        h2.setRight(h3);
        h2.setLeft(h4);

        binaryTree.setRoot(root);

        //前序遍历
//        binaryTree.preOrder();//1、2、3、4

        //中序遍历
//        binaryTree.infixOrder();//2、1、3、4

        //后序遍历
//        binaryTree.postOrder();//2、4、3、1

        //比较次数
//        HeroNode result = binaryTree.preOrderSearch(5);//4
//        HeroNode result = binaryTree.infixOrderSearch(5);//3
//        HeroNode result = binaryTree.postOrderSearch(5);//2
//        System.out.println(result);

        //删除前
        System.out.println("删除前：");
        binaryTree.preOrder();//1、2、3、5、4

        binaryTree.delNode(5);

        //删除后
        System.out.println("删除后：");
        binaryTree.preOrder();//1、2、3、4


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

    //删除结点
    public void delNode(int no){
        //当前二叉树为空
        if(root != null){
            //root结点是否为待删除结点
            if (root.getNo() == no){
                root = null;
                return;
            //递归删除结点
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("二叉树为空！");
        }

    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        //如果树不为空
        if (root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
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

    //删除结点
    public void delNode(int no){
        //由于为单向链表，所以要找到待删除结点的前一个
        //判断左子结点是否为为待删除结点，如果是将其删除
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }

        //判断右结点是否为待删除结点
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }

        //如果左子结点不为空，向左递归删除结点
        if (this.left != null){
            this.left.delNode(no);
        }

        //如果右子结点不空，向右递归删除结点
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    /**
     * 前序查找
     * @param no 待查找的员工的编号
     * @return 如果找到，返回该员工，没有找到返回null
     */
    public HeroNode preOrderSearch(int no){
//        System.out.println("前序查找");
        HeroNode res = null;
        //当前结点是否为目标值
        if (this.no == no){
            return this;
        }

        //向左递归前序查找
        if (this.left != null){
            res = this.left.preOrderSearch(no);
        }
        //判断向左递归是否找到
        if(res != null){
            return res;
        }

        //向右递归前序查找
        if (this.right != null){
            res = this.right.preOrderSearch(no);
        }

        return res;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no){
        HeroNode res = null;
        //向左递归中序查找
        if (this.left != null){
            res = this.left.infixOrderSearch(no);
        }
        if (res != null){
            return res;
        }

//        System.out.println("中序查找");
        //判断当前结点是否为目标结点
        if (this.no == no){
            return this;
        }

        //向右递归中序查找
        if (this.right != null){
            res = this.right.infixOrderSearch(no);
        }
        return res;
    }

    //后序查找
    public HeroNode postOrderSearch(int no){

        HeroNode res = null;
        //向左递归后序查找
        if (this.left != null){
            res = this.left.postOrderSearch(no);
        }

        if (res != null){
            return res;
        }

        //向右递归后序查找
        if (this.right != null){
            res = this.right.postOrderSearch(no);
        }

        if (res != null){
            return res;
        }

//        System.out.println("后序查找");
        //当前结点是否为目标值
        if (this.no == no){
            return this;
        }

        return res;
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