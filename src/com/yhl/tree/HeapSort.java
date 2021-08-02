package com.yhl.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序的实现：时间复杂度 = n * log2n
 * 80000个数据的数组排序耗时：1s不到
 * @author yhl
 * @create 2021-08-02 10:04
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9, 100, -1};
//
//        heapSort(arr);
//
//        System.out.println("堆排序后：" + Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String dateString = simpleDateFormat.format(date);
        System.out.println("排序前：" + dateString);

        heapSort(arr);

        Date date1 = new Date();
        String dateString1 = simpleDateFormat.format(date1);
        System.out.println("排序后：" + dateString1);


    }


    //堆排序

    public static void heapSort(int[] arr){
        //创建辅助变量用于交换
        int temp = 0;
        //先将arr转换成为大顶堆
        //从第一个非叶子结点开始
        for (int i = arr.length / 2 -1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //进行堆排序
        for (int i = arr.length - 1; i >= 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            adjustHeap(arr, 0, i);//i代表长度
        }
    }



    /**
     * 将子树转换成为大顶堆
     * @param arr 待转换的数组
     * @param i 非子结点的索引
     * @param length 数组的长度
     */
    public static void adjustHeap(int[] arr, int i, int length){
        //保存当前结点的值
        int temp = arr[i];

        //遍历i的子结点
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {

            //找出左右结点中较大的一个
            if (j + 1 < length && arr[j] < arr[j + 1]){
                j++;
            }

            //判断temp与较大的子结点的大小
            if (arr[j] > temp){
                //将j结点的值赋给i
                arr[i] = arr[j];
                i = j;
            }else {
                break;
            }
        }

        //将最后的子结点赋值temp
        arr[i] = temp;
    }
}
