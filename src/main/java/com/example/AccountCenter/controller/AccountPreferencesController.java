package com.example.AccountCenter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preferences")
public class AccountPreferencesController {
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
    void getAccountPreferences(
            @RequestParam(name = "accountId") String id
    ) {
        return;
    }
}
