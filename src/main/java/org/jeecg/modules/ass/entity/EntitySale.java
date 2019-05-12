package org.jeecg.modules.ass.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
@Data
public class EntitySale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name="时间",width=30,format="yyyy-MM-dd")
    private java.util.Date SALEDATE;

    @Excel(name="实体",width=35)
    private java.lang.String STORENAME;

    @Excel(name="当日销售",width=35)
    private java.lang.Double CURRAMT;

    @Excel(name="去年可比当日销售",width=35)
    private java.lang.Double COMAMT;

    @Excel(name="可比增长",width=35)
    private java.lang.Double COMINCREASE;

    @Excel(name="当月累计",width=35)
    private java.lang.Double CURRSUM;

    @Excel(name="同比月度累计",width=35)
    private java.lang.Double HISSUM;
}
