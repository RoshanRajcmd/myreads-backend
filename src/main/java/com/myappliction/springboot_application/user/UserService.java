//This class is the server logic layer in that does validation/business logic in between
//the API(Controller) and DB service(Repository) layer
package com.myappliction.springboot_application.user;

import com.myappliction.springboot_application.book.Book;
import com.myappliction.springboot_application.exception.userException.NoSuchUserExistsException;
import com.myappliction.springboot_application.exception.userException.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Gets all the Users in the Database
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchUserExistsException("No User found by the given ID"));
    }

    //Add the given User in the Database
    public void addNewUser(User newUser){
        //if there is any value in the result of JPQL Query then it means the email is already been registered by someone
        if(userRepository.findById(newUser.getId()).isPresent() ||
                userRepository.findUserByEmail(newUser.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Email is Taken");
        }
        else
            userRepository.save(newUser);
    }

    //Delete the given User in Database by userId
    public void deleteUser(Long userId){
        userRepository.deleteById(getUser(userId).getId());
    }

    //Update the details of given user in Database
    public void updateUserDetails(Long userId, String newName, String newEmail){
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
    }

    //Gets the Books under the given User
    public Set<Book> getUsersBooks(Long userId){
        return getUser(userId).getBooksList();
    }

    //Validates the given password and user email


    //Gets all the friends of the given User
    public Set<Long> getFriendsOfUser(Long userId){
        return getUser(userId).getFriendsIds();
    }
}
