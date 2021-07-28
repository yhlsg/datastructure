package com.yhl.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序：算法的时间复杂度 = O(n^2)
 * 80000条数据排序花费的时间：1s
 * 将数组的第一个先当成一个有序数组，遍历后面的数据，将其插入到前面的有序数组中
 * @author yhl
 * @create 2021-07-27 9:36
 */
public class InsertSort {

    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};

        //测试插入80000条数据所要花费的时间
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        //记录排序开始时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateString = simpleDateFormat.format(date);
        System.out.println("排序开始时间：" + dateString);

        insertSort(arr);

        //排序结束时间
        Date date1 = new Date();
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序结束时间：" + dateString1);
    }


    public static void insertSort(int[] arr){

        //进行arr.length - 1次循环
        for (int i = 1; i < arr.length; i++) {

            //从第二个数开始插入
            int insertVal = arr[i];//表示待插入的数据
            int insertIndex = i - 1;//表示要插入的数据的前一个

            //遍历前面的有序数组，找到插入的位置，并将数组前移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //判断要插入的位置是否为要插入值的起始位置
            if (insertIndex + 1 != i){
                //将待插入数据插入指定的位置
                arr[insertIndex + 1] = insertVal;
            }

//            System.out.println("第" + i + "次插入：" + Arrays.toString(arr));
        }
    }
}
