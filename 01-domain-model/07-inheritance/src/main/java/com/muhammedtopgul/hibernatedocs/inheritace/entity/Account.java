package com.muhammedtopgul.hibernatedocs.inheritace.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * created by Muhammed Topgul on 07/10/2021 at 12:55
 */

@MappedSuperclass
@Data
public abstract class Account extends BaseId {

    private String owner;
    private BigDecimal balance;
    private BigDecimal interestRate;
}
