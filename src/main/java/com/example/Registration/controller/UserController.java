package com.example.Registration.controller;

import com.example.Registration.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    void registerNewUser() {
        return;
    }

    /**
     * Receive user preferences based on id.
     * Ex.
     * GET http://localhost:8080?id={userId}
     * returns {
     *     id,
     *     email,
     *     preferredLocation,
     *     createdAt
     * }
     *
     * @param id The user id.
     */
    @GetMapping
    void userPreferenceDetails(
            @RequestParam(name = "userId") String id
    ) {
        return;
    }

    @DeleteMapping
    void deleteUserById(
            @RequestParam(name = "userId") String id
    ) {
        return;
    }

}
