package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suaee.common.core.util.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suaee.${package.ModuleName}.vo.${entity}Vo;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 根据id进行查询
     *
     * @param id
     * @return ${entity}
     */
   ${entity} get${entity}ById(Integer id);

    /**
     * 根据id进行删除
     *
     * @param id
     * @return Boolean
     */
    Boolean delete${entity}ById(Integer id);

    /**
     * 保存数据
     *
     * @param ${'${entity}' ?uncap_first} 对象
     * @return Boolean
     */
    Boolean save${entity}(${entity} ${'${entity}' ?uncap_first});

    /**
     * 修改数据
     *
     * @param ${'${entity}' ?uncap_first} 对象
     * @return success/fail
     */
    Boolean update${entity}(${entity} ${'${entity}' ?uncap_first});

    /**
     * 保存或者修改数据
     *
     * @param ${'${entity}' ?uncap_first}
     * @return R
     */
    R saveOrUpdate${entity}(${entity} ${'${entity}' ?uncap_first});

    /**
     * 查询列表
     *
     * @param ${'${entity}' ?uncap_first}Vo 对象
     * @return Ipage
     */
    IPage<${entity}Vo> select${entity}VoPage(Page page,${entity}Vo ${'${entity}' ?uncap_first}Vo);
  }
</#if>
