package org.tm.pro.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 热门房源信息
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@TableName("tb_house_hot")
public class HouseHot extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 房源ID
     */
	@TableField("house_id")
	private Integer houseId;
    /**
     * 热度（排序）
     */
	@TableField("hot_degree")
	private Integer hotDegree;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getHotDegree() {
		return hotDegree;
	}

	public void setHotDegree(Integer hotDegree) {
		this.hotDegree = hotDegree;
	}

	@Override
	public String toString() {
		return "HouseHot{" +
			", id=" + id +
			", houseId=" + houseId +
			", hotDegree=" + hotDegree +
			"}";
	}
}
