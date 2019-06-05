package org.jeecg.modules.ws.define.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ws_property")
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.UUID)
    private String id;

    private String propertyno;

    private String propertyname;

    private String propertyop;

    private String propertymark;

    private String propertyvaluetype;

    private String propertyflag;

    private Integer propertymin;

    private Integer porpertymax;

    private String note;

    private String showmark;
}
