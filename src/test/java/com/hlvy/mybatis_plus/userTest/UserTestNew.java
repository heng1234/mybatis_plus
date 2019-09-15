package com.hlvy.mybatis_plus.userTest;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hlvy.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.mapper.UserMapper;
import com.hlvy.mybatis_plus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * UserTestNew
 * mp进阶 逻辑删除
 * @author heng
 * @date 2019/4/11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestNew {

    @Autowired
    private UserMapper userMapper;
    /**
     * 逻辑删除演示
     * 执行sql UPDATE User SET deleted=1 WHERE id=1171800278613643266 AND deleted=0
     */
    @Test
    public void deleteByIdOne() {
        System.out.println(userMapper.deleteById(1171800278613643266L));
    }

    /**
     * 查询未删除的会自动带入 where deleted=0
     * 执行sql  SELECT id,name,age,email,manager_id,created_time,update_time,version,deleted FROM User WHERE deleted=0
     */
    @Test
    public void selectOne() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * 修改 会自动带入 where deleted=0
     * 执行sql  UPDATE User SET age=17 WHERE id=5 AND deleted=0
     */
    @Test
    public void updateOne() {
      User user = new User();
      user.setId(5L);
      user.setAge(17);
      System.out.println(userMapper.updateById(user));
    }

    /**
     * 查询全局排除deleted字段
     * 实体类 属性加入  @TableField(select = false)//查询的时候不显示
     */

    /**同执行上面select可以看到 自定义的语句不可以排除被删除的也可以查询出来*/
    /**
     * select * from user WHERE age > 17  不会带deleted=0
     * 可以自己加上 .eq(User::getDeleted,0)
     * select * from user WHERE age > 17 AND deleted = 0
     * 也可以写到sql只剩下安德文
     */
    @Test
    public void selectListOne() {
        List<User> list = userMapper.selectAll(Wrappers.<User>lambdaQuery()
                .gt(User::getAge,17).eq(User::getDeleted,0));
        list.forEach(System.out::println);
    }


}