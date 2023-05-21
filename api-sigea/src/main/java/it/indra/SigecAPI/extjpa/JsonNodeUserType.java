package it.indra.SigecAPI.extjpa;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class JsonNodeUserType implements UserType {
 
  private static final int[] SQL_TYPES = { Types.LONGVARCHAR };
 
  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return deepCopy(cached);
  }
 
  @Override
  public Object deepCopy(Object value) throws HibernateException {
    if (value == null) return value;
    try {
    	return ((JsonNode) value).deepCopy();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
 
  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return ((JsonNode)value).toString();
  }
 
  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == null) return (y != null);
    return (x.equals(y));
  }
 
  @Override
  public int hashCode(Object x) throws HibernateException {
    return ((JsonNode)x).toString().hashCode();
  }
 
  @Override
  public boolean isMutable() {
    return false;
  }
 
 
  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)throws HibernateException, SQLException {
		String jsonString = rs.getString(names[0]);
		if (jsonString == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = null;
		try {
			actualObj = mapper.readValue(jsonString, JsonNode.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualObj;
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
      if (value == null) {
          st.setNull(index, Types.OTHER);
      } else {

          st.setObject(index, ((JsonNode) value).toString(),Types.OTHER);
      }

  }
 
  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return deepCopy(original);
  }
 
  @Override
  public Class<?> returnedClass() {
    return JsonNode.class;
  }
 
  @Override
  public int[] sqlTypes() {
    return SQL_TYPES;
  }

 
}
