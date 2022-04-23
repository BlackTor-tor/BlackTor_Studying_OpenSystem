package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (InviteCodeRecord)实体类
 *
 * @author Cyanogen
 * @since 2022-04-18 20:52:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InviteCodeRecord implements Serializable {
    private static final long serialVersionUID = -86133129874511825L;
    /**
     * 记录id
     */
    private Long recordId;
    /**
     * 邀请人id
     */
    private String bsosId;
    /**
     * 被邀请人id
     */
    private String invitedId;
    /**
     * 使用时间
     */
    private Date usingTime;

}
