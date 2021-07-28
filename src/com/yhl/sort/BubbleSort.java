package com.yhl.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序  时间复杂度 ：O(n^2)
 * 80000条数据排序花费的时间：14s
 *
 * 优化：如果一次排序后，没有发生数据的交换，就直接输出结果
 * @author yhl
 * @create 2021-07-26 21:12
 */
public class BubbleSort {
    public static void main(String[] args) {

//        int[] arr1 = {3, 9, -1, 10, 20};
//        int[] arr2 = bubbleSort(arr1);
//        System.out.println(Arrays.toString(arr2));

        /*
        //测试时间
        Date date = new Date();
        System.out.println(date);
        //将时间格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateTime = simpleDateFormat.format(date);
        System.out.println(dateTime);

        long timeMillis = System.currentTimeMillis();
        System.out.println(timeMillis);
         */

        //创建80000数据的数组使用冒泡排序查看所花费的时间
        int[] arr = new int[80000];

        //向数组中添加数据
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*80000);//会产生一个[0,80000)的一个整数
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String dateString = format.format(date);
        System.out.println("排序前的时间：" + dateString);

        bubbleSort(arr);

        Date date2 = new Date();
        String dateString2 = format.format(date2);
        System.out.println("排序后的时间：" + dateString2);

        System.out.println(Arrays.toString(arr));



    }

    public static int[] bubbleSort(int[] arr){
        //临时变量，用于相邻两个数之间的交换
        int temp = 0;
        //记录是否发生数据的交换
        boolean isChange = false;

        //共进行多少次排序
        for (int i = 0; i < arr.length - 1; i++) {

            //每次排序交换多少次数据，为需要排序次数 - 当前排序的位次
            for (int j = 0; j < arr.length - 1 -i; j++) {

                if (arr[j] > arr[j + 1]){
                    isChange = true;
                    //交换
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }

//            System.out.println("第" + i + "排序：" + Arrays.toString(arr));

            if (isChange == false){
                return arr;
            }else {
                isChange = false;
            }
        }

        return arr;
    }
}
