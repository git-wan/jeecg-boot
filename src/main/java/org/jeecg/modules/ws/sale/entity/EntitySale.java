package org.jeecg.modules.ws.sale.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
public class EntitySale implements Serializable {

    private static final long serialVersionUID = 1L;

   @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
   @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="时间",width=30,format="yyyy-MM-dd")
    private java.util.Date SALEDATE;

    @Excel(name="实体",width=35)
    private String STORENAME;

    @Excel(name="当日销售",width=35)
    private Double CURRAMT;

    @Excel(name="去年可比当日销售",width=35)
    private Double COMAMT;

    @Excel(name="可比增长",width=35)
    private Double COMINCREASE;

    @Excel(name="当月累计",width=35)
    private Double CURRSUM;

    @Excel(name="同比月度累计",width=35)
    private Double HISSUM;
}
