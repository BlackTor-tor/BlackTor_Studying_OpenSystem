package com.study.repository.impl;

import com.study.entity.InviteCode;
import com.study.entity.InviteCodeRecord;
import com.study.mapper.InviteCodeDao;
import com.study.mapper.InviteCodeRecordDao;
import com.study.mapper.UserDao;
import com.study.repository.InviteCodeRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 邀请码仓库实现类
 *
 * @author Cyanogen
 * @date 2022-04-16 11:32
 */
@Repository
public class InviteCodeRepositoryImpl implements InviteCodeRepository {

    @Resource
    private InviteCodeDao inviteCodeDao;

    @Resource
    private InviteCodeRecordDao inviteCodeRecordDao;

    @Resource
    private UserDao userDao;


    @Override
    public InviteCode find(String inviteCode) {
        return inviteCodeDao.queryByInviteCode(inviteCode);
    }

    @Override
    public String save(String bsosId) {
        InviteCode inviteCode = new InviteCode();
        //生成合法邀请码
        do {
            inviteCode.setInviteCode(4, 2);
        } while (find(inviteCode.getInviteCode()) != null);

        inviteCode.setUseCount(0L);
        inviteCode.setBsosId(bsosId);
        inviteCode.setCreatTime(new Date());
        int insert = inviteCodeDao.insert(inviteCode);
        if (insert > 0) {
            return inviteCode.getInviteCode();
        } else {
            return null;
        }
    }

    @Override
    public String change(String inviteCode) {
        InviteCode code = inviteCodeDao.queryByInviteCode(inviteCode);
        String inviterId = code.getBsosId();
        //判断该邀请码是普通用户的邀请码
        List<String> roles = userDao.queryUserRoleById(inviterId);
        Set<String> set = new HashSet<>(roles);

        if (set.contains("user")) {
            code.setUseCount(code.getUseCount() + 1);
            inviteCodeDao.update(code);
        } else {
            inviteCodeDao.deleteById(inviteCode);
        }
        return inviterId;
    }

    @Override
    public Integer generateRecord(String bsosId, String invitedId) {
        return inviteCodeRecordDao.insert(InviteCodeRecord.builder()
                .invitedId(invitedId)
                .bsosId(bsosId)
                .usingTime(new Date())
                .build());
    }

}
