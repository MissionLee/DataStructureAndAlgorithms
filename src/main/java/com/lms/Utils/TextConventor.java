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
    String s="`DRUG_ID` varchar(32) COLLATE utf8mb4_bin NOT NULL,\n" +
            "  `DRUG_NAME` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '药品名称',\n" +
            "  `ALIAS_NAME` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '别名',\n" +
            "  `ENG_NAME` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '英文名',\n" +
            "  `SPELL_NAME` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '汉语拼音',\n" +
            "  `COMPONENT` text COLLATE utf8mb4_bin COMMENT '成份',\n" +
            "  `TYPE_STATUS` text COLLATE utf8mb4_bin COMMENT '性状',\n" +
            "  `DISEASE_EXPLAIN` text COLLATE utf8mb4_bin COMMENT '相关疾病文字说明',\n" +
            "  `RELATED_DISEASE` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '相关疾病（,分隔）',\n" +
            "  `USAGE_DOSAGE` text COLLATE utf8mb4_bin COMMENT '用法用量',\n" +
            "  `UNTOWARD_EFFECT` text CHARACTER SET utf8mb4 COMMENT '不良反应',\n" +
            "  `CONTRAINDICATION` text COLLATE utf8mb4_bin COMMENT '禁忌',\n" +
            "  `MATTERS_NEED_ATTENTION` text CHARACTER SET utf8mb4 COMMENT '注意事项',\n" +
            "  `SPECIAL_POPULATIONS` text COLLATE utf8mb4_bin COMMENT '特殊人群用药',\n" +
            "  `CORRESPONDING_FUNCTION` text CHARACTER SET utf8mb4 COMMENT '药物相互作用',\n" +
            "  `PHARMACOLOGICAL_EFFECTS` text CHARACTER SET utf8mb4 COMMENT '药理作用',\n" +
            "  `PHARMACOKINETICS` text CHARACTER SET utf8mb4 COMMENT '药代动力学',\n" +
            "  `STORAGE` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '贮藏',\n" +
            "  `CLASSIFICATION` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类',\n" +
            "  `SPECIFICATIONS` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '包装规格',\n" +
            "  `PERIOD_VALIDITY` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '有效期',\n" +
            "  `EXECUTION_STANDARD` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行标准',\n" +
            "  `APPROVAL_NUMBER` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '批准文号',\n" +
            "  `REVISION_DATE` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '说明书修订日期',\n" +
            "  `PRODUCTION` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生产单位',\n" +
            "  `STANDARD_CODE` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '药品本位码',\n" +
            "  `BICON_FLAG` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否为必康生产药品（0：不是，1：是）',\n" +
            "  `CREATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `UPDATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
            "  `LOGIC_DELETE` tinyint(2) DEFAULT '0' COMMENT '逻辑删除(0.未删除，1.已删除)',";




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
                String word = words[i].toLowerCase();
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
    public void mapPut() throws IOException {
        Reader reader1 = new StringReader(s); // 读取String
        BufferedReader in1 = new BufferedReader(reader1); //封装String
        while ((str=in1.readLine())!=null){
            str = str.trim().split(" ")[0]; // 获取 `create_id`
            Matcher matcher = compile.matcher(str);
            matcher.find(); // 获取 create_id
            String s2 = toUpperCase4FirstLetter(matcher.group(2).split("_")); // 获取 createId
            System.out.println("map.put(\""+s2+"\",\""+"abc"+"\");");
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
        System.out.println("==================");
        t.mapPut();

    }

}
