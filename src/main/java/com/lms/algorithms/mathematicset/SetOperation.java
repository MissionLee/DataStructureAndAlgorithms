package com.lms.algorithms.mathematicset;

import com.lms.simple.QuickSort;
import com.lms.simple.util.ArrayUtil;

/**
 * @author: MissingLi
 * @date: 15/05/18 19:49
 * @Description:
 * @Modified by:
 */
public class SetOperation {
    public static int[] intersaction(int[] orig, int[] refer) {
        int tmplength = orig.length;
        int[] tmp = new int[tmplength];
        int length = 0;// flag of tmp(the array to store the intersaction)

        orig = ArrayUtil.distinct(orig);
        //System.out.println("orig--------");
        //ArrayUtil.printIntArray(orig);
        //System.out.println("refer----------");
        refer = ArrayUtil.distinct(refer);
        //ArrayUtil.printIntArray(refer);
        int start = 0; //
        for (int i = 0; i < tmplength; i++) {
            for (int j = start; j < refer.length; j++) {
                if (orig[i] > refer[j]) { // if orig[i] is larger than refer[j] continue
                    continue;
                } else if (orig[i] == refer[j]) {//if == we find one
                    tmp[length++] = orig[i];     // put the number into result
                    start = ++j;                 // start next
                    break;
                } else {                         //if we find that orig[i] is less that refer[j]
                    start = j;                   //is means that orig[i] ls less that refer[++j]
                    break;                       // so we break,and next for-i we start for-j at this place
                }                                // refer[j] is larger than orig[i] but it may be equal with orig[++i]
            }

        }
        int[] result = new int[length];
        System.arraycopy(tmp, 0, result, 0, length);
        return result;
    }

    public static void main(String[] args) {
        //int[] a = ArrayUtil.generateRandomIntArray(10);
        //ArrayUtil.printIntArray(a);
        //int[] b = ArrayUtil.generateRandomIntArray(10);
        //ArrayUtil.printIntArray(b);
        int[] a = new int[]{0, 56, 75};
        int[] b = new int[]{2, 75, 99};
        int[] c = intersaction(a, b);
        System.out.println("asw-----");
        ArrayUtil.printIntArray(c);
    }
}
