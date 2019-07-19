package org.jeecg.modules.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.SysAnnouncement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 系统通告表
 * @author： jeecg-boot
 * @date：   2019-01-02
 * @version： V1.0
 */
public interface SysAnnouncementMapper extends BaseMapper<SysAnnouncement> {
    List<SysAnnouncement> querySysCementListByUserId(Page<SysAnnouncement> page, @Param("userId")String userId, @Param("msgCategory")String msgCategory);
}
