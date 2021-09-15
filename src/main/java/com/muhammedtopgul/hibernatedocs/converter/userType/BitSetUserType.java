package com.muhammedtopgul.hibernatedocs.converter.userType;


import com.muhammedtopgul.hibernatedocs.converter.basicType.BitSetTypeDescriptor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.BitSet;
import java.util.Objects;

/**
 * created by Muhammed Topgul on 15/09/2021 at 17:07
 */

public class BitSetUserType implements UserType {

    public static final BitSetUserType INSTANCE = new BitSetUserType();

    @Override
    public int[] sqlTypes() {
        return new int[]{StringType.INSTANCE.sqlType()};
    }

    @Override
    public Class returnedClass() {
        return BitSet.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return Objects.hashCode(x);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
            throws HibernateException, SQLException {
        String columnName = names[0];
        String columnValue = (String) rs.getObject(columnName);
        System.out.println(String.format("Result set column {0} value is {1}", columnName, columnValue));
        return columnValue == null ? null : BitSetTypeDescriptor.INSTANCE.fromString(columnValue);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        if (value == null) {
            System.out.println(String.format("Binding null to parameter {0} ", index));
            st.setNull(index, Types.VARCHAR);
        } else {
            String stringValue = BitSetTypeDescriptor.INSTANCE.toString((BitSet) value);
            System.out.println(String.format("Binding {0} to parameter {1} ", stringValue, index));
            st.setString(index, stringValue);
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value == null ? null :
                BitSet.valueOf(BitSet.class.cast(value).toLongArray());
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value)
            throws HibernateException {
        return (BitSet) deepCopy(value);
    }

    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }
}