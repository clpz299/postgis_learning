package com.general.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.general.template.pgeo.entity.Monomer;
import com.general.template.pgeo.entity.PointTest;

import java.util.List;

public interface MonomerTestMapper extends BaseMapper<Monomer> {

    int saveBatchList(List<Monomer> monomerList);
}
