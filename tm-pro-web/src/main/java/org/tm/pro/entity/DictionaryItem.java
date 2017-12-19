package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 字典项
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_dictionary_item")
public class DictionaryItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典项ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 所属字典
     */
	@TableField("dict_id")
	private Integer dictId;
    /**
     * 字典项名称
     */
	private String name;
    /**
     * 字典项值
     */
	private String value;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DictionaryItem{" +
			", id=" + id +
			", dictId=" + dictId +
			", name=" + name +
			", value=" + value +
			"}";
	}
}
