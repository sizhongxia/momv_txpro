package org.tm.pro.service.impl;

import org.tm.pro.entity.HousePriceTrend;
import org.tm.pro.mapper.HousePriceTrendDao;
import org.tm.pro.service.HousePriceTrendService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房源价格趋势 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class HousePriceTrendServiceImpl extends ServiceImpl<HousePriceTrendDao, HousePriceTrend> implements HousePriceTrendService {

}
