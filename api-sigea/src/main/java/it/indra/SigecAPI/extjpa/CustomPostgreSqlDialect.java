package it.indra.SigecAPI.extjpa;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomPostgreSqlDialect extends PostgreSQL94Dialect {

    public CustomPostgreSqlDialect() {
    	super();
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
        
        this.registerFunction("string_agg", new SQLFunctionTemplate( StandardBasicTypes.STRING, "string_agg(?1, ?2)") );
        this.registerFunction("string_agg", new SQLFunctionTemplate( StandardBasicTypes.STRING, "string_agg(?1, ?2 ORDER BY ?3 )") );
        
        this.registerFunction("arraypathfinder", new SQLFunctionTemplate( StandardBasicTypes.INTEGER, "sigec.arraypathfinder(?1,?2)") );
    }
}
