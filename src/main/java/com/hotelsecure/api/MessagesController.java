package com.hotelsecure.api;

import com.hotelsecure.exeption.ResourceNotFoundExeption;
import com.hotelsecure.repo.MessagesRepository;
import com.hotelsecure.tables.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    private MessagesRepository messagesRepository;

    @GetMapping("/getAll")
    public ResponseEntity<HashMap<Object, Object>> getAllMessages() {
//    public List<Messages> getAllMessages(){
//        return messagesRepository.findAll();
        List<Messages> messages = messagesRepository.findAll();
        return (ResponseEntity<HashMap<Object, Object>>) ResponseEntity.ok().body(new HashMap<>(){{put("messages", messages);}});
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Messages> getUserById(@PathVariable Long id){
        Messages Messages = messagesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("mission not exist with id :" + id));
        return ResponseEntity.ok(Messages);
    }

    @PostMapping("/create")
    public Messages createUser(@RequestBody Messages messages) throws IOException {
        System.out.println("messages is: "+messages);
        String xmlData = messages.getData();
        Messages a =  messagesRepository.save(messages);
        Long msgId = a.getID();
        addXmlToMessage(msgId, xmlData);
        return  a;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Messages> updateUserMassage(@PathVariable Long id, @RequestBody Messages MessagesDetails){
        Messages Messages = messagesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("message not exist with id :" + id));
        Messages.setDesccription(MessagesDetails.getDesccription());
        Messages.setPraiority(MessagesDetails.getPraiority());
        Messages.setStart_date(MessagesDetails.getStart_date());
        Messages.setStatus(MessagesDetails.getStatus());
        Messages.setType(MessagesDetails.getType());
        Messages.setData(MessagesDetails.getData());
        Messages.setMission_id(MessagesDetails.getMission_id());
        Messages updateUserMassage = messagesRepository.save(Messages);
        return ResponseEntity.ok(updateUserMassage);
    }

    @PostMapping("/updateGrade/{id}")
    public ResponseEntity<Messages> updateGrade(@PathVariable Long id, @RequestBody Messages great){
         Messages Messages = messagesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("mission not exist with id: " + id));
        Messages.setDesccription(String.valueOf(great.getID()));
        Messages.setStatus("DONE");
        Messages updateUserMassage = messagesRepository.save(Messages);
        messagesRepository.save(Messages);
        return  ResponseEntity.ok(updateUserMassage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        Messages Messages = messagesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("mission not exist with id :" + id));
        messagesRepository.delete(Messages);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }


    public void addXmlToMessage(Long message_id,String xmlData) throws IOException {
        System.out.println("start addXmlToMessage");
        java.io.FileWriter fw = new java.io.FileWriter("C:\\XML\\React App\\"+message_id+".xml");
        fw.write(xmlData);
        fw.close();
   }

}