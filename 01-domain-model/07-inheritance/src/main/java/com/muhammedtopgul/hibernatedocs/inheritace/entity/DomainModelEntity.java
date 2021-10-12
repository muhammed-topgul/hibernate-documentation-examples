package com.muhammedtopgul.hibernatedocs.inheritace.entity;

/**
 * created by Muhammed Topgul on 12/10/2021 at 11:14
 */

public interface DomainModelEntity<ID> {

    ID getId();

    Integer getVersion();
}
