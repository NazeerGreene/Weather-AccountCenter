package com.example.AccountCenter.controller;

import com.example.AccountCenter.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// todo: finish account controller

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
