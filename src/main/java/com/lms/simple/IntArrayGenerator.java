package com.lms.simple;

/**
 * @author: MissingLi
 * @date: 03/05/18 20:32
 * @Description:
 * @Modified by:
 */
public class IntArrayGenerator {
    /**
     * generate an int[] with given length
     **/
    public int[] getRandomIntArray(int length){
        int[] x = new int[length];
        for (int i = 0; i < length; i++) {
            x[i]= (int) Math.floor(Math.random()*100);
        }
        return x;
    }
    /**
     *  print an int[]
     **/
    public void printIntArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i+": "+arr[i]);
        }
    }
}
