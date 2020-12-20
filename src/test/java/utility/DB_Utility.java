package utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

//    public static void createConnection(){
//        String url = ConfigurationReader.getProperty("hr.database.url");
//        String userName = ConfigurationReader.getProperty("hr.database.username");
//        String password = ConfigurationReader.getProperty("hr.database.password");
//
//        try {
//            connection = DriverManager.getConnection(url,userName,password);
//            System.out.println("CONNECTION SUCCESSFUL");
//        } catch (SQLException e) {
//            System.out.println("CONNECTION HAS FAILED!!!" + e.getMessage());
//        }
//    }

    public static void createConnection(String url, String username, String password){

        try {
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED!!!" + e.getMessage());
        }
    }

    public static ResultSet runQuery(String query){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(query);
            System.out.println("QUERY SUCCESSFUL");
        } catch (SQLException e) {
            System.out.println("QUERY HAS FAILED!!!" + e.getMessage());
        }

        return rs;
    }

    public static int getRowCount(){
        int rowCount = 0;

        try {
            rs.last();
            rowCount = rs.getRow();

            //We put "beforeFirst" because we do not want to confuse the last location of cursor!!!
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT!!!" + e.getMessage());
        }

        return rowCount;
    }

    public static int getColumnCount(){
        int columnCount = 0;

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN COUNT!!!" + e.getMessage());
        }

        return columnCount;
    }

    public static List<String> getColumnNames(){
        List<String> columnNames = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1;i <= rsmd.getColumnCount();i++){
                columnNames.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN NAMES!!!" + e.getMessage());
        }

        return columnNames;
    }

    public static String getColumnDataAtRow(int rowNum, int columnNum){
        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(columnNum);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING CELL VALUES!!!" + e.getMessage());
        }

        return result;
    }

    public static void displayAllData(){
        try {
            rs.beforeFirst();

            while (rs.next()) {
                for (int i = 1;i <= getColumnCount();i++){
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println("ERROR WHILE GETTING ENTIRE DATA!!!" + e.getMessage());
        }
    }

    public static Map<String,String> getRowMap(int rowNum){
        Map<String,String> rowMap = new LinkedHashMap<>();

        try {
            rs.absolute(rowNum);
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1;i <= getColumnCount();i++){
                String key = rsmd.getColumnName(i);
                String value = rs.getString(i);
                rowMap.put(key,value);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowMap;
    }

    public static List<Map<String, String >> getAllDataAsMap(){
        List<Map<String, String>> mapList = new ArrayList<>();

        for (int i = 1;i < getRowCount();i++){
            mapList.add(getRowMap(i));
        }

        return mapList;
    }

    public static List<String> getColumnDataAsList(String colName){
        List<String> columnList = new ArrayList<>();

        for (int j = 1;j <= getColumnCount();j++){
            for (int i = 1;i <= getRowCount();i++){
                columnList.add(getColumnDataAtRow(i,j));
            }
        }

        return columnList;
    }

    public static List<Map<String, String>> getAllDataAsMapOld(){
        List<Map<String, String>> mapList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()){
                for (int i = 1;i <= getColumnCount();i++){
                    String key = rsmd.getColumnName(i);
                    String value = rs.getString(i);
                    Map<String,String> map = new LinkedHashMap<>();
                    map.put(key,value);
                    mapList.add(map);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mapList;
    }


    public static void destroy(){
        try {
            if (rs!=null) rs.close();
            if (statement!=null) statement.close();
            if (connection!=null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
