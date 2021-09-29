package com.example.backaccountscqrsdemo.command.service;

import com.example.backaccountscqrsdemo.command.command.CreateAccountCommand;
import com.example.backaccountscqrsdemo.command.command.DepositMoneyCommand;
import com.example.backaccountscqrsdemo.command.command.WithdrawMoneyCommand;
import com.example.backaccountscqrsdemo.command.dto.CreateAccountRequest;
import com.example.backaccountscqrsdemo.command.dto.DepositRequest;
import com.example.backaccountscqrsdemo.command.dto.WithdrawalRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AccountCommandService {

    private final CommandGateway commandGateway;

    public CompletableFuture<String> createAccount(CreateAccountRequest createAccountRequest){
        return commandGateway.send(new CreateAccountCommand(
            UUID.randomUUID().toString(),
            createAccountRequest.getStartingBalance()
        ));
    }


    public CompletableFuture<String> depositToAccount(DepositRequest depositRequest){
        return commandGateway.send(new DepositMoneyCommand(
            depositRequest.getAccountId(),
            depositRequest.getAmount()
        ));
    }


    public CompletableFuture<String> withdrawFromAccount(WithdrawalRequest withdrawalRequest){
        return commandGateway.send(new WithdrawMoneyCommand(
            withdrawalRequest.getAccountId(),
            withdrawalRequest.getAmount()
        ));
    }
}
