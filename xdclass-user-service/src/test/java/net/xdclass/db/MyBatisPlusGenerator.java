//package net.xdclass.db;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
///**
// * @author 程序狗没有春天
// * @version 1.00
// * @createTime 2024/10/4 21:35
// * 暂时只关注有TODO的地方
// */
//public class MyBatisPlusGenerator {
//    public static void main(String[] args) {
//        //1. 全局配置
//        GlobalConfig config = new GlobalConfig();
//        // 是否支持AR模式
//        config.setActiveRecord(true)
//                // 作者
//                .setAuthor("程序狗没有春天")
//                // 生成路径，最好使用绝对路径，window路径是不一样的  然后这里要cd进入你需要创建实体类的那个module里面去 然后pwd 查看绝对路径
//                //TODO  TODO  TODO  TODO
//                .setOutputDir("..\\main\\java")
//                // 文件覆盖 是否
//                .setFileOverride(true)
//                // 主键策略
//                .setIdType(IdType.AUTO)
//                .setDateType(DateType.ONLY_DATE)
//                // 设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
//                .setServiceName("%sService")
//                //实体类结尾名称
//                .setEntityName("%sDO")
//                //生成基本的resultMap
//                .setBaseResultMap(true)
//                //不使用AR模式
//                .setActiveRecord(false)
//                //生成基本的SQL片段
//                .setBaseColumnList(true);
//        //2. 数据源配置
//        DataSourceConfig dsConfig = new DataSourceConfig();
//        // 设置数据库类型
//        dsConfig.setDbType(DbType.MYSQL)
//                .setDriverName("com.mysql.cj.jdbc.Driver")
//                //TODO  TODO  TODO  TODO
//                .setUrl("jdbc:mysql://127.0.0.1:3306/xdclass_user?useSSL=false")
//                .setUsername("root")
//                .setPassword("123456");
//        //3. 策略配置globalConfiguration中
//        StrategyConfig stConfig = new StrategyConfig();
//        //全局大写命名
//        stConfig.setCapitalMode(true)
//                // 数据库表映射到实体的命名策略
//                .setNaming(NamingStrategy.underline_to_camel)
//                //使用lombok
//                .setEntityLombokModel(true)
//                //使用restcontroller注解
//                .setRestControllerStyle(true)
//                // 生成的表, 支持多表一起生成，以数组形式填写
//                //TODO  TODO  TODO  TODO
//                .setInclude("user", "address");
//        //4. 包名策略配置
//        PackageConfig pkConfig = new PackageConfig();
//        pkConfig.setParent("net.xdclass")
//                .setMapper("mapper")
//                .setService("service")
//                .setController("controller")
//                .setEntity("model")
//                .setXml("mapper");
//        //5. 整合配置
//        AutoGenerator ag = new AutoGenerator();
//        ag.setGlobalConfig(config)
//                .setDataSource(dsConfig)
//                .setStrategy(stConfig)
//                .setPackageInfo(pkConfig);
//        //6. 执行操作
//        ag.execute();
//        System.out.println("======= 小滴课堂 Done 相关代码生成完毕  ========");
//    }
//}
