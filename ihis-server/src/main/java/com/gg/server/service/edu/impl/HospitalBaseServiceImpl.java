package com.gg.server.service.edu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.server.config.Exception.ErrorCodeException;
import com.gg.server.entity.Doctor;
import com.gg.server.entity.edu.HospitalBase;
import com.gg.server.entity.edu.HospitalDept;
import com.gg.server.mapper.edu.HospitalBaseMapper;
import com.gg.server.mapper.edu.HospitalDeptMapper;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.dto.HspWithDeptDto;
import com.gg.server.service.edu.HospitalBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 医院基本信息表（主要有用的是前五个属性，后面是用来介绍医院的信息的，但是没有页面展示，所以其实没用） 服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-06-11
 */
@Service
public class HospitalBaseServiceImpl extends ServiceImpl<HospitalBaseMapper, HospitalBase> implements HospitalBaseService {


    @Autowired(required = false)
    private HospitalBaseMapper hospitalBaseMapper;

    @Autowired(required = false)
    private HospitalDeptMapper hospitalDeptMapper;

    @Override
    public List<HospitalBase> getHospitals() {
        List<HospitalBase> hospitals = hospitalBaseMapper.getHospitalsWithDept();
        return  hospitals;
    }

    @Override
    public HospitalBase getHospitalById(Integer id) {
        HospitalBase hospitalsWithDept = hospitalBaseMapper.getHospitalWithDept(id);
        return hospitalsWithDept;
    }

    @Override
    @Transactional
    public RespBean updateHospital(HospitalBase hospitalBase) {
        // 为空插入
        HospitalBase base = new HospitalBase();
        if (hospitalBase.getId() == null){
            base.setHspId(hospitalBase.getHspId());
            base.setFullName(hospitalBase.getFullName());
            base.setSimpleName(hospitalBase.getSimpleName());
            base.setHspRank(hospitalBase.getHspRank());
            base.setType(hospitalBase.getType());
            base.setCheckBySelf(hospitalBase.getCheckBySelf());
            base.setDes(hospitalBase.getDes());
            hospitalBaseMapper.insert(base);

        }else {
            // 修改基本信息表
            base = hospitalBaseMapper.selectById(hospitalBase.getId());
            base.setHspId(hospitalBase.getHspId());
            base.setFullName(hospitalBase.getFullName());
            base.setSimpleName(hospitalBase.getSimpleName());
            base.setHspRank(hospitalBase.getHspRank());
            base.setType(hospitalBase.getType());
            base.setCheckBySelf(hospitalBase.getCheckBySelf());
            base.setDes(hospitalBase.getDes());
            hospitalBaseMapper.updateById(base);
        }
        // 修改科室信息表
        for (HospitalDept hospitalDept: hospitalBase.getDepts()){
            if (hospitalDept.getId() == null){
                // 确定名字不会重复
                HospitalDept one = hospitalDeptMapper.selectOne(new QueryWrapper<HospitalDept>().eq("deptName", hospitalDept.getDeptName())
                        .eq("hospId", base.getId()));
                if (one == null){
                    hospitalDept.setHospId(base.getId());
                    hospitalDeptMapper.insert(hospitalDept);
                }
            }
        }
        return RespBean.success("修改成功");
    }
}
