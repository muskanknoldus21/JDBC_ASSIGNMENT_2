package com.knoldus;
import java.sql.*;
public class Main {
    static Connection con = null;
    static PreparedStatement stmt = null;
    static ResultSet rs;

    public void dbCon() throws Exception{
        con = DbConnection.getDbConnection();
    }

    //1st question from line 12 to line 24-----
    public void putDataToProductTable(int pid, double price, String name) throws Exception{
        String query = "INSERT INTO product VALUES (?,?,?)";
        stmt = con.prepareStatement(query);
        stmt.setInt(1,pid);
        stmt.setDouble(2,price);
        stmt.setString(3,name);
        int count = stmt.executeUpdate();
        System.out.println(count + " rows Effected");

    }
    public void putDataToCartTable(int pid, int qty) throws Exception{
        String query = "INSERT INTO cart VALUES (?,?)";
        stmt =con.prepareStatement(query);
        stmt.setInt(1,pid);
        stmt.setInt(2,qty);

        int count = stmt.executeUpdate();
        System.out.println(count + " rows Effected");
    }
    //.....

    //2nd Question from line 34 to line 46
    public void printData(int pid) throws Exception{
        String query = "SELECT * FROM product WHERE product.pid =" +pid;
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            System.out.println(rs.getDouble(2) + " " + rs.getString(3));
        }
        else{
            System.out.println("Empty");
        }

    }

    //3rd Question from line 49 to 57
    public void findAveragePrice() throws Exception{
        String query = "SELECT pid ,AVG(price) AS 'Avg Price' FROM product GROUP BY pid";
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt(1) + "  " + rs.getDouble(2));
        }
    }


    public static void main(String[] args) throws Exception{
        Main mainObj = new Main();
        mainObj.dbCon();
        mainObj.putDataToProductTable(13,70,"Muskan_Jain");
        mainObj.putDataToCartTable(5,6);
        mainObj.printData(11);
        mainObj.findAveragePrice();
        stmt.close();
        con.close();
    }
}
