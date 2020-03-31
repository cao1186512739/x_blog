package com.xblog.core.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@ApiModel(value = "菜单对象实体")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    @Id
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单路由地址")
    private String menuPath;

    @ApiModelProperty(value = "菜单描述")
    private String menuDes;

    @ApiModelProperty(value = "菜单图标")
    private String icons;

    @ApiModelProperty(value = "菜单是否需要授权")
    private Boolean isAuth;

    @ApiModelProperty(value = "父菜单ID")
    private Integer parentId;

    @ApiModelProperty(value = "菜单是否激活")
    private Boolean enabled;
}