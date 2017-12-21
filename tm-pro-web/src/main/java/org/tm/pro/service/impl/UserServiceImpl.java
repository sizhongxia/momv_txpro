package org.tm.pro.service.impl;

import org.springframework.stereotype.Service;
import org.tm.pro.entity.User;
import org.tm.pro.mapper.UserDao;
import org.tm.pro.service.UserService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
