package com.cheny.service;

import java.util.List;

import com.cheny.entity.Division;

public interface DivisionService {
    Division selectDivision(String id);

    List<Division> findDivisionByPage(String parentCode, Integer level);

    List<Division> selectByParentCode(String parentCode);

    int addDivision(Division division);

    int modifyDivision(Division division);

    int enableDivision(String id);

    int disableDivision(String id);

    int syncDivision(List<Division> divisionList);

    List<Division> exportDivisions();
}