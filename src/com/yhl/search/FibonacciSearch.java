package com.yhl.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 * @author yhl
 * @create 2021-07-29 14:34
 */
public class FibonacciSearch {
    private static int maxSize = 20;

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(f));

        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(arr, 9);
        System.out.println("index = " + index);
    }

    /**
     * 斐波那契查找(非递归）
     * @param arr 待查找的数组，必须位有序数组
     * @param findVal 查找值
     * @return
     */
    public static int fibSearch(int[] arr, int findVal){

        //创建斐波那契数列
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        //修剪数组
        //将待查找的数组转换成为斐波那契数列数的长度的数组

        int k = 0;//表示斐波那契分割数值的下标
        int low = 0;//左边索引
        int high = arr.length - 1;//右边索引

        //遍历数列找到大于等于arr的最小的数
        //确定第一个需要的斐波那契数列的值
        while (high > f[k] - 1){
            k++;
        }

        //
        //由于high是小于等于f[k] - 1， 所以需要将arr的长度拉长到f[k]，不够的地方使用high位置上的值填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //如果high 不等于 f[k] - 1，则需要填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }


        //一直寻找直到找到或找完
        while (low <= high){
            //中间位置的数据即黄金分割点上
            int mid = low + f[k - 1] -1;

            //判断该点上的数据与findVal的大小
            //向左继续查找
            if(findVal < temp[mid]){
                //将右边的指针移动到黄金分割点的左边一个
                high = mid - 1;//只能是-1，让2个数黄金分割完就结束，否则在求mid时会报数组角标越界异常
                //就是将斐波那契数列中的指针向前移动一位
                //也就是temp中下一次黄金分割总长度变为原先长度的 2 / 3，也就是斐波那契数列原先指针的前一位
                k--;
            //向右继续查找
            }else if (findVal > temp[mid]){
                //将起始索引移动到黄金分割点的前一位
                low = mid + 1;
                //将temp中下一次黄金分割总长度变为原先的 1 / 3
                //也就是相当于将斐波那契数列原先指针的前两位
                k -= 2;
            //找到查找值
            }else{
                //由于可能延长了原数组
                //查找值在high之前
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }

        //没有找到查找值
        return -1;
    }
}
