package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author muhammed-topgul created at 22/09/2021 22:32
 */

@Entity(name = "PhoneDetail")
@Table(schema = "associations", name = "phone_detail")
@Getter
@Setter
public class PhoneDetail extends BaseId {

    private String provider;

    private String technology;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    public Phone phone;
}
