package com.yhl.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找：
 * @author yhl
 * @create 2021-07-28 16:05
 */
public class BinarySearch {
    public static void main(String[] args) {

//        int[] arr = {1, 2, 3, 4, 5};
//        int index = binarySearch(arr, 0, 4, 5);
//        System.out.println(index);

        int[] arr = {1, 2,  4, 5};
        List index = binarySearch2(arr, 0, arr.length - 1, 3);
        System.out.println(index);

    }

    /**
     * 二分查找单个值
     * @param arr 要查找的数组，数组必须是有序的
     * @param left 左侧索引
     * @param right 右侧索引
     * @param findVal 需要查找的值
     * @return 如果找到返回该值，如果没有找到返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal){

        //没有找到
        if (left > right){
            return -1;
        }

        //中间值的索引
        int mid = (left + right) / 2;
        //指向中间值的指针
//        int midVal = arr[mid];

        if (findVal < arr[mid]){//当查找值小于中间值时
            //向mid左边递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {//查找值大于中间值时
            //向mid右边递归
            return binarySearch(arr, mid + 1, right, findVal);
        }else {//查找值等于中间位置的值时，返回该索引
            return mid;
        }

    }


    /**
     * 二分法查找多个值
     * @param arr 查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findValue 查找值
     * @return 如果找到返回对应的结果集，如果没有找到返回null
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findValue){

        //没有找到，返回null
        if (left > right){
            return null;
        }

        //中间索引的值
        int mid = (left + right) / 2;
        //向左遍历
        if (findValue < arr[mid]){
            return binarySearch2(arr, left, mid - 1, findValue);
        //向右遍历
        }else if (findValue > arr[mid]){
            return binarySearch2(arr, mid + 1, right, findValue);
        //查找值与中间的值相等时，将其加入集合，分别向两边查找是否还有相等值
        }else {

            //创建索引的结果集
            ArrayList<Integer> list = new ArrayList<>();
            list.add(mid);

            //向左寻找是否还有查找值
            int l = mid - 1;//向左边遍历的指针
            while (l >= left && arr[l] == findValue){
                    list.add(l);
                    l--;
            }

            //向右寻找是否还有寻找值
            int r = mid + 1;
            while (r <= right && arr[r] == findValue){
                list.add(r);
                r++;
            }

            //返回结果集
            return list;

        }

    }
}
