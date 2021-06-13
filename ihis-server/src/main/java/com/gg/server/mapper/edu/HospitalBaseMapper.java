package com.gg.server.mapper.edu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gg.server.entity.edu.HospitalBase;
import com.gg.server.pojo.dto.HspWithDeptDto;
import io.lettuce.core.dynamic.annotation.Key;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 医院基本信息表（主要有用的是前五个属性，后面是用来介绍医院的信息的，但是没有页面展示，所以其实没用） Mapper 接口
 * </p>
 *
 * @author gg
 * @since 2021-06-11
 */
@Component
public interface HospitalBaseMapper extends BaseMapper<HospitalBase> {

    List<HospitalBase> getHospitalsWithDept();

    HospitalBase getHospitalWithDept(@Param("id") Integer id);
}
