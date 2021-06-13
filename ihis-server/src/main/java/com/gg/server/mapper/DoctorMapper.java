package com.gg.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gg.server.entity.Doctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gg.server.entity.Menu;
import com.gg.server.entity.edu.ArticleContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gg
 * @since 2021-02-15
 */
@Component
public interface DoctorMapper extends BaseMapper<Doctor> {

    List<Doctor> getAccounts();

    Doctor getAccountById(@PathVariable("id") Integer id);
}
