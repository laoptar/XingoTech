package com.cheny.utils;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.cheny.entity.Division;

/**
 * 行政区划 CSV 导出工具类
 */
public class DivisionCsvUtil {

    /**
     * 将行政区划列表写入 CSV 响应流
     */
    public static void exportToCsv(List<Division> list, HttpServletResponse response) throws Exception {
        response.setContentType("text/csv;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=administrative_divisions.csv");

        try (Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8)) {
            // BOM for Excel compatibility
            writer.write('﻿');
            // header
            writer.write("主键,行政区划代码,行政区划名称,父级代码,层级,是否启用\n");
            // rows
            for (Division d : list) {
                writer.write(String.join(",",
                        nullToEmpty(d.getId()),
                        nullToEmpty(d.getCode()),
                        nullToEmpty(d.getName()),
                        nullToEmpty(d.getParentCode()),
                        levelToString(d.getLevel()),
                        d.getEnabled() != null ? (d.getEnabled() == 1 ? "启用" : "停用") : ""));
                writer.write("\n");
            }
            writer.flush();
        }
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    private static String levelToString(Integer level) {
        if (level == null)
            return "";
        switch (level) {
            case 1: return "省/直辖市/自治区";
            case 2: return "地级市/自治州/地区";
            case 3: return "市辖区/县级市/县";
            default: return level.toString();
        }
    }
}