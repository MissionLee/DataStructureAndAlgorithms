package com.lms.Utils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 14:36 2018/8/16
 **/
public class TextConventor {
    String s="  `ch_check_id` varchar(32) NOT NULL COMMENT '药材检验报告ID',\n" +
            "  `report_path` varchar(255) DEFAULT NULL COMMENT '药材检验单路径',\n" +
            "  `result` tinyint(4) DEFAULT NULL COMMENT '药材检验结果',\n" +
            "  `unqualified_reason` varchar(255) DEFAULT NULL COMMENT '药材检验不合格原因',\n" +
            "  `inspector` varchar(32) DEFAULT NULL COMMENT '检验质检员',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT '更新时间',\n" +
            "  `status` int(11) DEFAULT NULL COMMENT '业务状态',\n" +
            "  `logic_delete` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',\n" +
            "  `create_id` varchar(32) DEFAULT NULL COMMENT '创建人ID',\n" +
            "  `update_id` varchar(32) DEFAULT NULL COMMENT '更新人ID',\n" +
            "  `mechanism_id` int(11) DEFAULT NULL COMMENT '药材检测机构ID',\n" +
            "  `supply_code` varchar(32) NOT NULL COMMENT '供应信息编号',\n" +
            "  `type` varchar(32) DEFAULT NULL COMMENT '类型',";



    Pattern compile = Pattern.compile("(`)(\\w+)(`)"); // 配置Pattern 匹配 字段名称
    String str;
    public void DDL2UpperCase() throws IOException {
        Reader reader1 = new StringReader(s); // 读取String
        BufferedReader in1 = new BufferedReader(reader1); //封装String
        while ((str=in1.readLine())!=null){
            str = str.trim().split(" ")[0]; // 获取 `create_id`
            String s1 = str.toUpperCase(); //获取 `CREATE_ID`
            System.out.println(s1+",");
        }
    }
    public void DDL2CamelCaseOnly() throws IOException {
        Reader reader2 = new StringReader(s); // 读取String
        BufferedReader in2 = new BufferedReader(reader2); //封装String
        while ((str=in2.readLine())!=null){


            str = str.trim().split(" ")[0]; // 获取 `create_id`

            Matcher matcher = compile.matcher(str);
            matcher.find(); // 获取 create_id
            String s2 = toUpperCase4FirstLetter(matcher.group(2).split("_")); // 获取 createId
            System.out.println("#{"+s2+"},");
        }
    }
    public void DDL2CamelCaseFullInfo() throws IOException {
        Reader reader3 = new StringReader(s); // 读取String
        BufferedReader in3 = new BufferedReader(reader3); //封装String
        while ((str=in3.readLine())!=null){
            str = str.trim().split(" ")[0]; // 获取 `create_id`
            String s1 = str.toUpperCase(); //获取 `CREATE_ID`
            Matcher matcher = compile.matcher(str);
            matcher.find(); // 获取 create_id
            String s2 = toUpperCase4FirstLetter(matcher.group(2).split("_")); // 获取 createId
            System.out.println(s1+" AS "+ s2+"," );
        }
        System.out.println();
    }
    public void DDL2paramsDOTget() throws IOException {
        Reader reader4 = new StringReader(s);
        BufferedReader in4 = new BufferedReader(reader4);
        while ((str=in4.readLine())!=null){
            str = str.trim().split(" ")[0]; // 获取 `create_id`
            Matcher matcher = compile.matcher(str);
            matcher.find(); // 获取 create_id
            String s2 = toUpperCase4FirstLetter(matcher.group(2).split("_")); // 获取 createId
            System.out.println("String "+s2+" =(String) params.get(\""+s2+"\");");
        }
    }
    public String toUpperCase4FirstLetter(String... words){
        StringBuffer buffer = new StringBuffer();
        if(words != null && words.length > 0){
            for(int i=0;i<words.length;i++){
                String word = words[i];
                String firstLetter = word.substring(0, 1);
                String others = word.substring(1);
                String upperLetter = null;
                if(i != 0){
                    upperLetter = firstLetter.toUpperCase();
                } else {
                    upperLetter = firstLetter;
                }
                buffer.append(upperLetter).append(others);
            }
            return buffer.toString();
        }
        return "";
    }
    public void update() throws IOException {
        Reader reader1 = new StringReader(s); // 读取String
        BufferedReader in1 = new BufferedReader(reader1); //封装String
        while ((str=in1.readLine())!=null){
            str = str.trim().split(" ")[0]; // 获取 `create_id`
            String s1 = str.toUpperCase()+" = " + "#{"+toUpperCase4FirstLetter(str.replace("`","").split("_"))+"}";
            System.out.println(s1+",");
        }
    }
    public static void main(String[] args) throws IOException {
        TextConventor t = new TextConventor();
        t.DDL2CamelCaseFullInfo();
        System.out.println("====================");
        t.DDL2CamelCaseOnly();
        System.out.println("======================");
        t.DDL2UpperCase();
        System.out.println("======================");
        t.DDL2paramsDOTget();
        System.out.println("==================");
        t.update();

    }
}
