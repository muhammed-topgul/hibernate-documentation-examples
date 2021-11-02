package com.muhammedtopgul.hibernatedocs.basics.annotation;

import com.muhammedtopgul.hibernatedocs.basics.annotation.impl.CustomCreationValueGeneration;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author muhammed-topgul created at 16/09/2021 11:25
 */

@ValueGenerationType(generatedBy = CustomCreationValueGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomCreationTimestamp {
}
