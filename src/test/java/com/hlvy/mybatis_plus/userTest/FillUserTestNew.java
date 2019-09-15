package com.hlvy.mybatis_plus.userTest;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hlvy.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * UserTestNew
 * mp进阶 自动填充
 * @author heng
 * @date 2019/4/11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FillUserTestNew {

    @Autowired
    private UserMapper userMapper;
    /**
     * 插入一行user      自动填充
     * sql INSERT INTO User ( id, name, age, email, created_time ) VALUES ( 1172866953920393218, '恒果果', 19, 'xxx@qq.com', '2019-09-14 13:37:27.29' )
     */
    @Test
    public void insertUser() {
        System.out.println(("----- insertUser method test ------"));
        User user = new User();
        user.setName("恒果果");
        user.setAge(19);
        user.setEmail("xxx@qq.com");
        int row = userMapper.insert(user);
        System.out.println("插入成功"+row+"行");
    }

    /**
     * 修改 自动填充
     * 执行sql UPDATE User SET age=17, update_time='2019-09-14 13:26:30.305' WHERE id=5 AND deleted=0
     */
    @Test
    public void updateOne() {
      User user = new User();
      user.setId(5L);
      user.setAge(17);
      System.out.println(userMapper.updateById(user));
    }




}