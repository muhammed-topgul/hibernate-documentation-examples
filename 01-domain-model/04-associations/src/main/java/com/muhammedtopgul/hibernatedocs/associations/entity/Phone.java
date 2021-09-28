package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * created by Muhammed Topgul on 21/09/2021 at 16:10
 */

@Entity(name = "Phone")
@Table(schema = "associations", name = "phone")
@Getter
@Setter
public class Phone extends BaseId {

    @Column(name = "`number`")
    private String number;

    @OneToOne(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PhoneDetail phoneDetail;

    public void addPhoneDetail(PhoneDetail phoneDetail) {
        this.phoneDetail = phoneDetail;
        phoneDetail.setPhone(this);
    }

    public void removePhoneDetail(PhoneDetail phoneDetail) {
        if (phoneDetail != null) {
            this.phoneDetail = null;
            phoneDetail.setPhone(null);
        }
    }
}
