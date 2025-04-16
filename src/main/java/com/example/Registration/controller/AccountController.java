package com.example.Registration.controller;

import com.example.Registration.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/new")
    void addNewAccount() {
        return;
    }

    @GetMapping()
    void getAccountDetails() {

    }

    @DeleteMapping
    void deleteAccountById(
            @RequestParam(name = "accountId") String id
    ) {
        return;
    }

}
