package org.example.controllers;

import org.example.dto.UserDTO;
import org.example.dto.VehicleDTO;
import org.example.model.User;
import org.example.model.Vehicle;
import org.example.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public void createUser(@RequestBody UserDTO user) {
        userService.createUser(new User(null, user.getName(), user.getEmail(), user.getPhoneNo()));
    }

    @GetMapping("/{userId}")
    public User getUserDetails(@PathVariable Long userId) {
        return userService.getUserDetails(userId);
    }

    @PostMapping("/{userId}/vehicle")
    public void addVehicle(@PathVariable Long userId, @RequestBody VehicleDTO vehicle) {
        userService.addVehicle(userId, new Vehicle(null, vehicle.getLicencePlate(), vehicle.getVehicleType(), null));
    }

}
