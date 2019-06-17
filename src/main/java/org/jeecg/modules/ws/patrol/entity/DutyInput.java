package org.jeecg.modules.ws.patrol.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("ws_dutyinput")
public class DutyInput {
    @TableId(type = IdType.UUID)
    private String id;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dutydate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rdate;

    private String dutytype;

    private String watcher;

    private Boolean network;

    private Boolean serverip;

    private Boolean server;

    private Boolean control;

    private Double cityv1;

    private Double cityh1;

    private Double cityv2;

    private Double cityh2;

    private Double upsv1;

    private Double upsh1;

    private Double upsl1;

    private Boolean upshint1;

    private Double upsv2;

    private Double upsh2;

    private Double upsl2;

    private Boolean upshint2;

    private Double upsv3;

    private Double upsh3;

    private Double upsl3;

    private Boolean upshint3;

    private Double serverc1;

    private Double serverm1;

    private Double serverc2;

    private Double serverm2;

    private Double serverc3;

    private Double serverm3;

    private Double powerc;

    private Double powerm;

    private Double netc;

    private Double netm;

    private Boolean offarea;

    private Boolean tearoom;

    private Boolean destine;

    private Boolean lockroom;

    private Boolean macroom;

    private Boolean netroom;

    private Boolean powroom;

    private Boolean conroom;

    private Boolean oneserver;

    private String oaserver;

    private Boolean ncserver;

    private Boolean personserver;

    private Boolean memserver;

    private Boolean supserver;

    private Boolean oneweb;

    private Boolean memweb;

    private Boolean supweb;

    private Boolean conweb;

    private Boolean oaweb;

    private Boolean ncweb;

    private Boolean personweb;

    private Boolean comweb;

    private String errorlog;

    private Boolean cloud;

    private Boolean note;

    private Boolean wsapp;

    private Boolean backups;

    private Boolean gateway;


}
