package test;

import java.util.Stack;

/**
 * 二进制的输出
 * @author yhl
 * @create 2021-08-01 21:07
 */
public class BinaryTest {
    public static void main(String[] args) {
        /*
        int i = -1;
//        System.out.println(Integer.toBinaryString(-1));//11111111 11111111 11111111 11111111

        int j = 0;
        System.out.println(Integer.toBinaryString(19999));

        short i1 = 1;
        short i2 = 2;
        short i3 = (short) (i1 + i2);//两个short相加会默认进行类型提升，得到一个int类型的数据
         */
        System.out.println(1 << 1);
        System.out.println(1 >> 1);

    }

    private void test(){
        System.out.println("测试！");
    }


}

class test{
    public static void main(String[] args) {
        BinaryTest binaryTest = new BinaryTest();
    }
}
