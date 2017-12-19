package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 标签库
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_tag")
public class Tag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 房源标签ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	private String pinyin;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Tag{" +
			", id=" + id +
			", name=" + name +
			", pinyin=" + pinyin +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
