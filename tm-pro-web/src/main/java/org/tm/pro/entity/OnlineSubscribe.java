package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 上线新项目提醒
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_online_subscribe")
public class OnlineSubscribe extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户名称
     */
	@TableField("user_name")
	private String userName;
    /**
     * 订阅邮件
     */
	private String email;
    /**
     * 会话Token，取消订阅
     */
	private String token;
    /**
     * 订阅时间
     */
	@TableField("subscribe_time")
	private Date subscribeTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	@Override
	public String toString() {
		return "OnlineSubscribe{" +
			", id=" + id +
			", userName=" + userName +
			", email=" + email +
			", token=" + token +
			", subscribeTime=" + subscribeTime +
			"}";
	}
}
