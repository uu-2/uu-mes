package cn.yoyo.codegen.generator;

import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;
import cn.yoyo.components.biz.CrudBaseBiz;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DDDBizGenerator implements IGenerator {
    private String templatePath = "/templates/generator/biz.tpl";

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
        String dddBizPackage = packageConfig.getBasePackage()+".domain";

        String entityPackagePath = dddBizPackage.replace(".", "/");
        File entityJavaFile = new File(packageConfig.getSourceDir(), entityPackagePath + "/" +
                table.getEntityJavaFileName() + "Biz.java");


        if (entityJavaFile.exists() && !entityConfig.isOverwriteEnable()) {
            return;
        }

        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("entityConfig", entityConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.put("imports",new String[]{
                Component.class.getName(),
                CrudBaseBiz.class.getName(),
                dddBizPackage+".entity."+table.getEntityJavaFileName(),
                dddBizPackage+".points."+table.getEntityJavaFileName()+"Repository",
        });
        params.put("dddBizPackage", dddBizPackage);

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, entityJavaFile);
    }
}
