package com.lms.algorithms.tree.BinaryTree;

import org.junit.jupiter.api.Test;

/**
 * @author: book : Data Structure and Algorithms analysis in Java
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType>{
        BinaryNode(AnyType theElement){this(theElement,null,null);}

        public BinaryNode(AnyType theElement,BinaryNode<AnyType> lt,BinaryNode<AnyType> rt) {
            element=theElement;
            left=lt;
            right=rt;
        }
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;
    public BinarySearchTree() {
        root = null;
    }

    public void makrEmpty(){
        root =null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(AnyType x){
        return  contains(x,root);
    }
    /**
     * 1.如果 被比较的节点为空 返回 false（空节点 也不会有其他的 left right 节点了）
     * 2.根据比较结果大小，不断的向左右 查找 直到结束 或 找到
    */
    public boolean contains(AnyType x,BinaryNode<AnyType> t){
        if (t == null) return  false;
        int compareResult = x.compareTo(t.element);

        if(compareResult>0){
            return contains(x,t.right);
        }else if(compareResult<0){
            return contains(x,t.left);
        }else{
            return true;
        }
    }

    public AnyType findMin(){
        return  findMin(root.element,root.left);
    }
    public AnyType findMin(AnyType cur,BinaryNode<AnyType> t){
        if(t==null){
            return cur;
        }else{
            return findMin(t.element,t.left);
        }

    }
    public AnyType findMax(){
        return findMax(root.element,root.right);
    }
    public AnyType findMax(AnyType cur,BinaryNode<AnyType> t){
        if(t.right ==null){
            return cur;
        }else{
            return findMax(cur,t.right);
        }
    }
    public void insert(AnyType x){
        if(root ==null){
            root = new BinaryNode<AnyType>(x);
            return;
        }else{
            insert(x,root);
        }
    }
    public void insert(AnyType x,BinaryNode<AnyType> t){
        if(t == null){
            t = new BinaryNode<AnyType>(x);
//            System.out.println(t);
            return;
        }else{
            int compareResult = x.compareTo(t.element);
            if(compareResult>0){
                if(t.right==null){
                    t.right= new BinaryNode<AnyType>(x);
                    return;
                }
                insert(x,t.right);
            }else{
                if(t.left==null){
                    t.left = new BinaryNode<AnyType>(x);
                    return;
                }
                insert(x,t.left);
            }
        }
    }
    public void remove(AnyType x){
        remove(x,root);
    }
    public BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
        //对于节点t 进行处理，返回 处理过后的t（删除指定元素后的t）
        // 我们的思路就是： 如果t空的，直接返回
        //                如果 指定元素可能在 左边，那么让左边 = 新的左边
        //                如果 指定元素可能在 右边，那么让右边 = 新的右边
        //              而，新的左边，右边，就是 递归得到的
        if(t ==null) return t;
        int compareResult = x.compareTo(t.element);
        if(compareResult==0){//如果 当前的t就是 就是目标
            System.out.println("find the number,now delete it");
            
            if(t.left!=null&&t.right!=null){//如果 左右都有节点
                System.out.println("the node has two children node");
                t.element=findMin(t.element,t.right);//那么当前值替换为右侧子树中最小值
                t.right=remove(t.element,t.right);//右侧变为 右侧去掉最小值后新的子树
            }else  if(t.left==null&&t.right==null){
                //如果当前没有子节点了
                t=null;
            }else{
               return t=t.left==null?t.right:t.left;//在只有一个子节点的时候，用子节点替换当前节点
            }
        }else if(compareResult>0){
            t.right = remove(x,t.left);
        }else{
            t.left = remove(x,t.left);
        }

        return t;
//        BinaryNode<AnyType> theNode = find(x,t);
//        System.out.println(theNode);
//        if(theNode != null){
//            if(theNode.left!=null){
//                System.out.println(" the node  =  the node .left");
//                theNode=theNode.left;
//                return;
//            }else if(theNode.right!=null){
//                System.out.println("the node = the node .right");
//                theNode=theNode.right;
//
//                return;
//            }else{
//                System.out.println("make the node = null");
//                theNode=null;
//                System.out.println(theNode);
//                return;
//            }
//        }
    }
    private BinaryNode<AnyType> find(AnyType x,BinaryNode<AnyType> t){
        if(t==null ){
            return null;
        }else{
            int compareReuslt = x.compareTo(t.element);
            if(compareReuslt==0) {
                System.out.println(" find the number "+x.toString());
                System.out.println(t);
                return t;
            }else if(compareReuslt>0){
                return find(x,t.right);
            }else{
                return find(x,t.left);
            }
        }
    }
    public void printTree(){

    }
    public int depth(){
        return 0;
    }
    @Test
    public void test01(){
        BinarySearchTree bst = new BinarySearchTree<Integer>();
        bst.insert(1);
        bst.insert(11);
        bst.insert(12);
        bst.insert(163);
        bst.insert(134);
        bst.insert(541);
        bst.insert(14);
        bst.insert(12);
        bst.insert(10);
        bst.insert(91);
//        System.out.println(bst.root.right.element);
//        System.out.println(bst.root);
//        System.out.println(bst.isEmpty());
        System.out.println(bst.contains(91));
        System.out.println(bst.contains(11));
        bst.remove(11);
        for (int i = 0; i < 100; i++) {

        }
        System.out.println(bst.contains(11));
    }
}
