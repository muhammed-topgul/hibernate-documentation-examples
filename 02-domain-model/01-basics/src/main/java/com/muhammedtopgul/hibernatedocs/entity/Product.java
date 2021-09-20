package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseTimestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.BitSet;

/**
 * created by Muhammed Topgul on 15/09/2021 at 11:43
 */

@Entity(name = "Product")
//@TypeDef(
//        name = "bitset",
//        defaultForType = BitSet.class,
//        typeClass = BitSetType.class
//)
@Getter
@Setter
@ToString
public class Product extends BaseTimestamp {

    private String name;

    @Type(type = "bitset")
    private BitSet bitSet;

    @Lob
    private String warranty;

    @Lob
    private byte[] image;
}
