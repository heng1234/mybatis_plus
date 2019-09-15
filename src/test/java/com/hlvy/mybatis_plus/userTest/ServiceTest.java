package com.hlvy.mybatis_plus.userTest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.hlvy.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.mapper.UserMapper;
import com.hlvy.mybatis_plus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * UserTest
 *
 * @author heng
 * @date 2019/4/11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService userService;

    /**
     * 通用service
     * 查询所有user
     */
    @Test
    public void testSelect() {
        User user = userService.getById(5);
        System.out.println(user.toString());
    }

    /**
     * 通用service插入一行user
     */
    @Test
    public void insertUser() {
        System.out.println(("----- insertUser method test ------"));
        User user = new User();
        user.setName("恒果果");
        user.setAge(19);
        user.setEmail("xxx@qq.com");
        int row = userService.getBaseMapper().insert(user);
        System.out.println("插入成功"+row+"行");
    }
    /**
     * 通用service查询
     */
    @Test
    public void chain() {
      userService.lambdaQuery().gt(User::getAge,17).list().forEach(System.out::println);
    }
    /**
     * 通用service修改
     */
    @Test
    public void chain1() {
        System.out.println(userService.lambdaUpdate().eq(User::getId, 17).set(User::getAge, 17).update());
    }

    /**
     * 通用service删除
     */
    @Test
    public void chain2() {
        System.out.println(userService.lambdaUpdate().eq(User::getId, 17).remove());
    }
}