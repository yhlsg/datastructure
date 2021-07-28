package com.yhl.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序：
 * 80000条数据排序花费时间：1s不到
 * @author yhl
 * @create 2021-07-28 10:58
 */
public class RadixSort {

    public static void main(String[] args) {

//        //待排序的数组
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String dateString = simpleDateFormat.format(date);
        System.out.println("排序前：" + dateString);

        radixSort(arr);

        Date date1 = new Date();
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序后：" + dateString1);

    }

    //基数排序
    public static void  radixSort(int[] arr){

        //创建10个桶
        int[][] bucket = new int[10][arr.length];
        //一维数组来存储每个桶中的数组
        int[] bucketCount = new int[10];

        //找出待排序数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }

        //最大数的位数
        int maxLen = (max + "").length();

        //总共进行maxLen次排序
        for (int i = 0, n = 1; i < maxLen; i++, n *= 10) {

            //遍历数组，按照相对应的位数上的值放入不同的桶中
            for (int j = 0; j < arr.length; j++) {
                //相应位上的数
                int l = arr[j] / n % 10;
                //根据l入桶
                bucket[l][bucketCount[l]] = arr[j];
                bucketCount[l]++;
            }

            //将遍历所有的桶，将桶中的数据依次存入数组中
            int index = 0;
            for (int j = 0; j < bucketCount.length; j++) {
                //如果桶中有数据
                if (bucketCount[j] != 0){
                    //遍历该桶中的所有数据，并将其存入arr数组中
                    for (int k = 0; k < bucketCount[j]; k++) {
                        arr[index] = bucket[j][k];
                        index++;
                    }

                    //将第记录j个桶中数据的个数清空
                    bucketCount[j] = 0;
                }
            }


        }

    }
}
