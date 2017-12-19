package org.tm.pro.service.impl;

import org.tm.pro.entity.HouseLayout;
import org.tm.pro.mapper.HouseLayoutDao;
import org.tm.pro.service.HouseLayoutService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房源户型信息 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class HouseLayoutServiceImpl extends ServiceImpl<HouseLayoutDao, HouseLayout> implements HouseLayoutService {

}
