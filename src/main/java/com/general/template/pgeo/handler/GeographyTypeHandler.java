package com.general.template.pgeo.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBReader;
import org.postgis.PGgeography;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeographyTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        PGgeography pGgeography = new PGgeography(s);
        preparedStatement.setObject(i, pGgeography);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        PGgeography pGgeography = new PGgeography(resultSet.getString(s));
        if (pGgeography == null) {
            return null;
        }
        return pGgeography.toString();
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        PGgeography pGgeography = new PGgeography(resultSet.getString(i));
        if (pGgeography == null) {
            return null;

        }
        return pGgeography.toString();
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        PGgeography pGgeography = new PGgeography(callableStatement.getString(i));
        if (pGgeography == null) {
            return null;
        }
        return pGgeography.toString();
    }
    //取出数据转换,WKB->Geojson

    @Override

    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String WKB = rs.getString(columnName);
        if (WKB == null) {
            return null;
        }
        WKBReader reader = new WKBReader();
        Geometry geometry = null;
        try {
            geometry = reader.read(WKBReader.hexToBytes(WKB));
        } catch (Exception e) {
            //转换失败
            return null;
        }
        // 设置保留15位小数，否则GeometryJSON默认保留4位小数
        GeometryJSON geometryJson = new GeometryJSON(16);

        return geometryJson.toString(geometry);

    }
}
