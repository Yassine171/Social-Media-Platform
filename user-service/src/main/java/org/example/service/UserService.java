package org.example.service;

import lombok.AllArgsConstructor;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.model.User;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;


    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User updatedUser) {
        User user = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + updatedUser.getId()));

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setProfilePicture(updatedUser.getProfilePicture());
        user.setBio(updatedUser.getBio());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
