package com.lms.algorithms.tree.BinaryTree;


import java.util.Stack;



/**
 * @author: book : Data Structure and Algorithms analysis in Java
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        public BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makrEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * 1.如果 被比较的节点为空 返回 false（空节点 也不会有其他的 left right 节点了）
     * 2.根据比较结果大小，不断的向左右 查找 直到结束 或 找到
     */
    public boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) return false;
        int compareResult = x.compareTo(t.element);

        if (compareResult > 0) {
            return contains(x, t.right);
        } else if (compareResult < 0) {
            return contains(x, t.left);
        } else {
            return true;
        }
    }

    // 找最值的时候，我们很自然的会想到一种方法： 递归
    //  递归确实非常好用，但是递归也的效率毕竟有制约
    //  在 书中 看到了 用递归和 用循环 两种方式来完成 找最值的方法
    // 我再这里 也在不同的实现里面尝试一下
    public AnyType findMinValue() {
        return findMinValue(root.element, root.left);
    }

    public AnyType findMinValue(AnyType cur, BinaryNode<AnyType> t) {
        if (t == null) {
            return cur;
        } else {
            return findMinValue(t.element, t.left);
        }

    }

    private BinaryNode<AnyType> findMinNode() {
        return findMinNode(root);
    }

    private BinaryNode<AnyType> findMinNode(BinaryNode<AnyType> t) {
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    public AnyType findMaxValue() {
        return findMaxValue(root.element, root.right);
    }

    public AnyType findMaxValue(AnyType cur, BinaryNode<AnyType> t) {
        if (t.right == null) {
            return cur;
        } else {
            return findMaxValue(cur, t.right);
        }
    }

    public void insert(AnyType x) {
        if (root == null) {
            root = new BinaryNode<AnyType>(x);
            System.out.println(root);
            System.out.println(root.element);
            return;
        } else {
            System.out.println("insert" + x);
            insert(x, root);

        }
    }

    public void insert(AnyType x, BinaryNode<AnyType> t) {

        int compareResult = x.compareTo(t.element);
        if (compareResult > 0) {
            if (t.right == null) {
                t.right = new BinaryNode<AnyType>(x);
            } else {
                insert(x, t.right);
            }
        } else {
            if (t.left == null) {
                t.left = new BinaryNode<AnyType>(x);
            } else {
                insert(x, t.left);
            }
        }

    }

    public void remove(AnyType x) {
        remove(x, root);
    }

    public BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        //对于节点t 进行处理，返回 处理过后的t（删除指定元素后的t）
        // 我们的思路就是： 如果t空的，直接返回
        //                如果 指定元素可能在 左边，那么让左边 = 新的左边
        //                如果 指定元素可能在 右边，那么让右边 = 新的右边
        //              而，新的左边，右边，就是 递归得到的
        if (t == null) return t;
        int compareResult = x.compareTo(t.element);
        if (compareResult == 0) {//如果 当前的t就是 就是目标
            System.out.println("find the number,now delete it");

            if (t.left != null && t.right != null) {//如果 左右都有节点
                System.out.println("the node has two children node");
                t.element = findMinValue(t.element, t.right);//那么当前值替换为右侧子树中最小值
                t.right = remove(t.element, t.right);//右侧变为 右侧去掉最小值后新的子树
            } else if (t.left == null && t.right == null) {
                //如果当前没有子节点了
                t = null;
            } else {
                return t = t.left == null ? t.right : t.left;//在只有一个子节点的时候，用子节点替换当前节点
            }
        } else if (compareResult > 0) {
            t.right = remove(x, t.left);
        } else {
            t.left = remove(x, t.left);
        }

        return t;

    }

    private BinaryNode<AnyType> find(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        } else {
            int compareReuslt = x.compareTo(t.element);
            if (compareReuslt == 0) {
                System.out.println(" find the number " + x.toString());
                System.out.println(t);
                return t;
            } else if (compareReuslt > 0) {
                return find(x, t.right);
            } else {
                return find(x, t.left);
            }
        }
    }
    //printTree 实际上就是一次对整个树的遍历
    //我在看书的时候看到 对于树的几种遍历
    //  前序遍历：根节点->左子树->右子树（根节点在前面）

    //中序遍历：左子树->根节点->右子树（根节点在中间）

    //后序遍历：左子树->右子树->根节点（根节点在后边）

    //  递归的 -> 与树的结构对应，所以比较容易实现
    //  非递归的 ->
    public void printTreePreorderTraversal() {

        printTreePreorderTraversal(root);
    }

    public void printTreePreorderTraversal(BinaryNode<AnyType> t) {
        // recursive
        // the Preorder Traversal is normally used  in a Search condition
        if (t == null) return;
        System.out.println(t.element);
        if (t.left != null) {
            printTreePreorderTraversal(t.left);

        }
        if (t.right != null) {
            printTreePreorderTraversal(t.right);
        }
    }

    public void printTreeInorderTraversal() {
        printTreeInorderTraversal(root);
    }

    public void printTreeInorderTraversal(BinaryNode<AnyType> t) {
        // non-recursive
        // the output will be in order
        Stack<BinaryNode> s = new Stack<BinaryNode>();
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

    public void printTreePostorderTraversal() {
        // non-recursive
    }

    public int depth() {
        return 0;
    }


    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree<Integer>();
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(45);
        bst.insert(35);
        bst.insert(42345);
        bst.insert(374);
        bst.insert(2436);
        bst.insert(34367);
        bst.insert(45791);
        bst.insert(34414);
        bst.insert(8);
        bst.insert(55);

        for (int i = 0; i < 100; i++) {

        }
        System.out.println(bst.contains(11));
        System.out.println(bst.root);
        System.out.println(bst.root.right);
        System.out.println("-----------------------------------");
        bst.printTreePreorderTraversal();
        System.out.println("----------------------");
        bst.printTreeInorderTraversal();
    }
}
