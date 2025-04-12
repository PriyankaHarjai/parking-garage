package org.example.services;

import org.example.model.User;
import org.example.model.Vehicle;
import org.example.repositories.UserRepository;
import org.example.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public UserService(UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }


    public void addVehicle(Long userId, Vehicle vehicle) {
        Optional<User> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent()) {
            User user = userDetails.get();
            vehicle.setUser(user);
            vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("User Id not available");
        }

    }

    public User getUserDetails(Long userId) {
        if (userId != null) {
            return userRepository.findById(userId).orElseThrow();
        } else
            throw new RuntimeException("User Id is null");
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
