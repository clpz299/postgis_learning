package com.general.template.pgeo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.general.template.pgeo.entity.PointTest;

public interface PointTestService extends IService<PointTest> {

    PointTest getPointById(Long id);

    boolean insertPointTest(PointTest point);
}
