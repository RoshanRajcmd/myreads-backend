//This class is the server logic layer in that does validation/business logic in between the API(Controller) and DB service(Repository) layer
package com.myappliction.springboot_application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User newUser){
        Optional<User> userByEmail = userRepository.findUserByEmail(newUser.getEmail());
        //if there is any value in the result of JPQL Query then it means the email is already been registered by someone
        if(userByEmail.isPresent()){
            throw new IllegalStateException("Email is Taken");
        }
        userRepository.save(newUser);
    }

    public void deleteUser(Long userId){
        if(!userRepository.existsById(userId)){
            throw new IllegalStateException("No User found by the given ID");
        }
        else{
            userRepository.deleteById(userId);
        }
    }

    public void updateUserDetails(Long userId, String newName, String newEmail){
        User userById = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("No User found by the given ID"));
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
}
