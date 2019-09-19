package com.hlvy.mybatis_plus.userTest;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.hlvy.mybatis_plus.MybatisPlusApplication;
import com.hlvy.mybatis_plus.config.MybatisPlusConfig;
import com.hlvy.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.mapper.UserMapper;
import org.junit.Assert;
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
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有user
     */
    @Test
    public void testSelect() {
        MybatisPlusConfig.myTableName.set("user2019");
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
      //  Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * 插入一行user
     * 执行时间50毫秒
     * Time：50 ms - ID：com.hlvy.mybatis_plus.mapper.UserMapper.selectById
     * Execute SQL：SELECT id,name,age,email,manager_id,created_time,update_time,version FROM User WHERE id=5 AND deleted=0
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
     * 根据id查询
     * 多租户测试
     *  Consume Time：13 ms 2019-09-17 22:53:39
     *  Execute SQL：SELECT id, name, age, email, manager_id, created_time, update_time, version FROM User WHERE User.manager_id = 5 AND id = 5 AND deleted = 0
     */
    @Test
    public void setectUserById() {
      User user = userMapper.selectById(5);
        System.out.println(user.toString());
    }
    /**
     * 构造查询 具体构造查询看mybatis_plus官网
     * 查询 包含恒果果 年龄等于19的
     */
    @Test
    public void selectWhere() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","恒果果");
        wrapper.apply("age = {0}",19);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(user -> System.out.println(user));
    }
    /**
     * 构造查询 具体构造查询看mybatis_plus官网
     * 查询 包含恒果果 and (年龄等于19的或者年龄等于20的)
     */
    @Test
    public void selectWhereTwo() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","恒果果").and(wq->wq.eq("age",19).or().eq("age",20));
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(user -> System.out.println(user));
    }
    /**
     * 构造查询 具体构造查询看mybatis_plus官网
     * 查询  (年龄等于19的或者年龄小于19) and 包含恒果果
     */
    @Test
    public void selectWhereThree() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.nested(wq->wq.eq("age",19).or().lt("age",19)).like("name","恒果果");
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(user -> System.out.println(user));
    }
    /**
     * 构造查询 具体构造查询看mybatis_plus官网
     * 查询  年龄包含19 20 21 22 23 24
     */
    @Test
    public void selectWhereFour() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        List list = new ArrayList();
        list.add(19);
        list.add(20);
        list.add(21);
        list.add(22);
        list.add(23);
        list.add(24);
        wrapper.in("age",list);
        List<User> listRes = userMapper.selectList(wrapper);
        listRes.forEach(user -> System.out.println(user));
    }

    /**
     * 构造查询 具体构造查询看mybatis_plus官网
     * 查询  年龄包含19 20 21 22 23 24
     * 返回一条
     */
    @Test
    public void selectWhereFive() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        List list = new ArrayList();
        list.add(19);
        list.add(20);
        list.add(21);
        list.add(22);
        list.add(23);
        list.add(24);
        /**
         * last 可能有注入风险 拼接sql 具体看官网
         */
        wrapper.in("age",list).orderByDesc("id").last("limit 1");
        List<User> listRes = userMapper.selectList(wrapper);
        listRes.forEach(user -> System.out.println(user));
    }

    /**
     * 构造查询 具体构造查询看mybatis_plus官网
     * 查询 包含恒果果 年龄等于19的
     * 查询特定几列
     */
    @Test
    public void selectSix() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","恒果果").select("id,name");
        wrapper.apply("age = {0}",19);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(user -> System.out.println(user));
    }

    /**
     * 构造查询 具体构造查询看mybatis_plus官网
     * 查询 包含恒果果 年龄等于19的
     * 排除不显示的列
     */
    @Test
    public void selectSeven() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","恒果果");
        wrapper.select(User.class,
                info->!info.getColumn().equals("age")&&
                !info.getColumn().equals("email"));
        wrapper.apply("age = {0}",19);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(user -> System.out.println(user));
    }

    /**
     * condition
     * 如果为空则不加入该条件查询
     */
    @Test
    public void selectEight() {
        String name = "";
        Integer age = 19;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(name),"name",name);

        wrapper.apply(age!=null,"age = {0}",age);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(user -> System.out.println(user));
    }


    /**
     * condition
     * 创建条件构造器传入实体对象
     * 实体与设置条件互不干扰
     * 默认等值如果改为like 需要在属性加上注解 @TableField(condition = SqlCondition.LIKE)
     * 或者自己定义 @TableField(condition ="%s=#{%s}")
     */
    @Test
    public void selectN() {
        String name = "恒果果";
        Integer age = 19;
        User user = new User();
        user.setName(name);
        user.setAge(age);
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);

        List<User> list = userMapper.selectList(wrapper);
        list.forEach(userN -> System.out.println(userN));
    }

    /**
     * allEq用法
     */
    @Test
    public void selectTen() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","恒果果");
        map.put("age",null);
        //wrapper.allEq(map,false);
        wrapper.allEq((k,v)->!k.equals("name"),map,false);//过滤值
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(userN -> System.out.println(userN));
    }

    /**
     * selectMaps
     */
    @Test
    public void selectTenOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","恒果果");
        map.put("age",null);
        //wrapper.allEq(map,false);
        wrapper.allEq((k,v)->!k.equals("name"),map,false);//过滤值
        List<Map<String ,Object>> list = userMapper.selectMaps(wrapper);
        list.forEach(userN -> System.out.println(userN));
    }

    /**
     *selectObjs 只返回一列
     */
    @Test
    public void selectTenTwo() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.select("name","sum(age) age").like("email","@qq.com").groupBy("name").having("age>17");//过滤值
        List<Object> list = userMapper.selectObjs(wrapper);
        list.forEach(userN -> System.out.println(userN));
    }

    /**
     *selectCount() 查询总记录数 不能设置列
     */
    @Test
    public void selectTenThree() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",17);//过滤值
       Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    /**
     *selectOne() 返回一条记录如果多条会报错
     */
    @Test
    public void selectTenFour() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",17).orderByDesc("id").last(" LIMIT 1");//过滤值
        User user = userMapper.selectOne(wrapper);
        System.out.println(user.toString());
    }


    /**
     *lambda条件构造器
     */
    @Test
    public void selectTenFive() {
        /**
         * 方式一
         */
    // LambdaQueryWrapper<User> lambda =  new  QueryWrapper<User>().lambda();
        /**
         * 方式二
         */
     //LambdaQueryWrapper<User> lambdaQueryWrapper =  new LambdaQueryWrapper<>();
        /**
         * 方式三
         */
       LambdaQueryWrapper<User> lambdaQueryWrapper1=  Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper1.like(User::getName,"恒果果").and(wq->wq.lt(User::getAge,40).or().isNotNull(User::getAge));
       List<User> list = userMapper.selectList(lambdaQueryWrapper1);
       list.forEach(System.out::println);
    }

    /**
     * 自定义方法
     */
    @Test
    public void selectTenSix() {

        LambdaQueryWrapper<User> lambdaQueryWrapper1=  Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper1.like(User::getName,"恒果果").and(wq->wq.lt(User::getAge,40).or().isNotNull(User::getAge));
        List<User> list = userMapper.selectAll(lambdaQueryWrapper1);
        list.forEach(System.out::println);
    }

    /**
     * 分页
     */
    @Test
    public void selectTensevenPage() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",17);
        Page<User> page = new Page<User>(1,2,false);//传入false不查询总记录数
      /*  IPage<User> iPage =  userMapper.selectPage(page,wrapper);
        System.out.println(iPage.getPages());
        System.out.println(iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);*/
        IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(page,wrapper);
        System.out.println(iPage.getPages());
        System.out.println(iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * 自定义分页
     */
    @Test
    public void selectTeneightPage() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",17);
        //Page<User> page =       new Page<User>(1,2,false);//传入false不查询总记录数
        Page<User> page = new Page<User>(1,2);//传入false不查询总记录数
        IPage<User> iPage = userMapper.selectUserPage(page,wrapper);
        System.out.println(iPage.getPages());
        System.out.println(iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * 修改
     */
    @Test
    public void updateOne() {
        User user = new User();
        User whereuser = new User();//出现在where中
        whereuser.setId(1L);
        // user.setId(1L);
        user.setAge(17);//在set中
        //userMapper.updateById(user);
        UpdateWrapper <User> userUpdateWrapper = new UpdateWrapper<>(whereuser);
        // userUpdateWrapper.eq("id",1);//条件在where
        userMapper.update(user,userUpdateWrapper);
    }

    /**
     * 修改
     */
    @Test
    public void updateTwo() {
        User whereuser = new User();//出现在where中
        whereuser.setId(1L);
        UpdateWrapper <User> userUpdateWrapper = new UpdateWrapper<>(whereuser);
        userUpdateWrapper.set("age",17);//出现在set中
        userMapper.update(null,userUpdateWrapper);
    }

    /**
     * 修改 lambda
     */
    @Test
    public void updateThree() {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.<User>lambdaUpdate();
        lambdaUpdateWrapper.eq(User::getId, 1L).set(User::getAge,17);

        userMapper.update(null,lambdaUpdateWrapper);
    }

    /**
     * 修改 lambda
     */
    @Test
    public void updateFour() {
      new LambdaUpdateChainWrapper<User>(userMapper)
                .eq(User::getId, 1L).set(User::getAge,17).update();

    }
    /**
     * 根据id删除
     */
    @Test
    public void deleteOne() {
        userMapper.deleteById(1);

    }
    /**
     * 其它普通删除 map条件删除
     */
    @Test
    public void deleteTwo() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",2);
        userMapper.deleteByMap(map);

    }
    /**
     * 其它普通删除 in删除
     */
    @Test
    public void deleteThree() {


        userMapper.deleteBatchIds(Arrays.asList(1,2));

    }
    /**
     * 条件构造器删除
     */
@Test
public void deleteFour() {
     LambdaQueryWrapper<User> wrapper =  Wrappers.<User>lambdaQuery();
    wrapper.eq(User::getId,1);
    userMapper.delete(wrapper);

    }
}