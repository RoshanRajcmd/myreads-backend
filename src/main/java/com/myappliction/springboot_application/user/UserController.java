//This class acts as the API layer, that have all the REST APIs that the Client side can make use of.
package com.myappliction.springboot_application.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/myreads/api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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

    //Gives all the details of user by the given Id
    @GetMapping(path = "/getUserDetails/{userId}")
    public User getUserByID(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    //Checks if given email id is already taken by someone
    @GetMapping(path = "/checkEmailExists/{email}")
    public boolean isUserByEmailExist(@PathVariable("email") String email){
        return userService.isUserByEmailExist(email);
    }

    //Validates the user credentials given in Database
    @GetMapping(path = "/validateUser")
    public String validateUserCred(@RequestParam(value="email", defaultValue = "") String email,
                                    @RequestParam(value="password", defaultValue = "") String password){
        return userService.validateUserCred(email,password);
    }

    //API to add a new User into the table
    @PostMapping
    @RequestMapping(path = "/addNewUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewUser(@RequestBody User newUser){
        userService.addNewUser(newUser);
    }

    //API to delete a User from the table
    @DeleteMapping(path = "/deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }

    //API to update name and email of a User from the table
    @PutMapping(path = "/updateUser/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserDetails(@PathVariable("userId") String userId,
                                     @RequestBody User user){
        userService.updateUserDetails(userId, user);
    }

    //Get all books marked under given user
//    @GetMapping(path = "getUserBooks")
//    public ResponseEntity<Page<Book>> getUsersBooks(@RequestBody(required = true) Long userId,
//                                                    @RequestParam(value="page", defaultValue = "0") int page,
//                                                    @RequestParam(value ="size", defaultValue = "10") int size){
//        return ResponseEntity.ok().body(userService.getUsersBooks(userId, page, size));
//    }

}
