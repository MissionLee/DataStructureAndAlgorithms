package com.lms.algorithms.mathematic;

import java.util.Stack;

/**
 * @author: MissingLi
 * @date: 16/04/18 19:36
 * @Description:
 * @Modified by:
 */
public class PrintDiamond {
    /**          length = 3 width =5
     *            i j
     *      *     1-3
     *     * *    2-2 2-4
     *    *   *   3-1 3-5
     *     * *    4-2 4-4
     *      *     5-3
     * */
    public void print(int length){
        Stack<String> s1 = new Stack<String>();
        Stack<String> s2 = new Stack<String>();

        if(length%2==1){
          int width = length*2 -1;
            for (int i = 1  ; i <= width; i++) {
                for (int j = 1; j <= width; j++) {
                    if(i<=length){
                        if((j+i)==(length+1)){
                            System.out.print("*");
                            s1.push("*");

                        }else{
                            System.out.print("_");
                            s1.push("_");
                        }
                    }else{

                        System.out.print(s1.pop());
                    }

                }
                System.out.println();
            }
        }else{
            System.out.println("can not print with the length of an odd number");
        }
    }
    public static void main(String[] args) {
        int length = 3;
        PrintDiamond p = new PrintDiamond();
        p.print(length);
    }
}
