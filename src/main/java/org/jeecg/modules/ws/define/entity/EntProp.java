package org.jeecg.modules.ws.define.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ws_entityproperty")
public class EntProp implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.UUID)
    private String id;

    private String entityno;

    private String entityname;

    private String propertyno;

    private String propertyname;

    private String propertyvalue;

    private String showmark;

    private Integer propertymin;

    private Integer propertymax;

    private String valueflag;

    private String autoentry;

    private String valuetype;


}
