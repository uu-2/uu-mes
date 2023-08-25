package cn.yoyo.codegen;

public class Guide {
    public static void main(String[] args) {
        String wsDir = System.getProperty("user.dir");
        System.out.println("工作目录为：" + wsDir);
        System.out.println("""
                1. 生成模块
                2. 通过数据库表生成领域
                3. 生成空领域和Mapper
                """);
        String moduleName = Utils.inputString("请选择类型", "2");
        Utils.readCfg();
        switch (moduleName) {
            case "1" -> ModuleGenerator.main(args);
            case "2" -> Table2DomainGenerator.main(args);
            case "3" -> Domain2MapperGenerator.main(args);
            default -> System.out.println("类型不存在");
        }
    }
}
