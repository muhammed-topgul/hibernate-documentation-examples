package com.muhammedtopgul.hibernatedocs.inheritace.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * created by Muhammed Topgul on 12/10/2021 at 11:19
 */

@Entity(name = "Blog")
@Table(schema = "inheritances", name = "blog")
@Polymorphism(type = PolymorphismType.EXPLICIT) // hibernate will not get Blog from database because of PolymorphismType.EXPLICIT
@Getter                                         // hibernate only fetch PolymorphismType.EXPLICIT or they are not annotated at all with the @Polymorphism annotation
@Setter
public class Blog implements DomainModelEntity<Long> {

    @Id
    @Getter(AccessLevel.NONE)
    private Long id;

    @Version
    @Getter(AccessLevel.NONE)
    private Integer version;

    private String site;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Integer getVersion() {
        return this.version;
    }
}
