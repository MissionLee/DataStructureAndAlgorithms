Java编程题/也可以使用Scala实现
----------------------------------------------------------------------
1、数组排序（冒泡、选择、插入、归并排序; 根据网址算法逻辑实现代码）
	public int[] sortByA|B|C|D(int[] a);
	https://www.cnblogs.com/DxinSir/p/7289902.html

2、两个数组合并（加条件：两个有序数组合并为一个有序数组）
	public int[] mergeArray(int[] a, int[] b);

3、两个数组交集、并集、补集
	public int[] dealArrayA|B|C(int[] a, int[] b);

4、有序数组中是否有任意两个数的和为某一个固定值
	public boolean isPlused(int[] a, int sum);

5、兔子对数--数列a[1] = 1; a[2] = 1; n > 2时;  a[n] = a[n - 1] + a[n - 2];
	public long getRabbitsCount(int n);

----------------------------------------------------------------------
6、给定正整数 m <= n, 随机返回一个长度为m的整形数组，每个数都属于[0, n)
	public int[] randomMwithinN(int m, int n);

7、给定正整数 m <= n, 返回所有[m, n]之间的素数
	public int[] getPrimeNums(int m, int n);

8、编写程序解决“百钱买百鸡”问题。公鸡五钱一只，母鸡三钱一只，小鸡一钱三只，现有百钱欲买百鸡，共有多少种买法？
	public int[][] buyChicken();

9、二分法求一个正数的平方根(精确到0.01); 二分法:确定目标结果的范围上下区间[x, y]，取其中间值t = (x + y) / 2试测,锁定目标范围为[x, t]或者[t, y]; 重复上述二分操作
	public float getSqrtByDichotomy(float x);

10、证明:x属于[1, 1000], 若是奇数，就对它乘以3再加1,若是偶数,就对它除以2,这样得到一个新数,再按上述计算规则进行计算,一直进行下去,最终必然得到1; 返回任意短整型数x转换为1过程中转换的次数;
	public boolean always2One(int x);
	public int getTransfer2OneNum(int x);


----------------------------------------------------------------------
11、求64位SimHash的汉明距离——给定两个长整型数字，分别将其转换为64位二进制的字符串（左补0），比较两个字符串对应位置上的数字不同的个数，返回该0~64的个数

12、围棋白棋子连通区域个数统计算法（上下左右相邻为连通，左上左下右上右下方向不算）

13、JS五子棋游戏设计（判断输赢的算法）
	
14、JS拼图游戏设计（再来一局算法）
----------------------------------------------------------------------