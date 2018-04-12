package com.lms.algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author: MissingLi
 * @date: 11/04/18 19:23
 */
public class Tree {
    TreeSet<Integer> ts = new TreeSet<Integer>();
    TreeMap<Integer,String> tm = new TreeMap<Integer, String>();

    @Test
    public void testWhetherTheIteratorIsSorted(){
        ts.add(1);
        ts.add(2);
        ts.add(3);
        ts.add(10);
        ts.add(5);
        Iterator x=ts.iterator();
        while (x.hasNext()){
            System.out.println(x.next());
        }
        System.out.println("---------------------------");
        tm.put(1,null);
        tm.put(5,null);
        tm.put(7,null);
        tm.put(2,null);
        tm.put(99,null);
        tm.put(16,null);
        tm.put(11,null);
        Iterator y=tm.keySet().iterator();
        while (y.hasNext()){
            System.out.println(y.next());
        }
    }
}
