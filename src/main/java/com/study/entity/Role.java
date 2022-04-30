package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author Cyanogen
 * @since 2022-04-18 13:49:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 722736567280987768L;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    private String roleName;

}
