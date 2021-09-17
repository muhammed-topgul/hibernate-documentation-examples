package com.muhammedtopgul.hibernatedocs.converter.basicType;

import com.muhammedtopgul.hibernatedocs.enumeration.Gender;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

/**
 * created by Muhammed Topgul on 15/09/2021 at 17:35
 */

public class GenderType extends AbstractSingleColumnStandardBasicType<Gender> {

    public static final GenderType INSTANCE = new GenderType();

    public GenderType() {
        super(
                CharTypeDescriptor.INSTANCE,
                GenderJavaTypeDescriptor.INSTANCE
        );
    }

    public String getName() {
        return "genderType";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
