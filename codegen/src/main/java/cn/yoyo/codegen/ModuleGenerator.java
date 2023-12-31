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
        String moduleDir = Utils.getModuleDir(wsDir, moduleName, moduleType);

        if (FileUtil.exist(moduleDir)) {
            System.out.println("模块已存在");
            return;
        }

        moduleProjectInfo modInfo = getModuleProjectInfo(wsDir, moduleName, moduleType);

        generateModuleStruct(modInfo);
        generateModulePom(modInfo);
    }


    private static void generateModulePom(moduleProjectInfo modInfo) {

        String pomTemplate = FileUtil.readUtf8String(Utils.systemPath("templates/pom.xml"));
        String modulePom = pomTemplate
                .replaceAll("\\$\\{parent\\}", modInfo.parent())
                .replaceAll("\\$\\{module\\}", modInfo.moduleName())
                .replaceAll("\\$\\{rootPackage\\}", modInfo.moduleRootPackage())
                .replaceAll("\\$\\{rootPageDir\\}", modInfo.moduleRootPackage());
        FileUtil.writeUtf8String(modulePom, Utils.systemPath(modInfo.moduleProjectDir() + "/pom.xml"));
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
            FileUtil.writeUtf8String(content, Utils.systemPath(targetPath));
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
            FileUtil.mkdir(Utils.systemPath(dir));
        }
    }

    private static moduleProjectInfo getModuleProjectInfo(String wsDir, String moduleName, String moduleType) {
        String moduleProjectDir = Utils.getModuleDir(wsDir, moduleName, moduleType);
        String moduleRootPackage = (ROOT_PKG + "." + moduleName).replaceAll("-", ".");
        String moduleRootPackageDir = "/src/main/java/" + moduleRootPackage.replaceAll("\\.", "/");
        return new moduleProjectInfo(
                wsDir, moduleName, moduleType,
                moduleType, moduleProjectDir, moduleRootPackage, moduleRootPackageDir);
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
