package com.dsw.getback.query.helper;

/**
 * jpql object
 * @author dell
 *
 */
public class JPQL2Objects {
	/**
	 * jpql 
	 */
    private String jpql;
    
    /**
     * parmas
     */
    private Object[] args;
    
    public JPQL2Objects(String jpql, Object[] args) {
        this.args = args;
        this.jpql = jpql;
    }
    
    /**
     * jpql
     * @return
     */
    public String getJpql() {
        return jpql;
    }
    
    /**
     * jpql
     * @param jpql
     */
    public void setJpql(String jpql) {
        this.jpql = jpql;
    }
    
    /**
     * parmas
     * @return
     */
    public Object[] getArgs() {
        return args;
    }
    
    /**
     * parmas
     * @param args
     */
    public void setArgs(Object[] args) {
        this.args = args;
    }
    
}
