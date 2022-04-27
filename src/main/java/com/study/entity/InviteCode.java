package com.study.entity;

import com.study.common.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 邀请码表(InviteCode)实体类
 *
 * @author Cyanogen
 * @since 2022-04-18 20:50:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteCode implements Serializable {
    private static final long serialVersionUID = -45816339783702056L;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 邀请人id
     */
    private String bsosId;
    /**
     * 创建时间
     */
    private Date creatTime;
    /**
     * 使用次数
     */
    private Long useCount;

    /**
     * 随机生成邀请码
     *
     * @param digit 数字类型长度
     * @param chars 字符类型长度
     */
    public void setInviteCode(int digit, int chars) {

        int length = digit + chars;

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < digit; i++) {
            list.add(Const.SAMPLE_STRING.charAt(ThreadLocalRandom.current().nextInt(53, 63)));
        }
        for (int i = 0; i < chars; i++) {
            list.add(Const.SAMPLE_STRING.charAt(ThreadLocalRandom.current().nextInt(0, 53)));
        }
        Collections.shuffle(list);
        StringBuilder code = new StringBuilder(length);
        for (char c : list) {
            code.append(c);
        }
        this.inviteCode = code.toString();
    }

}
