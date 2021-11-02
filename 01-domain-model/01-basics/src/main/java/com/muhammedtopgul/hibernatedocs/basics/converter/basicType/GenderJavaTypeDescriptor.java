package com.muhammedtopgul.hibernatedocs.basics.converter.basicType;

import com.muhammedtopgul.hibernatedocs.basics.enumeration.Gender;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.CharacterTypeDescriptor;

/**
 * @author muhammed-topgul created at 15/09/2021 17:36
 */

public class GenderJavaTypeDescriptor extends AbstractTypeDescriptor<Gender> {

    public static final GenderJavaTypeDescriptor INSTANCE = new GenderJavaTypeDescriptor();

    protected GenderJavaTypeDescriptor() {
        super(Gender.class);
    }

    public String toString(Gender value) {
        return value == null ? null : value.name();
    }

    public Gender fromString(String string) {
        return string == null ? null : Gender.valueOf(string);
    }

    public <X> X unwrap(Gender value, Class<X> type, WrapperOptions options) {
        return CharacterTypeDescriptor.INSTANCE.unwrap(
                value == null ? null : value.getCode(),
                type,
                options
        );
    }

    public <X> Gender wrap(X value, WrapperOptions options) {
        return Gender.fromCode(
                CharacterTypeDescriptor.INSTANCE.wrap(value, options)
        );
    }
}
