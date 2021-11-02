package com.muhammedtopgul.hibernatedocs.schemageneration.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author muhammed-topgul created at 12/10/2021 13:13
 */

@Entity(name = "Person")
@Table(schema = "schema_generation", name = "person")
@DynamicInsert
@Getter
@Setter
public class Person extends BaseId {

    @ColumnDefault("'N/A'")
    private String name;

    @ColumnDefault("-1")
    private Long clientId;
}
