package com.lms.simple;

import org.junit.jupiter.api.Test;

/**
 * @author: MissingLi
 * @date: 03/05/18 20:31
 * @Description:
 * @Modified by:
 *
 *
 * 2、两个数组合并（加条件：两个有序数组合并为一个有序数组）
 *   public int[] mergeArray(int[] a, int[] b);
 */
public class ArrayMerge {
    public int[] mergeArray(int[] a ,int[] b){
        int al = a.length;
        int bl = b.length;
        int length =al+bl;
        int[] arr = new int[length];
        System.arraycopy(a,0,arr,0,al);
        System.arraycopy(b,0,arr,al,bl);
        return arr;
    }
    public int[] mergeArraySorted(int[] a,int[] b){
        Sort sort = new Sort();
        int[] arr = sort.sortByMerge2(mergeArray(a,b));
        return arr;
    }

    @Test
    public void testMergeArray(){
        IntArrayGenerator ig = new IntArrayGenerator();
        int[] a = ig.getRandomIntArray(5);
        System.out.println("a");
        ig.printIntArray(a);
        int[] b = ig.getRandomIntArray(5);
        System.out.println("b");
        ig.printIntArray(b);
        int[] merge = mergeArray(a,b);
        System.out.println("---------c-----------------");
        ig.printIntArray(merge);
    }
    @Test
    public void testMergeArraySorted(){
        IntArrayGenerator ig = new IntArrayGenerator();
        int[] a = ig.getRandomIntArray(5);
        System.out.println("a");
        ig.printIntArray(a);
        int[] b = ig.getRandomIntArray(5);
        System.out.println("b");
        ig.printIntArray(b);
        int[] merge = mergeArraySorted(a,b);
        System.out.println("---------c-----------------");
        ig.printIntArray(merge);
    }
}
