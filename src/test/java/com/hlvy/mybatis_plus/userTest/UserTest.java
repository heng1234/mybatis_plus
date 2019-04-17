package com.hlvy.mybatis_plus.userTest;

import com.hlvy.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * UserTest
 *
 * @author heng
 * @date 2019/4/11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
