package com.lms.simple;

import org.junit.jupiter.api.Test;

/**
 * @author: MissingLi
 * @date: 25/04/18 19:21
 * @Description:
 * @Modified by:
 */
public class Sort {
    /**
     *  print an int[]
     **/
    public void printIntArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i+": "+arr[i]);
        }
    }
    /**
     * generate an int[] with given length
     **/
    public int[] getRandomIntArray(int length){
        int[] x = new int[length];
        for (int i = 0; i < length; i++) {
                x[i]= (int) Math.floor(Math.random()*100);
        }
        return x;
    }
    /**
     * exchange two numbers in the given int[]
     **/
    private void exchange(int[] a,int x,int y){
        if(x>a.length||y>a.length){
            throw new IndexOutOfBoundsException("can not exchange");
        }
        a[x]=a[x]^a[y];
        a[y]=a[x]^a[y];
        a[x]=a[x]^a[y];
    }
    public int[] sortBySelect(int[] a){
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if(a[i]>a[j]){
                    exchange(a,i,j);
                }
            }
        }
        return a;
    }
    public int[] sortBySelect2(int[] arr){
        int max;
        for (int i = 0; i < arr.length; i++) {
            max = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]>arr[max])
                    max=j;
            }
            if(i!=max)exchange(arr,i,max);
        }
        return arr;
    }
    public int[] sortByBubble(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    exchange(arr,j,j+1);
                }
            }
        }
        return arr;

//         3-2-1-0
//         2-3-1-0     i=1
//       tmp=1 -1  2-3-3-0
//             -2  2-2-3-0
//             out 1-2-3-0
//       tmp 0  -1 1-2-3-3
//              -2 1-2-2-3
//              -3 1-1-2-3
//              out 0-1-2-3
    }
    public int[] sortByInsert(int[] arr){
        // 比较麻烦的就是 弄清楚 +1 关系 -- 关系什么的
        int tmp ;
        int j;
        for (int i = 1; i <arr.length; i++) {
            tmp =arr[i];
            for ( j = i-1; j >=0&&tmp<arr[j]  ; j--) {
                // if  tmp<arr[j] mean that tmp can be placed at index-j
                // so we move index-j to index-(j+1)
                //tmp 小于 arr[j]说明 tmp 可以被放在当前的 j 位置，
                // 那就把 j位置的内容想后挪动一位，并且继续比较
                arr[j+1]=arr[j];
            }
            // 当发现tmp比 j位置的数 大了之后，就把 tmp放在 j位置后面一位
            //else we put tmp at index-(j+1)
            // attention: the number index-(j+1) has already put in
            // index-(j+2)  => the j-- 
            // 刚开始没注意到 上面for循环 的 j-- 是执行了的，所以下面是 arr[j+1]
            arr[j+1]=tmp;
        }
        return arr;
    }
    public int[] sortByMerge(int[] arr){
        int m = getMergeMiddle(0,arr.length-1);
        System.out.println(m);
        sortByMerge(arr,0,m,arr.length-1);
        return arr  ;
    }
//    1-2-3-4-5-6-7
//    1-2-3-4   5-6-7
//    1-2 3-4  5-6-7
//    1-2-3-4 5-6-7
//    1-2-3-4 5-6 7
//    1-2-3-4 5-6-7
//    1-2-3-4-5-6-7
//
//    1-2-3-4-5-6-7
//
//    1-2 3-4 5-6 7
//
//    1-2-3-4 5-6-7
//    1-2-3-4-5-6-7
    /**
     *  我的思路 需要一个 merge 函数
     *   merge： 给定一个arr 能够对 这个 arr的 一段 进行 并归排序
     *   sortByMerge ： 分解给定的arr ，让 arr 分解为 数个长度为 1 或者 2的子数组
     *                  然后从最底层，想上对最小的子数组进行递归 的 merge
     *   加入由 1，2，3，4 四个元素，相当于 1 ，2 并归， 3，4并归。最后 1，2，3，4并归
     **/
    private void sortByMerge(int[] arr,int s,int m,int t){// 我个人认为 sortByMerge2 方法更好一些
        /**
         * 首先我们约定： 基数个元素的时候， 如 1，2，3，4，5  分为 1，2，3 一组，4，5一组
         * 
         * 在这种约定的情况下， 我们前半个分组 最小长度为2 后半个分组最小长度为 1
         **/
        if(t-s>1){// 如果 t-s>1 说明 当前分组最少 还有 3个元素 可以分为 2-1 或者以上
            int ss = s;
            int st = m-1;
            int sm = getMergeMiddle(ss,st);
            sortByMerge(arr,ss,sm,st);//对前半个分组进行 递归sortByMerge
            if(t-m>0){//如果t-m>0 说明 后半个分组至少还有 2 个元素 那么还需要 merge
                int ms=m;
                int mt=t;
                int mm = getMergeMiddle(ms,mt);
                sortByMerge(arr,ms,mm,mt);
            }else{// 如果t-m = 0 那么 就不需要对后分组做什么处理了（仅有一个元素）
            }
            merge(arr,s,m,t); //把当前的两个分组 merge了，就可以回归一层了

        }else{// t-s = 1 说明现在处理的内容 只有两个元素，那么merge这两个元素
            merge(arr,s,m,t);
        }
    }
//    1-2-3-4-5
//    1-2 3-4 5
//    1-2-3-4 5
    public int[] sortByMerge2(int[] arr){
        /**
         * 因为我们在最细粒度 是 2个元素进行 merge，所以有一种更炫酷的merge循环思路
         * 
         *  从头开始 两两分组，然后 四四分组。。。
         **/
        int length =arr.length;
        for (int i = 2; i/2 < length; i*=2) {// i=2,4,8,16
            System.out.println("------i:"+i);
            for (int j = 0; j < length; j+=i) {
                int s=j;
                int m=(j+i+j)/2;
                int t=(j+i)-1;
                if(t>=length){
                    t=length-1;
                }
                //System.out.println("s:"+s+"|m:"+m+"|t:"+t);
                merge(arr,s,m,t);
            }
            printIntArray(arr);
        }
        return arr;
    }
    private int getMergeMiddle(int s, int t){
        int x =0;
        if((t-s)%2==0){// we have odd numbers of elements
            x=(t+s)/2+1;
        }else {
            x=(t+s+1)/2;
        }
        return x;
    }
    /**
     * 　*　<pre>
     * 　*　二路归并
     * 　*　原理：将两个有序表合并和一个有序表
     * 　*　</pre>
     * 　*
     * 　*　@param　a
     * 　*　@param　s
     * 　*　第一个有序表的起始下标
     * 　*　@param　m
     * 　*　第二个有序表的起始下标
     * 　*　@param　t
     * 　*　第二个有序表的结束小标
     * 　*
     *  test  arr[2,3,1]
     */
//    1-2-0-3
//    0-2-1-3
    private void merge(int[] arr,int s,int m,int t){
        if(s>m||m>t||s>t) return;
        int[] tmp = new int[t-s+1];// 存放排序好的内容的数组
        int st=s;
        int md=m;
        for (int i = 0; i < t-s+1; i++) {
            if(md<=t&&st<m){// 如果 前后两个分组都还有剩余元素，那么先进行判断，然后放入tmp
                if(arr[st]>arr[md]){
                    tmp[i]=arr[md];
                    md++;
                }else{
                    tmp[i]=arr[st];
                    st++;
                }
            }else if(st<m){//如果其中一个分组没有元素了，那么把剩下一个分组的剩下的元素一次加入tmp
                tmp[i]=arr[st];
                st++;
            }else if(md<=t){
                tmp[i]=arr[md];
                md++;
            }
        }
        System.arraycopy(tmp,0,arr,s,tmp.length);
    }
    public void sortByQuick(int[] arr){
        // todo: quick sort
    }
    @Test
    public void testExchange(){
        int[] a = new int[3];
        exchange(a,4,2);
    }
//     1-3-5
//     2-4-5
//    new 0-0-0-0-0-0
//        1 2 3 4


    @Test
    public void testSelect(){
        int[] arr = getRandomIntArray(10);
        printIntArray(arr);
        arr=sortBySelect(arr);
        printIntArray(arr);
    }
    @Test
    public void testBubble(){
        int[] arr = getRandomIntArray(10);
        printIntArray(arr);
        System.out.println("------");
        arr = sortByBubble(arr);
        printIntArray(arr);
    }
    @Test
    public void testSelect2(){
        int[] arr = getRandomIntArray(10);
        printIntArray(arr);
        System.out.println("----------");
        arr = sortBySelect2(arr);
        printIntArray(arr);
    }
    @Test
    public void testInsert(){
        int[] arr = getRandomIntArray(10);
        printIntArray(arr);
        System.out.println("--------");
        arr = sortByInsert(arr);
        printIntArray(arr);
    }
    @Test
    public void testBasicMerge(){
        int[] arr = getRandomIntArray(5);
        printIntArray(arr);
        merge(arr,0,3,4);
        printIntArray(arr);
    }
    @Test
    public void testMerge(){
        int[] arr = getRandomIntArray(50);
        printIntArray(arr);
        System.out.println("----------------");
        arr=sortByMerge(arr);
        printIntArray(arr);
    }
    @Test
    public void testGetMiddleMerge()
    {
        int x = getMergeMiddle(5,9);
        System.out.println(x);
        int y = getMergeMiddle(2,4);
        System.out.println(y);
    }
    @Test
    public void testMerge2(){
        int[] arr = getRandomIntArray(16);
        printIntArray(arr);
        System.out.println("----------------");
        arr=sortByMerge2(arr);
        printIntArray(arr);
    }

    public int[] myMerge(int[] arr,int[] b){
        int[] tmp= new int[arr.length+b.length];
        int al=0;
        int bl=0;
        for (int i = 0; i < tmp.length; i++) {
            if(al< arr.length&&bl<b.length){
                tmp[i]=(arr[al]<b[bl]?arr[al++]:b[bl++]);
            }else if(al<arr.length){
                tmp[i]=arr[al++];
            }else{
                tmp[i]=b[bl++];
            }

        }


        return tmp;
    }
    @Test
    public void testMyMerge(){
        int[] a = {1,3,5,7,9};
        int[] b = {2,4,5};
        int[] c= myMerge(a,b);
        printIntArray(c);
    }
}
