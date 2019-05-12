package org.jeecg.modules.ass.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
@Data
public class AssResExcel implements Serializable {
    private static final long serialVersionUID = 1L;


    /** 关键词 */
    @Excel(name="评定计划",width=15)
    private java.lang.String planname;

    /** 打卡时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name="评定时间",width=20,format="yyyy-MM-dd")
    private java.util.Date assdate;

    @Excel(name="评定对象",width=15)
    private java.lang.String assobject;

    @Excel(name="评定人",width=15)
    private java.lang.String adjuster;

    @Excel(name="评定类别",width=15)
    private java.lang.String adjtype;

    @Excel(name="工作质量",width=15)
    private java.lang.Integer quality;

    @Excel(name="工作效率",width=15)
    private java.lang.Integer efficiency;

    @Excel(name="考勤纪律",width=15)
    private java.lang.Integer checkwork;

    @Excel(name="行为规范",width=15)
    private java.lang.Integer action;

    @Excel(name="责任感",width=15)
    private java.lang.Integer responsibility;

    @Excel(name="创新性",width=15)
    private java.lang.Integer creative;

    @Excel(name="总分",width=15)
    private java.lang.Integer score;

    @Excel(name="评定级别",width=15)
    private java.lang.String asslevel;

    @Excel(name="备注",width=30)
    private java.lang.String  note;

}
