package org.fbi.gwk.repository.model.gwk;

import java.util.ArrayList;
import java.util.List;

public class LsCardstatusExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public LsCardstatusExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
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
     * This method corresponds to the database table GWK.LS_CARDSTATUS
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
     * This method corresponds to the database table GWK.LS_CARDSTATUS
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_CARDSTATUS
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
     * This class corresponds to the database table GWK.LS_CARDSTATUS
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

        public Criteria andAccountIsNull() {
            addCriterion("ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("ACCOUNT =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("ACCOUNT <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("ACCOUNT >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("ACCOUNT <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("ACCOUNT like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("ACCOUNT not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("ACCOUNT in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("ACCOUNT not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("ACCOUNT between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andCardnameIsNull() {
            addCriterion("CARDNAME is null");
            return (Criteria) this;
        }

        public Criteria andCardnameIsNotNull() {
            addCriterion("CARDNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCardnameEqualTo(String value) {
            addCriterion("CARDNAME =", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotEqualTo(String value) {
            addCriterion("CARDNAME <>", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameGreaterThan(String value) {
            addCriterion("CARDNAME >", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameGreaterThanOrEqualTo(String value) {
            addCriterion("CARDNAME >=", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLessThan(String value) {
            addCriterion("CARDNAME <", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLessThanOrEqualTo(String value) {
            addCriterion("CARDNAME <=", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLike(String value) {
            addCriterion("CARDNAME like", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotLike(String value) {
            addCriterion("CARDNAME not like", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameIn(List<String> values) {
            addCriterion("CARDNAME in", values, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotIn(List<String> values) {
            addCriterion("CARDNAME not in", values, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameBetween(String value1, String value2) {
            addCriterion("CARDNAME between", value1, value2, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotBetween(String value1, String value2) {
            addCriterion("CARDNAME not between", value1, value2, "cardname");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBdgagencyIsNull() {
            addCriterion("BDGAGENCY is null");
            return (Criteria) this;
        }

        public Criteria andBdgagencyIsNotNull() {
            addCriterion("BDGAGENCY is not null");
            return (Criteria) this;
        }

        public Criteria andBdgagencyEqualTo(String value) {
            addCriterion("BDGAGENCY =", value, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyNotEqualTo(String value) {
            addCriterion("BDGAGENCY <>", value, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyGreaterThan(String value) {
            addCriterion("BDGAGENCY >", value, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyGreaterThanOrEqualTo(String value) {
            addCriterion("BDGAGENCY >=", value, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyLessThan(String value) {
            addCriterion("BDGAGENCY <", value, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyLessThanOrEqualTo(String value) {
            addCriterion("BDGAGENCY <=", value, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyLike(String value) {
            addCriterion("BDGAGENCY like", value, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyNotLike(String value) {
            addCriterion("BDGAGENCY not like", value, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyIn(List<String> values) {
            addCriterion("BDGAGENCY in", values, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyNotIn(List<String> values) {
            addCriterion("BDGAGENCY not in", values, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyBetween(String value1, String value2) {
            addCriterion("BDGAGENCY between", value1, value2, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andBdgagencyNotBetween(String value1, String value2) {
            addCriterion("BDGAGENCY not between", value1, value2, "bdgagency");
            return (Criteria) this;
        }

        public Criteria andGuidIsNull() {
            addCriterion("GUID is null");
            return (Criteria) this;
        }

        public Criteria andGuidIsNotNull() {
            addCriterion("GUID is not null");
            return (Criteria) this;
        }

        public Criteria andGuidEqualTo(String value) {
            addCriterion("GUID =", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotEqualTo(String value) {
            addCriterion("GUID <>", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThan(String value) {
            addCriterion("GUID >", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThanOrEqualTo(String value) {
            addCriterion("GUID >=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThan(String value) {
            addCriterion("GUID <", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThanOrEqualTo(String value) {
            addCriterion("GUID <=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLike(String value) {
            addCriterion("GUID like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotLike(String value) {
            addCriterion("GUID not like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidIn(List<String> values) {
            addCriterion("GUID in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotIn(List<String> values) {
            addCriterion("GUID not in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidBetween(String value1, String value2) {
            addCriterion("GUID between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotBetween(String value1, String value2) {
            addCriterion("GUID not between", value1, value2, "guid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table GWK.LS_CARDSTATUS
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
     * This class corresponds to the database table GWK.LS_CARDSTATUS
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