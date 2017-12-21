package org.tm.pro.service.impl;

import org.springframework.stereotype.Service;
import org.tm.pro.entity.Dictionary;
import org.tm.pro.mapper.DictionaryDao;
import org.tm.pro.service.DictionaryService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-18
 */
@Service
@DataSource("master")
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements DictionaryService {

}
