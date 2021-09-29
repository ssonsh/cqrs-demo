package com.example.backaccountscqrsdemo.query.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account {

    @Id
    private String accountId;
    private BigDecimal balance;
    private String status;

}
