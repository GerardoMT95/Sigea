package it.indra.SigecAPI.rsql;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

/**
 * <p>
 * Classe che traduce il nodo in jpa specification
 * </p>
 *
 * @author Vito Losito
 */

public class GenericRsqlSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 4341304968383194722L;
	private String property;
    private ComparisonOperator operator;
    private List<String> arguments;
    private String jsonBColumn;
    private String[] path;
    private String[] pathDeepArray;
    private boolean endingWithArray = false;
    private boolean noneOf = false;
    private String lastObjPath;
    private String nextIsJsonParamOfType;

    public GenericRsqlSpecification(final String property, final ComparisonOperator operator, final List<String> arguments) {
        super();
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;
        if (property.contains("[]")) {
        	if(property.endsWith("^")){
        		noneOf = true;
        	}
        	if(property.endsWith("[]")) {
        		endingWithArray = true;
        	}
        	if (property.indexOf(".") > property.indexOf("[]")) {
            	this.jsonBColumn = property.substring(0, property.indexOf("[]"));
            	pathDeepArray = property.replace("^", "").substring(property.indexOf("[]")+2).split("\\[\\]"); 
        	}else{
            	this.jsonBColumn = property.substring(0, property.indexOf("."));
            	pathDeepArray = property.replace("^", "").substring(property.indexOf(".")+1).split("\\[\\]");
        	}
        	if (!endingWithArray) {
        		String[] subPathDeepArray = new String[pathDeepArray.length-1];
        		for(int i = 0 ; i < pathDeepArray.length-1; i++ ) {
        			subPathDeepArray[i] = pathDeepArray[i];
        		}
        		lastObjPath = pathDeepArray[pathDeepArray.length-1];
        		pathDeepArray = subPathDeepArray;
        	}
        }else if (property.contains(".")) {
            this.jsonBColumn = property.substring(0, property.indexOf("."));
            path = property.substring(property.indexOf(".")+1).split("[.]"); 
        }
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        final List<Object> args = castArguments(root);
        final Object argument = args.get(0);
        List<Expression<?>> exprList = new ArrayList<Expression<?>>();
        Expression<String> jsonAttribute = null;
        List<String> exprListString = new ArrayList<String>();
        String expressionBuilder = null;
        List<String> deepArray = new ArrayList<String>();
        if(pathDeepArray != null) {
        	List<String> pathExpr = new ArrayList<String>();
        	for(int i = 0; i<pathDeepArray.length; i++) {
    			String[] subPath = pathDeepArray[i].split("[.]");
        		if (i==0) {
        			deepArray.add("'$'");
        		}else {
        			deepArray.add("x"+(i-1));
        		}
    			for (int j = 0; j<subPath.length; j++) {
    				deepArray.add("'" + subPath[j] + "'");
            	}
    			pathExpr.add("jsonb_extract_path(" + String.join(",", deepArray) + ")");
    			deepArray.clear();
        	}
        	String[] pathFinderArray = new String[pathExpr.size()];
        	pathExpr.toArray(pathFinderArray);
        	for (int i = pathDeepArray.length - 1; i>-1; i--) {
        		if(i == pathDeepArray.length - 1){
                	if (endingWithArray) {
                		expressionBuilder = "(exists (SELECT true FROM jsonb_array_elements("+pathFinderArray[i]+") x" + i +" WHERE x" + i +" ? '"+ argument.toString() +"'))";
                	}else {
                		String evaluation = null;
                		String pathEvaluation = null;
                		String[] lastObjPathSplit = lastObjPath.split("[.]") ;
                		for (int j = 0; j<lastObjPathSplit.length; j++) {
                    		exprListString.add(lastObjPathSplit[j]);
                    	}
                		pathEvaluation = "jsonb_extract_path_text(x"+i+", '" + String.join("','", exprListString) + "')";
                		switch (RsqlSearchOperation.getSimpleOperator(operator)) {
	                		case EQUAL: {
	                			evaluation = pathEvaluation + " LIKE '" + argument.toString().replace('*', '%') + "'";
	                	        break; 
	                		}
	                		case NOT_EQUAL: {
	                			evaluation = pathEvaluation + " NOT LIKE '" + argument.toString().replace('*', '%') + "'";
	                	        break; 
	                		}
	                		case GREATER_THAN: {
	                			evaluation = pathEvaluation + "::"+nextIsJsonParamOfType+" > '" + argument.toString()+ "'::"+nextIsJsonParamOfType;
	                	        break; 
	                		}
	                		case GREATER_THAN_OR_EQUAL: {
	                			evaluation = pathEvaluation + "::"+nextIsJsonParamOfType+" >= '" + argument.toString()+ "'::"+nextIsJsonParamOfType;
	                	        break; 
	                		}
	                		case LESS_THAN: {
	                			evaluation = pathEvaluation + "::"+nextIsJsonParamOfType+" < '" + argument.toString()+ "'::"+nextIsJsonParamOfType;
	                	        break; 
	                		}
	                		case LESS_THAN_OR_EQUAL: {
	                			evaluation = pathEvaluation + "::"+nextIsJsonParamOfType+" <= '" + argument.toString()+ "'::"+nextIsJsonParamOfType;
	                	        break; 
	                		}
	                		case IN: {
	                			List<String> argString = new ArrayList<String>();
	                			for(Object o : args) {
	                				argString.add((String) o);
	                			}
	                			evaluation = pathEvaluation + " IN (" + String.join(",", argString) + ")";
	                	        break; 
	                		}
	                		case NOT_IN:{
	                			List<String> argString = new ArrayList<String>();
	                			for(Object o : args) {
	                				argString.add((String) o);
	                			}
	                			evaluation = pathEvaluation + " NOT IN ('" + String.join("','", argString) + "')";
	                	        break; 
	                		}
                		}
                		expressionBuilder = "(exists (SELECT true FROM jsonb_array_elements("+pathFinderArray[i]+") x"+i+" WHERE "+evaluation+"))";
                	}
        		}else {
        			expressionBuilder = "(exists (SELECT true FROM jsonb_array_elements("+pathFinderArray[i]+") x"+i+" WHERE "+expressionBuilder+"))";
        		}
        	}
        	expressionBuilder = expressionBuilder.substring(expressionBuilder.indexOf("SELECT"), expressionBuilder.length() - 2);
        	expressionBuilder = expressionBuilder.replaceFirst("true", "count(*)");
        	if(noneOf) {
        		return builder.equal((builder.function("arraypathfinder", Integer.class,root.<String>get(jsonBColumn), builder.literal(expressionBuilder))),0);
        	}
        	return builder.greaterThan((builder.function("arraypathfinder", Integer.class,root.<String>get(jsonBColumn), builder.literal(expressionBuilder))),0);
        }else if (jsonBColumn!=null) {
        	exprList.add(root.<String>get(jsonBColumn));
        	for (int i = 0; i<path.length; i++) {
        		exprList.add(builder.literal(path[i]));
        	}
        	jsonAttribute = builder.function("jsonb_extract_path_text",String.class, exprList.toArray(new Expression<?>[exprList.size()]));
        }
        
        switch (RsqlSearchOperation.getSimpleOperator(operator)) {

        case EQUAL: {
        	if (jsonBColumn!=null) {
        		return builder.like(jsonAttribute, argument.toString().replace('*', '%'));
        	}else {
                if (argument instanceof String) {
                    return builder.like(root.get(property), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNull(root.get(property));
                } else if (argument instanceof java.sql.Date) {
        			Expression<Date> columnDate = builder.function("to_date", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd")), builder.literal("yyyy-MM-dd"));
        			Expression<Date> filterDate = builder.function("to_date", Date.class, builder.literal((java.sql.Date) argument), builder.literal("yyyy-MM-dd"));
        			return builder.equal(columnDate,filterDate);
        		}else if (argument instanceof java.sql.Timestamp) {
        			Expression<Date> columnDate = builder.function("to_timestamp", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd hh24:mi:ss")), builder.literal("yyyy-MM-dd hh24:mi:ss"));
        			Expression<Date> filterDate = builder.function("to_timestamp", Date.class, builder.literal((java.sql.Timestamp) argument), builder.literal("yyyy-MM-dd hh24:mi:ss"));
        			return builder.equal(columnDate,filterDate);
        		} else {
                    return builder.equal(root.get(property), argument);
                }
        	}
        }
        case NOT_EQUAL: {
        	if (jsonBColumn!=null) {
        		return builder.notLike(jsonAttribute, argument.toString().replace('*', '%'));
        	}else {
	            if (argument instanceof String) {
	                return builder.notLike(root.<String> get(property), argument.toString().replace('*', '%'));
	            } else if (argument == null) {
	                return builder.isNotNull(root.get(property));
	            } else if (argument instanceof java.sql.Date) {
        			Expression<Date> columnDate = builder.function("to_date", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd")), builder.literal("yyyy-MM-dd"));
        			Expression<Date> filterDate = builder.function("to_date", Date.class, builder.literal((java.sql.Date) argument), builder.literal("yyyy-MM-dd"));
        			return builder.notEqual(columnDate,filterDate);
        		}else if (argument instanceof java.sql.Timestamp) {
        			Expression<Date> columnDate = builder.function("to_timestamp", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd hh24:mi:ss")), builder.literal("yyyy-MM-dd hh24:mi:ss"));
        			Expression<Date> filterDate = builder.function("to_timestamp", Date.class, builder.literal((java.sql.Timestamp) argument), builder.literal("yyyy-MM-dd hh24:mi:ss"));
        			return builder.notEqual(columnDate,filterDate);
        		}else {
	                return builder.notEqual(root.get(property), argument);
	            }
        	}
        }
        case GREATER_THAN: {
        	if (jsonBColumn!=null) {
        		return builder.greaterThan(jsonAttribute, argument.toString());
        	}else {
        		if (argument instanceof java.sql.Date) {
        			Expression<Date> columnDate = builder.function("to_date", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd")), builder.literal("yyyy-MM-dd"));
        			Expression<Date> filterDate = builder.function("to_date", Date.class, builder.literal((java.sql.Date) argument), builder.literal("yyyy-MM-dd"));
        			return builder.greaterThan(columnDate,filterDate);
        		}else if (argument instanceof java.sql.Timestamp) {
        			Expression<Date> columnDate = builder.function("to_timestamp", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd hh24:mi:ss")), builder.literal("yyyy-MM-dd hh24:mi:ss"));
        			Expression<Date> filterDate = builder.function("to_timestamp", Date.class, builder.literal((java.sql.Timestamp) argument), builder.literal("yyyy-MM-dd hh24:mi:ss"));
        			return builder.greaterThan(columnDate,filterDate);
        		}else {
            		return builder.greaterThan(root.<String> get(property), argument.toString());
        		}
        	}
        }
        case GREATER_THAN_OR_EQUAL: {
        	if (jsonBColumn!=null) {
        		return builder.greaterThanOrEqualTo(jsonAttribute, argument.toString());
        	} else if (argument instanceof java.sql.Date) {
    			Expression<Date> columnDate = builder.function("to_date", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd")), builder.literal("yyyy-MM-dd"));
    			Expression<Date> filterDate = builder.function("to_date", Date.class, builder.literal((java.sql.Date) argument), builder.literal("yyyy-MM-dd"));
    			return builder.greaterThanOrEqualTo(columnDate,filterDate);
    		}else if (argument instanceof java.sql.Timestamp) {
    			Expression<Date> columnDate = builder.function("to_timestamp", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd hh24:mi:ss")), builder.literal("yyyy-MM-dd hh24:mi:ss"));
    			Expression<Date> filterDate = builder.function("to_timestamp", Date.class, builder.literal((java.sql.Timestamp) argument), builder.literal("yyyy-MM-dd hh24:mi:ss"));
    			return builder.greaterThanOrEqualTo(columnDate,filterDate);
    		}else {
        		return builder.greaterThanOrEqualTo(root.<String> get(property), argument.toString());
        	}
        }
        case LESS_THAN: {
        	if (jsonBColumn!=null) {
        		return builder.lessThan(jsonAttribute, argument.toString());
        	} else if (argument instanceof java.sql.Date) {
    			Expression<Date> columnDate = builder.function("to_date", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd")), builder.literal("yyyy-MM-dd"));
    			Expression<Date> filterDate = builder.function("to_date", Date.class, builder.literal((java.sql.Date) argument), builder.literal("yyyy-MM-dd"));
    			return builder.lessThan(columnDate,filterDate);
    		}else if (argument instanceof java.sql.Timestamp) {
    			Expression<Date> columnDate = builder.function("to_timestamp", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd hh24:mi:ss")), builder.literal("yyyy-MM-dd hh24:mi:ss"));
    			Expression<Date> filterDate = builder.function("to_timestamp", Date.class, builder.literal((java.sql.Timestamp) argument), builder.literal("yyyy-MM-dd hh24:mi:ss"));
    			return builder.lessThan(columnDate,filterDate);
    		}else {
        		return builder.lessThan(root.<String> get(property), argument.toString());
        	}
        }
        case LESS_THAN_OR_EQUAL: {
        	if (jsonBColumn!=null) {
        		return builder.lessThanOrEqualTo(jsonAttribute, argument.toString());
        	} else if (argument instanceof java.sql.Date) {
    			Expression<Date> columnDate = builder.function("to_date", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd")), builder.literal("yyyy-MM-dd"));
    			Expression<Date> filterDate = builder.function("to_date", Date.class, builder.literal((java.sql.Date) argument), builder.literal("yyyy-MM-dd"));
    			return builder.lessThanOrEqualTo(columnDate,filterDate);
    		}else if (argument instanceof java.sql.Timestamp) {
    			Expression<Date> columnDate = builder.function("to_timestamp", Date.class, builder.function("to_char", String.class, root.get(property),builder.literal("yyyy-MM-dd hh24:mi:ss")), builder.literal("yyyy-MM-dd hh24:mi:ss"));
    			Expression<Date> filterDate = builder.function("to_timestamp", Date.class, builder.literal((java.sql.Timestamp) argument), builder.literal("yyyy-MM-dd hh24:mi:ss"));
    			return builder.lessThanOrEqualTo(columnDate,filterDate);
    		}else {
        		return builder.lessThanOrEqualTo(root.<String> get(property), argument.toString());
        	}
        }
        case IN:
        	if (jsonBColumn!=null) {
        		return jsonAttribute.in(args);
        	}else {
        		return root.get(property).in(args);
        	}
        case NOT_IN:
        	if (jsonBColumn!=null) {
        		return builder.not(jsonAttribute.in(args));
        	}else {
        		return builder.not(root.get(property).in(args));
        	}
        }

        return null;
    }

    private List<Object> castArguments(final Root<T> root) {
        if (jsonBColumn==null) {
	        final Class<? extends Object> type = root.get(property).getJavaType();
	        System.out.println(property + "type "+ type.getName());
	        final List<Object> args = arguments.stream().map(arg -> {
	            if (type.equals(Integer.class)) {
	               return Integer.parseInt(arg);
	            } else if (type.equals(Long.class)) {
	               return Long.parseLong(arg);
	            } else if (type.equals(BigDecimal.class)) {
	            	return new BigDecimal(String.valueOf(arg));
	            } else if (type.equals(java.sql.Date.class)) {
	            	return Date.valueOf(String.valueOf(arg).replace("T", " "));
	            } else if (type.equals(java.sql.Timestamp.class)) {
	            	try {
	            		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	            		java.util.Date date = (java.util.Date) formatter.parse(String.valueOf(arg).replace("T", " "));
	            	    java.sql.Timestamp timeStampDate = new java.sql.Timestamp(date.getTime());
	            	    return timeStampDate;
					} catch (ParseException e) {
						e.printStackTrace();
						return arg;
					}
	            }  else {
	                return arg;
	            }            
	        }).collect(Collectors.toList());
	
	        return args;
        }else {
        	final List<Object> args = arguments.stream().map(arg -> {
        		if(String.valueOf(arg).matches("^([0-9]{4})-([0-1][0-9])-([0-3][0-9])T([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$")) {
        			nextIsJsonParamOfType = "date";
        			return String.valueOf(arg).replace("T", " ");
        		}else {
        			nextIsJsonParamOfType = "integer";
        			return arg;
        		}
        	}).collect(Collectors.toList());
        	return args;
        }
    }

}
