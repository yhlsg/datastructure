package com.yhl.stack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 后缀表达式实现计算机(逆波兰表达式):
 * 步骤：
 * 1、使用ArrayList存储表达式中的每一个字符；
 * 2、遍历每一个字符，入栈并计算，返回结果
 *
 *
 * 中缀表达式 ==> 后缀表达式
 * 准备：先将字符串中的每个字符储存到ArrayList集合中
 * 1、创建一个栈和一个集合
 * 2、在栈中操作数和操作符，将结果加入到ArrayList中即可
 *
 * @author yhl
 * @create 2021-07-25 15:30
 */
public class PolandNotation {

    public static void main(String[] args) {

//        //需要计算的后缀表达式
//        //(3 + 4) X 5 -6 ==>> 3 4 + 5 * 6 -
//        String suffixExpression = "3 4 + 5 * 6 -";
//
//        //将字符串中的字符存入到集合中去
//        ArrayList<String> list = getList(suffixExpression);
//
//        //运算返回结果
//        Integer res = calculate(list);
//
//        System.out.println("(3 + 4) X 5 -6通过后缀表达式计算的结果为：" + res);


        String expression = "1+((2+3)*4)-5";//所对应的后缀表达式是：123+4*+5-

        ArrayList<String> list = toArrayList(expression);
//        System.out.println(list);//中缀表达式集合：[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        ArrayList<String> suffix = getSuffix(list);
//        System.out.println(suffix);//后缀表达式集合：[1, 2, 3, +, 4, *, +, 5, -]

        Integer result = calculate(suffix);
        System.out.println(result);//后缀表达式计算结果：16


    }


    //将中缀表达式转化成后缀表达式
    public static ArrayList<String> getSuffix(ArrayList<String> arrayList){
        //创建一个运算符栈
        Stack<String> stack = new Stack<>();
        //创建一个ArrayList集合来存储结果
        ArrayList<String> list = new ArrayList<>();

        //遍历传入的中缀表达式集合
        for (String s : arrayList) {
            //如果s是数字，直接加入到结果集中
            if (s.matches("\\d")){//正则表达式表示：s为多位数
                list.add(s);
            //如果是运算符，则需比较优先级
            }else {
                //s为"("直接入栈
                if (s.equals("(")){
                    stack.push(s);

                //s为")"时，将栈顶运算符出栈加入结果集，直到遇到"("为止
                }else if (s.equals(")")){
                    while (!stack.peek().equals("(")){
                        list.add(stack.pop());
                    }
                    //将"("出栈
                    stack.pop();
                }else {
                    //当s为括号外其他的运算符时
                    //与栈顶的运算符比较优先级，若s的优先级小于等于栈顶运算符的优先级就栈顶运算符pop出
                    //并加入到结果集中
                    while (!stack.isEmpty() && Operation.getValue(s) <= Operation.getValue(stack.peek())){//栈不为空
                        list.add(stack.pop());
                    }
                    //将该运算符入栈
                    stack.push(s);
                }

            }
        }

        //将栈中的运算符依次加入结果集中
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }

        return list;
    }

    //将字符串中的每个字符储存到ArrayList集合中
    public static ArrayList<String> toArrayList(String s){
        //创建一个ArrayList集合
        ArrayList<String> list = new ArrayList<>();

        //遍历字符串，并将其中的字符加入到ArrayList中
        int i = 0;
        char c = ' ';
        while (i < s.length()){
            //当前遍历的字符
             c = s.charAt(i);
            //当该字符为运算符时，直接加入到集合之中
            if (c < 48 || c > 57){
                list.add(c + "");
                i++;
            //当是数字时，要判断后面一个数字是否为字符，若为字符则直接添加，否则继续判断
            }else {
                String str = "";
                //若是数字将数字加入到str中
                while (i < s.length() && s.charAt(i) >= 48 && s.charAt(i) <= 57){
                    str += s.charAt(i);
                    i++;
                }

                //将str加入到ArrayList集合中
                list.add(str);
            }
        }

        return list;
    }

    //将字符串转换成为集合
    public static ArrayList<String> getList(String suffixExpression){
        //将字符串分割
        String[] s = suffixExpression.split(" ");

        ArrayList<String> list = new ArrayList<>();

        //将数组存入集合中
        for (String item : s){
            list.add(item);
        }

        return list;
    }


    //计算后缀表达式的结果
    //输入一个ArrayList返回计算结果
    public static Integer calculate(ArrayList<String> list){

        //创建一个栈
        Stack<Integer> stack = new Stack<>();

        //遍历list
        for (String item : list) {
            //判断是数字还是运算符
            if (item.matches("\\d+")){ //匹配的是多位数
                //将数字入栈
               stack.push(Integer.parseInt(item));
            //是运算符
            }else {
                //从栈中弹出两个数做计算，并将计算结果入栈
                int num1 = stack.pop();
                int num2 = stack.pop();
                int res = 0;

                switch (item){
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("输入的运算符有误！");
                }

                //将计算结果入栈
                stack.push(res);
            }

        }

        //将计算结果返回
        return stack.peek();
    }

}


//比较优先级的类
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //获取对应运算符的优先级
    public static int getValue(String s){
        //返回优先级所对应的数
        int res = 0;
        switch (s){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                break;
        }
        return res;
    }
}
