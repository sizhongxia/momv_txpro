package org.tm.pro.service.impl;

import org.tm.pro.entity.HouseConsult;
import org.tm.pro.mapper.HouseConsultDao;
import org.tm.pro.service.HouseConsultService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房源预约咨询信息表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class HouseConsultServiceImpl extends ServiceImpl<HouseConsultDao, HouseConsult> implements HouseConsultService {

}
