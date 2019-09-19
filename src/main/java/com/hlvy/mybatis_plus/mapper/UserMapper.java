package com.hlvy.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlvy.mybatis_plus.baseMapper.MyBaseMapper;
import com.hlvy.mybatis_plus.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper
 *
 * @author heng
 **/

public interface UserMapper extends MyBaseMapper<User> {

   /* @Select("select * from user ${ew.customSqlSegment}")*/
   List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);


   IPage<User> selectUserPage(Page<User> page,@Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
