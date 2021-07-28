package com.yhl.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序： 时间复杂度 = O(n^2)
 * 80000条数据排序花费的时间：4s
 *
 * 从要排序的数组中选择一个最小的，排到最前面
 * 总共要进行arr.length - 1次排序，每次排序找出当前没有排序数据的最小值放到最前面
 *
 * @author yhl
 * @create 2021-07-26 22:24
 */
public class SelectSort {

    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//
//        SelectSort(arr);
//
//        System.out.println("排序后：" + Arrays.toString(arr));

        //计算排序80000数据所需要的时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String dateString = format.format(date);
        System.out.println("排序前：" + dateString);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        SelectSort(arr);

        Date date1 = new Date();
        String dateString1 = format.format(date1);
        System.out.println("排序前：" + dateString1);

        System.out.println(Arrays.toString(arr));

    }

    public static void SelectSort(int[] arr){
        //总共要进行arr.length - 1次排序
        for (int i = 0; i < arr.length - 1; i++) {

            //1、找出当前未排序数据的最小值
            //假设最小值的索引为最前一个, i表示寻找最小值区域的第一个索引
            int minIndex = i;
            int min = arr[i];
            //遍历剩余数据，寻找最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }

            //2、最小值和第一个数据的索引不相等时，交换数据
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
