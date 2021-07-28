package com.yhl.sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序：时间复杂度 = O(n*log2n)
 *
 * 排序80000的数组耗时：1s不到
 *
 * 先将数组的最左边确定为基准数
 * 先从最右边寻找比基准数小的数，再从最左边找比基准数大的数，交换两个数，直到两边相遇退出循环
 * 交换最左边的基准数和退出循环时的索引的值
 * 递归调用该方法排序索引左端和右端
 * @author yhl
 * @create 2021-07-27 21:01
 */
public class QuickSort {
    public static void main(String[] args) {

//        int[] arr = {6,2,3,8,5,6,7,8,9,10};
//        quickSort(arr, 0, 9);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateString = simpleDateFormat.format(date);
        System.out.println("排序前：" + dateString);

        quickSort(arr, 0, 79999);

        Date date1 = new Date();
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序前：" + dateString1);
    }



    public static void quickSort(int[] arr, int left, int right){

        //递归结束
        if (left > right){
            return;
        }

        int l = left;//最左边的下标
        int r = right;//最右边的下标
        int pivot = arr[left];//基准数

        //辅助变量用于交换
        int temp = 0;

        //当l == r时退出该次排序
        while (l < r){

            //一定要先从右边开始寻找，因为基准数在左边，需要最后找到一个小于基准数的与其交换

            //找到小于pivot的数或者l > r退出
            while (arr[r] >= pivot && l < r){
                r--;
            }

            //从左边开始一直遍历，直到找到大于pivot的数或者l > r退出
            while (arr[l] <= pivot && l < r){
                l++;
            }

            //满足交换条件，pivot基准数两边的数交换
            if (l < r){
                temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }

        //交换最后l和r相交点与基准点
        arr[left] = arr[l];
        arr[l] = pivot;

        //递归调用左半数组
        quickSort(arr, left, l - 1);
        //递归调用右半数组
        quickSort(arr, l + 1, right);
    }
}
