package org.tm.pro.service.impl;

import org.tm.pro.entity.OnlineSubscribe;
import org.tm.pro.mapper.OnlineSubscribeDao;
import org.tm.pro.service.OnlineSubscribeService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 上线新项目提醒 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class OnlineSubscribeServiceImpl extends ServiceImpl<OnlineSubscribeDao, OnlineSubscribe> implements OnlineSubscribeService {

}
