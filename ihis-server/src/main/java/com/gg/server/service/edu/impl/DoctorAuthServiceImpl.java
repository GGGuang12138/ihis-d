package com.gg.server.service.edu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.server.entity.edu.DoctorAuth;
import com.gg.server.entity.edu.DoctorBase;
import com.gg.server.mapper.edu.DoctorAuthMapper;
import com.gg.server.mapper.edu.DoctorBaseMapper;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.enums.DoctorStatus;
import com.gg.server.service.edu.DoctorAuthService;
import com.gg.server.mapper.edu.DoctorAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 医生身份认证信息表，数据进行加密处理 服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-06-10
 */
@Service
public class DoctorAuthServiceImpl extends ServiceImpl<DoctorAuthMapper, DoctorAuth> implements DoctorAuthService {


    @Autowired
    private DoctorAuthMapper doctorAuthMapper;
    @Autowired
    private DoctorBaseMapper doctorBaseMapper;

    /**
     * 添加医生授权资料
     * @param doctorAuth
     * @return
     */
    @Override
    @Transactional
    public RespBean addDoctorAuth(DoctorAuth doctorAuth) {
        if (doctorAuth.getId() != null){
            DoctorAuth auth = doctorAuthMapper.selectById(doctorAuth.getId());
            DoctorBase doctorBase = doctorBaseMapper.selectById(auth.getDid());
            doctorBase.setAuthId(auth.getId());
            doctorBase.setUpdateTime(LocalDateTime.now());
            doctorBase.setState(DoctorStatus.WAIT_REVIEW.getValue());
            doctorBaseMapper.updateById(doctorBase);
            return RespBean.success("修改成功");
        }
        // 第一次提交
        boolean b = doctorAuthMapper.addDoctorAuth(doctorAuth, "123");
        if (b) {
            List<DoctorAuth> did = doctorAuthMapper.selectList(new QueryWrapper<DoctorAuth>()
                    .eq("did", doctorAuth.getDid()));
            DoctorAuth auth = did.get(0);
            DoctorBase doctorBase = doctorBaseMapper.selectById(auth.getDid());
            doctorBase.setAuthId(auth.getId());
            doctorBase.setUpdateTime(LocalDateTime.now());
            doctorBase.setState(DoctorStatus.WAIT_REVIEW.getValue());
            doctorBaseMapper.updateById(doctorBase);
        }

        return RespBean.success(b);
    }

    @Override
    public DoctorAuth getAuthInfo(Integer doctorId) {
        DoctorAuth doctorAuth = doctorAuthMapper.selectDoctorAuthById(doctorId,"123");
        return doctorAuth;
    }
}
