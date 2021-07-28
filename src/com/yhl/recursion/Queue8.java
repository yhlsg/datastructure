package com.yhl.recursion;


/**
 * 用递归算法解决八皇后问题： 92 个
 * 在8 X 8 的表格中，每两个皇后之间不能在同一行、同一列、同一个斜线上
 * 步骤：
 * 用数组arr = {}表示八个皇后所在的位置，数组的索引表示行号，值表示列号
 *
 * @author yhl
 * @create 2021-07-26 15:58
 */
public class Queue8 {

    //用于保存结果的数组
    int[] arr = new int[8];
    //最大行数
    int max = 8;
    //计数
    int count ;

    public static void main(String[] args) {

        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);

        System.out.println("八皇后问题的结果共有：" + queue8.count + "个");

    }

    /**
     * 判断当前行数的皇后位置
     *
     * 递归打印出结果
     * @param n 表示当前行数
     */
    public  void check(int n){

        //递归结束条件，结束该方法
        if (n == max){ //0 到 max - 1
            print();
            return;
        }else {
            //从一行的n，0开始遍历，遍历每一列
            for (int i = 0; i < max; i++) {
                //将当前列值，存入数组中
                arr[n] = i;
                //判断当前皇后是否冲突
                if (judge(n)){
                    //不冲突，遍历下一行 n + 1 行
                    check(n + 1);
                }
                //冲突则遍历改行的下一个
            }
        }
    }

    //打印成功的八皇后结果集
    public void print(){
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        //每打印完一行换行
        System.out.println();
    }


    //判断第n个皇后是否与其他皇后冲突
    public boolean judge(int n){
        //遍历前n - 1个皇后是否与该皇后冲突
        for (int i = 0; i < n  ; i++) {
            //与某个皇后冲突，就返回false
            //由于是按行分成不同的皇后的
            //列冲突：arr[i] == arr[n]
            //斜线冲突：Math.abs(n - i) == Math.abs(arr[n] - arr[i]) 当行列差的绝对值相等时，两个点在同一个斜线上
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }

        //与前n - 1个都不冲突，返回true
        return true;

    }
}
