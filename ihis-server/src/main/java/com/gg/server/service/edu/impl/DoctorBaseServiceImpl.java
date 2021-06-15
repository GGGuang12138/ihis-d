package com.gg.server.service.edu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.server.config.Exception.ErrorCodeException;
import com.gg.server.entity.Doctor;
import com.gg.server.entity.DoctorRole;
import com.gg.server.entity.Role;
import com.gg.server.entity.edu.ArticleContent;
import com.gg.server.entity.edu.DoctorAuth;
import com.gg.server.entity.edu.DoctorBase;
import com.gg.server.entity.edu.RefundInfo;
import com.gg.server.mapper.DoctorMapper;
import com.gg.server.mapper.DoctorRoleMapper;
import com.gg.server.mapper.edu.DoctorAuthMapper;
import com.gg.server.mapper.edu.DoctorBaseMapper;
import com.gg.server.mapper.edu.RefundInfoMapper;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.RespPageBean;
import com.gg.server.pojo.enums.DoctorStatus;
import com.gg.server.service.edu.DoctorBaseService;
import com.gg.server.utils.DoctorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-06-12
 */
@Service
public class DoctorBaseServiceImpl extends ServiceImpl<DoctorBaseMapper, DoctorBase> implements DoctorBaseService {

    @Autowired
    private DoctorBaseMapper doctorBaseMapper;
    @Autowired
    private DoctorAuthMapper doctorAuthMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private RefundInfoMapper refundInfoMapper;
    @Autowired
    private DoctorRoleMapper doctorRoleMapper;

    @Override
    public List<DoctorBase> getUncheckPerson() {
        List<DoctorBase> doctor = doctorBaseMapper.selectList(new QueryWrapper<DoctorBase>().eq("state", DoctorStatus.WAIT_REVIEW));
        return  doctor;
    }

    @Override
    public RespPageBean getAllPerson(Integer currentPage, Integer size, DoctorBase doctorBase) {
        Page<DoctorBase> page = new Page<>(currentPage,size);
        IPage<DoctorBase> articleByPage = doctorBaseMapper.selectAllPerson(page, doctorBase);
        return new RespPageBean(articleByPage.getTotal(),articleByPage.getRecords());

    }

    @Override
    public DoctorBase getDoctorBase() {
        Integer baseId = DoctorUtils.getCurrentAdmin().getBaseId();
        // 为空创建base记录
        if (baseId == null){
            DoctorBase doctorBase = new DoctorBase();
            doctorBase.setId(DoctorUtils.getCurrentAdmin().getId());
            doctorBase.setDid(DoctorUtils.getCurrentAdmin().getId());
            doctorBase.setSex(true);
            doctorBase.setName(DoctorUtils.getCurrentAdmin().getRealName());
            doctorBaseMapper.insert(doctorBase);
            DoctorUtils.getCurrentAdmin().setBaseId(doctorBase.getId());
            DoctorBase base = doctorBaseMapper.selectById(doctorBase.getId());
            return base;
        }
        // 不为空
        DoctorBase doctorBase = doctorBaseMapper.selectById(baseId);
        return doctorBase;
    }

    @Override
    @Transactional
    public RespBean saveDoctor(Doctor doctor) {
        Integer id = doctor.getId();
        if (id == null){
            throw ErrorCodeException.valueOf("参数异常");
        }
        Doctor doctorNew = doctorMapper.selectById(id);
        doctorNew.setRealName(doctor.getRealName());
        doctorNew.setUsername(doctor.getUsername());
        doctorNew.setPhone(doctor.getUsername());
        doctorNew.setUserFace(doctor.getUserFace());
        doctorMapper.updateById(doctorNew);
        DoctorUtils.getCurrentAdmin().getBaseId();
        DoctorBase doctorBaseNew = doctorBaseMapper.selectById(DoctorUtils.getCurrentAdmin().getBaseId());
        doctorBaseNew.setName(doctor.getRealName());
        doctorBaseNew.setCoverUrl(doctor.getUserFace());
        doctorBaseMapper.updateById(doctorBaseNew);

        return RespBean.success("上传成功");
    }

    @Override
    @Transactional
    public RespBean saveDoctorBase(DoctorBase doctorBase,Integer time) {
        Integer id = doctorBase.getId();
        if (id == null){
            throw ErrorCodeException.valueOf("参数异常");
        }
        DoctorBase doctorBaseNew = doctorBaseMapper.selectById(id);
        if (time == 0){
            doctorBaseNew.setBetter(doctorBase.getBetter());
            doctorBaseNew.setDes(doctorBase.getDes());
            doctorBaseMapper.updateById(doctorBaseNew);
        }else {
            doctorBaseNew.setType(doctorBase.getType());
            doctorBaseNew.setHspName(doctorBase.getHspName());
            doctorBaseNew.setOffLineId(doctorBase.getOffLineId());
            doctorBaseNew.setTitleName(doctorBase.getTitleName());
            doctorBaseNew.setLevel(doctorBase.getLevel());
            doctorBaseNew.setNationLevel(doctorBase.getNationLevel());
            doctorBaseMapper.updateById(doctorBaseNew);
        }
        return RespBean.success("保存成功");

    }

    @Override
    public DoctorBase getDoctorBaseById(Integer doctorId) {
        DoctorBase doctorBase = doctorBaseMapper.selectById(doctorId);
        return doctorBase;
    }

    @Override
    public RespBean refundDoctorAuth(RefundInfo refundInfo) {
        if (refundInfo.getCheckId() == null){
            throw ErrorCodeException.valueOf("参数异常");
        }
        DoctorBase doctorBase = doctorBaseMapper.selectById(refundInfo.getCheckId());
        doctorBase.setUpdateTime(LocalDateTime.now());
        doctorBase.setState(DoctorStatus.FAIL.getValue());
        doctorBaseMapper.updateById(doctorBase);
        // 插入记录
        refundInfo.setRefundType(1);// 0为资讯1为人
        refundInfo.setCheckAccountId(DoctorUtils.getCurrentAdmin().getId());
        refundInfo.setCheckAccountName(DoctorUtils.getCurrentAdmin().getRealName());
        refundInfoMapper.insert(refundInfo);
        return RespBean.success("驳回成功");
    }

    @Override
    @Transactional
    public RespBean passDoctorAuth(Integer doctorId) {
        DoctorBase doctorBase = doctorBaseMapper.selectById(doctorId);
        doctorBase.setState(DoctorStatus.PASS.getValue());
        doctorBaseMapper.updateById(doctorBase);
        // 新增权限
        Integer did = doctorBase.getDid();
        DoctorRole doctorRole = new DoctorRole();
        doctorRole.setRid(3);//医生角色为3
        doctorRole.setDoctorId(did);
        doctorRoleMapper.insert(doctorRole);
        return RespBean.success("通过审核");
    }


}
