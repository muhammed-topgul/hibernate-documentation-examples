package com.muhammedtopgul.hibernatedocs.basics.converter.attributeConverter;

import com.muhammedtopgul.hibernatedocs.basics.enumeration.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author muhammed-topgul created at 15/09/2021 17:22
 */

@Converter
public class GenderConverter implements AttributeConverter<Gender, Character> {

    @Override
    public Character convertToDatabaseColumn(Gender attribute) {
        if (attribute == null)
            return null;

        return attribute.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(Character dbData) {
        if (dbData == null)
            return null;

        return Gender.fromCode(dbData);
    }
}
