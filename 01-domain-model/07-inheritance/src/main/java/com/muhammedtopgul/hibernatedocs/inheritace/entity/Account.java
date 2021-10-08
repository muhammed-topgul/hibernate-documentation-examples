package com.muhammedtopgul.hibernatedocs.inheritace.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * created by Muhammed Topgul on 07/10/2021 at 12:55
 */

// 1. strategy: @MappedSuperclass
// @MappedSuperclass
// 2. strategy: InheritanceType.SINGLE_TABLE
@Entity(name = "Account")
@Table(schema = "inheritances", name = "account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@ToString
public abstract class Account extends BaseId {

    private String owner;
    private BigDecimal balance;
    private BigDecimal interestRate;
}
