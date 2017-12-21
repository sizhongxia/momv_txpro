package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.tm.pro.entity.BaseEntity;

/**
 * <p>
 * 上传文件
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-21
 */
@TableName("tb_picture")
public class Picture extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 文件MD5摘要
     */
	private String md5;
    /**
     * 文件名称
     */
	private String name;
    /**
     * 文件大小
     */
	private Long size;
    /**
     * 文件类别
     */
	private String type;
    /**
     * 文件访问链接
     */
	private String url;
    /**
     * 上传时间
     */
	@TableField("upload_time")
	private Date uploadTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "Picture{" +
			", id=" + id +
			", md5=" + md5 +
			", name=" + name +
			", size=" + size +
			", type=" + type +
			", url=" + url +
			", uploadTime=" + uploadTime +
			"}";
	}
}
