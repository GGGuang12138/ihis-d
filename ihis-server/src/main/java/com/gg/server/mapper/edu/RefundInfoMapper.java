package com.gg.server.mapper.edu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gg.server.entity.edu.RefundInfo;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 审核驳回信息表（包括人员驳回、资讯发布驳回） Mapper 接口
 * </p>
 *
 * @author gg
 * @since 2021-06-12
 */
@Component
public interface RefundInfoMapper extends BaseMapper<RefundInfo> {

}
