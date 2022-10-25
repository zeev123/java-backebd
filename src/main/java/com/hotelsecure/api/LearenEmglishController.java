package com.hotelsecure.api;

import com.hotelsecure.Functions.LearenEnglishPage;
import com.hotelsecure.Functions.LearenEnglsData;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/LearenEmglish/")
public class LearenEmglishController {

    @GetMapping("getMessageData/{id}")
    public Object ObjectreturnMessageData(@PathVariable int id){
        LearenEnglishPage learenEnglishPage = new LearenEnglishPage();
        return learenEnglishPage.convertTheXmlToJasonFile(id);
    }

    @GetMapping("getAll")
    public static List<Map<String, Object>> returnAllTheMessages(){
        System.out.println("start returnAllTheMessages");
        LearenEnglishPage learenEnglishPage = new LearenEnglishPage();
        return learenEnglishPage.returnAllEnglishMeesagesData();
    }

    public static void main(String[] args) {
        System.out.println(returnAllTheMessages());
    }


    @PostMapping("/trasformDataToXml")
    public Object createUser(@RequestBody List<LearenEnglsData> learenEnglsData) throws ParserConfigurationException, TransformerException {
        String article = learenEnglsData.get(0).getNewArticle();
        int learenSize = learenEnglsData.size();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element element = document.createElement("MessageData");
        Attr atr = document.createAttribute("article");
        atr.setValue(article);
        element.setAttributeNode(atr);
        document.appendChild(element);
        int counter = learenSize;
        while (counter>0) {
            counter--;
            String HebrwoValue = learenEnglsData.get(counter).getHebrwoValue();
            String engilshValue = learenEnglsData.get(counter).getEngilshValue();
            Element chiledElement = document.createElement("word");

            Element engilshValue1 = document.createElement("enV");
            engilshValue1.appendChild(document.createTextNode(engilshValue));
            chiledElement.appendChild(engilshValue1);

            Element HebrwoValue1 = document.createElement("heV");
            HebrwoValue1.appendChild(document.createTextNode(HebrwoValue));
            chiledElement.appendChild(HebrwoValue1);

            element.appendChild(chiledElement);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult streamResult = new StreamResult(new File("C:\\XML\\messages\\learenEnglishData.xml"));
        transformer.transform(source, streamResult);

        // Transform Document to XML String
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer1 = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer1.transform( new DOMSource( document ), new StreamResult( writer ) );
        String a = String.valueOf(writer);
        LearenEnglsData learenEnglsData1 = new LearenEnglsData();
        learenEnglsData1.setXmlReturn(a);
        return learenEnglsData1;
    }
}