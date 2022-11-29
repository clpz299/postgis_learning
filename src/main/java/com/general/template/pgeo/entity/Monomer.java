package com.general.template.pgeo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.general.template.pgeo.handler.GeographyTypeHandler;
import com.general.template.pgeo.handler.PgGeometryTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

//一标三识_单体化表
@TableName(value = "monomer",autoResultMap = true)
@Data
public class Monomer implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "酒店(OST_HOTEL)、住宅房屋(OST_HOME)、党政机关(OST_PARTY)、小区楼宇(OST_BUILDING)、店铺(OST_SHOP)")
    private String type;

    //楼层高
    @ApiModelProperty(value = "楼层高")
    private Double floorHeig;

    @ApiModelProperty(value = "基础高")
    private Double baseHeigh;

    @ApiModelProperty(value = "顶高")
    private Double topHeight;

    @ApiModelProperty(value = "地理数据")
    @TableField(exist = false)
    private String geometry;

    @ApiModelProperty(value = "地理数据")
    @TableField(typeHandler = GeographyTypeHandler.class)
//    @TableField(typeHandler = PgGeometryTypeHandler.class)
    private String geog;

    @ApiModelProperty(value = "颜色")
    private String color;

    @ApiModelProperty(value = "小区名称")
    private String communityName;

    @ApiModelProperty(value = "楼号")
    private String buildingNo;

    @ApiModelProperty(value = "单元")
    private String unit;

    @ApiModelProperty(value = "小区名称")
    private String doorNo;

    @ApiModelProperty(value = "楼层")
    private String floorName;

    @ApiModelProperty(value = "相机坐标")
    private String viewingAngle;

}