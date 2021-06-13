package com.gg.server.service.edu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.server.entity.edu.HospitalBase;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.dto.HspWithDeptDto;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 医院基本信息表（主要有用的是前五个属性，后面是用来介绍医院的信息的，但是没有页面展示，所以其实没用） 服务类
 * </p>
 *
 * @author gg
 * @since 2021-06-11
 */
@Service
public interface HospitalBaseService extends IService<HospitalBase> {

    /**
     * 获取医院
     */
    List<HospitalBase> getHospitals();

    /**
     * 获取医院信息通过Id
     */
    HospitalBase getHospitalById(Integer id);

    /**
     * 修改医院信息
     * @param hospitalBase
     * @return
     */
    RespBean updateHospital(HospitalBase hospitalBase);

}
