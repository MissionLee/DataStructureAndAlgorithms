package com.lms.basic.io.stream;

import java.io.*;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 17:10 2018/7/25
 **/
public class IOStream {
    static String file= "IOStream.out";
    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("asd"));

        BufferedReader in = new BufferedReader(new StringReader("MissionLee\nHello\n\nHurry up"));
        // 用自己定义的方法读取一个文件
        BufferedReader in2 = new BufferedReader(new StringReader(BufferedInputFile.read("F:\\03GitHubProject\\DataStructureAndAlgorithms\\src\\main\\java\\com\\lms\\basic\\io\\stream\\BufferedInputFile.java")));


        // 以 FileWriter 构建一个输出
        // 然后用缓冲输出提升效率
        // 用 PrintWriter 获取方便的方法
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while((s=in2.readLine())!=null)
            out.println(lineCount++ + ": " + s);
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
