package cn.yoyo.codegen;

import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.util.StrUtil;
import cn.yoyo.codegen.generator.*;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.generator.GeneratorFactory;
import com.zaxxer.hikari.HikariDataSource;

import java.nio.file.Path;

public class Table2DomainGenerator {

    private static String sourceDir;
    private static String basePackage;
    private static String schema;
    private static String table;
    private static String hasCurdApi;

    public static void main(String[] args) {
        String wsDir = System.getProperty("user.dir");
        System.out.println("工作目录为：" + wsDir);

        String moduleName = Utils.inputModuleName();
        String moduleType = StrUtil.splitToArray(moduleName, "-")[0];
        String moduleDir = Utils.getModuleDir(wsDir, moduleName, moduleType);
        sourceDir = moduleDir + "/src/main/java";

        String rootPkg = Utils.readProp("root", "请输入包名", "cn.yoyo");
        basePackage = rootPkg + "." + moduleName.replaceAll("-", ".");

        // 从配置文件或终端读取
        String dbHost = Utils.readProp("host", "请输入数据库地址", "");
        schema = Utils.readProp("schema", "请输入数据库名", "");
        table = Utils.readProp("table", "请输入表名", "");
        if (StrUtil.isEmpty(schema) || StrUtil.isEmpty(table)) {
            System.out.println("数据库名字和表名不能为空");
            return;
        }
        String username = Utils.readProp("username", "请输入数据库用户名", "");
        String password = Utils.readProp("password", "请输入数据库密码", "");
        hasCurdApi = Utils.inputString("是否生产CRUD Restful API", "Y");

        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://" + dbHost + "/" + schema + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        //创建配置内容
        GlobalConfig globalConfig = createGlobalConfigUseStyle2(table.split(","));

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle2(String[] tables) {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包

        globalConfig.getPackageConfig()
                .setSourceDir(sourceDir)
                .setBasePackage(basePackage)
                .setEntityPackage(basePackage + ".infra.repository.mybatis.dto")
                .setTableDefPackage(basePackage + ".infra.repository.mybatis.dto")
                .setMapperPackage(basePackage + ".infra.repository.mybatis.mapper")
                .setServiceImplPackage(basePackage + ".infra.repository")
                .setServicePackage(basePackage + ".domain.repository")
        ;

        //设置表前缀和只生成哪些表，setGenerateTable 未配置时，生成所有表
        globalConfig.getStrategyConfig()
                .setGenerateSchema(schema)
                .setTablePrefix("t_")
                .setGenerateTable(tables);

        //设置生成 entity.tpl 并启用 Lombok
        globalConfig.enableEntity()
                .setClassPrefix("T")
                .setWithLombok(true);
        globalConfig.enableTableDef()
                .setClassPrefix("T");

        //设置生成 mapper
        globalConfig.enableMapper()
                .setClassPrefix("T");

        GeneratorFactory.registerGenerator("ddd-entity", new DDDEntityGenerator());
        GeneratorFactory.registerGenerator("convert", new ConvertGenerator());
        GeneratorFactory.registerGenerator("ddd-biz", new DDDBizGenerator());
        GeneratorFactory.registerGenerator("ddd-repo", new DDDRepositoryGenerator());
        GeneratorFactory.registerGenerator("ddd-repo-impl", new DDDRepositoryImplGenerator());

        if ("Y".equalsIgnoreCase(hasCurdApi)) {

            String rootPkg = Utils.readProp("root", "请输入包名", "cn.yoyo");
            String apiModulePath = Utils.inputString("请输入API模块名", "facade-admin");
            String apiUrl = Utils.inputString("请输入API接口地址", "/api");
            apiModulePath = apiModulePath.split("-")[0] + "/" + apiModulePath;
            Path p = Path.of(apiModulePath);
            if (!PathUtil.exists(p, true) || !PathUtil.isDirectory(p)) {
                System.out.println("API模块不存在");
                System.exit(1);
            }

            GeneratorFactory.registerGenerator("controller", new DDDApiGenerator(rootPkg, apiModulePath, apiUrl));
        }

        //可以单独配置某个列
//        ColumnConfig columnConfig = new ColumnConfig();
//        columnConfig.setColumnName("tenant_id");
//        columnConfig.setLarge(true);
//        columnConfig.setVersion(true);
//        globalConfig.getStrategyConfig()
//                .setColumnConfig("account", columnConfig);

        return globalConfig;
    }
}
