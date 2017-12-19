package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 基础城市数据
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_base_city")
public class BaseCity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 城市编码
     */
	private String code;
    /**
     * 父级城市编码
     */
	private String pcode;
    /**
     * 城市名称
     */
	private String name;
    /**
     * 城市名称拼音
     */
	private String pinyin;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "BaseCity{" +
			", id=" + id +
			", code=" + code +
			", pcode=" + pcode +
			", name=" + name +
			", pinyin=" + pinyin +
			", createTime=" + createTime +
			"}";
	}
}
