package com.hlvy.mybatis_plus.userTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hlvy.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * UserTestNew
 * mp进阶 乐观锁
 * @author heng
 * @date 2019/4/11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OptTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * 修改 乐观锁
     * 执行sql UPDATE User SET age=17, update_time='2019-09-15 11:35:01.144', version=2 WHERE id=5 AND version=1 AND deleted=0
     */
      @Test
    public void updateOne() {
        int vsrsion = 1;
        User user = new User();
        user.setId(5L);
        user.setAge(17);
        user.setVersion(vsrsion);
        System.out.println(userMapper.updateById(user));
    }

    /**
     * 乐观锁 queryWrapper对象不能复用 会导致条件出错
     * sql UPDATE User SET age=17, update_time='2019-09-15 11:42:33.317', version=3 WHERE deleted=0 AND id = 3 AND version = 2
     */
    @Test
    public void updateTwo() {
        int vsrsion = 2;
        User user = new User();
        user.setAge(17);
        user.setVersion(vsrsion);
        QueryWrapper<User> queryWrapper = Wrappers.<User>query();
        queryWrapper.eq("id",3);
        System.out.println(userMapper.update(user,queryWrapper));
        /**
         * 下面是复用代码示例  不能复用
         *  sql UPDATE User SET age=19, update_time='2019-09-15 11:42:33.317', version=4 WHERE deleted=0 AND id = 3 AND version = 2 AND id = 3 AND version = 3
         */
      /*  user.setVersion(3);
        user.setAge(19);
        queryWrapper.eq("id",3);
        System.out.println(userMapper.update(user,queryWrapper));*/
    }



}