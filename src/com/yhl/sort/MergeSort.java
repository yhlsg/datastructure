package com.yhl.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 归并排序：
 * 80000条数据排序花费的时间：1s不到
 *
 * @author yhl
 * @create 2021-07-27 23:05
 */
public class MergeSort {
    public static void main(String[] args) {

//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, 7, temp);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String dateString = format.format(date);
        System.out.println("排序前的时间：" + dateString);

        mergeSort(arr, 0, 79999, temp);

        Date date1 = new Date();
        String dateString1 = format.format(date1);
        System.out.println("排序前的时间：" + dateString1);
    }

    //归并排序
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        //递归，将数组逐步分成单个数，再将逐步其合并排序
        //在数组内部将数组分成单个数的数组
        if (left < right){
            int mid = (left + right) / 2;//中间位置的索引

            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);

            //每次分完过后，将其合并排序
            merge(arr, left, mid, right, temp);
        }

    }


    /**
     * 合并方法
     * @param arr 排序数组
     * @param left 左边数组的起始索引
     * @param mid  左边数组的结束索引
     * @param right 右边数组的结束索引
     * @param temp 辅助数组 用来储存合并的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;//左边数组的起始下标
        int j = mid + 1;//右边数组的起始索引
        int t = 0;//temp数组的下标

        //1、比较两个数组中的值，将较小的数放入temp数组
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //2、将剩余数依次添加到数组中
        while (i <= mid){
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right){
            temp[t] = arr[j];
            t++;
            j++;
        }

        //3、将合并好的数组复制回原数组中
        t = 0;//从temp数组的开头开始复制
        int tempLeft = left;//需要复制回的原数组的下标

        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
