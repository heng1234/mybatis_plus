package com.hlvy.mybatis_plus.generator;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CodeGenerator
 *
 * @author heng
 * @date 2019/4/11
 **/
public class CodeGenerator {


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        /*注意！如果您选择了非默认引擎，需要在 AutoGenerator 中 设置模板引擎。*/
        // set freemarker engine
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());



        // 全局配置
        GlobalConfig gc = new GlobalConfig();
      //  String projectPath = System.getProperty("user.dir");
        System.err.println(System.getProperty("user.dir")+"--------------");
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/mybatis_plus/src/main/java");//生成文件的输出目录
        //是否在xml中添加二级缓存配置
        gc.setAuthor("heng");//开发人员
        gc.setFileOverride(false);//是否覆盖已有文件
        gc.setOpen(true);//是否打开输出目录
        gc.setEnableCache(true);//是否在xml中添加二级缓存配置
        gc.setSwagger2(false); //实体属性 Swagger2 注解
        gc.setKotlin(false);// Kotlin 模式
        gc.setActiveRecord(true);//开启 ActiveRecord 模式
        gc.setBaseResultMap(true);//开启 BaseResultMap
        gc.setSwagger2(false);//是否开启swagger2配置
        gc.setIdType(IdType.AUTO);//主键自动增长
        gc.setBaseColumnList(false);//开启 baseColumnList
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //数据库连接
        dsc.setUrl("jdbc:mysql://121.40.179.66:3306/suaee?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");//驱动
        dsc.setUsername("root");//用户名
        dsc.setPassword("b9b8-4231-8b38-c395269a93be");//密码
        dsc.setDbType(DbType.MYSQL);//数据库类型
//        类型转换
//        默认由 dbType 类型决定选择对应数据库内置实现
//        实现 ITypeConvert 接口自定义数据库 字段类型 转换为自己需要的 java 类型，内置转换类型无法满足可实现 IColumnType 接口自定义
        dsc.setTypeConvert(new MySqlTypeConvert(){
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType){
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return  super.processTypeConvert(globalConfig,fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名:"));//模块名moduleName
        pc.setController("controller");//controller 控制器模板
        pc.setService("service");//Service 类模板
        pc.setServiceImpl("service.impl");//Service impl 实现类模板
        pc.setEntity("entity");//Java 实体类模板
        pc.setMapper("mapper");//mapper 模板
        pc.setParent("com.hlvy.mybatis_plus");//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setXml("mapperxml");//xml
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
             /*   Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);*/
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return   projectPath+"/mybatis_plus/src/main/java/generator/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper"+StringPool.DOT_XML;
            }
        });
        // 自定义  xxAdd.html 生成
        focList.add(new FileOutConfig("/templates/vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath+"/mybatis_plus/src/main/java/generator/entity/vo/"+pc.getModuleName()+"/vo/" + tableInfo.getEntityName() + "Vo.java";
            }
        });


      /*  cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("E:/mybatigenrator");
                return false;
            }
        });*/
       cfg.setFileOutConfigList(focList);
       mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false);// 全局大写命名 ORACLE 注意
        strategy.setSkipView(true);//是否跳过视图
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        //strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setTablePrefix(new String[]{"tb_"});//表前缀
        //strategy.setFieldPrefix(new String[]{});//字段前缀

        //strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");//自定义继承的Entity类全称，带包名
        strategy.setEntityLombokModel(false);//【实体】是否为lombok模型（默认 false）
        strategy.setRestControllerStyle(true);//Boolean类型字段是否移除is前缀（默认 false）
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");//自定义继承的Controller类全称，带包名
        strategy.setInclude(scanner("person").split(","));//需要包含的表名，允许正则表达式（与exclude二选一配置）
        //strategy.setSuperEntityColumns("id");//自定义基础的Entity类，公共字段
        strategy.setControllerMappingHyphenStyle(true);//驼峰转连字符
        strategy.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
       // strategy.setVersionFieldName("version");//乐观锁名称
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
