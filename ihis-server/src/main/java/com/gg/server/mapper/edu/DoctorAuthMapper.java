package com.gg.server.mapper.edu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gg.server.entity.edu.DoctorAuth;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 医生身份认证信息表，数据进行加密处理 Mapper 接口
 * </p>
 *
 * @author gg
 * @since 2021-06-10
 */
@Component
public interface DoctorAuthMapper extends BaseMapper<DoctorAuth> {

    /**
     * 添加医生身份认证信息
     * @param doctorAuth
     * @return
     */
    boolean addDoctorAuth(@Param("doctorAuth") DoctorAuth doctorAuth,@Param("AES_KEY") String key);

    /**
     * 根据Id查找医生信息
     * @return
     */
    DoctorAuth selectDoctorAuthById(@Param("id") Integer id,@Param("AES_KEY") String key);
}
