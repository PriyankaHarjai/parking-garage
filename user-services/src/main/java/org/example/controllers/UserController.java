package org.example.controllers;

import org.example.model.User;
import org.example.model.Vehicle;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public void createUser(@RequestBody User user){

    }

    @GetMapping("/{userId}")
    public void getUserDetails(@PathVariable Long userId){

    }

    @PostMapping("/{userId}/vehicle")
    public void addVehicle(@PathVariable Long userId,@RequestBody Vehicle vehicle){

    }

}
