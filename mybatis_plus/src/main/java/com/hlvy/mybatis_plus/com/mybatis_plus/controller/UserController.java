package com.hlvy.mybatis_plus.com.mybatis_plus.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.suaee.com.mybatis_plus.service.IUserService;
import com.suaee.common.core.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suaee.com.mybatis_plus.vo.UserVo;
import com.hlvy.mybatis_plus.com.mybatis_plus.entity.User;


/**
* <p>
*  前端控制器
* </p>
*
* @author heng
* @since 2019-09-17
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    protected IUserService iUserService;

    /**
     * 获取详情数据
     *
     * @param  id 主键id
     * @return 详情数据
     */
    @GetMapping(value = "/{id}")
    public R info(@PathVariable("id") Integer id) {
        User user = iUserService.getUserById(id);
        return new R<>(user);
    }

    /**
     * 根据id删除数据
     *
     * @param id 主键id
     * @return 返回删除结果
     */
    @DeleteMapping(value = "/{id}")
    public R delete(@PathVariable("id") Integer id) {
        boolean isSucces = iUserService.deleteUserById(id);
        return new R<>(isSucces);
    }

    /**
    * 保存或者修改数据
    *
    * @param user 数据
    * @return 返回保存结果
    */
    @PostMapping(value = "/saveOrUpdate")
    public R saveOrUpdate(User user) {
        return iUserService.saveOrUpdateUser(user);
    }

    /**
     * 查询列表
     *
     * @return R 返回列表结果
     */
    @PostMapping(value = "/list")
    public R list(UserVo userVo, Page page) {
         IPage<UserVo> pageData = iUserService.selectUserVoPage(page, userVo);
         return new R<>(pageData);
    }

 }
