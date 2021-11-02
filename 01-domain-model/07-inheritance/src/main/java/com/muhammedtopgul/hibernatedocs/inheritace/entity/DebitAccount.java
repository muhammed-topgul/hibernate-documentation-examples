package com.muhammedtopgul.hibernatedocs.inheritace.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author muhammed-topgul created at 07/10/2021 12:56
 */

@Entity(name = "DebitAccount")
@Table(schema = "inheritances", name = "debit_account")
// @PrimaryKeyJoinColumn(name = "account_id") // for 3. strategy: InheritanceType.JOINED
@Data
public class DebitAccount extends Account {

    private BigDecimal overdraftFee;
}
