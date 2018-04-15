package com.lms.algorithms.tree.BinaryTree;


import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author: MissingLi
 * @date: 14/04/18 10:23
 * @Description:
 * @Modified by:
 */
public class BinarySearchTreeAVL<AnyType extends Comparable<? super AnyType>> {
    // Allowed imbalance
    private static final int ALLOWED_IMBALANCE = 1;

    private static class AvlNode<AnyType> {
        AvlNode(AnyType e) {
            this(e, null, null);
        }

        AvlNode(AnyType e, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            element = e;
            left = lt;
            right = rt;
            height = 0;
        }

        int depu = 1;
        AnyType element;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;
    }

    public BinarySearchTreeAVL() {

    }

    private AvlNode root;


    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    public void insert(AnyType x) {
        root = insert(x,root);
    }
//    private void insert(AnyType x, AvlNode<AnyType> t){
//
//    }

    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
//        System.out.println("insert");
        //如果 t为空，在此处创建一个 节点
        //否则用 compareTo 判断将 insert操作 交接给 这个节点的左右节点
        if (t == null) {
//            System.out.println("t == null");
            t = new AvlNode<AnyType>(x, null, null);
        } else {
            int compareResult = x.compareTo(t.element);
            if (compareResult > 0) {
                t.right = insert(x, t.right);

//                t.height=Math.max(height(t.left),height(t.right));
            } else if (compareResult < 0) {
                t.left = insert(x, t.left);
//                t.height=Math.max(height(t.left),height(t.right));
            } else {
                t.depu++;
            }
        }
        return balance(t);
    }

    private AvlNode<AnyType> balance(AvlNode<AnyType> t) {
        //要求：  1.维护每个节点的高度 height ： 每一次递归返回，所在节点都是 两个子节点最高高度+1
        //       2.维持平衡

        //对于一个节点 判断是否平衡，首先认为 在当次 insert 之前是平衡的
        // 然后 造成不平衡
        // 1. 对于叶子节点 没啥影响 因为左右都是 null
        // 2. 对于其他节点，每次判定是否平衡，并且进行转换
        if(t==null){
            return t;
        }
        // s1 : 左树偏高
        if(height(t.left)-height(t.right)>ALLOWED_IMBALANCE){
            // 左左偏高
            if(height(t.left.left)>=height(t.left.right))
                t=rotateWithLeftChild(t);
            // 左右偏高
            else
                t=doubleWithLeftChild(t);
        }else if(height(t.right)-height(t.left)>ALLOWED_IMBALANCE){
            if(height(t.right.right)>=height(t.right.left))
                t=rotateWithRightChild(t);
            else
                t=doubleWithRightChild(t);
        }
        t.height = Math.max(height(t.left),height(t.right))+1;
        return t;
    }
    // 关于旋转，原理分析请参考 Data Sturcture and Algorithm Analysis in Java书中的讲解
    // 这里 是我的总结 : 
    //       [这里的左旋，右旋就是 书中的左旋和右旋。下面几个方法中的左右，指的是 左偏高，右偏高]

    // 左左 ： 单-右旋转
    // 右右 ： 单-左旋转
    // 左右 ： 右旋（深层）+左旋
    // 右左 ： 左旋（深层）+右旋
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2){
        //右旋转

        // 当前元素 左左偏高
        // 1. t.left 转为t
        // 2. t 转为 t.right
        // 3. t.left.left 转为 t.left
        //               O(k2)       U(k1)
        //            U(k1) O =>   U    O(k2)
        //          U   K              K  O
        AvlNode<AnyType> k1=k2.left;
        k2.left=k1.right;
        k1.right=k2;
        k2.height=Math.max(height(k2.left),height(k2.right))+1;
        k1.height=Math.max(height(k1.left),height(k1.right))+1;
        return k1;

        //下方这一部分代码 是我最初的想法，其中有一些问题
        // 1. 忘了更新高度
        // 2. 按照我这个写法，是没法兼容 容许2层以及以上的高度差距的 
//        AvlNode<AnyType> newt = new AvlNode<AnyType>(t.left.element);
//        newt.right=t;
//        newt.left=t.left.left;
//        return newt;
    }
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3){
        k3.left=rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2){
        // 左旋转
        
        //        O(k2)              U(k1)
        //      O    U(k1)  =>    O(k2) U
        //         K   U        O   K
        AvlNode<AnyType> k1 =k2.right;
        k2.right=k1.left;
        k1.left=k2;
        k2.height=Math.max(height(k2.left),height(k2.right))+1;
        k1.height=Math.max(height(k1.left),height(k1.right))+1;
        return k1;
    }
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3){
        k3.right=rotateWithLeftChild(k3.right);
        return  rotateWithRightChild(k3);
    }

    public void printTreeInorderTraversal() {
        printTreeInorderTraversal(root);
    }

    public void printTreeInorderTraversal(AvlNode<AnyType> t) {
        // non-recursive
        // the output will be in order
        Stack<AvlNode> s = new Stack<AvlNode>();
        while (t != null || !s.empty()) {
            while (t != null) {
                s.push(t);
                t = t.left;
            }
            if (!s.empty()) {
                t = s.pop();
                System.out.println(t.element);
                t = t.right;
            }
        }
    }
    public void printTree(){
        printTree(root);
    }
    public void printTree(AvlNode<AnyType> t){
        // recursive
        if(t!=null){
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    public void remove(AnyType x){
        remove(x,root);
    }
    private AvlNode<AnyType> remove(AnyType x,AvlNode<AnyType> t){
        if(t==null){
            return t;
        }
        int compareReuslt = x.compareTo(t.element);
        if(compareReuslt <0){
            t.left=remove(x,t.left);
        }else if(compareReuslt>0){
            t.right=remove(x,t.right);
        }else if(t.left!=null&&t.right!=null){
            t.element=findMin(t.right).element;
            t.right=remove(t.element,t.right);
        }else{
            t=(t.left!=null)?t.left:t.right;

        }
        return balance(t);
    }

    private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }
    @Test
    public void TestInsert() {
        BinarySearchTreeAVL binarySearchTreeAVL = new BinarySearchTreeAVL<Integer>();
        binarySearchTreeAVL.insert(2);
        binarySearchTreeAVL.insert(1);
        binarySearchTreeAVL.insert(3);
        binarySearchTreeAVL.insert(3);
        binarySearchTreeAVL.insert(31);
        binarySearchTreeAVL.insert(32);
        binarySearchTreeAVL.insert(13);
        binarySearchTreeAVL.insert(1233);
        binarySearchTreeAVL.insert(35);
        binarySearchTreeAVL.insert(36);
        binarySearchTreeAVL.insert(8234);
        binarySearchTreeAVL.insert(0);

//        System.out.println(binarySearchTreeAVL.root.right.depu);
//        System.out.println(binarySearchTreeAVL.root.height);
//        binarySearchTreeAVL.printTreeInorderTraversal();
        binarySearchTreeAVL.printTree();
    }
    @Test
    public void TestHeight(){
        BinarySearchTreeAVL ba2 = new BinarySearchTreeAVL<Integer>();
        for (int i = 0; i < 10000; i++) {
            ba2.insert(Math.floor(Math.random()*1000)-500);
            if(i%1000==0){
                System.out.println(ba2.root.element);
                System.out.println(ba2.root.height);
            }
        }

//        ba2.printTreeInorderTraversal();
    }
}
