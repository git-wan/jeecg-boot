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
@TableName("ass_sco")
public class AssSco implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@TableId(type = IdType.UUID)
	private String id;

	private String assid;

	private String assinfoid;

	private String groupname;

	private Integer score;


}
