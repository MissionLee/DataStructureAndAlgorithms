package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author: MissingLi
 * @date: 26/04/18 10:43
 * @Description:
 * @Modified by:
 */
public class BasicTest {
    private class InnerClass{
        int num=1;
    }
    public class InnerClasse{
        int num =2;
    }

    InnerClass innerClasses[] = new InnerClass[10];
    InnerClasse innerClassees[] = new InnerClasse[10];

    public static void main(String[] args) {
        InnerClass innerClasses[] = new InnerClass[10];
        InnerClasse innerClassees[] = new InnerClasse[10];
        System.out.println(innerClassees.length);
        System.out.println(innerClasses.length);
//        Integer[] integers = new Integer[10];
//        String a = "ab";
//        String b = a.intern();
//        System.out.println(a==b);
//        List<Integer> list = new ArrayList<Integer>();
//        for (int i = 0; i < 1000; i++) {
//            list.add(i);
//        }
//        list.stream().forEach(new Consumer<Integer>() {
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        });


    }
}
