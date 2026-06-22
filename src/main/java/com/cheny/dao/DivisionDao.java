package com.cheny.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cheny.entity.Division;

public interface DivisionDao {
    Division selectById(String id);

    List<Division> selectList(@Param("parentCode") String parentCode, @Param("level") Integer level);

    List<Division> selectAll();

    int insert(Division division);

    int update(Division division);

    int updateEnabled(@Param("id") String id, @Param("enabled") Integer enabled);

    Division selectByCode(String code);

    int upsertByCode(Division division);
}
