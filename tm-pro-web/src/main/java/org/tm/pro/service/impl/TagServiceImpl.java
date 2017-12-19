package org.tm.pro.service.impl;

import org.tm.pro.entity.Tag;
import org.tm.pro.mapper.TagDao;
import org.tm.pro.service.TagService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签库 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements TagService {

}
