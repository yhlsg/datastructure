package test;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * BigDecimal 用来对超过16位有效位的数进行精确的运算
 * @author yhl
 * @create 2021-07-28 21:06
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);//0.30000000000000004

        //两个双精度的浮点数相加
        BigDecimal b1 = new BigDecimal("0.1");
        BigDecimal b2 = new BigDecimal("0.2");
        BigDecimal sum = b1.add(b2);
        System.out.println(sum);//0.3

//        HashMap hashMap = new HashMap();
//        hashMap.put(1, 2);
//        System.out.println(hashMap);
    }
}
