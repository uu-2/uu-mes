package cn.yoyo.codegen.generator;

import cn.yoyo.components.biz.CrudBaseApi;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DDDApiGenerator implements IGenerator {
    private String templatePath = "/templates/generator/api.tpl";
    private String rootPkg;
    private String apiModulePath;
    private String apiUrl;

    public DDDApiGenerator(String rootPkg, String apiModulePath, String apiUrl) {
        this.rootPkg = rootPkg;
        this.apiModulePath = apiModulePath;
        this.apiUrl = apiUrl;
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    @Override
    public void setTemplatePath(String s) {
        this.templatePath = s;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isEntityGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        EntityConfig entityConfig = globalConfig.getEntityConfig();
        String dddBizPackage = packageConfig.getBasePackage() + ".domain";

        String dddAdapterPackage = rootPkg+".micro.admin" + ".adapter";
        String dddRestPackage = dddAdapterPackage + ".rest";

        String entityPackagePath = dddRestPackage.replace(".", "/");
        File entityJavaFile = new File(apiModulePath + "/src/main/java", entityPackagePath + "/" +
                table.getEntityJavaFileName() + "Api.java");

        if (entityJavaFile.exists() && !entityConfig.isOverwriteEnable()) {
            return;
        }

        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("entityConfig", entityConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.put("apiUrl", apiUrl);
        params.put("imports", new String[]{
                RestController.class.getName(),
                RequestMapping.class.getName(),
                Tag.class.getName(),
                CrudBaseApi.class.getName(),
                dddBizPackage + ".entity." + table.getEntityJavaFileName(),
                dddBizPackage + "." + table.getEntityJavaFileName() + "Biz",
        });
        params.put("dddRestPackage", dddRestPackage);

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, entityJavaFile);
    }
}
