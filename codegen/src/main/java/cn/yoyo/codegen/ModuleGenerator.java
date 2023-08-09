package cn.yoyo.codegen;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;


public class ModuleGenerator {

    static  String ROOT_PKG;

    public static void main(String[] args) {
        String wsDir = System.getProperty("user.dir");
        System.out.println("工作目录为：" + wsDir);

        ROOT_PKG = Utils.readProp("root", "请输入包名", "cn.yoyo");

        String moduleName = Utils.inputModuleName();
        String moduleType = StrUtil.splitToArray(moduleName, "-")[0];
        String parentDir = "module".equals(moduleType) ? "" : "micro";
        String moduleDir = Utils.getModuleDir(wsDir, moduleName, parentDir);

        if (FileUtil.exist(moduleDir)) {
            System.out.println("模块已存在");
            return;
        }

        moduleProjectInfo modInfo = getModuleProjectInfo(wsDir, moduleName, moduleType);

        generateModuleStruct(modInfo);
        generateModulePom(modInfo);
    }


    private static void generateModulePom(moduleProjectInfo modInfo) {

        String pomTemplate = FileUtil.readUtf8String("templates/pom.xml");
        String modulePom = pomTemplate
                .replaceAll("\\$\\{parent\\}", modInfo.parent())
                .replaceAll("\\$\\{module\\}", modInfo.moduleName())
                .replaceAll("\\$\\{rootPackage\\}", modInfo.moduleRootPackage())
                .replaceAll("\\$\\{rootPageDir\\}", modInfo.moduleRootPackage());
        FileUtil.writeUtf8String(modulePom, modInfo.moduleProjectDir() + "/pom.xml");
    }

    static void generateModuleStruct(moduleProjectInfo modInfo) {
        if ("module".equals(modInfo.moduleType())) {
            genmoduleDir(modInfo);
        } else {
            genmicroDir(modInfo);
        }

    }

    private static void genmoduleDir(moduleProjectInfo modInfo) {

        String[] dirs = {
                modInfo.moduleProjectDir() + "/src/main/resources",
        };
        for (String dir : dirs) {
            FileUtil.mkdir(dir);
        }
        FileUtil.loopFiles("templates/module-struct", pathname -> {
            if (pathname.getName().contains("package-info.java")) {
                return true;
            }
            return false;
        }).stream().forEach(file -> {
            String relativePath = file.getAbsolutePath().split("templates/module-struct")[1];
            String targetPath = modInfo.moduleProjectDir() + modInfo.moduleRootPackageDir() + relativePath;
            FileUtil.touch(targetPath);
            String content = FileUtil.readUtf8String(file)
                    .replaceAll("\\$\\{moduleRootPackage\\}", modInfo.moduleRootPackage());
            FileUtil.writeUtf8String(content, targetPath);
        });
    }

    private static void genmicroDir(moduleProjectInfo modInfo) {

        String[] dirs = {
                modInfo.moduleProjectDir() + modInfo.moduleRootPackageDir() + "/adapter/listener",
                modInfo.moduleProjectDir() + modInfo.moduleRootPackageDir() + "/adapter/rest",
                modInfo.moduleProjectDir() + modInfo.moduleRootPackageDir() + "/adapter/job",
                modInfo.moduleProjectDir() + modInfo.moduleRootPackageDir() + "/adapter/mq",
                modInfo.moduleProjectDir() + "/src/main/resources",
        };

        for (String dir : dirs) {
            FileUtil.mkdir(dir);
        }
    }

    private static moduleProjectInfo getModuleProjectInfo(String wsDir, String moduleName, String moduleType) {
        String parent = "module".equals(moduleType) ? "yoyo" : "micro";
        String parentPath = "module".equals(moduleType) ? "" : "micro";
        String moduleProjectDir = Utils.getModuleDir(wsDir, moduleName, parentPath);
        String moduleRootPackage = (ROOT_PKG + "." + moduleName).replaceAll("-", ".");
        String moduleRootPackageDir = "/src/main/java/" + moduleRootPackage.replaceAll("\\.", "/");
        return new moduleProjectInfo(
                wsDir, moduleName, moduleType,
                parent, moduleProjectDir, moduleRootPackage, moduleRootPackageDir);
    }

    private record moduleProjectInfo(
            String wsDir,
            String moduleName,
            String moduleType,
            String parent,
            String moduleProjectDir,
            String moduleRootPackage,
            String moduleRootPackageDir) {
    }


}
