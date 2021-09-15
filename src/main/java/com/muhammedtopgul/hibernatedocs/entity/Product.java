package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.converter.basicType.BitSetType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.BitSet;

/**
 * created by Muhammed Topgul on 15/09/2021 at 11:43
 */

@Entity(name = "Product")
@TypeDef(
        name = "bitset",
        defaultForType = BitSet.class,
        typeClass = BitSetType.class
)
@Getter
@Setter
public class Product {

    @Id
    private Integer id;

    @Type(type = "bitset")
    private BitSet bitSet;
}
