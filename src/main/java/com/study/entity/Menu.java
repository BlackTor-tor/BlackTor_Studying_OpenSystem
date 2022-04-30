package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 记录访问的url(Menu)实体类
 *
 * @author Cyanogen
 * @since 2022-04-30 17:27:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = -80928929369555266L;

    private Long menuId;
    /**
     * 访问地址
     */
    @NotBlank(message = "url不应该为空")
    private String url;

    /**
     * 可访问权限集合
     */
    @NotEmpty(message = "可访问权限不应该为空")
    private List<Role> roles;

}
