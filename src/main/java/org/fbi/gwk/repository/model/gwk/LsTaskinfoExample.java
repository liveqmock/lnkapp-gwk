package org.fbi.gwk.repository.model.gwk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LsTaskinfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public LsTaskinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTaskidIsNull() {
            addCriterion("TASKID is null");
            return (Criteria) this;
        }

        public Criteria andTaskidIsNotNull() {
            addCriterion("TASKID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskidEqualTo(String value) {
            addCriterion("TASKID =", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotEqualTo(String value) {
            addCriterion("TASKID <>", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThan(String value) {
            addCriterion("TASKID >", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidGreaterThanOrEqualTo(String value) {
            addCriterion("TASKID >=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThan(String value) {
            addCriterion("TASKID <", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLessThanOrEqualTo(String value) {
            addCriterion("TASKID <=", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidLike(String value) {
            addCriterion("TASKID like", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotLike(String value) {
            addCriterion("TASKID not like", value, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidIn(List<String> values) {
            addCriterion("TASKID in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotIn(List<String> values) {
            addCriterion("TASKID not in", values, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidBetween(String value1, String value2) {
            addCriterion("TASKID between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andTaskidNotBetween(String value1, String value2) {
            addCriterion("TASKID not between", value1, value2, "taskid");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNull() {
            addCriterion("TABLENAME is null");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNotNull() {
            addCriterion("TABLENAME is not null");
            return (Criteria) this;
        }

        public Criteria andTablenameEqualTo(String value) {
            addCriterion("TABLENAME =", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotEqualTo(String value) {
            addCriterion("TABLENAME <>", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThan(String value) {
            addCriterion("TABLENAME >", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLENAME >=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThan(String value) {
            addCriterion("TABLENAME <", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThanOrEqualTo(String value) {
            addCriterion("TABLENAME <=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLike(String value) {
            addCriterion("TABLENAME like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotLike(String value) {
            addCriterion("TABLENAME not like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameIn(List<String> values) {
            addCriterion("TABLENAME in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotIn(List<String> values) {
            addCriterion("TABLENAME not in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameBetween(String value1, String value2) {
            addCriterion("TABLENAME between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotBetween(String value1, String value2) {
            addCriterion("TABLENAME not between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablepkIsNull() {
            addCriterion("TABLEPK is null");
            return (Criteria) this;
        }

        public Criteria andTablepkIsNotNull() {
            addCriterion("TABLEPK is not null");
            return (Criteria) this;
        }

        public Criteria andTablepkEqualTo(String value) {
            addCriterion("TABLEPK =", value, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkNotEqualTo(String value) {
            addCriterion("TABLEPK <>", value, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkGreaterThan(String value) {
            addCriterion("TABLEPK >", value, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkGreaterThanOrEqualTo(String value) {
            addCriterion("TABLEPK >=", value, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkLessThan(String value) {
            addCriterion("TABLEPK <", value, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkLessThanOrEqualTo(String value) {
            addCriterion("TABLEPK <=", value, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkLike(String value) {
            addCriterion("TABLEPK like", value, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkNotLike(String value) {
            addCriterion("TABLEPK not like", value, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkIn(List<String> values) {
            addCriterion("TABLEPK in", values, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkNotIn(List<String> values) {
            addCriterion("TABLEPK not in", values, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkBetween(String value1, String value2) {
            addCriterion("TABLEPK between", value1, value2, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTablepkNotBetween(String value1, String value2) {
            addCriterion("TABLEPK not between", value1, value2, "tablepk");
            return (Criteria) this;
        }

        public Criteria andTasktypeIsNull() {
            addCriterion("TASKTYPE is null");
            return (Criteria) this;
        }

        public Criteria andTasktypeIsNotNull() {
            addCriterion("TASKTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTasktypeEqualTo(String value) {
            addCriterion("TASKTYPE =", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotEqualTo(String value) {
            addCriterion("TASKTYPE <>", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeGreaterThan(String value) {
            addCriterion("TASKTYPE >", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASKTYPE >=", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLessThan(String value) {
            addCriterion("TASKTYPE <", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLessThanOrEqualTo(String value) {
            addCriterion("TASKTYPE <=", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLike(String value) {
            addCriterion("TASKTYPE like", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotLike(String value) {
            addCriterion("TASKTYPE not like", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeIn(List<String> values) {
            addCriterion("TASKTYPE in", values, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotIn(List<String> values) {
            addCriterion("TASKTYPE not in", values, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeBetween(String value1, String value2) {
            addCriterion("TASKTYPE between", value1, value2, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotBetween(String value1, String value2) {
            addCriterion("TASKTYPE not between", value1, value2, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktimeIsNull() {
            addCriterion("TASKTIME is null");
            return (Criteria) this;
        }

        public Criteria andTasktimeIsNotNull() {
            addCriterion("TASKTIME is not null");
            return (Criteria) this;
        }

        public Criteria andTasktimeEqualTo(Date value) {
            addCriterion("TASKTIME =", value, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeNotEqualTo(Date value) {
            addCriterion("TASKTIME <>", value, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeGreaterThan(Date value) {
            addCriterion("TASKTIME >", value, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TASKTIME >=", value, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeLessThan(Date value) {
            addCriterion("TASKTIME <", value, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeLessThanOrEqualTo(Date value) {
            addCriterion("TASKTIME <=", value, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeIn(List<Date> values) {
            addCriterion("TASKTIME in", values, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeNotIn(List<Date> values) {
            addCriterion("TASKTIME not in", values, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeBetween(Date value1, Date value2) {
            addCriterion("TASKTIME between", value1, value2, "tasktime");
            return (Criteria) this;
        }

        public Criteria andTasktimeNotBetween(Date value1, Date value2) {
            addCriterion("TASKTIME not between", value1, value2, "tasktime");
            return (Criteria) this;
        }

        public Criteria andOperidIsNull() {
            addCriterion("OPERID is null");
            return (Criteria) this;
        }

        public Criteria andOperidIsNotNull() {
            addCriterion("OPERID is not null");
            return (Criteria) this;
        }

        public Criteria andOperidEqualTo(String value) {
            addCriterion("OPERID =", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidNotEqualTo(String value) {
            addCriterion("OPERID <>", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidGreaterThan(String value) {
            addCriterion("OPERID >", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidGreaterThanOrEqualTo(String value) {
            addCriterion("OPERID >=", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidLessThan(String value) {
            addCriterion("OPERID <", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidLessThanOrEqualTo(String value) {
            addCriterion("OPERID <=", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidLike(String value) {
            addCriterion("OPERID like", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidNotLike(String value) {
            addCriterion("OPERID not like", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidIn(List<String> values) {
            addCriterion("OPERID in", values, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidNotIn(List<String> values) {
            addCriterion("OPERID not in", values, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidBetween(String value1, String value2) {
            addCriterion("OPERID between", value1, value2, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidNotBetween(String value1, String value2) {
            addCriterion("OPERID not between", value1, value2, "operid");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("REMARKS =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("REMARKS <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("REMARKS >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("REMARKS >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("REMARKS <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("REMARKS <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("REMARKS like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("REMARKS not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("REMARKS in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("REMARKS not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated do_not_delete_during_merge Mon Mar 09 17:29:36 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}