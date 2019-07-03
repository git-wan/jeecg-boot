package org.jeecg.modules.ws.patrol.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@TableName("ws_entpropstatus")
public class EntPropStatus {
    @TableId(type = IdType.UUID)
    private String id;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date indate;

    private String entityno;

    private String entityname;

    private String propertyno;

    private String propertyname;

    private String propertyvalue;

    private Integer propertymin;

    private String valueflag;

    private String valuetype;

    private String propertychar;

    private String status;

}
