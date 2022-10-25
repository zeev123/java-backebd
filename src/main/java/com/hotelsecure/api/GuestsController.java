package com.hotelsecure.api;

import com.hotelsecure.repo.GuestsRepo;
import com.hotelsecure.tables.Guests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/guests/")
public class GuestsController {
    @Autowired
    private GuestsRepo guestsRepo;

    @GetMapping(value = "/families")
    public List<Guests> families () {
        return guestsRepo.familiesRooms();
    }

    @GetMapping(value = "/couples")
    public List<Guests> couples () {
        return guestsRepo.GetCouples();
    }



    @GetMapping(value = "/getGuestsByRoom/{id}")
    public List<Guests> GuestsByRoom (@PathVariable Long id) {
        return guestsRepo.getGuestsByRoom(id);
    }

    @GetMapping(value = "/DeleteByRoom/{id}")
    public String DeleteByRoom (@PathVariable Long id) {
        try {
            guestsRepo.deleteGuestsByRoom(id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "delete guests successfully";
    }

    // create a new user
    @PostMapping("/create")
    public Guests createUser(@RequestBody Guests guests) {
        return guestsRepo.save(guests);
    }

}
