package com.yhl.huffmancode;


import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码的实现：
 *
 * @author yhl
 * @create 2021-08-02 20:39
 */
public class HuffmanCode {

    //存储赫夫曼编码表
    //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static HashMap<Byte, String> huffmanCodes = new HashMap<>();
    //用于存储拼接叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args){

        //待编码的字符串
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();


        /*
        //获取结点子树的集合
        List<Node> nodes = getNodeList(bytes);
//        System.out.println(nodes);

        //将结点转换成为赫夫曼树
        Node root = huffmanCodeTree(nodes);

//        root.preOrder();

        //从赫夫曼树中获取每个字符所对应的编码
        Map<Byte, String> huffmanCodeMap = huffmanCode(root);
//        System.out.println(huffmanCodeMap);

        //将原字节数组转换成为赫夫曼编码
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodeMap);

        System.out.println("赫夫曼编码为：" + Arrays.toString(huffmanCodeBytes));
         */

//        byte[] huffmanCodeBytes = huffmanZip(bytes);
//        System.out.println("赫夫曼编码是：" + Arrays.toString(huffmanCodeBytes));

        //解码得到原来的字符串
//        byte[] sourceCodes = deCode(huffmanCodeBytes, huffmanCodes);

//        System.out.println("解码后：" + new String(sourceCodes));

        //文件压缩
//        String srcFile = "E:\\Saved Pictures\\src.bmp";
//        String dstFile = "E:\\Saved Pictures\\1.zip";
//        fileZip(srcFile, dstFile);

        //文件解压
        String srcFile = "E:\\Saved Pictures\\1.zip";
        String dstFile = "E:\\Saved Pictures\\src2.bmp";
        unFileZip(srcFile, dstFile);
        //还原不成功，由于赫夫曼编码表是静态的，共用一个，上面的代码没有注释，
        //已经执行了一遍从而导致这次压缩时map中已经含有数据，编码表不正确，所以解压不成功


    }

    //解压文件

    /**
     * 将通过赫夫曼编码的压缩文件解压成为原文件
     * @param zipFile 压缩文件地址
     * @param dstFile 解压文件的存放处
     */
    public static void unFileZip(String zipFile, String dstFile){

        InputStream is = null;
        OutputStream os = null;
        ObjectInputStream ois = null;

        try {
            //文件输入流
            is = new FileInputStream(zipFile);
            //创建对象输入流
            ois = new ObjectInputStream(is);

            //从对象输入流中读取对象
            //赫夫曼编码字节数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //调用解码方法将该字节数组还原
            byte[] bytes = deCode(huffmanBytes, huffmanCodes);

            //将还原的字节数组输出到指定的文件位置
            os = new FileOutputStream(dstFile);
            os.write(bytes);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    //压缩文件

    /**
     * 将指定文件压缩到指定位置
     * @param srcFile 待压缩文件的位置
     * @param dstFile 压缩文件存储的位置
     */
    public static void fileZip(String srcFile, String dstFile){

        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            //创建文件输入流将srcFile下的文件输入
             fis = new FileInputStream(srcFile);
             //在内存中创建字节数组存储输入的文件
            byte[] bytes = new byte[fis.available()];
            //将流中的字节数据存储到数组中
            fis.read(bytes);

            //将文件通过赫夫曼编码进行压缩
            byte[] huffmanZip = huffmanZip(bytes);

            //创建输出流将压缩后的文件和赫夫曼编码表进行输出
            fos = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(fos);

            //输出压缩文件
            oos.writeObject(huffmanZip);

            //输出赫夫曼编码表用于解码
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //解码
    /**
     * 进行解码操作，即将赫夫曼编码转换成为原字节数组
     * @param huffmanCodeBytes 接收到的赫夫曼编码
     * @param huffmanCodes 赫夫曼编码表
     * @return 返回原字节数组
     */
    public static byte[] deCode(byte[] huffmanCodeBytes, Map<Byte, String> huffmanCodes){
        //先将赫夫曼编码转换成为对应的二进制字符串
        //创建可变字符串用来存储二进制数据
        StringBuilder bytesStr = new StringBuilder();
        for (int i = 0; i < huffmanCodeBytes.length; i++) {
            //为最后一个字节时，不需要补码
            if (i == huffmanCodeBytes.length - 1){
                bytesStr.append(toBinaryString(huffmanCodeBytes[i], false));
            }else {
                bytesStr.append(toBinaryString(huffmanCodeBytes[i], true));
            }
        }

//        System.out.println("赫夫曼编码所对应的二进制：" + bytes);
        //1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100

        //先将赫夫曼编码表key和value调换
        HashMap<String, Byte> map = new HashMap<>(huffmanCodes.size());
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }


        //根据赫夫曼编码表匹配该二进制字符串中的每个赫夫曼编码所对应的字符
        //用于存储每个字符
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < bytesStr.length(); ) {//i是指向单个字符所对应二进制的开始位置的指针
            int count = 1;//每个字符二进制的长度

            //遍历二进制字符串
            while (true){

                //待判断的二进制字符串
                String  s = bytesStr.substring(i, i + count);

                //没有二进制该字符
                if (map.get(s) == null){
                    count++;
                //有该二进制字符
                }else {
                    //将该二进制字符所对应的字节存入集合中
                    list.add(map.get(s));
                    break;
                }
            }

            //指针指向下一个字符的开始
            i += count;
        }

        //将集合转换成为数组
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }

        return bytes;
    }

    /**
     * 将byte b转换成为其所对应的二进制
     * @param b 需要转换的字节
     * @param flag 标记是否要补位
     * @return
     */
    public static String toBinaryString(byte b, boolean flag){
        //将b转换成为int，方便转换成为二进制
        int temp = b;
        //判断是否需要补位
        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);

        if (flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }



    //编码
    /**
     * 根据原字节数组，生成对应的赫夫曼编码并返回
     * @param bytes 原字符串所对应的字节数组
     * @return 赫夫曼编码
     */
    public static byte[] huffmanZip(byte[] bytes){

        //将原字节数组封装到Node结点中
        List<Node> nodeList = getNodeList(bytes);

        //生成赫夫曼树
        Node root = huffmanCodeTree(nodeList);

        //生成赫夫曼编码表
        Map<Byte, String> huffmanCodes = huffmanCode(root);

        //生成赫夫曼编码
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;

    }

    /**
     * 获得赫夫曼编码后的字节数组
     * @param bytes 原始字符串对应的字节数组
     * @param huffmanCodeMap 赫夫曼编码表
     * @return
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodeMap){
        //将原来的编码转换成为赫夫曼编码的可变字符串，缩减数据的长度
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            String s = huffmanCodeMap.get(b);
            stringBuilder.append(s);
        }

//        System.out.println("赫夫曼编码所对应的二进制：" + stringBuilder);

        //将可变字符串中的二进制存入到byte数组中
        //确定数组的长度
        int len;
        if (stringBuilder.length() / 8 == 0){
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建用于存储赫夫曼编码的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        //用于向byte数组中添加数据
        int index = 0;

        //遍历可变字符串将每8个二进制存入到数组中
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String str;
            if (i + 8 > stringBuilder.length()){
                str = stringBuilder.substring(i);
            }else {
                str = stringBuilder.substring(i, i + 8);
            }

            //将8个二进制转换成为一个字节byte存入byte数组中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(str, 2);

            index++;
        }


        return huffmanCodeBytes;
    }



    //方法的重载
    public static Map<Byte, String> huffmanCode(Node node){
        if (node == null){
            return null;
        }

        //向左递归
        huffmanCode(node.left, "0", stringBuilder);
        //向右递归
        huffmanCode(node.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * 生成赫夫曼编码表
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
                huffmanCodes.put(node.data, s.toString());
            }else {//不是
                //向左递归
                huffmanCode(node.left, "0", s);

                //向右递归
                huffmanCode(node.right, "1", s);
            }
        }

        return huffmanCodes;
    }


    //构建赫夫曼编码树
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