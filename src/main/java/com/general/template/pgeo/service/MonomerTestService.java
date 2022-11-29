package com.general.template.pgeo.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.general.template.pgeo.entity.Monomer;
import com.general.template.pgeo.entity.PointTest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MonomerTestService extends IService<Monomer> {
    int saveGeoJsonMultiPolygon(List<Monomer> monomerList);
}
