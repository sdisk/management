package com.hq.management.web.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hq.management.web.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * SysMenu 表数据库控制层接口
 *
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	List<String> selectMenuIdsByuserId(String uid);

	List<String> selectResourceByUid(@Param("uid") String uid);

}