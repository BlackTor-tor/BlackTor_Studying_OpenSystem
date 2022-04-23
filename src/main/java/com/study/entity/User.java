package com.study.entity;

import com.study.common.Const;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 用户基本信息表(User)实体类
 *
 * @author Cyanogen
 * @since 2022-04-18 13:26:27
 */
@Data
public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = 474964404097138059L;
    /**
     * 用户id
     */
    private String bsosId;
    /**
     * 注册序号
     */
    private Integer registrationNumber;
    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;
    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String userAccount;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式错误")
    private String email;
    /**
     * 最近登录时间
     */
    private Date recentLoginTime;
    /**
     * 用户注册时间
     */
    private Date registerTime;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 个人介绍
     */
    private String descriptions;
    /**
     * 手机号
     */
    @Length(min = 11, max = 11, message = "手机号格式错误")
    private String phone;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 安全问题
     */
    private String problem;
    /**
     * 安全问题答案
     */
    private String ans;
    /**
     * 是否实名注册
     */
    private Boolean isRealName;
    /**
     * 打卡得分
     */
    private Long score;
    /**
     * 用户是否可用
     */
    private Boolean isEnable;
    /**
     * 用户权限集合
     */
    private List<Role> roles;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    /**
     * 生成用户id
     *
     * @param length 生成的随机字符串长度
     *
     */
    public void setBsosId(int length) {


        if (length < 1) {
            length = 1;
        }
        final StringBuilder sb = new StringBuilder(length);
        int baseLength = Const.SAMPLE_STRING.length();
        while (sb.length() < length) {
            //此处用ThreadLocalRandom 不用Random的感兴趣的同学可以看看这俩的区别
            //主要区别在于多线程高并发环境下 ThreadLocalRandom比Random生成随机数的速度快
            int number = ThreadLocalRandom.current().nextInt(baseLength);
            sb.append(Const.SAMPLE_STRING.charAt(number));
        }
        this.bsosId = sb.toString();
    }

}
