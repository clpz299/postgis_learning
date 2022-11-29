package com.general.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.general.template.auth.dto.SysUserDTO;
import com.general.template.auth.service.SysUserService;
import com.general.template.pgeo.entity.Monomer;
import com.general.template.pgeo.entity.PointTest;
import com.general.template.pgeo.service.MonomerTestService;
import com.general.template.pgeo.service.PointTestService;
import netscape.javascript.JSObject;
import org.geotools.geojson.geom.GeometryJSON;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TemplateApplicationTests {

    @Resource
    private SysUserService service;

    @Resource
    private PointTestService pointTestService;

    @Resource
    private MonomerTestService monomerTestService;

    @Test
    void testPostGis() throws IOException {
//        PointTest point = new PointTest();
//        point.setName("中寨居委会");
//        point.setGeom("POINT(109.262605 27.200669)");//POINT(lng,lat) 经度,纬度
//        pointTestService.insertPointTest(point);

//        PointTest point = pointTestService.getPointById(1L);
//        System.out.println(point);

        List<Monomer> monomerList = new ArrayList<>();
        GeometryJSON geometryJSON = new GeometryJSON(18);
        String geojson = FileUtil.readUtf8String("D:\\wvp\\Monomer.geojson");
        JSONObject gjsonObject = JSONObject.parseObject(geojson);
        JSONArray features = gjsonObject.getJSONArray("features");
        for (int i = 0; i < features.size(); i++) {
            JSONObject properties =  features.getJSONObject(i).getJSONObject("properties");
            Geometry read = geometryJSON.read(features.getJSONObject(i).getString("geometry"));
            System.out.println(read.toString());
            Monomer monomer = new Monomer();
            monomer.setGeog(read.toString());
            monomer.setFloorHeig(properties.getDouble("floor_height"));
            monomer.setBaseHeigh(properties.getDouble("base_height"));
            monomer.setTopHeight(properties.getDouble("top_height"));
            monomer.setCommunityName(properties.getString("address"));
            monomerList.add(monomer);
        }

        monomerTestService.saveGeoJsonMultiPolygon(monomerList);


    }

    @Test
    void testWkbAndGeojson() throws ParseException, IOException {
        WKBReader reader = new WKBReader();
        Geometry geometry = reader.read(WKBReader.hexToBytes("010600000001000000010300000001000000080000008015C8D5F04E5E408027C609FE2D3E401082B3D3F04E5E40F020DE63FE2D3E40D840493AF04E5E4050242739FE2D3E40A8C17025F04E5E40F03E8EC0012E3E40C007A1E9F04E5E40602244F7012E3E4068FBDFFDF04E5E40703E278AFE2D3E40B80D38F2F04E5E40E0CF403EFE2D3E408015C8D5F04E5E408027C609FE2D3E40"));
        GeometryJSON geometryJSON = new GeometryJSON(9);
        String s = geometryJSON.toString(geometry);
        System.out.println(s);

        Geometry read = geometryJSON.read(s);
        System.out.println(read.toString());
        WKBWriter wkbWriter = new WKBWriter();
        byte[] write = wkbWriter.write(geometry);
        String s1 = WKBWriter.toHex(write);
        System.out.println(s1);
    }

    @Test
    void contextLoads() {
        List<Long> roles = new ArrayList<>();
        roles.add(1L);
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUsername("admin");
        sysUserDTO.setPassword("123456");
        sysUserDTO.setEnabled(true);
        sysUserDTO.setEmail("clpz@.qq.com");
        sysUserDTO.setLocked(false);
        sysUserDTO.setFullName("clpz");
        sysUserDTO.setOrgId(1L);
        sysUserDTO.setRoleIds(roles);
        service.create(sysUserDTO, null);
    }

}
