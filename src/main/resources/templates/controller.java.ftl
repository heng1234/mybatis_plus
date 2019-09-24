package ${package.Controller};

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
<#if restControllerStyle>import org.springframework.web.bind.annotation.RestController;<#else>import org.springframework.stereotype.Controller;</#if>
import com.suaee.${package.ModuleName}.service.I${entity}Service;
import com.suaee.common.core.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suaee.${package.ModuleName}.vo.${entity}Vo;
import ${package.Entity}.${entity};


/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>@RestController<#else>@RestController</#if>
@RequestMapping("<#if package.ModuleName??>/${'${entity}' ?uncap_first}</#if><#if controllerMappingHyphenStyle??><#else></#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else><#if superControllerClass??>public class ${table.controllerName} {<#else>public class ${table.controllerName} {</#if>
    @Autowired
    protected ${table.serviceName} ${'${table.serviceName}' ?uncap_first};

    /**
     * 获取${table.comment!}详情数据
     *
     * @param  id 主键id
     * @return ${table.comment!}详情数据
     */
    @GetMapping(value = "/{id}")
    public R info(@PathVariable("id") Integer id) {
        ${entity} ${'${entity}' ?uncap_first} = ${'${table.serviceName}' ?uncap_first}.get${entity}ById(id);
        return new R<>(${'${entity}' ?uncap_first});
    }

    /**
     * 根据id删除${table.comment!}数据
     *
     * @param id 主键id
     * @return 返回删除结果
     */
    @DeleteMapping(value = "/{id}")
    public R delete(@PathVariable("id") Integer id) {
        boolean isSucces = ${'${table.serviceName}' ?uncap_first}.delete${entity}ById(id);
        return new R<>(isSucces);
    }

    /**
    * 保存或者修改数据
    *
    * @param ${'${entity}' ?uncap_first} 数据
    * @return 返回保存结果
    */
    @PostMapping(value = "/saveOrUpdate")
    public R saveOrUpdate(${entity} ${'${entity}' ?uncap_first}) {
        return ${'${table.serviceName}' ?uncap_first}.saveOrUpdate${entity}(${'${entity}' ?uncap_first});
    }

    /**
     * 查询${table.comment!}列表
     *
     * @return R 返回列表结果
     */
    @PostMapping(value = "/list")
    public R list(${entity}Vo ${'${entity}' ?uncap_first}Vo, Page page) {
         IPage<${entity}Vo> pageData = ${'${table.serviceName}' ?uncap_first}.select${entity}VoPage(page, ${'${entity}' ?uncap_first}Vo);
         return new R<>(pageData);
    }

 }
</#if>
