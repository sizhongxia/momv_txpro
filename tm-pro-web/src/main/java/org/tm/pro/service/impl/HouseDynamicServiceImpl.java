package org.tm.pro.service.impl;

import org.tm.pro.entity.HouseDynamic;
import org.tm.pro.mapper.HouseDynamicDao;
import org.tm.pro.service.HouseDynamicService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房源动态 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class HouseDynamicServiceImpl extends ServiceImpl<HouseDynamicDao, HouseDynamic> implements HouseDynamicService {

}
