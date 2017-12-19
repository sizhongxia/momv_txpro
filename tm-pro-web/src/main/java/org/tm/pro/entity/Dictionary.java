package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_dictionary")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 系统字典主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 字典名称
     */
	private String name;
    /**
     * 字典访问编码
     */
	@TableField("visit_code")
	private String visitCode;
    /**
     * 字典说明
     */
	private String remarks;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisitCode() {
		return visitCode;
	}

	public void setVisitCode(String visitCode) {
		this.visitCode = visitCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Dictionary{" +
			", id=" + id +
			", name=" + name +
			", visitCode=" + visitCode +
			", remarks=" + remarks +
			"}";
	}
}
