package com.hotelsecure.api;

import com.hotelsecure.repo.RoomsRepo;
import com.hotelsecure.tables.Guests;
import com.hotelsecure.tables.GuestsInRoom;
import com.hotelsecure.tables.Rooms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rooms/")
public class RoomsController {
    @Autowired
    private RoomsRepo roomsRepo;

    @GetMapping(value = "/room/{id}")
    public List<Rooms> roomById (@PathVariable Long id) {
        return roomsRepo.roomByLocalId(id);
    }

    @GetMapping(value = "/available")
    public List<Rooms> availeableRooms() {
        return roomsRepo.getAvaileableRooms();
    }

    @GetMapping(value = "/unAvailable")
    public List<Rooms>unAvaileableRooms() {
        return roomsRepo.getUnAvaileableRooms();
    }

    @GetMapping(value = "/couples")
    public List<Guests> getFamlies() {
        return roomsRepo.getCouples();
    }

    @Value("${twilio.auth_token}")
    public  String auth_token;
    @Value("${twilio.account_sid}")
    public String account_sid;
    @Value("${twilio.trial_number}")
    public String trial_number;

    @GetMapping(value = "/status")
    public String  getCapacityPresentOfTheHotel(){
        int present = roomsRepo.getPresentOfCapisity();
        Twilio.init(account_sid, auth_token);
        PhoneNumber to  = new PhoneNumber("+972587517049");
        PhoneNumber from  = new PhoneNumber(trial_number);
        String newMessage = "Hello manager, \n" +
                "Hotel occupancy rates are: "+present+"\n"+
                "have a good day";
        try{
            Message message = Message.creator(to, from, newMessage).create();
            System.out.println(message.getSid());
        }catch ( Exception e){
            return e.getMessage();
        }
        return "send message successfully";
    }
}
