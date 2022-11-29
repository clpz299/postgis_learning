package com.general.template.pgeo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.general.template.mapper.MonomerTestMapper;
import com.general.template.pgeo.entity.Monomer;
import com.general.template.pgeo.service.MonomerTestService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@DS("master")
public class MonomerTestServiceImpl extends ServiceImpl<MonomerTestMapper, Monomer> implements MonomerTestService {

    @Resource
    private MonomerTestMapper monomerTestMapper;

    @Override
    public int saveGeoJsonMultiPolygon(@Param("monomerList") List<Monomer> monomerList) {
        return monomerTestMapper.saveBatchList(monomerList);
    }
}
