package com.yhl.avltree;

/**
 * 平衡二叉树的实现
 * @author yhl
 * @create 2021-08-05 16:15
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //创建AVLTree
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //中序遍历
        avlTree.infixOrder();

        //获取左右子树的高度
        System.out.println("进行左旋转之后：");
        System.out.println("左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度：" + avlTree.getRoot().rightHeight());
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("当前AVLTree的根结点为：" + avlTree.getRoot().right.left);



    }
}

//平衡二叉树
class AVLTree{
    private Node root;

    public AVLTree() {
    }

    public AVLTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
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

    //右旋转的实现
    public void rightRotate(){
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

    //左旋转的实现
    public void leftRotate(){
        //创建一个新的结点指向左子结点
        Node newNode = new Node(this.value);
        //新结点的左子结点指向该结点的左子结点
        newNode.left = this.left;
        //新结点的右子结点指向该结点的右子结点的左子结点
        newNode.right = this.right.left;
        //将该结点的值变成该结点的右子结点的值
        this.value = this.right.value;
        //将该结点左子结点指向新结点
        this.left = newNode;
        //将该结点的右子结点指向该结点右结点的右结点
        this.right = this.right.right;
    }

    //返回右子树的高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }

        return left.height();
    }

    //获得以该结点为子树的树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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

        //判断是否需要左旋转 (右子树的高度 - 左子树的高度) > 1
        if (this.rightHeight() - this.leftHeight() > 1){
            //双旋转的情况：右结点的左子树的高度大于右子树的高度
            // 先将右子树的右旋转，再将该结点左旋转
            if (right.leftHeight() > right.rightHeight()){
                //先右子树右旋转
                right.rightRotate();
                //然后该结点左旋转
                this.leftRotate();
            }else {
                this.leftRotate();
            }

            return;
        }

        //判断是否需要右旋转 (左子树的高度 - 右子树的高度 > 1)
        if (this.leftHeight() - this.rightHeight() > 1){
            //双旋转的情况：左结点的右子树的高度大于左子树的高度
            // 向将左子树左旋转，再将该结点右旋转
            if (left.rightHeight() > left.leftHeight()){
                //向将左子树左旋转
                left.leftRotate();
                //再将该结点右旋转
                this.rightRotate();
            }else {
                this.rightRotate();
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