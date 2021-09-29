package com.example.backaccountscqrsdemo.query.service;

import com.example.backaccountscqrsdemo.common.event.AccountActivatedEvent;
import com.example.backaccountscqrsdemo.common.event.AccountCreatedEvent;
import com.example.backaccountscqrsdemo.common.event.AccountCreditedEvent;
import com.example.backaccountscqrsdemo.common.event.AccountDebitedEvent;
import com.example.backaccountscqrsdemo.query.entity.Account;
import com.example.backaccountscqrsdemo.query.query.FindAccountByIdQuery;
import com.example.backaccountscqrsdemo.query.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageAccountService {

    private final AccountRepository accountRepository;

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent){
      log.info("Handling AccountCreatedEvent!");
      Account account = new Account();
      account.setAccountId(accountCreatedEvent.getId());
      account.setBalance(accountCreatedEvent.getBalance());
      account.setStatus("CREATED");

      accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent accountActivatedEvent){
        log.info("Handling AccountActivatedEvent!");

        Account account = accountRepository.findById(accountActivatedEvent.getId()).orElse(null);

        if(account != null){
            account.setStatus(accountActivatedEvent.getStatus());
            accountRepository.save(account);
        }
    }

    @EventHandler
    public void on(AccountCreditedEvent accountCreditedEvent){
        log.info("Handling AccountCreditedEvent!");

        Account account = accountRepository.findById(accountCreditedEvent.getId()).orElse(null);

        if(account != null){
            account.setBalance(account.getBalance().add(accountCreditedEvent.getAmount()));
            accountRepository.save(account);
        }
    }

    @EventHandler
    public void on(AccountDebitedEvent accountDebitedEvent){
        log.info("Handling AccountDebitedEvent!");

        Account account = accountRepository.findById(accountDebitedEvent.getId()).orElse(null);

        if(account != null){
            account.setBalance(account.getBalance().subtract(accountDebitedEvent.getAmount()));
            accountRepository.save(account);
        }
    }

    @QueryHandler
    public Account handle(FindAccountByIdQuery query){
        log.info("Handling FindAccountByIdQuery!");
        Account account = accountRepository.findById(query.getAccountId()).orElse(null);
        return account;
    }
}
