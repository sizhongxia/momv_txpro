package org.tm.pro.web.poi.entity;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Excel(name = "用户ID", height = 20, width = 30, isImportField = "true_st")
	private String id;
	@Excel(name = "用户头像", type = 2, height = 20, width = 13, imageType = 1)
	private String headPic;
	@Excel(name = "用户姓名", height = 20, width = 30, isImportField = "true_st")
	private String name;
	@Excel(name = "用户性别", replace = { "男_1", "女_2" }, suffix = "生", isImportField = "true_st")
	private int sex;
	@Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
	private Date birthday;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

}
