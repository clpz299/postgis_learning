package com.general.template.pgeo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.general.template.pgeo.handler.PgGeometryTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "point_test", autoResultMap = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PointTest {

    @TableId
    private Long id;

    private String name;

    @TableField(typeHandler = PgGeometryTypeHandler.class)
    private String geom;

    @TableField(exist = false)
    private String geoJson;

}
