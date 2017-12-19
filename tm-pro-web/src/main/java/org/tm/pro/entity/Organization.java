package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_organization")
public class Organization extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 组织ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("organization_name")
	private String organizationName;
    /**
     * 组织描述
     */
	@TableField("organization_desc")
	private String organizationDesc;
    /**
     * 排序编号
     */
	@TableField("sort_number")
	private Integer sortNumber;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationDesc() {
		return organizationDesc;
	}

	public void setOrganizationDesc(String organizationDesc) {
		this.organizationDesc = organizationDesc;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	@Override
	public String toString() {
		return "Organization{" +
			", id=" + id +
			", organizationName=" + organizationName +
			", organizationDesc=" + organizationDesc +
			", sortNumber=" + sortNumber +
			"}";
	}
}
