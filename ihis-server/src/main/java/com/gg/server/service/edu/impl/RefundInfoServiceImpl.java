package com.gg.server.service.edu.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.server.entity.edu.RefundInfo;
import com.gg.server.mapper.edu.RefundInfoMapper;
import com.gg.server.service.edu.RefundInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核驳回信息表（包括人员驳回、资讯发布驳回） 服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-06-12
 */
@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo> implements RefundInfoService {

}
