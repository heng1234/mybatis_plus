package com.hlvy.mybatis_plus.com.mybatis_plus.service.impl;

import com.hlvy.mybatis_plus.com.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.com.mybatis_plus.mapper.UserMapper;
import com.hlvy.mybatis_plus.com.mybatis_plus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import com.suaee.common.core.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suaee.com.mybatis_plus.vo.UserVo;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heng
 * @since 2019-09-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 根据id进行查询
     *
     * @param id
     * @return User
     */
    @Override
    public User getUserById(Integer id) {
        if (id == null) {
            return null;
        }
        return baseMapper.selectById(id);
    }

    /**
     * 根据id进行删除
     *
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean deleteUserById(Integer id) {
        if(id == null) {
           return false;
        }
        User user = new User();
        user.setId(id);
        user.setIsDeleted(1);
        Integer result = baseMapper.updateById(user);
        if(result > 0){
           return true;
        }
        return false;
    }

    /**
     * 保存数据
     *
     * @param user 对象
     * @return Boolean
     */
    @Override
    public Boolean saveUser(User user) {
        user.setIsDeleted(0);
        user.setCreateTime(LocalDateTime.now());
        Integer result = baseMapper.insert(user);
        if(result > 0){
           return true;
        }
        return false;
    }

    /**
     * 更新数据
     *
     * @param user 对象
     * @return Boolean
     */
    @Override
    public Boolean updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        Integer result = baseMapper.updateById(user);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 保存或者修改数据
     *
     * @param user
     * @return R
     */
    @Override
    public R saveOrUpdateUser(User user) {
        Integer id = user.getId();
        boolean flag = false;
        if ( id == null){
            flag = saveUser(user);
        } else {
           flag = updateUser(user);
        }
        return new R(flag, "result");
    }

    /**
     * 查询列表
     *
     * @param userVo 对象
     * @return Ipage
     */
    @Override
    public IPage<UserVo> selectUserVoPage(Page page, UserVo userVo) {
        return  userMapper.selectUserVoPage(page, userVo);
     }
}
