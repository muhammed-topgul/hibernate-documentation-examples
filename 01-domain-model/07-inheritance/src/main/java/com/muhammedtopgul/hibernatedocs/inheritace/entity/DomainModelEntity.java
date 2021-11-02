package com.muhammedtopgul.hibernatedocs.inheritace.entity;

/**
 * @author muhammed-topgul created at 12/10/2021 11:14
 */

public interface DomainModelEntity<ID> {

    ID getId();

    Integer getVersion();
}
