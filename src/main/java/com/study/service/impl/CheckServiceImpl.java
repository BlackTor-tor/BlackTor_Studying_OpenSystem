package com.study.service.impl;

import com.study.repository.InviteCodeRepository;
import com.study.repository.UserRepository;
import com.study.service.CheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 检查服务实现类
 *
 * @author Cyanogen
 * @date 2022-04-16 11:21
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private InviteCodeRepository inviteCodeRepository;

    @Override
    public Boolean checkInviteCode(String inviteCode) {
        return inviteCodeRepository.find(inviteCode) != null;
    }

    @Override
    public Boolean checkAccount(String userAccount) {
        return userRepository.checkAccount(userAccount);
    }
}
