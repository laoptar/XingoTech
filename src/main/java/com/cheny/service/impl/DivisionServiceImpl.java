package com.cheny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheny.dao.DivisionDao;
import com.cheny.entity.Division;
import com.cheny.service.DivisionService;
import com.cheny.utils.IdUtil;

@Service("divisionService")
public class DivisionServiceImpl implements DivisionService {

    @Resource
    private DivisionDao divisionDao;

    @Override
    public Division selectDivision(String id) {
        return divisionDao.selectById(id);
    }

    @Override
    public List<Division> findDivisionByPage(String parentCode, Integer level) {
        return divisionDao.selectList(parentCode, level);
    }

    @Override
    public List<Division> selectByParentCode(String parentCode) {
        return divisionDao.selectList(parentCode, null);
    }

    @Override
    public int addDivision(Division division) {
        division.setId(IdUtil.generateUserId());
        if (division.getEnabled() == null) {
            division.setEnabled(1);
        }
        return divisionDao.insert(division);
    }

    @Override
    public int modifyDivision(Division division) {
        if (division.getId() == null || division.getId().isEmpty()) {
            throw new IllegalArgumentException("行政区划主键不能为空");
        }
        Division old = divisionDao.selectById(division.getId());
        if (old == null) {
            throw new IllegalArgumentException("行政区划不存在，无法修改");
        }
        return divisionDao.update(division);
    }

    @Override
    public int enableDivision(String id) {
        return divisionDao.updateEnabled(id, 1);
    }

    @Override
    public int disableDivision(String id) {
        return divisionDao.updateEnabled(id, 0);
    }

    @Override
    public int syncDivision(List<Division> divisionList) {
        if (divisionList == null || divisionList.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (Division division : divisionList) {
            if (division.getId() == null || division.getId().isEmpty()) {
                division.setId(IdUtil.generateUserId());
            }
            if (division.getEnabled() == null) {
                division.setEnabled(1);
            }
            count += divisionDao.upsertByCode(division);
        }
        return count;
    }

    @Override
    public List<Division> exportDivisions() {
        return divisionDao.selectAll();
    }
}