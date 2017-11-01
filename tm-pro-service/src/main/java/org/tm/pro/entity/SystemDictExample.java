package org.tm.pro.entity;

import java.util.ArrayList;
import java.util.List;

public class SystemDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemDictExample() {
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

        public Criteria andDictSortIsNull() {
            addCriterion("dict_sort is null");
            return (Criteria) this;
        }

        public Criteria andDictSortIsNotNull() {
            addCriterion("dict_sort is not null");
            return (Criteria) this;
        }

        public Criteria andDictSortEqualTo(Integer value) {
            addCriterion("dict_sort =", value, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortNotEqualTo(Integer value) {
            addCriterion("dict_sort <>", value, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortGreaterThan(Integer value) {
            addCriterion("dict_sort >", value, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("dict_sort >=", value, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortLessThan(Integer value) {
            addCriterion("dict_sort <", value, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortLessThanOrEqualTo(Integer value) {
            addCriterion("dict_sort <=", value, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortIn(List<Integer> values) {
            addCriterion("dict_sort in", values, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortNotIn(List<Integer> values) {
            addCriterion("dict_sort not in", values, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortBetween(Integer value1, Integer value2) {
            addCriterion("dict_sort between", value1, value2, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictSortNotBetween(Integer value1, Integer value2) {
            addCriterion("dict_sort not between", value1, value2, "dictSort");
            return (Criteria) this;
        }

        public Criteria andDictCodeIsNull() {
            addCriterion("dict_code is null");
            return (Criteria) this;
        }

        public Criteria andDictCodeIsNotNull() {
            addCriterion("dict_code is not null");
            return (Criteria) this;
        }

        public Criteria andDictCodeEqualTo(String value) {
            addCriterion("dict_code =", value, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeNotEqualTo(String value) {
            addCriterion("dict_code <>", value, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeGreaterThan(String value) {
            addCriterion("dict_code >", value, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeGreaterThanOrEqualTo(String value) {
            addCriterion("dict_code >=", value, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeLessThan(String value) {
            addCriterion("dict_code <", value, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeLessThanOrEqualTo(String value) {
            addCriterion("dict_code <=", value, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeLike(String value) {
            addCriterion("dict_code like", value, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeNotLike(String value) {
            addCriterion("dict_code not like", value, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeIn(List<String> values) {
            addCriterion("dict_code in", values, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeNotIn(List<String> values) {
            addCriterion("dict_code not in", values, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeBetween(String value1, String value2) {
            addCriterion("dict_code between", value1, value2, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictCodeNotBetween(String value1, String value2) {
            addCriterion("dict_code not between", value1, value2, "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictExplainIsNull() {
            addCriterion("dict_explain is null");
            return (Criteria) this;
        }

        public Criteria andDictExplainIsNotNull() {
            addCriterion("dict_explain is not null");
            return (Criteria) this;
        }

        public Criteria andDictExplainEqualTo(String value) {
            addCriterion("dict_explain =", value, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainNotEqualTo(String value) {
            addCriterion("dict_explain <>", value, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainGreaterThan(String value) {
            addCriterion("dict_explain >", value, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainGreaterThanOrEqualTo(String value) {
            addCriterion("dict_explain >=", value, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainLessThan(String value) {
            addCriterion("dict_explain <", value, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainLessThanOrEqualTo(String value) {
            addCriterion("dict_explain <=", value, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainLike(String value) {
            addCriterion("dict_explain like", value, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainNotLike(String value) {
            addCriterion("dict_explain not like", value, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainIn(List<String> values) {
            addCriterion("dict_explain in", values, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainNotIn(List<String> values) {
            addCriterion("dict_explain not in", values, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainBetween(String value1, String value2) {
            addCriterion("dict_explain between", value1, value2, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andDictExplainNotBetween(String value1, String value2) {
            addCriterion("dict_explain not between", value1, value2, "dictExplain");
            return (Criteria) this;
        }

        public Criteria andItemLengthIsNull() {
            addCriterion("item_length is null");
            return (Criteria) this;
        }

        public Criteria andItemLengthIsNotNull() {
            addCriterion("item_length is not null");
            return (Criteria) this;
        }

        public Criteria andItemLengthEqualTo(Integer value) {
            addCriterion("item_length =", value, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthNotEqualTo(Integer value) {
            addCriterion("item_length <>", value, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthGreaterThan(Integer value) {
            addCriterion("item_length >", value, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_length >=", value, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthLessThan(Integer value) {
            addCriterion("item_length <", value, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthLessThanOrEqualTo(Integer value) {
            addCriterion("item_length <=", value, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthIn(List<Integer> values) {
            addCriterion("item_length in", values, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthNotIn(List<Integer> values) {
            addCriterion("item_length not in", values, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthBetween(Integer value1, Integer value2) {
            addCriterion("item_length between", value1, value2, "itemLength");
            return (Criteria) this;
        }

        public Criteria andItemLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("item_length not between", value1, value2, "itemLength");
            return (Criteria) this;
        }

        public Criteria andDictCodeLikeInsensitive(String value) {
            addCriterion("upper(dict_code) like", value.toUpperCase(), "dictCode");
            return (Criteria) this;
        }

        public Criteria andDictExplainLikeInsensitive(String value) {
            addCriterion("upper(dict_explain) like", value.toUpperCase(), "dictExplain");
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