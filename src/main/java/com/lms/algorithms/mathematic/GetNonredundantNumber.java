package com.lms.algorithms.mathematic;

/**
 * @author: MissingLi
 * @date: 16/04/18 19:30
 * @Description:
 * @Modified by:
 */
public class GetNonredundantNumber {
    public static void main(String[] args) {
        // get a three digits with [1,2,3,4]
        // 1. no repeating between numbsers
        // 2. no repeating in numbers
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    if(i!=j&&i!=k&&j!=k){
                        System.out.println(i*100+j*10+k);
                    }
                }
            }
        }
    }
}
