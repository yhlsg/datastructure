package com.yhl.search;

/**
 * 线性查找：
 *
 * @author yhl
 * @create 2021-07-28 15:48
 */
public class SeqSearch {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};

        int index = seqSearch(arr, -1);
        System.out.println(index);
    }

    public static int seqSearch(int[] arr, int value){
        //找到返回该值的索引
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }

        //没找到返回-1
        return -1;

    }
}
