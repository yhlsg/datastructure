package linear_table;

import org.junit.Test;
import sun.management.Agent;

/**
 * @author yhl
 * @create 2021-05-24 12:58
 */
public class GetElem {
    private static final int ERROR = 0;
    private static final int OK = 1;


    //获取元素操作
    public static Object getElem(Object[] args, int i){
        if(args.length == 0 || i < 1 || i > args.length){
            return null;
        }else {
            Object o = args[i - 1];
            return o;
        }
    }

    //插入操作
//    public static Integer InsertElem(Object[] args, int i, Object o){
//        if (args[args.length - 1] != null)//线性表最后一个有元素
//            return ERROR;
//
//        if (i < 1 || i > args.length)//i不在范围内
//            return ERROR;
//
//        if (i != args.length){
//            for (int j = i; j <= args.length; j++) {
//                args[i + 1] = args[i];
//            }
//        }
//
//        args[i] = o;
//
//    }

    @Test
    public void test(){
        String[] strs = new String[5];
//        System.out.println(strs.length);

        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);

        }
    }



}
