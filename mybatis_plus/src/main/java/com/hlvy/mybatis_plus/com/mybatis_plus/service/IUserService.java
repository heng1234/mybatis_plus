package com.hlvy.mybatis_plus.com.mybatis_plus.service;

import com.hlvy.mybatis_plus.com.mybatis_plus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suaee.common.core.util.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suaee.com.mybatis_plus.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heng
 * @since 2019-09-17
 */
public interface IUserService extends IService<User> {

    /**
     * 根据id进行查询
     *
     * @param id
     * @return User
     */
   User getUserById(Integer id);

    /**
     * 根据id进行删除
     *
     * @param id
     * @return Boolean
     */
    Boolean deleteUserById(Integer id);

    /**
     * 保存数据
     *
     * @param user 对象
     * @return Boolean
     */
    Boolean saveUser(User user);

    /**
     * 修改数据
     *
     * @param user 对象
     * @return success/fail
     */
    Boolean updateUser(User user);

    /**
     * 保存或者修改数据
     *
     * @param user
     * @return R
     */
    R saveOrUpdateUser(User user);

    /**
     * 查询列表
     *
     * @param userVo 对象
     * @return Ipage
     */
    IPage<UserVo> selectUserVoPage(Page page,UserVo userVo);
  }
