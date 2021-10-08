package com.muhammedtopgul.hibernatedocs.inheritace.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * created by Muhammed Topgul on 07/10/2021 at 12:57
 */

@Entity(name = "CreditAccount")
@Table(schema = "inheritances", name = "credit_account")
@PrimaryKeyJoinColumn(name = "account_id")
@Data
public class CreditAccount extends Account {

    private BigDecimal creditLimit;
}
