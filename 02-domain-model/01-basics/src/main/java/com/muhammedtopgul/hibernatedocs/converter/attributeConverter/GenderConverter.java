package com.muhammedtopgul.hibernatedocs.converter.attributeConverter;

import com.muhammedtopgul.hibernatedocs.enumeration.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * created by Muhammed Topgul on 15/09/2021 at 17:22
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
