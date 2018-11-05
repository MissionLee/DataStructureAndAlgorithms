package com.lms.learn;

import java.io.*;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 15:33 2018/9/17
 **/
public class LearnJavaClone implements Cloneable{
    public int  age;
    public Object clone(){
        LearnJavaClone l = null;
        try{
            l = (LearnJavaClone)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return l;
    }
    public void superrr(){
        System.out.println(super.getClass());
    }

    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);

        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return  oi.readObject();
    }
    public static void main(String[] args) throws CloneNotSupportedException {
        LearnJavaClone l1 = new LearnJavaClone();
        l1.age = 10;
        LearnJavaClone l2 = (LearnJavaClone) l1.clone();
        System.out.println("l1 :"+l1);
        System.out.println("l2 :"+l2);
        System.out.println(l2.age);
        l2.age = 100;
        System.out.println("l1.age = "+l1.age);

        LearnJavaClone l3 = (LearnJavaClone) l1.clone();
        System.out.println("l3 :"+l3);
        System.out.println("===================");
        l1.superrr();
        System.out.println("Serializable");
        String a = "ab";
        System.out.println(a==new String("ab"));
    }
}
