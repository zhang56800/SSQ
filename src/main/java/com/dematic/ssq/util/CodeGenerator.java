package com.dematic.ssq.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */


    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        //构建一个代码自动生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 1、创建全局配置类的对象
        GlobalConfig gc = new GlobalConfig();
        //获取当前项目路径
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath = " + projectPath);
        //自动生成代码存放的路径
        gc.setOutputDir(projectPath + "/src/main/java");
        //设置 --作者注释
        gc.setAuthor("zhang,haoxiang");
        //是否打开文件夹
        gc.setOpen(false);
        //是否覆盖已有文件
        gc.setFileOverride(false);
        //各层文件名称方式，例如： %sAction 生成 UserAction  %s占位符
        gc.setServiceName("%sService");
        //设置日期策略  date类型
        gc.setDateType(DateType.ONLY_DATE);
        //设置主键策略 雪花算法
        gc.setIdType(IdType.ASSIGN_ID);
        //设置开启 swagger2 模式
        gc.setSwagger2(true);
        //把全局配置放入代码生成器
        mpg.setGlobalConfig(gc);

        // 2、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:sqlserver://127.0.0.1:1433;databaseName=SSQ");
        dsc.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dsc.setUsername("SSQ");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.SQL_SERVER);
        mpg.setDataSource(dsc); //把数据源配置加入到代码生成器

        // 3、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dematic.ssq");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        // ...  有默认值，点击查看源码
        mpg.setPackageInfo(pc);//包加入代码生成器

        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        //下划线转驼峰命名  表
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 下划线转驼峰命名字段
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //实体类是否加上lombok注解
        strategy.setEntityLombokModel(false);
        //控制层采用RestControllerStyle注解
        strategy.setRestControllerStyle(true);
        // RequestMapping中 驼峰转连字符 -
        strategy.setControllerMappingHyphenStyle(true);
        //要映射的数据库表名  （重点）
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //添加表名前缀
        //strategy.setTablePrefix("m_"); //自动拼接上m_
        //逻辑删除字段名
        strategy.setLogicDeleteFieldName("deleted");
        //乐观锁字段名
        strategy.setVersionFieldName("version");
        // -------自动填充策略
        ArrayList<TableFill> fillList = new ArrayList<>();
        fillList.add(new TableFill("createTime", FieldFill.INSERT));
        fillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));
        // 参数是 List<TableFill> 的链表
        strategy.setTableFillList(fillList);
        mpg.setStrategy(strategy);

        //---------------------------------
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
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
            //输出了 静态资源下的 Mapper
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //        FreemarkerTemplateEngine模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
