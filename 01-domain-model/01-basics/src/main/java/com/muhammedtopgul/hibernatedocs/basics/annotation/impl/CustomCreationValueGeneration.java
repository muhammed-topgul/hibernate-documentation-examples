package com.muhammedtopgul.hibernatedocs.basics.annotation.impl;

import com.muhammedtopgul.hibernatedocs.basics.annotation.CustomCreationTimestamp;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

/**
 * @author muhammed-topgul created at 16/09/2021 11:26
 */

public class CustomCreationValueGeneration implements AnnotationValueGeneration<CustomCreationTimestamp> {

    @Override
    public void initialize(CustomCreationTimestamp annotation, Class<?> propertyType) {
    }

    /**
     * Generate value on INSERT
     *
     * @return when to generate the value
     */
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    /**
     * Returns null because the value is generated by the database.
     *
     * @return null
     */
    public ValueGenerator<?> getValueGenerator() {
        return null;
    }

    /**
     * Returns true because the value is generated by the database.
     *
     * @return true
     */
    public boolean referenceColumnInSql() {
        return true;
    }

    /**
     * Returns the database-generated value
     *
     * @return database-generated value
     */
    public String getDatabaseGeneratedReferencedColumnValue() {
        return "current_timestamp";
    }
}
