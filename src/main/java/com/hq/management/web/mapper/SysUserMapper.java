package com.hq.management.web.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hq.management.web.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * SysUser 表数据库控制层接口
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	List<Map<Object, Object>> selectUserList(Page<Map<Object, Object>> page, @Param("search") String search);
}