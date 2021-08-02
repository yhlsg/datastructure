package com.yhl.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼树的实现
 * @author yhl
 * @create 2021-08-02 15:25
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        //创建赫夫曼树
        Node root = huffmanTree(arr);

        //前序遍历
        root.preOrder();//67, 29, 38, 15, 7, 8, 23, 10, 4, 1, 3, 6, 13
    }



    /**
     * 赫夫曼树的创建
     * @param arr 树的叶子结点中的权值
     * @return 赫夫曼树的根结点
     */
    public static Node huffmanTree(int[] arr){
        //先将arr数组中的数据封装到结点中，并添加到集合中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }

        while (nodes.size() > 1){
            //将集合中的结点按照权值从小到大的顺序排列
            Collections.sort(nodes);

            //从集合中取出两个二叉树，组合成一个新的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //新的二叉树
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            //将原先的两个结点在集合中删除
            nodes.remove(0);
            nodes.remove(0);

            //将新的二叉树放入集合中
            nodes.add(parent);
        }

        //返回根结点
        return nodes.get(0);
    }
}


//结点
class Node implements Comparable<Node>{
    private int value;//权值
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}
