package com.lms.simple.util;

import org.junit.jupiter.api.Test;

/**
 * @author: MissingLi
 * @date: 12/05/18 14:41
 * @Description:
 * @Modified by:
 */
public class ArrayUtil<T> {

    //change the number of the given index of an int[]
    public static int[] exchangeIntArray(int[] arr,int i,int j){
        System.out.println("exchange: "+i+" "+j);
        if(i>=arr.length||j>=arr.length){
            throw new ArrayIndexOutOfBoundsException(i>=arr.length?i:j);
        }
        arr[i]^=arr[j];
        arr[j]^=arr[i];
        arr[i]^=arr[j];
        return arr;
    }

    // generate an int array of a given length
    public static int[] generateRandomIntArray(int length){
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i]= (int) Math.floor(Math.random()*100);
        }
        return arr;
    }

    // print array with index
    public  static void  printIntArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i+": "+arr[i]);
        }

    }

    public static int getSum(int[] arr){
        int sum = 0;
        for (int x:arr
             ) {
            sum+=x;
        }
        return sum;
    }
    @Test
    public void testExchangeIntArray(){
        exchangeIntArray(new int[10],8,7);
    }
}
