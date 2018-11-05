package test;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 20:12 2018/10/25
 **/
public class BasicTest2 {

    public static void main(String[] args) {
        BasicTest basicTest = new BasicTest();
        System.out.println(basicTest.innerClasses);
        // 这里报错提示 cannot access ‘length’ in _Dummy_.__Array
        System.out.println(basicTest.innerClassees.length);


    }
}
