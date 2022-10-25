package com.hotelsecure.api;


import com.hotelsecure.exeption.ResourceNotFoundExeption;
import com.hotelsecure.repo.MissionsRepository;
import com.hotelsecure.tables.Missions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/missions/")
public class MissionsController {
    @Autowired
    private MissionsRepository missionsRepository;

    @GetMapping("/getAll")
    public ResponseEntity<HashMap<Object, Object>> getUsers() {
        List<Missions> mission = missionsRepository.findAll();
        return ResponseEntity.ok().body(new HashMap<>(){{put("missions", mission);}});
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Missions> getUserById(@PathVariable Long id){
        Missions missions = missionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Mission not exist with id :" + id));
        return ResponseEntity.ok(missions);
    }

    @PostMapping("/create")
    public Missions createUser(@RequestBody Missions missions){
        missions.setEnd_date(null);
        return missionsRepository.save(missions);
    }

    @PutMapping("/changeToProces/{id}")
    public ResponseEntity<Missions> changeStatusToInProcess(@PathVariable Long id){
        Missions missions = missionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("mission not exist with id :" + id));
        missions.setStatus("in_process");
        Date date = new Date();
        missions.setStart_work_date(date);
        Missions updateUserMassage = missionsRepository.save(missions);
        return ResponseEntity.ok(updateUserMassage);
    }
    @PutMapping("/changeToDone/{id}")
    public ResponseEntity<Missions> changeStatusToDone(@PathVariable Long id){
        Missions missions = missionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("mission not exist with id :" + id));
        missions.setStatus("done");
        Date date = new Date();
        missions.setEnd_date(date);
        Missions updateUserMassage = missionsRepository.save(missions);
        return ResponseEntity.ok(updateUserMassage);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Missions> updateUserMassage(@PathVariable Long id, @RequestBody Missions MissionsDetails){
        Missions missions = missionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("mission not exist with id :" + id));
        missions.setThe_missions(MissionsDetails.getThe_missions());
        missions.setStart_date(MissionsDetails.getStart_date());
        missions.setStart_work_date(MissionsDetails.getStart_work_date());
        missions.setEnd_date(MissionsDetails.getEnd_date());
        missions.setStatus(MissionsDetails.getStatus());
        missions.setPhoto_path(MissionsDetails.getPhoto_path());
        missions.setType(MissionsDetails.getType());
        missions.setMissions_message(MissionsDetails.getMissions_message());
        missions.setType(MissionsDetails.getType());
        missions.setShould_end(MissionsDetails.getShould_end());
        missions.setNote(MissionsDetails.getNote());
        Missions updateUserMassage = missionsRepository.save(missions);
        return ResponseEntity.ok(updateUserMassage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        Missions Missions = missionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("mission not exist with id :" + id));
        missionsRepository.delete(Missions);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }
}
