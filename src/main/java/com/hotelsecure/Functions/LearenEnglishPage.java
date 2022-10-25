package com.hotelsecure.Functions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearenEnglishPage {
    public List<Map<String, Object>> returnAllEnglishMeesagesData(){
        List<Map<String, Object>> allMessages = new ArrayList<>();
        Map<String, Object> tempMessage = new HashMap<String, Object>();
        int message_id = 0;
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlserver://ZEEV-RESNER;Database = super_market", "zeevi", "password");
            String query = "select * from messages\n" +
                    "where type = 'LEAREN_ENGLISH_MESSAGE'\n" +
                    "order by start_date desc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println("start while");
                message_id = rs.getInt("id");
                List<Map<String, Object>> messageData1 = new ArrayList<>();
                List<Map<String, Object>> messageData2 = new ArrayList<>();
                messageData1 = convertTheXmlToJasonFile(message_id);
                System.out.println("message_id is: "+message_id);
                allMessages.addAll(messageData1);
                messageData1 = messageData2;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Opps Error");
            e.printStackTrace();
        }
        return allMessages;
    }

    public  List<Map<String, Object>> convertTheXmlToJasonFile(int message_id){
        String messageIdString = String.valueOf(message_id);
        List<Map<String, Object>> messageData = new ArrayList<>();
        try {
            java.io.File fXmlFile = new File("C:\\XML\\React App\\"+message_id+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("word");
            NodeList ListArticle = doc.getElementsByTagName("MessageData");
            Node nNodeArticle = ListArticle.item(0);
            Element eElementArticle = (Element) nNodeArticle;
            String article =
                    eElementArticle.getAttribute("article");
            int counter = 0;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String hebrwoValue =
                            eElement.getElementsByTagName("heV").item(0).getTextContent();
                    String engilshValue =
                            eElement.getElementsByTagName("enV").item(0).getTextContent();
                    Map<String, Object> msg = new HashMap<String, Object>();
                    msg.put("heV",hebrwoValue);
                    msg.put("enV",engilshValue);
                    msg.put("msgId",message_id);
                    if (counter==0){
                        msg.put("newArticle",article);
                    }else{
                        msg.put("newArticle","null");
                    }
                    messageData.add(msg);
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageData;
    }
}
