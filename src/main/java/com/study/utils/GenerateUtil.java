package com.study.utils;

import com.study.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成类
 *
 * @author Cyanogen
 * @date 2022-04-18 13:51
 */
public class GenerateUtil {

    private static final String SAMPLE_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE_LENGTH = SAMPLE_STRING.length();
    /**
     *获得登录用户id
     *
     * @return 用户id
     */
    public static String getLoggedInUserId(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null){
            throw  new UsernameNotFoundException("用户未登录");
        }else {
            return user.getBsosId();
        }
    }

    /**
     * 获取随机字符串
     *
     * @param length 生成的随机字符串长度
     * @return 随机字符串
     */
    public static String randomString(int length) {

        if (length < 1) {
            length = 1;
        }
        final StringBuilder sb = new StringBuilder(length);
        int baseLength = SAMPLE_STRING.length();
        while (sb.length() < length) {
            //此处用ThreadLocalRandom 不用Random的感兴趣的同学可以看看这俩的区别
            //主要区别在于多线程高并发环境下 ThreadLocalRandom比Random生成随机数的速度快
            int number = ThreadLocalRandom.current().nextInt(baseLength);
            sb.append(SAMPLE_STRING.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取随机字符串
     *
     * @param digit 数字类型长度
     * @param chars 字符类型长度
     * @return 随机字符串
     */
    public static String randomString(int digit, int chars) {

        int length = digit + chars;

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < digit; i++) {
            list.add(SAMPLE_STRING.charAt(ThreadLocalRandom.current().nextInt(52, BASE_LENGTH)));
        }
        for (int i = 0; i < chars; i++) {
            list.add(SAMPLE_STRING.charAt(ThreadLocalRandom.current().nextInt(0, 53)));
        }
        Collections.shuffle(list);
        StringBuilder code = new StringBuilder(length);
        for (char c : list) {
            code.append(c);
        }
        return code.toString();
    }
}
