package com.hlvy.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * User
 *
 * @author heng
 * @date 2019/4/11
 **/
@Data
@TableName("User")
public class User {

    @TableId(value = "id",type = IdType.NONE)
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
