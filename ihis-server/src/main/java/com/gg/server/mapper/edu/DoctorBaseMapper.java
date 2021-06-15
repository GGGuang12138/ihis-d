package com.gg.server.mapper.edu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gg.server.entity.edu.ArticleContent;
import com.gg.server.entity.edu.DoctorBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gg
 * @since 2021-06-12
 */
@Component
public interface DoctorBaseMapper extends BaseMapper<DoctorBase> {

    /**
     * 查询所有人员
     * @param page
     * @param doctorBase
     * @return
     */
    IPage<DoctorBase> selectAllPerson(Page<DoctorBase> page,
                                      @Param("doctorBase") DoctorBase doctorBase);

}
