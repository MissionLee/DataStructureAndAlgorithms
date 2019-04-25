package com.lms.Utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-04-09 14:14
 */
public class DBAnalysis {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "bicon#123";
    private String database;

    public DBAnalysis(String database) throws SQLException, IOException {
        this.database = database;
        printAnalysis();
    }

    private void printAnalysis() throws SQLException, IOException {
        String URL = database;
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        String databaseName = connection.getCatalog();
        List<String> tableNames = getTables(databaseMetaData, databaseName);
        for (String tableName :
                tableNames) {
            System.out.println("============== " + tableName + " =============");
            ResultSet colSet = databaseMetaData.getColumns(databaseName, null, tableName, null);
            List<String> colNamesOrig = new LinkedList<>();
            while (colSet.next()) {
                colNamesOrig.add(colSet.getString("COLUMN_NAME"));
            }
            printCommonCode(colNamesOrig,tableName);
        }

    }

    private static void printCommonCode(List<String> colNameOrig,String tableName) throws IOException {
        StringBuffer totalBuffer = new StringBuffer();
        totalBuffer.append("\n############ "+tableName+" #############\n");
        Map<String, String> namePairs = new LinkedHashMap<>();
        for (String name :
                colNameOrig) {
            namePairs.put(name.toUpperCase(), getCamelCaseForName(name));
        }
        // 从前端传来的数据 map 获取内容
        System.out.println("=============  从前端传来的parameterMap获取参数 约定 parameterMap名为 params");
        totalBuffer.append(printGetParameterFromWebFront(namePairs)).append("\n\n");
        // Mapper 文件中的 select as 代码段
        System.out.println("=============  Mapper 文件中的 select as 代码段");
        totalBuffer.append(printSelectAsMapperFragment(namePairs,tableName)).append("\n\n");
        // 用#{}方式作为mapper参数的代码段
        // System.out.println("用#{}方式作为mapper参数的代码段");
        // printPoundSignParameter(namePairs);
        // Update语句用到的内容
        System.out.println("=============  Mapper文件中用到的 update代码段");
        totalBuffer.append(printUpdateMapperFragment(namePairs,tableName)).append("\n\n");
        System.out.println("=============  Mapper文件中用到的INSER 代码段");
        totalBuffer.append(printInsertMaperFragment(namePairs,tableName)).append("\n\n");
        FileUtils.writeStringToFile(new File("C:\\Users\\MissionLee\\Desktop\\kuaijie.txt"),totalBuffer.toString(),"utf8",true);

    }
    // INSERT 代码段
    private static String printInsertMaperFragment(Map<String,String> namePairs,String tableName){
        StringBuffer buffer = new StringBuffer();
        Set<String> keys = namePairs.keySet();
        int size = keys.size();
        int num = 1;
        buffer.append("INSERT INTO ").append(tableName).append("(\n");
        for (String name :
                keys) {
            buffer.append("`").append(name).append("`");
            if (num++ < size)
                buffer.append(",\n");
            else
                buffer.append("\n");
        }
        num=1;
        buffer.append(") VALUES (\n");
        for (String name :
                keys) {
            buffer.append("#{").append(namePairs.get(name)).append("}");
            if (num++ < size)
                buffer.append(",\n");
            else
                buffer.append("\n");
        }
        buffer.append(")\n");
        System.out.println(buffer.toString());
        return buffer.toString();
    }
    // UPDATE 语句用到的内容
    private static String printUpdateMapperFragment(Map<String,String> namePairs,String tableName){
        StringBuffer buffer = new StringBuffer();
        Set<String> keys = namePairs.keySet();
        int size = keys.size();
        int num = 1;
        buffer.append("UPDATE ").append(tableName).append(" SET\n");
        for (String name :
                keys) {
            buffer.append("`").append(name).append("`").append(" = #{").append(namePairs.get(name)).append("}");
            if (num++ < size)
                buffer.append(",\n");
            else
                buffer.append("\n");
        }
        buffer.append("WHERE ");
        System.out.println(buffer.toString());
        return buffer.toString();
    }
    // 用#{}方式作为mapper参数的代码段
    private static String printPoundSignParameter(Map<String, String> namePairs) {
        StringBuffer buffer = new StringBuffer();
        Set<String> keys = namePairs.keySet();
        int size = keys.size();
        int num = 1;
        for (String name :
                keys) {
            buffer.append("#{").append(namePairs.get(name)).append("}");
            if (num++ < size)
                buffer.append(",\n");
            else
                buffer.append("\n");
        }
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    // Mapper 文件中的 select as 代码段
    private static String printSelectAsMapperFragment(Map<String, String> namePairs, String tableName) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT \n");
        Set<String> keys = namePairs.keySet();
        int size = keys.size();
        int num = 1;
        for (String name :
                keys) {

            buffer.append("`").append(name.toUpperCase()).append("`").append(" AS ").append(namePairs.get(name));
            if (num++ < size)
                buffer.append(",\n");
            else
                buffer.append("\n");

        }
        buffer.append("FROM ").append(tableName).append(" WHERE LOGICAL_DELETE = 0");
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    // 从前端传来的数据 map 获取内容
    private static String printGetParameterFromWebFront(Map<String, String> namePairs) {
        StringBuffer buffer = new StringBuffer();
        Set<String> keys = namePairs.keySet();

        for (String name :
                keys) {
            buffer.append("String ").append(namePairs.get(name)).append(" = (String) params.get(\"").append(namePairs.get(name)).append("\");\n");
        }
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    private static List<String> getTables(DatabaseMetaData databaseMetaData, String databaseName) throws SQLException {
        List<String> tableNames = new LinkedList<>();
        ResultSet rs = databaseMetaData.getTables(databaseName, null, null,
                new String[]{"TABLE"});
//        new String[]{"TABLE","VIEW"}
        System.out.println(rs);
        while (rs.next()) {
            tableNames.add(rs.getString(3));
        }
        return tableNames;
    }

    /**
     * 将下划线分割模式的名称，转为驼峰
     */
    public static String getCamelCaseForName(String name) {
        return getCamelCaseForName(name, "_");
    }

    public static String getCamelCaseForName(String name, String splitPattern) {
        return combineNamesToCamelCase(name.split(splitPattern));
    }

    public static String combineNamesToCamelCase(String... workds) {
        if (workds != null)
            if (workds.length > 1) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(workds[0].toLowerCase());
                for (int i = 1; i < workds.length; i++) {
                    String lower = workds[i].toLowerCase();
                    String firstLetter = lower.substring(0, 1);
                    String other = lower.substring(1);
                    buffer.append(firstLetter.toUpperCase()).append(other);
                }
                return buffer.toString();
            } else {
                return workds[0].toLowerCase();
            }
        else return "";
    }

    public static void main(String[] args) throws SQLException, IOException {
        DBAnalysis dbAnalysis = new DBAnalysis("jdbc:mysql://10.1.24.214:3306/db_clinic?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=true");
    }


}
