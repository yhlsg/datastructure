package com.yhl.tree;

/**
 * 顺序存储二叉树的实现：
 * 当前结点在数组中的索引为n时：
 * 1、左子结点的索引为：2 * n + 1
 * 2、右子结点的索引为：2 * n + 2
 * 3、父结点为：(n - 1) / 2
 * @author yhl
 * @create 2021-07-31 16:16
 */
public class  ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);

        //前序遍历
//        arrayBinaryTree.preOrder();//1, 2, 4, 5, 3, 6, 7

        //中序遍历
//        arrayBinaryTree.infixOrder();//4, 2, 5, 1, 6, 3, 7

        //后序遍历
        arrayBinaryTree.postOrder();//4, 5, 2, 6, 7, 3, 1

    }
}

//顺序存储二叉树
class ArrayBinaryTree{

    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //前序遍历方法的重载
    public void preOrder(){
        this.preOrder(0);
    }

    //顺序存储二叉树的前序遍历
    public void preOrder(int index){
        //二叉树为空时
        if (arr == null && arr.length == 0){
            System.out.println("二叉树为空！");
            return;
        }

        //输出当前结点
        System.out.println(arr[index]);

        //向左递归前序遍历
        if ((2 * index + 1) < arr.length){
            preOrder(2 * index + 1);
        }

        //向右递归前序遍历
        if ((2 * index + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }

    //中序遍历方法的重载
    public void infixOrder(){
        infixOrder(0);
    }

    //中序遍历
    public void infixOrder(int index){
        if (arr == null && arr.length == 0){
            System.out.println("二叉树为空！");
            return;
        }

        //向左中序遍历
        if ((2 * index + 1) < arr.length){
            infixOrder(2 * index + 1);
        }

        //输出当前结点
        System.out.println(arr[index]);

        //向右中序遍历
        if ((2 * index + 2) < arr.length){
            infixOrder(2 * index + 2);
        }
    }

    //后序遍历方法的重载
    public void postOrder(){
        postOrder(0);
    }
    //后序遍历
    public void postOrder(int index){
        if (arr == null && arr.length == 0){
            System.out.println("二叉树为空！");
        }

        //向左后序遍历
        if ((2 * index + 1) < arr.length){
            postOrder(2 * index + 1);
        }

        //向右后序遍历
        if ((2 * index + 2) < arr.length){
            postOrder(2 * index + 2);
        }

        //输出当前结点
        System.out.println(arr[index]);
    }
}
