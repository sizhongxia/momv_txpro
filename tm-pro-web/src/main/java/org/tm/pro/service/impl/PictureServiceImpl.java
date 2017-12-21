package org.tm.pro.service.impl;

import org.tm.pro.entity.Picture;
import org.tm.pro.mapper.PictureDao;
import org.tm.pro.service.PictureService;
import org.tm.pro.web.anno.DataSource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 上传文件 服务实现类
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-21
 */
@Service
@DataSource("master")
public class PictureServiceImpl extends ServiceImpl<PictureDao, Picture> implements PictureService {
}