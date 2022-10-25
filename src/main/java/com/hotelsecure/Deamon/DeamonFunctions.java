package com.hotelsecure.Deamon;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DeamonFunctions {

    public int Chack_If_There_is_deamons_that_need_update(){
//        System.out.println("start Chack_If_There_is_deamons_that_need_update");
        ArrayList<Integer> list = new ArrayList<Integer>();
        int demon_id = 0;
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlserver://ZEEV-RESNER;Database = super_market", "zeevi", "password");
            String query = "select top 1 * from deamons\n" +
                    "where last_run_date < DATEADD(mi,-run_every,getDate())";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                demon_id = rs.getInt("id");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Opps Error");
            e.printStackTrace();
        }
        return demon_id;
    }
    public void update_shut_down_demon(String demon_name) {
        System.out.println("shuting down messages demon");
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlserver://ZEEV-RESNER;Database = super_market", "zeevi", "password");
            String query = "update deamons set last_run_date = getdate()" +
                    "where name = '"+demon_name+"' ";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Opps Error");
            e.printStackTrace();
        }
        update_Deamon_hiistory(demon_name);
    }
    public void update_Deamon_hiistory(String Deamon_name){
        String a = "d";
        System.out.println("start update_Deamon_hiistory deamon_name is: "+Deamon_name);
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlserver://ZEEV-RESNER;Database = super_market", "zeevi", "password");
            String query = "insert into  Deamon_history(name,last_run_date,message)\n" +
                    "values('"+Deamon_name+"',getdate(),null) ";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Opps Error");
            e.printStackTrace();
        }
    }
    public void runDeamonNow(int deamon_id){
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlserver://ZEEV-RESNER;Database = super_market", "zeevi", "password");
            String query = "\n" +
                    "update deamons\n" +
                    "set last_run_date = getdate() - 5\n" +
                    "where id = "+deamon_id+" ";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Opps Error");
            e.printStackTrace();
        }
    }
}
