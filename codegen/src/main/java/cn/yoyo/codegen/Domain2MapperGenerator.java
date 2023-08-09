package cn.yoyo.codegen;

import cn.hutool.core.io.FileUtil;

public class Domain2MapperGenerator {
    public static void main(String[] args) {
        String wsDir = System.getProperty("user.dir");
        System.out.println("工作目录为：" + wsDir);

        String moduleName = Utils.inputString("请输入模块名(如:demo):", "demo");
        String moduleDir = Utils.getModuleDir(wsDir, moduleName, "");
        if (!FileUtil.exist(moduleDir)) {
            System.out.println("模块不存在");
            return;
        }

        String domainName = Utils.inputString("请输入领域名(如:order):", "order");
        String domainDir = moduleDir + "/src/main/java/com/three3q/yoyo/" + moduleName + "/domain/" + domainName;
        if (FileUtil.exist(domainDir)) {
            System.out.println("领域已存在");
            return;
        }
//        generateDomainStruct(moduleName, domainName, wsDir);
    }
}
