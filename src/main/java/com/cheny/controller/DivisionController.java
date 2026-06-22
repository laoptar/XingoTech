package com.cheny.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheny.entity.Division;
import com.cheny.entity.Msg;
import com.cheny.service.DivisionService;
import com.cheny.utils.DivisionCsvUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/division")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    @ResponseBody
    public Msg findDivisionByPage(
            @RequestParam(value = "pageindex", defaultValue = "1") Integer pageindex,
            @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize,
            @RequestParam(value = "parentCode", required = false) String parentCode,
            @RequestParam(value = "level", required = false) Integer level) {
        PageHelper.startPage(pageindex, pagesize);
        List<Division> list = divisionService.findDivisionByPage(parentCode, level);
        PageInfo<Division> pageInfo = new PageInfo<Division>(list, pagesize);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping(value = {"/select/{id}"}, method = {RequestMethod.GET})
    @ResponseBody
    public Msg selectDivision(@PathVariable("id") String id) throws Exception {
        Division division = divisionService.selectDivision(id);
        return Msg.success().add("division", division);
    }

    @RequestMapping(value = {"/addDivision"}, method = {RequestMethod.POST})
    @ResponseBody
    public Msg addDivision(@RequestBody Division division) throws Exception {
        int rows = divisionService.addDivision(division);
        return Msg.success().add("rows", rows);
    }

    @RequestMapping(value = {"/editDivision"}, method = {RequestMethod.POST})
    @ResponseBody
    public Msg editDivision(@RequestBody Division division) throws Exception {
        int rows = divisionService.modifyDivision(division);
        return Msg.success().add("rows", rows);
    }

    @RequestMapping(value = {"/enable/{id}"}, method = {RequestMethod.POST})
    @ResponseBody
    public Msg enableDivision(@PathVariable("id") String id) throws Exception {
        int rows = divisionService.enableDivision(id);
        return Msg.success().add("rows", rows);
    }

    @RequestMapping(value = {"/disable/{id}"}, method = {RequestMethod.POST})
    @ResponseBody
    public Msg disableDivision(@PathVariable("id") String id) throws Exception {
        int rows = divisionService.disableDivision(id);
        return Msg.success().add("rows", rows);
    }

    @RequestMapping(value = {"/sync"}, method = {RequestMethod.POST})
    @ResponseBody
    public Msg syncDivision(@RequestBody List<Division> divisionList) throws Exception {
        int rows = divisionService.syncDivision(divisionList);
        return Msg.success().add("rows", rows);
    }

    @RequestMapping(value = {"/export"}, method = {RequestMethod.GET})
    public void exportDivisions(HttpServletResponse response) throws Exception {
        List<Division> list = divisionService.exportDivisions();
        DivisionCsvUtil.exportToCsv(list, response);
    }
}