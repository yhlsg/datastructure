package com.yhl.sort;

import org.omg.CORBA.ARG_OUT;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序：
 * 1、交换：耗时---15s
 * 2、移位：耗时---1s
 *
 * 用arr.length除以2直到结果为0时停止，来确定排序的次数
 * 每次排序后将步长缩小
 * @author yhl
 * @create 2021-07-27 15:05
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr1 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        //排序前
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateString = simpleDateFormat.format(date);
        System.out.println("排序前：" + dateString);

//        ShellSort(arr);
        ShellSort2(arr);
//        System.out.println(Arrays.toString(arr1));

        //排序后
        Date date1 = new Date();
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序后：" + dateString1);

//        System.out.println(Arrays.toString(arr));
    }

    //子排序中使用交换交换法
    public static void ShellSort(int[] arr){
        //在每个子排序中使用交换的方式来排序
        int temp = 0;//临时变量
        //排序的次数，
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            //遍历每个数，并将该数在每次排序所分成的小组中排好序
            for (int i = gap; i < arr.length; i++) {
                //
                for (int j = i - gap; j >= 0; j -= gap) {
                    //交换两个数
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }

        }
    }


    //移位法
    public static void ShellSort2(int[] arr){

        //步长，进行排序的次数
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            //每次子排序，对gap后面的每个数使用插入排序，插入到前面排序好的每一组中
            for (int i = gap; i < arr.length; i++) {
                //每一组数组的排序都是一次插入排序
                //记录需要插入数的值和索引
                int index = i;
                int value = arr[i];

                //遍历前面已经排序好的数组，找出插入位置
                while (index - gap >= 0 && value < arr[index - gap]){
                    //将值插入的索引向前位移，并将判断过的数向后移动
                    arr[index] = arr[index - gap];
                    index -= gap;
                }

                //将待插入数插入
                if (index != i){
                    arr[index] = value;
                }
            }
        }

    }

}
