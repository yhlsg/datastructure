package com.yhl.recursion;

/**
 * 递归算法的简单测试
 *
 * @author yhl
 * @create 2021-07-26 9:03
 */
public class recursionTest {
    public static void main(String[] args) {
        //调用递归方法打印数据
        test(4);//2、3、4

        //递归求阶乘
        int factorial = factorial(4);// 4！= 24
        System.out.println(factorial);

        //递归求和
        int sum = sum(4);// 10
        System.out.println(sum);

    }


    //打印数据
    public static void test(int n){
        if (n > 2){
            test(n - 1);
        }

        System.out.println(n);
    }

    //求1到n的和
    public static int sum(int n){
        if (n == 1){
            return 1;
        }else {
            return sum(n - 1) + n;
        }

    }

    //阶乘
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n - 1) * n;
        }

    }
}
