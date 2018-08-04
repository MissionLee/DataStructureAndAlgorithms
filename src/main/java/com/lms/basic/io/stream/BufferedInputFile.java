package com.lms.basic.io.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 16:59 2018/7/26
 **/
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s = in.readLine())!=null)
            sb.append(s + "\n");
        in.close();
        return sb.toString();
    }
}
