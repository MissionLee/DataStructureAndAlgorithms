package com.lms.simple.util;

import com.lms.simple.QuickSort;
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
    public static int[] distinct(int[] arr){
        int[] tmp = new int[arr.length];
        int len=0;
        QuickSort qs = new QuickSort();
        arr = qs.sort(arr);
        tmp[0]=arr[0];
        for (int i = 1; i < arr.length; i++) {
            //System.out.println("len:"+len);
            if(tmp[len]==arr[i]){
                continue;
            }else{
                tmp[++len]=arr[i];
            }
        }
        int[] result = new int[++len];
        System.arraycopy(tmp,0,result,0,len);
        //System.out.println("-=-=-=-=-=-=-=-");
        //printIntArray(result);
        return result;
    }
    @Test
    public void testDistinct(){
        int[] arr = new int[]{1,5,5};
        printIntArray(arr);
        System.out.println("===================");
        arr = distinct(arr);
        System.out.println("======================");
        printIntArray(arr);
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
