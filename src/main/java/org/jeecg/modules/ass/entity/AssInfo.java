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
@TableName("ass_info")
public class AssInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@TableId(type = IdType.UUID)
	private String id;

	private String asstype;

	private String scoregroup;

	private String scoreinfo;

	private String groupname;

	private String score;


}
