package com.joe.rbac.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Objects;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
        //指定包名
        String packageName = "com.joe.rbac";
        //指定生成的表名
        String[] tableNames = new String[]{"sys_dept", "sys_dict", "sys_job", "sys_log", "sys_menu",
                "sys_role", "sys_role_dept", "sys_role_menu", "sys_user", "sys_user_role"};

        new MybatisPlusGenerator().generateByTables(packageName, tableNames);
    }


    /**
     * 根据表自动生成
     *
     * @param packageName 包名
     * @param tableNames  表名
     */
    private void generateByTables(String packageName, String... tableNames) {
        // 配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        // 全局变量配置
        GlobalConfig globalConfig = getGlobalConfig();
        // 包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);
        // 自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig);
    }

    /**
     * 集成
     *
     * @param dataSourceConfig 配置数据源
     * @param strategyConfig   策略配置
     * @param config           全局变量配置
     * @param packageConfig    包名配置
     */
    private void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig config, PackageConfig packageConfig) {
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @return PackageConfig 包名配置
     */
    private PackageConfig getPackageConfig(String packageName) {
        return new PackageConfig()
                .setParent(packageName)
                .setController("controller")
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl");
    }

    /**
     * 全局配置
     *
     * @return GlobalConfig
     */
    private GlobalConfig getGlobalConfig() {
        return new GlobalConfig()
                .setOutputDir(getOutputDir("rbac"))
                .setOpen(false)
                .setFileOverride(true)
                //生成基本的resultMap
                .setBaseResultMap(true)
                //生成基本的SQL片段
                .setBaseColumnList(false)
                // 作者
                .setAuthor("joe");
    }

    /**
     * 返回项目路径
     *
     * @param projectName 项目名
     * @return 项目路径
     */
    private String getOutputDir(String projectName) {
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
        int index = path.indexOf(projectName);
        return "/"+path.substring(1, index) + projectName + "/src/main/java/";
    }


    /**
     * 策略配置
     *
     * @param tableNames 表名
     * @return StrategyConfig
     */
    private StrategyConfig getStrategyConfig(String... tableNames) {

        return new StrategyConfig()
                .setEntityLombokModel(true)
//                .setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model")
                .setNaming(NamingStrategy.underline_to_camel)
                //需要生成的的表名，多个表名传数组
                .setInclude(tableNames)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true);
    }

    /**
     * 配置数据源
     *
     * @return 数据源配置 DataSourceConfig
     */
    private DataSourceConfig getDataSourceConfig() {
        String dbUrl = "jdbc:mysql://localhost:3306/joe_rbac?serverTimezone=GMT%2B8";
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("admin123456")
                .setDriverName("com.mysql.jdbc.Driver");
    }
}
