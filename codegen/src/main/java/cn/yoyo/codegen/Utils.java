package cn.yoyo.codegen;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);
    static Cfg cfg;
    static Properties prop = new Properties();

    public static String inputString(String prompt, String defaultValue) {
        System.out.print(prompt + "(" + defaultValue + "):  ");
        String input = scanner.next();
        if (input == null || input.trim().length() == 0) {
            return defaultValue;
        }
        return input;
    }

    public static String getModuleDir(String wsDir, String moduleName, String parentDir) {
        if (StrUtil.isEmpty(parentDir)) {
            return wsDir + "/" + moduleName;
        } else {
            return wsDir + "/" + parentDir + "/" + moduleName;
        }
    }

    public static String inputModuleName() {
        String moduleName = Utils.inputString("请输入模块名【micro/facade/module-xxx】", "module-demo");
        if (StringUtils.startsWithIgnoreCase(moduleName, "micro-")
                || StringUtils.startsWithIgnoreCase(moduleName, "module-")
                || StringUtils.startsWithIgnoreCase(moduleName, "facade-")
        ) {
            return moduleName;
        }
        System.out.println("模块名字必须以【micro-】/【module-】/【adapter-】开头");
        System.exit(1);
        return "";
    }

    public static String readProp(String name, String prompt, String defaultValue) {
        String v = prop.getProperty(name);
        if (v == null) {
            v = inputString(prompt, defaultValue);
            prop.setProperty(name, v);
        }
        return v;
    }

    public static Cfg readCfg() {
        if (cfg != null) {
            return cfg;
        }

        InputStream input = null;
        try {

            input = new FileInputStream("codegen/src/main/resources/cfg.properties");

            // 加载属性文件
            prop.load(input);

            // 读取属性值
            cfg = new Cfg(
                    prop.getProperty("root"),
                    prop.getProperty("host"),
                    prop.getProperty("schema"),
                    prop.getProperty("username"),
                    prop.getProperty("password"),
                    prop.getProperty("crud_api_module")
            );
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cfg;
    }

    public record Cfg(
            String root,
            String host,
            String schema,
            String username,
            String password,
            String curdApiModule
    ) {

    }
}
