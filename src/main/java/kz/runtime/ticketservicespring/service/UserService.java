package kz.runtime.ticketservicespring.service;

import kz.runtime.ticketservicespring.entities.User;
import kz.runtime.ticketservicespring.repo.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }
    public List<User> fetchAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    public void saveUser(User user) {
        userRepository.insertUser(user.getCreationDate(), user.getUsername());
    }
    public User updateUser(Long id, User user) {
        User userId = userRepository.findUserById(id);
        userId.setCreationDate(user.getCreationDate());
        userId.setUsername(user.getUsername());
        userRepository.updateUser(userId.getCreationDate(), user.getUsername(), userId.getId());
        return userId;
    }
}
