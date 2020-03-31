package com.xblog.core.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName InputUserDto
 * @Description: 用户输入实体对象
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/

@Data
@ApiModel(value = "用户输入实体对象")
public class InputUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @Id
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @NonNull
    private String username;

    @NonNull
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户简介")
    private String preInfo;
}
