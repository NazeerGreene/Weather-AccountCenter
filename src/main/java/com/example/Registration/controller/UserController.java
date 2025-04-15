package com.example.Registration.controller;

import com.example.Registration.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AccountService accountService;

    @Autowired
    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

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
