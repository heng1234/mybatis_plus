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
import com.hlvy.mybatis_plus.config.MybatisPlusConfig;
import com.hlvy.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.mapper.UserMapper;
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
public class InjectorTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 自定义sql注入器删除所有
     */
    @Test
    public void deleteAll() {
        System.out.println(userMapper.deleteAll());

    }
    /**
     * 自定义inser 选装器
     * 谨慎用 值在mysql测试过切mysql默认值不会填写
     * Consume Time：10 ms 2019-09-19 23:47:14
     *  Execute SQL：INSERT INTO User ( id, name, age, email, created_time ) VALUES ( 1174711555019276289, '恒果果', 19, 'xxx@qq.com', '2019-09-19T23:47:14.468+0800' )
     */
    @Test
    public void inserBath() {
        User user = new User();
        user.setName("恒果果");
        user.setAge(19);
        user.setEmail("xxx@qq.com");
        user.setManagerId(1L);
        User user2 = new User();
        user2.setName("恒果果2");
        user2.setAge(19);
        user2.setEmail("xxx@qq.com");
        user.setManagerId(1L);
        userMapper.insertBatchSomeColumn( Arrays.asList(user,user2));

    }

    /**
     * 根据id删除并自动填充 LogicDeleteByIdWithFill
     *  sql Consume Time：73 ms 2019-09-19 23:59:04
     *  Execute SQL：UPDATE User SET age=17,update_time='2019-09-19T23:59:04.167+0800',deleted=1 WHERE id=5 AND deleted=0
     */
    @Test
    public void deleteByIdWithFill() {
        User user = new User();
        user.setId(5L);
        user.setAge(17);//需要在删除的时候填充字段  需要在实体类加上上自动填充注解    @TableField(fill = FieldFill.UPDATE)
        System.out.println(userMapper.deleteByIdWithFill(user));

    }


    /**
     * 根据id更新固定的那几个字段不包含逻辑删除 不更新name 没有值会更新为空
     * UPDATE User SET age=17,update_time='2019-09-20T00:06:04.856+0800',deleted=1 WHERE id=1 AND deleted=0
     */
    @Test
    public void alwaysUpdateSomeColumnById() {
        User user = new User();
        user.setId(1L);
        user.setName("xxx");
        user.setAge(17);
        System.out.println(userMapper.deleteByIdWithFill(user));

    }
}