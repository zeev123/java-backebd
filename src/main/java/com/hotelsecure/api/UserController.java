package com.hotelsecure.api;

import com.hotelsecure.exeption.ResourceNotFoundExeption;
import com.hotelsecure.repo.UserRepo;
import com.hotelsecure.service.UserService;
import com.hotelsecure.tables.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/users/")
@Slf4j
public class UserController {
    @Autowired
    private UserRepo usersRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/getAll")
    public ResponseEntity<HashMap<Object, Object>> getUsers() throws InterruptedException {
        Thread.sleep(2000);
        List<User> users = usersRepository.findAll();
        return ResponseEntity.ok().body(new HashMap<>(){{put("users", users);}});
    }

    @PostMapping("/create")
    public Object createUser(@RequestBody User user) {
        User newUser;
        try {
            newUser = userService.seveUser(user);
            userService.addRoleToUser(user.getName(), user.getRole());
        } catch (Exception e) {
            return e.getMessage();
        }
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("user not exist with id :" + id));
        return ResponseEntity.ok(users);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("user not exist with id :" + id));
        usersRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/lock/{id}")
    public ResponseEntity<User> lockUser(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(2000);
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("user not exist with id :" + id));
        Map<String, User> response = new HashMap<>();
        user.setIsLock(true);
        User updateUserMassage = usersRepository.save(user);
        response.put("lock", updateUserMassage);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/unLock/{id}")
    public ResponseEntity<User> unLockUser(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(2000);
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("user not exist with id :" + id));
        Map<String, User> response = new HashMap<>();
        user.setIsLock(false);
        User updateUserMassage = usersRepository.save(user);
        response.put("unLock", updateUserMassage);
        return ResponseEntity.ok(user);
    }

}
