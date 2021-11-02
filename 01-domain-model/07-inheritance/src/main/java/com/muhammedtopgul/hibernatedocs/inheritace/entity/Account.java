package com.muhammedtopgul.hibernatedocs.inheritace.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author muhammed-topgul created at 07/10/2021 12:55
 */

// 1. strategy: @MappedSuperclass
// @MappedSuperclass

@Entity(name = "Account")
@Table(schema = "inheritances", name = "account")
// 2. strategy: InheritanceType.SINGLE_TABLE
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)

// 3. strategy: InheritanceType.JOINED
// @Inheritance(strategy = InheritanceType.JOINED)
// 4. strategy: InheritanceType.TABLE_PER_CLASS
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
public abstract class Account extends BaseId {

    private String owner;
    private BigDecimal balance;
    private BigDecimal interestRate;
}
