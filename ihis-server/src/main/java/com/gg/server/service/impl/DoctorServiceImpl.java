package com.gg.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gg.server.config.security.JwtTokenUtil;
import com.gg.server.entity.*;
import com.gg.server.mapper.DoctorMapper;
import com.gg.server.mapper.DoctorRoleMapper;
import com.gg.server.mapper.RoleMapper;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.enums.AdminMenu;
import com.gg.server.service.DoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.javadoc.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-02-15
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private DoctorRoleMapper doctorRoleMapper;
    /**
     * 登陆之后返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        // 登陆判断
        if (StringUtils.isEmpty(code)){
            return RespBean.error("验证码不能为空");
        }
        BASE64Decoder decoder = new BASE64Decoder();
        String header = request.getHeader("captcha");
        String captcha = null;
        try {
            byte[] bytes = decoder.decodeBuffer(request.getHeader("captcha"));
            captcha = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(captcha)){
            return RespBean.error("验证码验证错误,重新加载");
        }
        if (!captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码错误");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails){
            return RespBean.error("用户名错误");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("密码错误");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员");
        }
        // 更新security登陆对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登陆成功",tokenMap);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Override
    public Doctor getDoctorByUsername(String username) {
        return doctorMapper.selectOne(new QueryWrapper<Doctor>().eq("username", username)
                .eq("enabled", true));
    }

    /**
     * 根据用户 id 查询对应角色列表
     * @param doctorId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer doctorId) {
        return roleMapper.getRoles(doctorId);
    }

    /**
     * 获取账号信息
     * @return
     */
    @Override
    public List<Doctor> getAccounts() {
        List<Doctor> accounts = doctorMapper.getAccounts();
        return accounts;
    }

    @Override
    public Doctor getAccountById(Integer id) {
        Doctor accountById = doctorMapper.getAccountById(id);
        return accountById;
    }

    @Override
    public RespBean updateAccount(Doctor doctor) {
        if (doctor.getId() == null){
            // 新增
            doctor.setPhone(doctor.getUsername());
            doctor.setPassword("$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe");
            doctor.setUserFace("https://img.yzcdn.cn/vant/cat.jpeg");
            doctor.setCreateTime(LocalDate.now());
            doctor.setRemark(doctor.getRemark());
            doctor.setEnabled(doctor.isEnabled());
            doctorMapper.insert(doctor);
            // 添加账号角色
            if (doctor.getRoles().size() == 0){
                return RespBean.success("插入成功");
            }
            for (Role role: doctor.getRoles()){
                DoctorRole doctorRole = new DoctorRole();
                doctorRole.setDoctorId(doctor.getId());
                doctorRole.setRid(role.getId());
                doctorRoleMapper.insert(doctorRole);
            }
            return RespBean.success("新增成功");
        } else {
            // 在原来的信息上进行修改
            Doctor doctorOld = doctorMapper.selectById(doctor.getId());
            doctorOld.setRealName(doctor.getRealName());
            doctorOld.setUsername(doctor.getUsername());
            doctorOld.setPhone(doctor.getPhone());
            doctorOld.setAccountType(doctor.getAccountType());
            doctorOld.setRemark(doctor.getRemark());
            doctor.setEnabled(doctor.isEnabled());
            doctorMapper.updateById(doctorOld);
            // 添加菜单
            // 遍历老角色列表是否还在新角色列表
            for (Role role: getRoles(doctorOld.getId())){
                if(doctor.getRoles().contains(role)){
                    // 还在新角色里，不做更改
                }else {
                    // 不在新角色里，删除相关关系
                    doctorRoleMapper.delete(new QueryWrapper<DoctorRole>()
                            .eq("doctorId",role.getId())
                            .eq("rid",role.getId()));
                }
            }
            // 遍历新角色的列表是否已经存在老的角色列表上
            for (Role role: doctor.getRoles()){
                if(getRoles(doctorOld.getId()).contains(role)){
                    // 老角色已存在不做处理
                }else {
                    // 老角色不存在，增加相关关系
                    DoctorRole doctorRole = new DoctorRole();
                    doctorRole.setDoctorId(doctor.getId());
                    doctorRole.setRid(role.getId());
                    doctorRoleMapper.insert(doctorRole);
                }
            }
            return RespBean.success("修改成功");
        }
    }
}
