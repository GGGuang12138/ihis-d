package com.gg.server.service.edu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.server.entity.Doctor;
import com.gg.server.entity.edu.DoctorAuth;
import com.gg.server.pojo.RespBean;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生身份认证信息表，数据进行加密处理 服务类
 * </p>
 *
 * @author gg
 * @since 2021-06-10
 */
@Service
public interface DoctorAuthService extends IService<DoctorAuth> {

    /**
     * 添加医生授权资料
     * @param doctorAuth
     * @return
     */
    RespBean addDoctorAuth(DoctorAuth doctorAuth);

    /**
     * 获取授权新
     * @param doctorId
     * @return
     */
    DoctorAuth getAuthInfo(Integer doctorId);

}
