package org.tm.pro.entity;

import java.util.ArrayList;
import java.util.List;

public class SystemInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemInfoExample() {
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

        public Criteria andSystemTitleIsNull() {
            addCriterion("system_title is null");
            return (Criteria) this;
        }

        public Criteria andSystemTitleIsNotNull() {
            addCriterion("system_title is not null");
            return (Criteria) this;
        }

        public Criteria andSystemTitleEqualTo(String value) {
            addCriterion("system_title =", value, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleNotEqualTo(String value) {
            addCriterion("system_title <>", value, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleGreaterThan(String value) {
            addCriterion("system_title >", value, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleGreaterThanOrEqualTo(String value) {
            addCriterion("system_title >=", value, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleLessThan(String value) {
            addCriterion("system_title <", value, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleLessThanOrEqualTo(String value) {
            addCriterion("system_title <=", value, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleLike(String value) {
            addCriterion("system_title like", value, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleNotLike(String value) {
            addCriterion("system_title not like", value, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleIn(List<String> values) {
            addCriterion("system_title in", values, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleNotIn(List<String> values) {
            addCriterion("system_title not in", values, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleBetween(String value1, String value2) {
            addCriterion("system_title between", value1, value2, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemTitleNotBetween(String value1, String value2) {
            addCriterion("system_title not between", value1, value2, "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptIsNull() {
            addCriterion("system_descript is null");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptIsNotNull() {
            addCriterion("system_descript is not null");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptEqualTo(String value) {
            addCriterion("system_descript =", value, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptNotEqualTo(String value) {
            addCriterion("system_descript <>", value, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptGreaterThan(String value) {
            addCriterion("system_descript >", value, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptGreaterThanOrEqualTo(String value) {
            addCriterion("system_descript >=", value, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptLessThan(String value) {
            addCriterion("system_descript <", value, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptLessThanOrEqualTo(String value) {
            addCriterion("system_descript <=", value, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptLike(String value) {
            addCriterion("system_descript like", value, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptNotLike(String value) {
            addCriterion("system_descript not like", value, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptIn(List<String> values) {
            addCriterion("system_descript in", values, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptNotIn(List<String> values) {
            addCriterion("system_descript not in", values, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptBetween(String value1, String value2) {
            addCriterion("system_descript between", value1, value2, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptNotBetween(String value1, String value2) {
            addCriterion("system_descript not between", value1, value2, "systemDescript");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitIsNull() {
            addCriterion("login_fail_limit is null");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitIsNotNull() {
            addCriterion("login_fail_limit is not null");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitEqualTo(String value) {
            addCriterion("login_fail_limit =", value, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitNotEqualTo(String value) {
            addCriterion("login_fail_limit <>", value, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitGreaterThan(String value) {
            addCriterion("login_fail_limit >", value, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitGreaterThanOrEqualTo(String value) {
            addCriterion("login_fail_limit >=", value, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitLessThan(String value) {
            addCriterion("login_fail_limit <", value, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitLessThanOrEqualTo(String value) {
            addCriterion("login_fail_limit <=", value, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitLike(String value) {
            addCriterion("login_fail_limit like", value, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitNotLike(String value) {
            addCriterion("login_fail_limit not like", value, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitIn(List<String> values) {
            addCriterion("login_fail_limit in", values, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitNotIn(List<String> values) {
            addCriterion("login_fail_limit not in", values, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitBetween(String value1, String value2) {
            addCriterion("login_fail_limit between", value1, value2, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitNotBetween(String value1, String value2) {
            addCriterion("login_fail_limit not between", value1, value2, "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountIsNull() {
            addCriterion("login_fail_count is null");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountIsNotNull() {
            addCriterion("login_fail_count is not null");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountEqualTo(Integer value) {
            addCriterion("login_fail_count =", value, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountNotEqualTo(Integer value) {
            addCriterion("login_fail_count <>", value, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountGreaterThan(Integer value) {
            addCriterion("login_fail_count >", value, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_fail_count >=", value, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountLessThan(Integer value) {
            addCriterion("login_fail_count <", value, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountLessThanOrEqualTo(Integer value) {
            addCriterion("login_fail_count <=", value, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountIn(List<Integer> values) {
            addCriterion("login_fail_count in", values, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountNotIn(List<Integer> values) {
            addCriterion("login_fail_count not in", values, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountBetween(Integer value1, Integer value2) {
            addCriterion("login_fail_count between", value1, value2, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailCountNotBetween(Integer value1, Integer value2) {
            addCriterion("login_fail_count not between", value1, value2, "loginFailCount");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredIsNull() {
            addCriterion("login_fail_expired is null");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredIsNotNull() {
            addCriterion("login_fail_expired is not null");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredEqualTo(Integer value) {
            addCriterion("login_fail_expired =", value, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredNotEqualTo(Integer value) {
            addCriterion("login_fail_expired <>", value, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredGreaterThan(Integer value) {
            addCriterion("login_fail_expired >", value, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_fail_expired >=", value, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredLessThan(Integer value) {
            addCriterion("login_fail_expired <", value, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredLessThanOrEqualTo(Integer value) {
            addCriterion("login_fail_expired <=", value, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredIn(List<Integer> values) {
            addCriterion("login_fail_expired in", values, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredNotIn(List<Integer> values) {
            addCriterion("login_fail_expired not in", values, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredBetween(Integer value1, Integer value2) {
            addCriterion("login_fail_expired between", value1, value2, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andLoginFailExpiredNotBetween(Integer value1, Integer value2) {
            addCriterion("login_fail_expired not between", value1, value2, "loginFailExpired");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeIsNull() {
            addCriterion("only_chrome is null");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeIsNotNull() {
            addCriterion("only_chrome is not null");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeEqualTo(String value) {
            addCriterion("only_chrome =", value, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeNotEqualTo(String value) {
            addCriterion("only_chrome <>", value, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeGreaterThan(String value) {
            addCriterion("only_chrome >", value, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeGreaterThanOrEqualTo(String value) {
            addCriterion("only_chrome >=", value, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeLessThan(String value) {
            addCriterion("only_chrome <", value, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeLessThanOrEqualTo(String value) {
            addCriterion("only_chrome <=", value, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeLike(String value) {
            addCriterion("only_chrome like", value, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeNotLike(String value) {
            addCriterion("only_chrome not like", value, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeIn(List<String> values) {
            addCriterion("only_chrome in", values, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeNotIn(List<String> values) {
            addCriterion("only_chrome not in", values, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeBetween(String value1, String value2) {
            addCriterion("only_chrome between", value1, value2, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeNotBetween(String value1, String value2) {
            addCriterion("only_chrome not between", value1, value2, "onlyChrome");
            return (Criteria) this;
        }

        public Criteria andSystemTitleLikeInsensitive(String value) {
            addCriterion("upper(system_title) like", value.toUpperCase(), "systemTitle");
            return (Criteria) this;
        }

        public Criteria andSystemDescriptLikeInsensitive(String value) {
            addCriterion("upper(system_descript) like", value.toUpperCase(), "systemDescript");
            return (Criteria) this;
        }

        public Criteria andLoginFailLimitLikeInsensitive(String value) {
            addCriterion("upper(login_fail_limit) like", value.toUpperCase(), "loginFailLimit");
            return (Criteria) this;
        }

        public Criteria andOnlyChromeLikeInsensitive(String value) {
            addCriterion("upper(only_chrome) like", value.toUpperCase(), "onlyChrome");
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