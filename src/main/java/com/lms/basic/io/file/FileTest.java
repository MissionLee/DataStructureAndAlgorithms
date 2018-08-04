package com.lms.basic.io.file;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 16:19 2018/7/24
 **/
public class FileTest {
    public static void main(String[] args){
        /***
         * 我在给这个main加参数的时候输入 *.git 报错，输入\\*.git 正常 (实际上这个时个错误的写法)
         *
         * 例如：要匹配所有.java结尾的文件 ：   .*.java    => 任意字符0到n个 + .java结尾
         * Exception in thread "main" java.util.regex.PatternSyntaxException: Dangling meta character '*' near index 0
         */
        Pattern.compile("git");
        File path = new File(".\\src\\main\\java\\com\\lms\\algorithms\\mathematic");
        String[] list;
        if(args.length == 0)
            list=path.list();
        else
            list = path.list(new DirFilter(args[0]));


        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String dirItem:list){
            System.out.println(dirItem);
        }
    }
}
