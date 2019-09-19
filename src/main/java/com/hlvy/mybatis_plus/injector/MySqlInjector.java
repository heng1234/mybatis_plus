package com.hlvy.mybatis_plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.hlvy.mybatis_plus.method.DeleteAllMethod;

import java.util.List;

/**
 * MySqlInjector
 * 自定义sql注入器
 * @author heng
 * @date 2019/9/19
 **/

public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //拿到父类的getMethodList方法
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAllMethod());
        //InsertBatchSomeColumn 选装器 谨慎用 值在mysql测试过切mysql默认值不会填写
        methodList.add(new InsertBatchSomeColumn(t->!t.isLogicDelete()&& !t.getColumn().equals("update_time")));//排除插入字段
       //根据id删除并自动填充 LogicDeleteByIdWithFill
        methodList.add(new LogicDeleteByIdWithFill());
        //根据id更新固定的那几个字段不包含逻辑删除 不更新name
        methodList.add(new AlwaysUpdateSomeColumnById(t->!t.getColumn().equals("name")));
       // methodList.add(new MyInsertAll());
       // methodList.add(new MysqlInsertAllBatch());
        return methodList;
    }
}
