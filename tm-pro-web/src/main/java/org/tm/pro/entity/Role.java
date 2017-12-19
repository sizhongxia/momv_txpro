package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 组织机构ID
     */
	@TableField("organization_id")
	private Integer organizationId;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 角色编码
     */
	@TableField("role_code")
	private String roleCode;
    /**
     * 角色说明
     */
	@TableField("role_explain")
	private String roleExplain;
    /**
     * 使用状态
     */
	@TableField("using_state")
	private String usingState;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleExplain() {
		return roleExplain;
	}

	public void setRoleExplain(String roleExplain) {
		this.roleExplain = roleExplain;
	}

	public String getUsingState() {
		return usingState;
	}

	public void setUsingState(String usingState) {
		this.usingState = usingState;
	}

	@Override
	public String toString() {
		return "Role{" +
			", id=" + id +
			", organizationId=" + organizationId +
			", roleName=" + roleName +
			", roleCode=" + roleCode +
			", roleExplain=" + roleExplain +
			", usingState=" + usingState +
			"}";
	}
}
