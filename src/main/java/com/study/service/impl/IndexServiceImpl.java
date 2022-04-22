package com.study.service.impl;


import com.study.entity.User;
import com.study.repository.InviteCodeRepository;
import com.study.repository.UserRepository;
import com.study.service.IndexService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户通用操作
 *
 * @author Cyanogen
 * @date 2022-04-13 21:05:33
 */
@Service
public class IndexServiceImpl implements IndexService, UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private InviteCodeRepository inviteCodeRepository;

    @Override
    public Boolean register(User user, String inviteCode) {
        int insert = userRepository.save(user);

        //更改邀请码状态
        //返回邀请码的用户id
        String inviterId = inviteCodeRepository.change(inviteCode);
        //在邀请码记录表新增加记录
        inviteCodeRepository.generateRecord(inviterId, user.getBsosId());
        return insert > 0;
    }

    @Override
    public Boolean verifyAnswer(String userAccount, String answer) {
        return null;
    }

    @Override
    public Boolean changePassword(String userAccount, String password) {
        User user = userRepository.findByUserAccount(userAccount);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        int update = userRepository.updateUser(user);
        return update > 0;
    }

    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {

        //密码认证在框架内实现
        User user = userRepository.findByUserAccount(userAccount);
        if (user != null) {
            if (!user.getIsRealName()) {
                Date date = new Date();
                Date registerTime = user.getRegisterTime();
                long div = date.getTime() - registerTime.getTime();
                if (div >= 604800) {
                    userRepository.deleteById(user.getBsosId());
                    throw new UsernameNotFoundException("用户未实名认证,账号已回收");
                }
            }
            user.setRoles(userRepository.findUserRolesByAccount(userAccount));
            //返回后会调用内部认证
            return user;
        }
        throw new UsernameNotFoundException("用户不存在!");
    }
}
