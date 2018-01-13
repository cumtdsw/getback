package com.dsw.getback.query.helper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import com.dsw.getback.query.Order;
import com.dsw.getback.query.OrderType;
import com.dsw.getback.query.condition.BaseCondition;
import com.dsw.getback.query.result.QueryResult;

public class ServiceHelper {

    private static Logger logger = LogManager.getLogger(ServiceHelper.class);

    /**
     * 获取排序字段的sql语句
     * 
     * @param orders 排序字段
     * @param alias 表的别名
     * @return 生成的排序sql语句
     */
    public static String getOrderNativeSql(Order[] orders, String alias) {
        logger.info("now enter ServiceHelper.getOrderNativeSql() method.");
        logger.info("parameter [alias] is:" + alias);

        if (orders == null)
            return "";

        StringBuffer sql = new StringBuffer();

        boolean orderByFirst = true;

        for (Order of : orders) {
            String orderName = of.getName();
            String order = " " + (of.getType() == OrderType.DESC ? "desc" : "asc");

            if (orderName != null && !(orderName.trim().equals(""))) {
                if (orderByFirst) {
                    sql.append(" order by ");
                    orderByFirst = false;
                } else {
                    sql.append(", ");
                }
                if (alias != null && !alias.equals("")) {
                    sql.append(alias.trim()).append(".");
                }
                sql.append(orderName.trim()).append(order);
            }
        }

        logger.info("order sql is:" + sql.toString());
        logger.info("now leave ServiceHelper.getOrderNativeSql() method.");

        return sql.toString();
    }
    
    /**
     * 获取排序字段的jpql语句
     * 
     * @param orders
     *            排序字段
     * @param alias
     *            表的别名
     * @return 生成的排序jpql语句
     */
    public static String getOrderHql(Order[] orders, String alias) {
        logger.info("now enter ServiceHelper.getOrderHql() method.");
        logger.info("parameter [alias] is:" + alias);

        if (orders == null)
            return "";

        StringBuffer hql = new StringBuffer();

        boolean orderByFirst = true;

        for (Order of : orders) {
            String orderName = of.getName();
            String order = " " + (of.getType() == OrderType.DESC ? "desc" : "asc");

            if (orderName != null && !(orderName.trim().equals(""))) {
                if (orderByFirst) {
                    hql.append(" order by ");
                    orderByFirst = false;
                } else {
                    hql.append(", ");
                }
                if (alias != null && !alias.equals("")) {
                    hql.append(alias.trim()).append(".");
                }
                hql.append(orderName.trim()).append(order);
            }
        }

        logger.info("order sql is:" + hql.toString());
        logger.info("now leave ServiceHelper.getOrderHql() method.");

        return hql.toString();
    }
    
    /**
     * 增加排序字段 
     * @param criteria
     * @param orders
     */
    public static void addOrder(Criteria criteria, Order[] orders) {
        if (orders == null || orders.length < 1)
            return;

        for (Order od : orders) {
            if (od.getType() == OrderType.DESC) {
                org.hibernate.criterion.Order order = org.hibernate.criterion.Order.desc(od.getName());
                criteria.addOrder(order);
            } else {
                org.hibernate.criterion.Order order = org.hibernate.criterion.Order.asc(od.getName());
                criteria.addOrder(order);
            }
        }
    }
    
    /**
     * 将long数组转成字符串
     * @param val - long数组
     * @return 逗号分隔的字符串
     */
    public static String toString(long[] val) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < val.length - 1; ++i) {
            builder.append(i).append(",");
        }
        builder.append(val[val.length - 1]);
        return builder.toString();
    }

    public static int appendArg(StringBuilder sb, List<Object> args, int no, long[] val) {
        for (int i = 0; i < val.length; ++i, ++no) {
            if (i < val.length - 1)
                sb.append("?").append(no).append(" , ");
            else
                sb.append("?").append(no);

            args.add(val[i]);
        }
        return no;
    }

    public static <T> int appendArg(StringBuilder sb, List<Object> args, int startIndex, T[] val) {
        for (int i = 0; i < val.length; ++i, ++startIndex) {
            if (i < val.length - 1)
                sb.append("?").append(startIndex).append(" , ");
            else
                sb.append("?").append(startIndex);
            args.add(val[i]);
        }
        sb.append(")");
        return startIndex;
    }
    
    public static int appendArg(StringBuilder sb, List<Object> args, int no, int[] val) {
        for (int i = 0; i < val.length; ++i, ++no) {
            if (i < val.length - 1)
                sb.append("?").append(no).append(" , ");
            else
                sb.append("?").append(no);

            args.add(val[i]);
        }
        return no;
    }

    public static int appendArg(StringBuilder sb, List<Object> args, int no, String[] val) {
        for (int i = 0; i < val.length; ++i, ++no) {
            if (i < val.length - 1)
                sb.append("?").append(no).append(" , ");
            else
                sb.append("?").append(no);

            args.add(val[i]);
        }
        return no;
    }

    /**
     * 按查询条件进行查询
     * 
     * @param query
     *            使用Hibernate直接进行查询，主要用在Spatial查询中
     * @param cond
     *            查询条件
     * @return 查询结果
     */
    @SuppressWarnings("unchecked")
    public static QueryResult advQuery(org.hibernate.Query query, BaseCondition cond) {
        // check parameter
        if (cond == null) {
            logger.info("parameter cond is null.");
            throw new IllegalArgumentException("parameter cond is null.");
        }

        // check qmodel.
        if (cond.isPaged() && (cond.getCurrPage() <= 0 || cond.getPageSize() <= 0)) {
            logger.info("parameter currentPage or pageSize is invalide.");
            throw new IllegalArgumentException("parameter currentPage or pageSize is invalide.");
        }

        QueryResult result = new QueryResult();
        if (!cond.isPaged()) {
            result.setResults(query.list());
            return result;
        }

        int currentPage = cond.getCurrPage();
        int pageSize = cond.getPageSize();
        int maxPages = (cond.getMaxPages() <= 0 ? 10 : cond.getMaxPages());
        int startPage = currentPage / maxPages * maxPages + (currentPage % maxPages == 0 ? -(maxPages - 1) : 1);

        int first = (startPage - 1) * pageSize;
        query.setFirstResult(first);
        query.setMaxResults(pageSize * (maxPages + 1));
        List<Object> listRtn = query.list();

        // SplitFirstModel sf = new SplitFirstModel();
        int nCount = listRtn.size();
        int nPageNumber = nCount % pageSize > 0 ? (nCount / pageSize + 1) : (nCount / pageSize);

        // set paged info
        result.setTotalPages(nPageNumber + startPage - 1);
        result.setCurrPage(currentPage);
        result.setIsPaged(true);
        result.setPageSize(pageSize);
        result.setStartPage(startPage);
        int endPage = startPage + nPageNumber - 1;
        endPage = endPage > (startPage + maxPages - 1) ? (startPage + maxPages - 1) : endPage;
        result.setEndPage(endPage);
        result.setMaxPages(maxPages);

        int fromIdx = (currentPage - startPage) * pageSize;
        fromIdx = (fromIdx > nCount ? nCount : fromIdx);
        int toIdx = fromIdx + pageSize;
        toIdx = (toIdx > nCount ? nCount : toIdx);
        for (int i = fromIdx; i < toIdx; ++i) {
            result.addResult(listRtn.get(i));
        }

        logger.info("now leave function EntityServiceBean.advQuery().");

        return result;
    }
    
    /**
     * 根据包含字段设置select表达式
     * 
     * @param aliasName
     * @param includeFields
     * @return
     */
    public static void setProjections(Criteria criteria, String[] includeFields) {
        if (includeFields == null || includeFields.length < 1)
            return;

        ProjectionList projList = Projections.projectionList();
        for (String f : includeFields) {
            projList.add(Projections.property(f));
        }
        criteria.setProjection(projList);
    }
    
    /**
     * 根据包含字段设置select表达式
     * 
     * @param aliasName
     * @param includeFields
     * @return
     */
    public static String getSelectExpression(String aliasName, String[] includeFields) {
        if (includeFields == null || includeFields.length < 1)
            return "select " + aliasName + " ";

        StringBuilder sb = new StringBuilder("select new map(");
        boolean isFirst = true;
        for (String f : includeFields) {
            if (isFirst) {
                sb.append(aliasName).append(".").append(f).append(" as ").append(f);
                isFirst = false;
            } else {
                sb.append(",").append(aliasName).append(".").append(f).append(" as ").append(f);
            }
        }
        sb.append(") ");
        return sb.toString();
    }

    public static Object ConvertEntity(Object src, Class<?> dstType) {
        try {
            Class<?> srcType = src.getClass();
            if (srcType.equals(dstType)) {
                return src;
            } else if (src instanceof Map) {
                Object dst = dstType.newInstance();
                //Map<String, Object>
                Map<?, ?> map = (Map<?, ?>) src;
                for (Object keyObj : map.keySet()) {
                	String key = (String) keyObj;
                    Object value = map.get(key);

                    if (value != null) {
                        String setterName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                        Method method = dstType.getDeclaredMethod(setterName, value.getClass());
                        method.invoke(dst, value);
                    }
                }
                return dst;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new PersistenceException("转换实体对象异常！", ex);
        }
    }

    public static Object ConvertEntity(Object src, String[] includeFields, Class<?> dstType) {
    	try {
    		Class<?> srcType = src.getClass();
    		if (srcType.equals(dstType)) {
                return src;
            } else if (src instanceof Object[]) {
    			Object dst = dstType.newInstance();
    			Object[] objArrays = (Object[]) src;
    			for (int i = 0; i < includeFields.length; ++i) {
    				String field = includeFields[i];
    				if (field.indexOf(".") != -1) {
    					String fld = field.substring(0, field.indexOf("."));
    					String getterName = "get" + fld.substring(0, 1).toUpperCase() + fld.substring(1);
    					String setterName = "set" + fld.substring(0, 1).toUpperCase() + fld.substring(1);
    					Method method = dstType.getDeclaredMethod(getterName);
    					Object obj = method.invoke(dst);
    					if (obj == null) {
    						obj = method.getReturnType().newInstance();
    						dstType.getDeclaredMethod(setterName, obj.getClass()).invoke(dst, obj);
    					}
    					
    					String f = field.substring(field.indexOf(".") + 1);
    					setterName = "set" + f.substring(0, 1).toUpperCase() + f.substring(1);
    					Class<?> resultClass = objArrays[i].getClass();
    					Class<?> paraClass = null;
    					if (resultClass == java.sql.Timestamp.class) {
    						paraClass = java.util.Date.class;
    					} else {
    						paraClass = resultClass;
    					}
    					method = obj.getClass().getDeclaredMethod(setterName, paraClass);
    					method.invoke(obj, objArrays[i]);
    				} else {
    					String setterName = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    					Class<?> resultClass = objArrays[i].getClass();
    					Class<?> paraClass = null;
    					if (resultClass == java.sql.Timestamp.class) {
    						paraClass = java.util.Date.class;
    					} else {
    						paraClass = resultClass;
    					}
    					Method method = dstType.getDeclaredMethod(setterName, paraClass);
    					method.invoke(dst, objArrays[i]);
    				}
    			}
    			return dst;
    		} else {
    			return null;
    		}
    	} catch (Exception ex) {
    		throw new PersistenceException("转换实体对象异常！", ex);
    	}
    }
    
}
