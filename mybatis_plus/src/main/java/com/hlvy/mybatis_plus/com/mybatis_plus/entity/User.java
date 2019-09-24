package com.hlvy.mybatis_plus.com.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
* <p>
* 
* </p>
*
* @author heng
* @since 2019-09-17
*/
@TableName("tb_user")
public class User extends Model<User> {

private static final long serialVersionUID = 1L;
    private Integer id;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
    * 用户邮箱(用户登录)
    */
@TableField("email")
    private String email;

    /**
    * 用户密码
    */
@TableField("password")
    private String password;

    /**
    * 手机号码(用于登录)
    */
@TableField("mobile_phone")
    private String mobilePhone;

    /**
    * 用户类型 0普通前台用户 1.机构用户 2.后台平台用户
    */
@TableField("type")
    private Integer type;

    /**
    * 创建时间
    */
@TableField("create_time")
    private LocalDateTime createTime;

    /**
    * 用户角色(多个用逗号隔开)
    */
@TableField("user_role")
    private String userRole;

    /**
    * 修改时间
    */
@TableField("update_time")
    private LocalDateTime updateTime;

    /**
    * 0 正常  1删除
    */
@TableField("is_deleted")
    private Integer isDeleted;

    /**
    * 部门名字
    */
@TableField("department")
    private String department;

    /**
    * 姓名
    */
@TableField("name")
    private String name;

    /**
    * 职位
    */
@TableField("position")
    private String position;

        public Integer getId() {
        return id;
        }

            public void setId(Integer id) {
        this.id = id;
        }
        public String getEmail() {
        return email;
        }

            public void setEmail(String email) {
        this.email = email;
        }
        public String getPassword() {
        return password;
        }

            public void setPassword(String password) {
        this.password = password;
        }
        public String getMobilePhone() {
        return mobilePhone;
        }

            public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        }
        public Integer getType() {
        return type;
        }

            public void setType(Integer type) {
        this.type = type;
        }
        public LocalDateTime getCreateTime() {
        return createTime;
        }

            public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        }
        public String getUserRole() {
        return userRole;
        }

            public void setUserRole(String userRole) {
        this.userRole = userRole;
        }
        public LocalDateTime getUpdateTime() {
        return updateTime;
        }

            public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        }
        public Integer getIsDeleted() {
        return isDeleted;
        }

            public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
        }
        public String getDepartment() {
        return department;
        }

            public void setDepartment(String department) {
        this.department = department;
        }
        public String getName() {
        return name;
        }

            public void setName(String name) {
        this.name = name;
        }
        public String getPosition() {
        return position;
        }

            public void setPosition(String position) {
        this.position = position;
        }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
    return "User{" +
            "id=" + id +
            ", email=" + email +
            ", password=" + password +
            ", mobilePhone=" + mobilePhone +
            ", type=" + type +
            ", createTime=" + createTime +
            ", userRole=" + userRole +
            ", updateTime=" + updateTime +
            ", isDeleted=" + isDeleted +
            ", department=" + department +
            ", name=" + name +
            ", position=" + position +
    "}";
    }
}
