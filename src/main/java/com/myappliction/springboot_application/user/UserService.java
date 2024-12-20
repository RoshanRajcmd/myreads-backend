//This class is the server logic layer in that does validation/business logic in between
//the API(Controller) and DB service(Repository) layer
package com.myappliction.springboot_application.user;

import com.myappliction.springboot_application.exception.userException.NoSuchUserExistsException;
import com.myappliction.springboot_application.exception.userException.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserService {
    private static Logger log;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Gets all the Users in the Database
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Returns True if the given email exists in user table of database
    public boolean isUserByEmailExist(String email){
        //if there is any value in the result of JPQL Query then it means the email is already been registered by someone
        return userRepository.findUserByEmail(email).isPresent();
    }

    public User getUser(String userId){
        return userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new NoSuchUserExistsException("No User found by the given ID"));
    }

    //Add the given User in the Database
    public void addNewUser(User newUser){
        if(isUserByEmailExist(newUser.getEmail())){
            throw new UserAlreadyExistsException("Email is Taken");
        }
        else {
            userRepository.save(newUser);
        }
    }

    //Delete the given User in Database by userId
    public void deleteUser(String userId){
        userRepository.deleteById(UUID.fromString(userId));
    }

    //Update the details of given user in Database
    public void updateUserDetails(String userId, String newName, String newEmail){
        User userById = getUser(userId);
        if(newName != null &&
                !newName.isEmpty() &&
                !Objects.equals(userById.getName(), newName)) {
            userById.setName(newName);
        }
        if(newEmail != null &&
                !newEmail.isEmpty() &&
                !Objects.equals(userById.getEmail(), newEmail)) {
            userById.setEmail(newEmail);
        }
        userRepository.save(userById);
    }

    //Gets the Books under the given User
//    public Page<Book> getUsersBooks(Long userId, int page, int size){
//        return getUser(userId).getBooksList(PageRequest.of(page, size, Sort.by("name")));
//    }

    //Validates the given password and user email and returns the user if exists by the given credentials
    public String validateUserCred(String email, String password){
        User userByMail = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NoSuchUserExistsException("No User found by the given Email"));
        if(userByMail.getPassword().equals(password))
            return userByMail.getId().toString();
        else
            return "";
    }


    //Gets all the friends of the given User
    public Set<Long> getFriendsOfUser(String userId){
        return getUser(userId).getFriendsIds();
    }
}
