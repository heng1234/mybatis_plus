package com.hlvy.mybatis_plus.com.mybatis_plus.mapper;

import com.hlvy.mybatis_plus.com.mybatis_plus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suaee.com.mybatis_plus.vo.UserVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heng
 * @since 2019-09-17
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询列表
     *
     * @param userVo 对象
     * @return Ipage
     */
    IPage<UserVo> selectUserVoPage(Page page,@Param("userVo") UserVo userVo);
}
