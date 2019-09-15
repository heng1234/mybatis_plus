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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * ARTest
 * 直接操作实体类
 * @author heng
 * @date 2019/4/11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {
    /**
     * 操作实体类进行crud
     */
    @Test
    public void insertUser() {

        System.out.println(("----- insertUser method test ------"));
        User user = new User();
        user.setName("AR恒果果");
        user.setAge(19);
        user.setEmail("xxx@qq.com");
        boolean bol = user.insert();
        System.out.println("插入是否成功_"+bol);
    }

    /**
     * 查询
     */
    @Test
    public void selectByID() {

        System.out.println(("----- insertUser method test ------"));
        User user = new User();
        user.setId(5L);
       // User user1 = user.selectById(7);
        User user1 = user.selectById();
        System.out.println(user1.toString());
    }

    /**
     * update
     */
    @Test
    public void updateUser() {

        User user = new User();
        user.setId(5L);
        user.setAge(17);
        // User user1 = user.selectById(7);
     user.updateById();
    }


    /**
     * delete
     */
    @Test
    public void deleteUser() {

        User user = new User();
        user.setId(1L);
        user.deleteById();
    }

    /**
     * insertOrUpdate
     * 会进行一次查询
     * id为空不会查询
     * id为空或者不存在执行insert否则执行update
     */
    @Test
    public void insertOrUpdateUser() {

        User user = new User();
        user.setId(5L);
        user.setAge(17);
        user.insertOrUpdate();
    }
}