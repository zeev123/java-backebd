package com.hotelsecure.Deamon;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Deamon_Messages_Controller {
    public void MessageController() throws IOException {
        DeamonFunctions deamonFunctions = new DeamonFunctions();
        addlogs(" start running Messages controller");
        int message_id = 0;
        String type = "";
        String data = "";
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlserver://ZEEV-RESNER;Database = super_market", "zeevi", "password");
            String query = "select top 1 *\n" +
                    "from messages\n" +
                    "WHERE status = 'NEW'\n" +
                    "order by praiority desc;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int counter = 0;
            while (rs.next()) {
                message_id = rs.getInt("id");
                type = rs.getString("type");
                data = rs.getString("data");
                counter = 2;
            }
            if (counter == 0) {
//                deamonFunctions.update_shut_down_demon("Messages controller");
                addlogs(" shutting down Messages controller");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Opps Error");
            e.printStackTrace();
        }
        if (type.equals("PING")){
            update_end_process(message_id);
        }
        if (type.equals("LEAREN_ENGLISH_MESSAGE")){
            addXmlToMessage(message_id,data);
        }
    }
    public void update_end_process(int message_id){
        addlogs(" start update_end_process of message "+message_id+" ");
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlserver://ZEEV-RESNER;Database = super_market", "zeevi", "password");
            String query = "update messages set status = 'DONE' WHERE ID = "+message_id+" ";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Opps Error");
            e.printStackTrace();
        }
    }

    public void addXmlToMessage(int message_id,String xmlData) throws IOException {
        System.out.println("start addXmlToMessage");
        java.io.FileWriter fw = new java.io.FileWriter("C:\\XML\\"+message_id+".xml");
        fw.write(xmlData);
        fw.close();
        update_end_process(message_id);
    }

    public  void addlogs(String new_messge) {
        try {
            FileReader reader = new FileReader("C:\\Deamon_logs\\Messages controller_logs.txt");
            int data = reader.read();
            String oldData = "";
            while (data != -1) {
                oldData += ((char) data);
                data = reader.read();
            }
            reader.close();
            FileWriter fileWriter = new FileWriter("C:\\Deamon_logs\\Messages controller_logs.txt");
            java.util.Date date = new java.util.Date();
            fileWriter.append(oldData + " \n" + date + new_messge + "\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("error...");
        }
    }
}
