package com.muhammedtopgul.hibernatedocs.inheritace.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * created by Muhammed Topgul on 07/10/2021 at 12:56
 */

@Entity(name = "DebitAccount")
@Table(schema = "inheritances", name = "debit_account")
@Data
public class DebitAccount extends Account {

    private BigDecimal overdraftFee;
}
