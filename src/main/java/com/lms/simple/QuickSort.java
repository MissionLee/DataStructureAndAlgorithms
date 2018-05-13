package com.lms.simple;

import com.lms.simple.util.ArrayUtil;

/**
 * @author: MissingLi
 * @date: 12/05/18 11:39
 * @Description:
 * @Modified by:
 */
public class QuickSort {
    public int[] sort(int[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    /**
     * we need three main parameter
     * 1. the start index of the subArray in the given array
     * 2. the end index of the subArray in the given array
     * 3. take one of the number between [i,j] as Reference-value
     * <p>
     * there are situations that we need to deal with
     * 1.
     **/
    public int[] sort(int[] arr, int i, int j) {
        System.out.println("sort-" + i + "_" + j);
        ArrayUtil.printIntArray(arr);
        System.out.println("==========");
        int referenceValue = arr[i];
        System.out.println("referenceValue:" + i + ":" + referenceValue);
        int left = i + 1;
        int right = j;
        if (left == right) {
            if (arr[left] < referenceValue)
                ArrayUtil.exchangeIntArray(arr, i, j);
        }
        // outer loop / right loop
        for (; right > left; right--) {// 1. start left-- find first number less than r-v
            System.out.println("right:" + right);
            // deal with the situation that right meet with left
            // we should do it right after right --


            if (arr[right] < referenceValue) { // 2. now we find it ,than we start the inner loop
                System.out.println("we find that:" + right + ":" + arr[right] + "<" + referenceValue);
                // inner loop / left loop
                for (; left < right; left++) { // 3. start left++ find first number larger than r-v
                    System.out.println("left:" + left);
                    if (arr[left] > referenceValue) {

                        ArrayUtil.exchangeIntArray(arr, left, right); // now we find two numbers , so we need to exchange them
                        break;// than break inner loop and back to outer loop
                    }
                }
                left--;// when left++ is not qualified with (left < right)
                // we should pull the plus back
            }
            if (right - 1 == left) { //
                System.out.println("right - 1 = left");
                if (arr[right] < referenceValue)
                    ArrayUtil.exchangeIntArray(arr, i, right);
                sort(arr, i, left);
                sort(arr, left, j);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        QuickSort qc = new QuickSort();
        //int[] arr = ArrayUtil.generateRandomIntArray(6);
        //System.out.println("***" + ArrayUtil.getSum(arr));

        int[] arr = new int[]{10,82,8,20,22,24};
        qc.sort(arr);
        System.out.println("***" + ArrayUtil.getSum(arr));
        ArrayUtil.printIntArray(arr);

    }
}
