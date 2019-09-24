package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import com.suaee.common.core.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suaee.${package.ModuleName}.vo.${entity}Vo;


/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    @Resource
    private ${entity}Mapper ${'${entity}' ?uncap_first}Mapper;

    /**
     * 根据id进行查询
     *
     * @param id
     * @return ${entity}
     */
    @Override
    public ${entity} get${entity}ById(Integer id) {
        if (id == null) {
            return null;
        }
        return baseMapper.selectById(id);
    }

    /**
     * 根据id进行删除
     *
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean delete${entity}ById(Integer id) {
        if(id == null) {
           return false;
        }
        ${entity} ${'${entity}' ?uncap_first} = new ${entity}();
        ${'${entity}' ?uncap_first}.setId(id);
        ${'${entity}' ?uncap_first}.setIsDeleted(1);
        Integer result = baseMapper.updateById(${'${entity}' ?uncap_first});
        if(result > 0){
           return true;
        }
        return false;
    }

    /**
     * 保存数据
     *
     * @param ${'${entity}' ?uncap_first} 对象
     * @return Boolean
     */
    @Override
    public Boolean save${entity}(${entity} ${'${entity}' ?uncap_first}) {
        ${'${entity}' ?uncap_first}.setIsDeleted(0);
        ${'${entity}' ?uncap_first}.setCreateTime(LocalDateTime.now());
        Integer result = baseMapper.insert(${'${entity}' ?uncap_first});
        if(result > 0){
           return true;
        }
        return false;
    }

    /**
     * 更新数据
     *
     * @param ${'${entity}' ?uncap_first} 对象
     * @return Boolean
     */
    @Override
    public Boolean update${entity}(${entity} ${'${entity}' ?uncap_first}) {
        ${'${entity}' ?uncap_first}.setUpdateTime(LocalDateTime.now());
        Integer result = baseMapper.updateById(${'${entity}' ?uncap_first});
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 保存或者修改数据
     *
     * @param ${'${entity}' ?uncap_first}
     * @return R
     */
    @Override
    public R saveOrUpdate${entity}(${entity} ${'${entity}' ?uncap_first}) {
        Integer id = ${'${entity}' ?uncap_first}.getId();
        boolean flag = false;
        if ( id == null){
            flag = save${entity}(${'${entity}' ?uncap_first});
        } else {
           flag = update${entity}(${'${entity}' ?uncap_first});
        }
        return new R(flag, "result");
    }

    /**
     * 查询列表
     *
     * @param ${'${entity}' ?uncap_first}Vo 对象
     * @return Ipage
     */
    @Override
    public IPage<${entity}Vo> select${entity}VoPage(Page page, ${entity}Vo ${'${entity}' ?uncap_first}Vo) {
        return  ${'${entity}' ?uncap_first}Mapper.select${entity}VoPage(page, ${'${entity}' ?uncap_first}Vo);
     }
}
</#if>
