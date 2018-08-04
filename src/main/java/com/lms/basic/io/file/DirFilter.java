package com.lms.basic.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 16:20 2018/7/24
 **/
public class DirFilter implements FilenameFilter {
    private Pattern pattern;
    public DirFilter(String regex){
        pattern = Pattern.compile(regex);
    }

    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }

}
