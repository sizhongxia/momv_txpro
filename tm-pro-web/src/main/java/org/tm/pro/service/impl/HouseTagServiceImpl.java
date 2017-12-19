package org.tm.pro.service.impl;

import org.tm.pro.entity.HouseTag;
import org.tm.pro.mapper.HouseTagDao;
import org.tm.pro.service.HouseTagService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房源标签关联 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class HouseTagServiceImpl extends ServiceImpl<HouseTagDao, HouseTag> implements HouseTagService {

}
