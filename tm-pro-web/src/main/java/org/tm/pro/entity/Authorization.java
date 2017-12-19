package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 权限字
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_authorization")
public class Authorization extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 父级模块ID
     */
	private Integer pid;
    /**
     * 模块名称
     */
	@TableField("module_name")
	private String moduleName;
    /**
     * 模块说明
     */
	@TableField("module_introduce")
	private String moduleIntroduce;
    /**
     * 权限字
     */
	@TableField("authorization_code")
	private String authorizationCode;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleIntroduce() {
		return moduleIntroduce;
	}

	public void setModuleIntroduce(String moduleIntroduce) {
		this.moduleIntroduce = moduleIntroduce;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	@Override
	public String toString() {
		return "Authorization{" +
			", id=" + id +
			", pid=" + pid +
			", moduleName=" + moduleName +
			", moduleIntroduce=" + moduleIntroduce +
			", authorizationCode=" + authorizationCode +
			"}";
	}
}
