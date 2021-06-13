package com.gg.server.service.edu.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.server.entity.edu.CommentInfo;
import com.gg.server.mapper.edu.CommentInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 作品评论信息表 服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-05-06
 */
@Service
public class CommentInfoServiceImpl extends ServiceImpl<CommentInfoMapper, CommentInfo> implements IService<CommentInfo> {

}
