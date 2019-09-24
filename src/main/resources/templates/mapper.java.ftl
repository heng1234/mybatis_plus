package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suaee.${package.ModuleName}.vo.${entity}Vo;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    /**
     * 查询列表
     *
     * @param ${'${entity}' ?uncap_first}Vo 对象
     * @return Ipage
     */
    IPage<${entity}Vo> select${entity}VoPage(Page page,@Param("${'${entity}' ?uncap_first}Vo") ${entity}Vo ${'${entity}' ?uncap_first}Vo);
}
</#if>
