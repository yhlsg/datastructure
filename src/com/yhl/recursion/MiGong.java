package com.yhl.recursion;

/**
 * 迷宫回溯问题：
 * 递归方法解决。
 * @author yhl
 * @create 2021-07-26 10:59
 */
public class MiGong {
    public static void main(String[] args) {
        //用二维数组表示迷宫
        int[][] map = new int[8][7];
        //构造出迷宫
        //1 表示墙 、0 表示可以走的路 、2 表示走过的路 3 表示走过的死路
        //先构造出上下两行墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //构造出左右两面墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //
        map[3][1] = 1;
        map[3][2] = 1;

        //遍历输出map数组
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            //换行
            System.out.println();
        }

        boolean result = setWay(map, 1, 1);
        System.out.println();

        //遍历输出寻找后的map数组
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            //换行
            System.out.println();
        }



    }

    /**
     * 迷宫回溯算法
     * @param map 迷宫
     * @param i 表示开始坐标
     * @param j
     * @return true 表示成功走出迷宫，false表示失败
     */
    public static boolean setWay(int[][] map, int i, int j){
        //走出迷宫
        if(i == 6 && j == 5){
            map[i][j] = 2;
            return true;
        }else {
            if (map[i][j] == 0){//i，j点可以走
                //走向i,j点，将map[i][j]赋值为2，标记走过
                map[i][j] = 2;

                //从i，j点，向下一步走(使用递归)
                //按照 下 -> 右 --> 上 --> 左顺序依次行走
                if (setWay(map, i + 1, j)){
                    return true;
                }else if(setWay(map, i, j + 1)){
                    return true;
                }else if (setWay(map, i - 1, j)){
                    return true;
                }else if (setWay(map, i, j - 1)){
                    return true;
                }else {

                    //如果遇到一个上下左右都走不通，则标记该点不可以走为3，则返回false回溯
                    // 返回上一个点，继续进行其他方向的判断
                    map[i][j] = 3;
                    return false;
                }
            //如果该点不为0，为1，2，3，表示该点已经走过或者不可走
            }else {
                return false;
            }
        }
    }
}
