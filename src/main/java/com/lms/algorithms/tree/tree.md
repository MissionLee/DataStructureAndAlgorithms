# Tree 

[An article talks about Tree -In Chinese/一篇介绍Tree结构的文章-中文](https://blog.csdn.net/zhangyuan19880606/article/details/51220561)

In Java Collections,we have two Data Structure type of Tree: TreeMap/TreeSet

And we can see in Source code of TreeSet that:

在Java Collections里面有两种Tree结构的数据，实际上值有一个实现，因为TreeSet里面可以看到：

```java
    
    /**
     * Constructs a new, empty tree set, sorted according to the
     * natural ordering of its elements.  All elements inserted into
     * the set must implement the {@link Comparable} interface.
     * Furthermore, all such elements must be <i>mutually
     * comparable</i>: {@code e1.compareTo(e2)} must not throw a
     * {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the set.  If the user attempts to add an element
     * to the set that violates this constraint (for example, the user
     * attempts to add a string element to a set whose elements are
     * integers), the {@code add} call will throw a
     * {@code ClassCastException}.
     */
    public TreeSet() {
        this(new TreeMap<E,Object>());
    }
    // 实际上， TreeSet 在底层使用的就是TreeMap
```

根据Tree结构的简单原理，其中有很重要的一点是其天然的排序特性。我们来做一下简单的测试

In the tree-DataStructure , all the Data is sorted , here is a simple test.

```java
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
    public void test(){
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
```

结构/OutPut

```note
1
2
3
5
10
---------------------------
1
2
5
7
11
16
99
```