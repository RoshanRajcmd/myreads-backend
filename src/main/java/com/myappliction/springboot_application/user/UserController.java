//This class acts as the API layer, that have all the REST APIs that the Client side can make use of.
package com.myappliction.springboot_application.user;


import com.myappliction.springboot_application.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/myreads/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //API to get all the Users in Users table along with its details
    //The CrossOrigin annotation is used when the API is getting access from other ports like when the front end of the
    //application is trying to access the API from the request mapping path
    @GetMapping
    @CrossOrigin
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //Validates the user credentials given in Database
    @GetMapping(path = "/validateUser")
    public boolean validateUserCred(@RequestParam(value="email", defaultValue = "") String email,
                                    @RequestParam(value="password", defaultValue = "") String password){
        return userService.validateUserCred(email,password);
    }

    //API to add a new User into the table
    @PostMapping
    public void addNewUser(@RequestBody User newUser){
        //need a try catch block
        userService.addNewUser(newUser);
        //return ResponseEntity.created(URI.create("userId")).body(userService.addNewUser(newUser));
    }

    //API to delete a User from the table
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    //API to update name and email of a User from the table
    @PutMapping(path = "{userId}")
    public void updateUserDetails(@PathVariable("userId") Long userId,
                                     @RequestParam(value="newName", defaultValue = "") String newName,
                                     @RequestParam(value="newEmail", defaultValue = "") String newEmail){
        userService.updateUserDetails(userId, newName, newEmail);
    }

    //Get all books marked under given user
//    @GetMapping(path = "getUserBooks")
//    public ResponseEntity<Page<Book>> getUsersBooks(@RequestBody(required = true) Long userId,
//                                                    @RequestParam(value="page", defaultValue = "0") int page,
//                                                    @RequestParam(value ="size", defaultValue = "10") int size){
//        return ResponseEntity.ok().body(userService.getUsersBooks(userId, page, size));
//    }

}
