package org.tm.pro.service.impl;

import org.tm.pro.entity.House;
import org.tm.pro.mapper.HouseDao;
import org.tm.pro.service.HouseService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房源基础信息表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class HouseServiceImpl extends ServiceImpl<HouseDao, House> implements HouseService {

}
