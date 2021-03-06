package com.muhammedtopgul.hibernatedocs.basics.entity.base;

import com.muhammedtopgul.hibernatedocs.basics.user.LoggedUserGenerator;
import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author muhammed-topgul created at 16/09/2021 11:03
 */

@MappedSuperclass
@Getter
public abstract class BaseTimestamp extends BaseId {

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date lastModified;

    @GeneratorType(type = LoggedUserGenerator.class, when = GenerationTime.INSERT)
    private String createdBy;

    @GeneratorType(type = LoggedUserGenerator.class, when = GenerationTime.ALWAYS)
    private String updatedBy;
}
