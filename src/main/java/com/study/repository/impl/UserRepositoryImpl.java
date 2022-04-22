package com.study.repository.impl;

import com.study.entity.Role;
import com.study.entity.User;
import com.study.mapper.UserDao;
import com.study.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.study.utils.GenerateUtil.randomString;

/**
 * 用户仓库实现类
 *
 * @author Cyanogen
 * @date 2022-04-16 11:12
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserDao userDao;

    @Override
    public Integer save(User user) {
        String bsosId;
        do {
            bsosId = randomString(256);
        } while (userDao.queryById(bsosId) != null);
        user.setBsosId(bsosId);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setIsRealName(false);
        user.setIsEnable(true);
        user.setScore(0L);
        user.setRegistrationNumber(0);
        user.setRegisterTime(new Date());
        return userDao.insert(user);
    }

    @Override
    public User findByUserAccount(String userAccount) {
        return null;
    }

    @Override
    public Integer deleteById(String bsosId) {
        return null;
    }

    @Override
    public Boolean checkAccount(String userAccount) {
        return null;
    }

    @Override
    public Integer updateUser(User user) {
        return null;
    }

    @Override
    public List<Role> findUserRolesByAccount(String userAccount) {
        return userDao.queryUserRoleByUserAccount(userAccount);
    }
}
