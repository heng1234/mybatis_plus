package com.hlvy.mybatis_plus.comporent;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * MetaObjectHandler
 * 自动填充工具类
 * @author heng
 * @date 2019/9/14
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
private static final Logger LOGGER= LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        //是否存在set方法
      boolean bol =   metaObject.hasSetter("createdTime");
      //拿到createdTime的值
        Object createdTime = getFieldValByName("createdTime", metaObject);
        //插入时填充创建时间 fieldName是属性名
        LOGGER.info("insert 自动填充 "+ LocalDateTime.now());
       // setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
        if(bol){//有set方法
        if (createdTime == null) {//值为null填充
            setFieldValByName("createdTime", new Date(), metaObject);
         }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //是否存在set方法
        boolean bol =   metaObject.hasSetter("updateTime");
        //拿到updateTime的值
        Object updateTime = getFieldValByName("updateTime", metaObject);
        //修改时填充当前时间  fieldName是属性名
        LOGGER.info("update 自动填充 "+ LocalDateTime.now());
        //setUpdateFieldValByName("updateTime", LocalDateTime.now(),metaObject);
       if (bol){//有set方法
        if (updateTime == null) {//值为null填充
            setFieldValByName("updateTime", new Date(), metaObject);
        }
       }
    }
}
