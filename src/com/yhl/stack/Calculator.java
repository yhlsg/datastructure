package com.yhl.stack;


/**
 * 中缀表达式
 *
 * 计算机的实现：
 * 1、先将创建两个栈，一个数栈、一个符号栈；
 * 2、遍历表达式，如果是数字压入数栈，如果是符号则先判断符号栈中是否为空，如果为空则直接入栈
 * 如果不为空，则判断当前符号与栈顶的符号的优先级，如果当前的符号优先级要小于等于栈顶的符号，
 * 就将栈顶的符号出栈，并从数栈中pop出两个数据进行计算，将计算结果push回数栈中，
 * 然后再将符号压入栈中；
 * 3、表达式遍历完了以后，便从数栈顶pop出两个数，从符号栈顶pop出一个符号反复计算，直到符号栈为空
 * 结束，数栈中最后一个数据即为结果。
 *
 * 测试：
 * 表达式："3+2*6-2"
 * 结果：13
 *
 * 当数字为多位数时：
 * 当判断字符为数字时，不能直接入栈，需要先判断其是否为最后一个字符
 * 如果是则直接入栈，
 * 如果不是则先判断下一个字符是数字还是字符
 *      如果是数字，就继续判断；是字符就直接入栈
 *
 * 测试：
 * 表达式：30+2*6-2=40
 *
 * @author yhl
 * @create 2021-07-25 10:50
 */
public class Calculator {
    public static void main(String[] args) {

        //创建数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //创建所需要的变量
        int res = 0;//结果
        int index = 0;//遍历的索引

        int num1 = 0;
        int num2 = 0;
        int oper = 0;

        //要计算的表达式
        String s = "3+2*6-2"; //13
        //用来储存多位数
        String keepNum = "";

        //遍历表达式中的字符，并计算
        while (true){

            char ch = s.substring(index, index + 1).charAt(0);
            //如果ch是符号
            if (operStack.isOper(ch)){
                //栈不为空
                if (!operStack.isEmpty()){
                    //该运算符小于栈顶的运算符
                    if (operStack.property(ch) <= operStack.property(operStack.getTop())){
                        //计算
                        oper = operStack.pop();
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = numStack.calculate(num1, num2, oper);

                        //将运算结果和运算符入栈
                        numStack.push(res);
                        operStack.push(ch);
                    }else {//该运算符优先级大于栈顶的运算符的优先级
                        //直接将运算符入栈
                        operStack.push(ch);
                    }

                //栈为空时
                }else {
                    //直接将运算符入栈
                    operStack.push(ch);
                }

            //ch为数字
            }else {
//                //直接将数字入栈
//                numStack.push(ch - 48);//或者可以写成 ch - '0'
                //判断数字个数，先将数字储存在keepNum字符串中

                keepNum += ch;

                //当ch为最后一个字符时，直接将keepNum入栈
                if (index == s.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));//将keepNum转换成数字，并入栈

                //不是最后一个
                }else{
                    //判断后一个字符是否为字符
                    if (numStack.isOper(s.substring(index + 1, index + 2).charAt(0))){
                        //是字符，直接入栈
                        numStack.push(Integer.parseInt(keepNum));

                        //keepNum要清空
                        keepNum = "";
                    }
                }
            }

            //循环条件
            if (index == s.length() -1){
                break;
            }

            index++;
        }

        //表达式遍历结束，计算栈中剩余的值
        while (true){
            //符号栈为空则跳出循环
            if (operStack.isEmpty()){
                break;
            }

            //计算
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = numStack.calculate(num1, num2, oper);
            //将计算结果入栈
            numStack.push(res);
        }

        //运算结束后数栈中只有一个数字，即为表达式的结果
        res = numStack.pop();
        System.out.println("表达式：" + s + "=" + res);




    }
}


//模拟栈类
class ArrayStack2{
    private int maxSize;//栈的最大容量
    private int[] stack;//存储数据的数组
    private int top = -1;//栈顶

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //获取栈顶数据
    public int getTop(){
        return stack[top];
    }
    //判断是数字还是符号
    public boolean isOper(int ch){
        return ch == '/' || ch == '*' || ch == '+' || ch == '-';
    }

    //计算符号的优先级
    //返回的数字越大，优先级越高
    public int property(int c){
        if (c == '*' || c == '/'){
            return 1;
        }else if (c == '+' || c == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * 用两个数和一个符号计算返回计算结果
     *
     * @param num1 前一个数
     * @param num2 后一个数
     * @param oper 运算符
     * @return
     */
    public int calculate(int num1, int num2, int oper){

        //表示返回结果
        int res = 0;
        switch (oper){
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;//注意数据的顺序
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意数据的顺序
                break;
            default:
                break;
        }

        return res;
    }

    //栈满
    public boolean isFull(){
        return top == maxSize -1;

    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int num){
        //入栈条件，判断栈是否已满
        if (isFull()){
            System.out.println("栈满！");
            return;
        }

        top++;
        stack[top] = num;
    }

    //出栈
    public int pop(){
        //出栈条件，判断是栈否为空
        if (isEmpty()){
            throw new RuntimeException("栈为空！");
        }

        int value = stack[top];
        top--;

        return value;
    }

    //显示栈的数据(遍历栈)
    public void showStack(){

        //栈为空时
        if (isEmpty()){
            System.out.println("栈为空，没有数据！");
            return;
        }

        //从上向下遍历数据，输出
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}

