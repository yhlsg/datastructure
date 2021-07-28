package com.yhl.sparseArray;

/**
 * 稀疏数组
 * @author yhl
 * @create 2021-06-07 22:33
 */
public class sparseArray {
    public static void main(String[] args) {
        //原二维数组的创建，用1表示黑子，2表示蓝子
        int[][] array1 = new int[11][11];
        array1[1][2] = 1;
        array1[2][3] = 2;

        //遍历输出原数组
        for (int[] arr : array1){
            for (int i = 0; i < arr.length; i++){
                System.out.print(arr[i] + "\t");
            }
            System.out.println();
        }



        //遍历数组，找到非0的数
        int sum = 0;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if (array1[i][j] != 0){
                    sum++;
                }
            }

        }
        //创建稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //遍历数组中不为0的数，并将其存入稀疏数组中
        int count = 1;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if (array1[i][j] != 0){
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array1[i][j];
                    count++;
                }
            }
        }
        System.out.println();

        //输出稀疏数组
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
            System.out.println();
        }

        System.out.println();

        //稀疏数组转换成为二维数组
        int[][] array2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        //从第二行遍历稀疏数组，将非0值加入到二维数组中
        for (int i = 1; i < sparseArray.length; i++) {
            array2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array2[i].length; j++) {
                System.out.print(array2[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
