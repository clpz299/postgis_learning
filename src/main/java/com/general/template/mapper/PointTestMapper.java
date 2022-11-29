package com.general.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.general.template.pgeo.entity.PointTest;
import org.apache.ibatis.annotations.Param;

public interface PointTestMapper extends BaseMapper<PointTest> {

    PointTest findGeoJsonById(@Param("id")Long Id);
}
