package com.yhl.huffmancode;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 赫夫曼编码的实现：
 *
 * @author yhl
 * @create 2021-08-02 20:39
 */
public class HuffmanCode {
    public static void main(String[] args){

        //待编码的字符串
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();

        //获取结点子树的集合
        List<Node> nodes = getNodeList(bytes);
//        System.out.println(nodes);

        //将结点转换成为赫夫曼树
        Node root = huffmanCodeTree(nodes);

//        root.preOrder();

        //从赫夫曼树中获取每个字符所对应的编码
        Map<Byte, String> huffmanCode = huffmanCode(root);
        System.out.println(huffmanCode);


    }

    //存储赫夫曼编码
    //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static HashMap<Byte, String> map = new HashMap<>();
    //用于存储拼接叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();


    //方法的重载
    public static Map<Byte, String> huffmanCode(Node node){
        if (node == null){
            return null;
        }

        //向左递归
        huffmanCode(node.left, "0", stringBuilder);
        //向右递归
        huffmanCode(node.right, "1", stringBuilder);

        return map;
    }
    /**
     * 生成赫夫曼编码
     * 表示将node底下所有的叶子结点都生成
     * @param node
     * @param code 表示左右路径，左 -> 0， 右 -> 1
     * @param stringBuilder 用来存储路径
     * @return
     */
    public static Map<Byte, String> huffmanCode(Node node, String code, StringBuilder stringBuilder){
        //由于递归回来之后还有可能重复使用stringBuilder参数
        StringBuilder s = new StringBuilder(stringBuilder);

        if (node != null){
            //将code添加到stringBuilder路径中
            s.append(code);
            //判断node是否为叶子结点
            if (node.data != null){//是
                //将路径加入到map中
                map.put(node.data, s.toString());
            }else {//不是
                //向左递归
                huffmanCode(node.left, "0", s);

                //向右递归
                huffmanCode(node.right, "1", s);
            }
        }

        return map;
    }


    //赫夫曼编码树
    public static Node huffmanCodeTree(List<Node> nodes){
        while (nodes.size() > 1){
            //先将nodes中的结点按照weight权值从小到大排序
            Collections.sort(nodes);
            //从nodes集合中取出两个
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //将两个子树合并成一个子树
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将合并后的两个子树从集合中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将parent加入到集合中
            nodes.add(parent);
        }

        return nodes.get(0);

    }

    /**
     * 将字符封装为Node的List集合
     * @param bytes 要转换成为list的byte数组
     * @return 返回list集合
     */
    public static List<Node> getNodeList(byte[] bytes){

        ArrayList<Node> nodes = new ArrayList<>();

        //创建HashMap来统计String中每个重复字符的个数
        HashMap<Byte, Integer> map = new HashMap<>();
        //取出bytes中的每个byte，放入map中
        for (byte b : bytes) {
            //判断map中是否已经含有b
            Integer count = map.get(b);
            if (count == null){
                map.put(b, 1);
            }else {
                map.put(b, count + 1);
            }
        }

        //遍历map中的数据，将其封装到Node中，并放入集合中
        for (Map.Entry<Byte, Integer> entry : map.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }
}

//结点
class Node implements Comparable<Node>{
    Byte data;//字符所对应的10进制
    int weight;//权值
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
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