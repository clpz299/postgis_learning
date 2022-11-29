package com.general.template.pgeo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.general.template.auth.service.SysRoleService;
import com.general.template.entity.SysRole;
import com.general.template.mapper.PointTestMapper;
import com.general.template.mapper.SysRoleMapper;
import com.general.template.pgeo.entity.PointTest;
import com.general.template.pgeo.service.PointTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DS("master")
public class PointTestServiceImpl extends ServiceImpl<PointTestMapper, PointTest> implements PointTestService {

    @Resource
    private PointTestMapper pointTestMapper;

    @Override
    public PointTest getPointById(Long id) {
        return pointTestMapper.findGeoJsonById(id);
    }

    @Override
    public boolean insertPointTest(PointTest point) {
        return pointTestMapper.insert(point) > 0;
    }
}
