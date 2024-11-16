//This class acts as the API layer, that have all the REST APIs that the Client side can make use of.
package com.myappliction.springboot_application.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping(path = "getAllUsers")
    @CrossOrigin
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //API to add a new User into the table
    @PostMapping
    public void registerNewUser(@RequestBody User newUser){
        userService.addNewUser(newUser);
    }

    //API to delete a User from the table
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    //API to update name and email of a User from the table
    @PutMapping(path = "{userId}")
    public void updateUserDetails(@PathVariable("userId") Long userId,
                                     @RequestBody(required = false) String newName,
                                     @RequestBody(required = false) String newEmail){
        userService.updateUserDetails(userId, newName, newEmail);
    }

}
