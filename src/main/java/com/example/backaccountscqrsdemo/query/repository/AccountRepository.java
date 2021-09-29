package com.example.backaccountscqrsdemo.query.repository;

import com.example.backaccountscqrsdemo.query.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
