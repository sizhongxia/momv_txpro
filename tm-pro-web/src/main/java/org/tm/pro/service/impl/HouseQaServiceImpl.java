package org.tm.pro.service.impl;

import org.tm.pro.entity.HouseQa;
import org.tm.pro.mapper.HouseQaDao;
import org.tm.pro.service.HouseQaService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房源问答 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class HouseQaServiceImpl extends ServiceImpl<HouseQaDao, HouseQa> implements HouseQaService {

}
