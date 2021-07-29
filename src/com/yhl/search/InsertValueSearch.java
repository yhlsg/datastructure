package com.yhl.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 插值查找：
 * 在二分法查找上做了点改进
 * @author yhl
 * @create 2021-07-29 10:07
 */
public class InsertValueSearch {
    public static void main(String[] args) {

        int[] arr = new int[100];//1, 2, ...., 100
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }


        List<Integer> list = insertValueSearch(arr, 0, arr.length - 1, 99);
        System.out.println(list);


    }

    /**
     * 插值查找
     * @param arr 待查找的数组，必须位有序数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findValue 查找的值
     * @return 找到返回索引值集合，没找到返回null
     */
    public static List<Integer> insertValueSearch(int[] arr, int left, int right, int findValue){

        System.out.println("插值排序~");
        //判断arr是否已经寻找完以及findValue是否可以找到
        //findValue < arr[left] || findValue > arr[right]必须写，不然可能导致mid索引越界
        if (left > right || findValue < arr[left] || findValue > arr[right]){
            return  null;
        }

        //自适应mid
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);

        //向左递归
        if (findValue < arr[mid]){
            return insertValueSearch(arr, left, mid - 1, findValue);
        //向右递归
        } else if (findValue > arr[mid]) {
            return insertValueSearch(arr, mid + 1, right, findValue);
        }else {
            //先将该值的索引加入到结合之中
            ArrayList<Integer> list = new ArrayList<>();
            list.add(mid);

            //分别向左向右遍历是否还有寻找值
            //向左
            int i = mid - 1;
            while (i >= left && arr[i] == findValue){
                //相同将i加入集合
                list.add(i);
                i--;
            }

            //向右
            int j = mid + 1;
            while (j <= right && arr[j] == findValue){
                list.add(j);
                j++;
            }

            return list;
        }
    }
}
