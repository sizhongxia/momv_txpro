package org.tm.pro.entity;

import java.util.ArrayList;
import java.util.List;

public class RoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleCodeIsNull() {
            addCriterion("role_code is null");
            return (Criteria) this;
        }

        public Criteria andRoleCodeIsNotNull() {
            addCriterion("role_code is not null");
            return (Criteria) this;
        }

        public Criteria andRoleCodeEqualTo(String value) {
            addCriterion("role_code =", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeNotEqualTo(String value) {
            addCriterion("role_code <>", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeGreaterThan(String value) {
            addCriterion("role_code >", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("role_code >=", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeLessThan(String value) {
            addCriterion("role_code <", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeLessThanOrEqualTo(String value) {
            addCriterion("role_code <=", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeLike(String value) {
            addCriterion("role_code like", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeNotLike(String value) {
            addCriterion("role_code not like", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeIn(List<String> values) {
            addCriterion("role_code in", values, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeNotIn(List<String> values) {
            addCriterion("role_code not in", values, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeBetween(String value1, String value2) {
            addCriterion("role_code between", value1, value2, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeNotBetween(String value1, String value2) {
            addCriterion("role_code not between", value1, value2, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleExplainIsNull() {
            addCriterion("role_explain is null");
            return (Criteria) this;
        }

        public Criteria andRoleExplainIsNotNull() {
            addCriterion("role_explain is not null");
            return (Criteria) this;
        }

        public Criteria andRoleExplainEqualTo(String value) {
            addCriterion("role_explain =", value, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainNotEqualTo(String value) {
            addCriterion("role_explain <>", value, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainGreaterThan(String value) {
            addCriterion("role_explain >", value, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainGreaterThanOrEqualTo(String value) {
            addCriterion("role_explain >=", value, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainLessThan(String value) {
            addCriterion("role_explain <", value, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainLessThanOrEqualTo(String value) {
            addCriterion("role_explain <=", value, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainLike(String value) {
            addCriterion("role_explain like", value, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainNotLike(String value) {
            addCriterion("role_explain not like", value, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainIn(List<String> values) {
            addCriterion("role_explain in", values, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainNotIn(List<String> values) {
            addCriterion("role_explain not in", values, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainBetween(String value1, String value2) {
            addCriterion("role_explain between", value1, value2, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andRoleExplainNotBetween(String value1, String value2) {
            addCriterion("role_explain not between", value1, value2, "roleExplain");
            return (Criteria) this;
        }

        public Criteria andUsingStateIsNull() {
            addCriterion("using_state is null");
            return (Criteria) this;
        }

        public Criteria andUsingStateIsNotNull() {
            addCriterion("using_state is not null");
            return (Criteria) this;
        }

        public Criteria andUsingStateEqualTo(String value) {
            addCriterion("using_state =", value, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateNotEqualTo(String value) {
            addCriterion("using_state <>", value, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateGreaterThan(String value) {
            addCriterion("using_state >", value, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateGreaterThanOrEqualTo(String value) {
            addCriterion("using_state >=", value, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateLessThan(String value) {
            addCriterion("using_state <", value, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateLessThanOrEqualTo(String value) {
            addCriterion("using_state <=", value, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateLike(String value) {
            addCriterion("using_state like", value, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateNotLike(String value) {
            addCriterion("using_state not like", value, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateIn(List<String> values) {
            addCriterion("using_state in", values, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateNotIn(List<String> values) {
            addCriterion("using_state not in", values, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateBetween(String value1, String value2) {
            addCriterion("using_state between", value1, value2, "usingState");
            return (Criteria) this;
        }

        public Criteria andUsingStateNotBetween(String value1, String value2) {
            addCriterion("using_state not between", value1, value2, "usingState");
            return (Criteria) this;
        }

        public Criteria andRoleNameLikeInsensitive(String value) {
            addCriterion("upper(role_name) like", value.toUpperCase(), "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleCodeLikeInsensitive(String value) {
            addCriterion("upper(role_code) like", value.toUpperCase(), "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleExplainLikeInsensitive(String value) {
            addCriterion("upper(role_explain) like", value.toUpperCase(), "roleExplain");
            return (Criteria) this;
        }

        public Criteria andUsingStateLikeInsensitive(String value) {
            addCriterion("upper(using_state) like", value.toUpperCase(), "usingState");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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