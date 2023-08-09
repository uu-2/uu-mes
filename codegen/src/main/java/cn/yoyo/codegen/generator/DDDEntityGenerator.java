package cn.yoyo.codegen.generator;

import cn.hutool.core.collection.ListUtil;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;
import cn.yoyo.components.biz.convert.ConvertTemplate;
import org.mapstruct.Mapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DDDEntityGenerator implements IGenerator {
    private String templatePath = "/templates/generator/entity.tpl";

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
        String dddEntityPackage = packageConfig.getBasePackage()+".domain.entity";

        String bizPackagePath = dddEntityPackage.replace(".", "/");
        File entityJavaFile = new File(packageConfig.getSourceDir(), bizPackagePath + "/" +
                table.getEntityJavaFileName() + ".java");


        if (entityJavaFile.exists() && !entityConfig.isOverwriteEnable()) {
            return;
        }

        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("entityConfig", entityConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.put("dddEntityPackage", dddEntityPackage);

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, entityJavaFile);
    }
}
