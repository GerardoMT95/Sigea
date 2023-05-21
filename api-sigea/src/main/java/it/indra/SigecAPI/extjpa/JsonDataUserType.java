package it.indra.SigecAPI.extjpa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SerializationException;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataUserType implements UserType , DynamicParameterizedType {

	private Class<?> clazz;

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
	    final String cellContent = rs.getString(names[0]);
	    if (cellContent == null) {
	        return null;
	    }
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        return mapper.readValue(cellContent.getBytes("UTF-8"), returnedClass());
	    } catch (final Exception ex) {
	        throw new RuntimeException("Failed to convert String to Invoice: " + ex.getMessage(), ex);
	    }
	}
	
	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int idx, SharedSessionContractImplementor session) throws HibernateException, SQLException {
	    if (value == null) {
	        ps.setNull(idx, Types.OTHER);
	        return;
	    }
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final StringWriter w = new StringWriter();
	        mapper.writeValue(w, value);
	        w.flush();
	        ps.setObject(idx, w.toString(), Types.OTHER);
	    } catch (final Exception ex) {
	        throw new RuntimeException("Failed to convert Invoice to String: " + ex.getMessage(), ex);
	    }
	}


    @Override
	public Object deepCopy(final Object value) throws HibernateException {
	    try {
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(bos);
	        oos.writeObject(value);
	        oos.flush();
	        oos.close();
	        bos.close();
	         
	        ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
	        Object obj = new ObjectInputStream(bais).readObject();
	        bais.close();
	        return obj;
	    } catch (ClassNotFoundException | IOException ex) {
	        throw new HibernateException(ex);
	    }
	}

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
    	if (returnedClass() == String.class) {
    		return (String)this.deepCopy(value);
    	}else {
		    Object copy = deepCopy(value);
		
		    if (copy instanceof Serializable) {
		        return (Serializable) copy;
		    }
		
		    throw new SerializationException(String.format("Cannot serialize '%s', %s is not Serializable.", value, value.getClass()), null);
    	}
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        if (x == null) {
            return 0;
        }

        return x.hashCode();
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ObjectUtils.nullSafeEquals(x, y);
    }

    @Override
    public Class<?> returnedClass() {
        return clazz;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

	@Override
	public void setParameterValues(Properties parameters) {
		String clazzString = parameters.getProperty("class");
		try {
			clazz = Class.forName(clazzString);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}