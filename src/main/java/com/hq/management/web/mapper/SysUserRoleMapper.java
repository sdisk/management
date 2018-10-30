package com.hq.management.web.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hq.management.web.entity.SysUserRole;

import java.util.List;

/**
 *
 * SysUserRole 表数据库控制层接口
 *
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	List<String> selectPermissionByUid(String uid);

}