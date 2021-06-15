package com.gg.server.service.edu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.server.entity.Doctor;
import com.gg.server.entity.edu.DoctorAuth;
import com.gg.server.entity.edu.DoctorBase;
import com.gg.server.entity.edu.RefundInfo;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.RespPageBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gg
 * @since 2021-06-12
 */
@Service
public interface DoctorBaseService extends IService<DoctorBase> {

    /**
     * 获取未审核人员
     * @return
     */
    List<DoctorBase> getUncheckPerson();


    /**
     * 获取基本信息
     * @return
     */
    DoctorBase getDoctorBase();


    /**
     * 保存基本信息
     * @param doctorBase
     * @return
     */
    RespBean saveDoctor(Doctor doctor);

    /**
     * 保存医生基本信息
     * @param doctorBase
     * @return
     */
    RespBean saveDoctorBase(DoctorBase doctorBase, Integer time);

    /**
     * 通过ID获取医生信息
     * @param doctorId
     * @return
     */
    DoctorBase getDoctorBaseById(Integer doctorId);

    /**
     * 驳回医生认证
     * @param refundInfo
     * @return
     */
    RespBean refundDoctorAuth(RefundInfo refundInfo);

    /**
     * 通过医生认证
     * @param doctorId
     * @return
     */
    RespBean passDoctorAuth(Integer doctorId);

    /**
     * 获取所有个人信息
     * @param currentPage
     * @param size
     * @param doctorBase
     * @return
     */
    RespPageBean getAllPerson(Integer currentPage, Integer size, DoctorBase doctorBase);
}
