package kz.runtime.ticketservicespring.service;

import kz.runtime.ticketservicespring.entities.User;
import kz.runtime.ticketservicespring.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        String name = user.getUsername();
        LocalDate creationDate = user.getCreationDate();
        Long id = user.getId();
        findUserById(id);
        user.setUsername(user.getUsername());
        user.setCreationDate(user.getCreationDate());
        return userRepository.save(user);
    }
}
