package com.example.backaccountscqrsdemo.command.controller;

import com.example.backaccountscqrsdemo.command.dto.CreateAccountRequest;
import com.example.backaccountscqrsdemo.command.dto.DepositRequest;
import com.example.backaccountscqrsdemo.command.dto.WithdrawalRequest;
import com.example.backaccountscqrsdemo.command.service.AccountCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/bank-account")
@RequiredArgsConstructor
public class BankAccountController {

    private final AccountCommandService accountCommandService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest request){
        try{
            CompletableFuture<String> response = accountCommandService.createAccount(request);
            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest request){
        try{
            CompletableFuture<String> response = accountCommandService.depositToAccount(request);
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawalRequest request){
        try{
            CompletableFuture<String> response = accountCommandService.withdrawFromAccount(request);
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
