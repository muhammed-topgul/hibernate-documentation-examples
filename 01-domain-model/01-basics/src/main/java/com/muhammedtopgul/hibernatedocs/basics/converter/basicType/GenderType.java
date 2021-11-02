package com.muhammedtopgul.hibernatedocs.basics.converter.basicType;

import com.muhammedtopgul.hibernatedocs.basics.enumeration.Gender;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

/**
 * @author muhammed-topgul created at 15/09/2021 17:35
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
