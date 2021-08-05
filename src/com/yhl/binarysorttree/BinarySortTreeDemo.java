package com.yhl.binarysorttree;

/**
 * 二叉排序树的创建
 *
 * @author yhl
 * @create 2021-08-04 20:17
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        //创建二叉排序树并向其中添加数据
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //删除叶子结点
        binarySortTree.delNode(1);
        binarySortTree.delNode(5);
        binarySortTree.delNode(3);
//        binarySortTree.delNode(0);
        binarySortTree.delNode(7);
        binarySortTree.delNode(10);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);

        //中序遍历
        binarySortTree.infixOrder();//1, 3, 5, 7, 9, 10, 12
    }
}

//二叉排序树
class BinarySortTree{
    private Node root;

    public BinarySortTree() {
    }

    public BinarySortTree(Node root) {
        this.root = root;
    }

    //删除指定的结点
    public void delNode(int value){
        //判断当前树是否为空
        if(root == null){
            return;
        }
        //寻找指定的结点
        Node targetNode = search(value);
        if (targetNode == null){//没有找到
            return;
        }

        //是否只有一个结点
        if (root.left == null && root.right == null){
            root = null;//将root置空即可
            return;
        }

        //获取父结点
        Node parent = searchParent(value);

        //该结点为叶子结点
        if (targetNode.left == null && targetNode.right == null){
            //该叶子结点为parent的左子结点
            if (parent.left != null && parent.left == targetNode){
                parent.left = null;
            }
            //该叶子结点为parent的右子结点
            if (parent.right != null && parent.right == targetNode){
                parent.right = null;
            }
        //该结点的左右子结点都不为空
        }else if (targetNode.left != null && targetNode.right != null){
            //向右子子树寻找最小的结点
            Node minNode = targetNode.right;
            while (true){
                //当该结点的左边为空时，该结点就是最小结点
                if (minNode.left == null){
                    break;
                }
                minNode = minNode.left;
            }

            //要先将最小结点删除不然会出现堆溢出
            //将最小结点删除
            delNode(minNode.value);
            //将该结点修改成为最小结点的值
            targetNode.value = minNode.value;

        //该结点只有一个子结点不为空
        }else {
            //左子结点不为空
            if (targetNode.left != null){
                //判断该结点是否为root结点
                if (parent == null){
                    root = targetNode.left;
                }else {
                    if (parent.left == targetNode){
                        parent.left = targetNode.left;
                    }else{
                        parent.right = targetNode.left;
                    }
                }
            }else {
                if (parent == null){
                    root = targetNode.right;
                }else {
                    if (parent.left == targetNode){
                        parent.left = targetNode.right;
                    }else {
                        parent.right = targetNode.right;
                    }
                }
            }

        }




    }

    //寻找指定值的父结点
    public Node searchParent(int value){
        if (root == null){
            return null;
        }

        Node parent = root.searchParent(value);
        return parent;

    }

    //寻找某个结点
    public Node search(int value){
        if (root == null){
            return null;
        }

        Node node = root.search(value);

        return node;
    }

    //向树中添加结点
    public void add(Node node){
        // 如果root为空，直接将root指向node
        if (root == null){
            root = node;
        }else {
            root.add(node);
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
}

//结点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 找到待删除的结点
     * @param value 待删除结点的值
     * @return 待删除结点
     */
    public Node search(int value){
        /*
        Node parent = getParent(value);
        //找到待删除结点的父结点
        if (parent != null){
            //返回待删除结点
            if (parent.left != null && parent.left.value == value){
                return parent.left;
            }else {
                return parent.right;
            }
        //没有找到
        }else {
            return null;
        }
         */

        if (this.value == value){
            return this;
        }

        if (value < this.value && this.left != null){
            //向左递归
            return this.left.search(value);
        }
        if (value > this.value && this.right != null){
            //向右递归
            return this.right.search(value);
        }
        //没有找到
        return null;

    }

    /**
     * 找到待删除结点的父结点
     * @param value 待删除结点的值
     * @return 待删除结点的父结点
     */
    public Node searchParent(int value){
        //该结点是父结点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }

        //该结点不是父结点
        //向左子结点查找
        if (value < this.value && this.left != null){
            //向左递归
            return this.left.searchParent(value);
        }
        //向右子结点查找
        if (value >= this.value && this.right != null){
            //向右递归
            return this.right.searchParent(value);
        }

        //没有找到
        return null;

    }

    //添加结点
    public void add(Node node){
        //判断node结点的中的值的大小
        //小于
        if (node.value < this.value){
            //左结点是否为空
            if (this.left == null){
                this.left = node;
            }else {
                //向左递归
                this.left.add(node);
            }
        //大于
        }else {
            //判断右结点是否为空
            if (this.right == null){
                this.right = node;
            }else {
                //向右递归
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null){
            this.right.infixOrder();
        }
    }
}