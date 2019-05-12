package org.jeecg.modules.ass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: jeecg 测试demo 
 * @author： jeecg-boot 
 * @date： 	2018-12-29 
 * @version：V1.0
 */
@Data
@TableName("assess")
public class Assess implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@TableId(type = IdType.UUID)
	private String id;


	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date assdate;

	private String planname;

	private String adjuster;

	private String assobject;

	private String remark;

	private String status;

	private String adjtype;

	private Integer score;


}
